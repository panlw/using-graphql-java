# Thread with IAM Context -- Mechanism that clear IAM context from thread context

如果需要一个全局上下文的访问工具，可以参考下面的`RequestContextHolder`通过`Filter`实现，
不过如果使用[GraphQL Java Servlet](https://www.graphql-java-kickstart.com/servlet/)
即`GraphQLWebAutoConfiguration`通过建立 Web 服务的话可以考虑通过`graphql.servlet.core.GraphQLServletListener`
来替代`Filter`来实现，如下所示：

```java
@Component
class XyzIamContextServletListener implements GraphQLServletListener {

    RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        final XyzIamContext context = XyzIamContextHolder.getContext();
        loadContext(request).ifPresent(context::setSubject)
        return new RequestCallback() {
            void onFinally(HttpServletRequest request, HttpServletResponse response) {
                XyzIamContextHolder.clearContext();
            }
        }
    }

    private Optional<XyzIamSubject> loadContext(HttpServletRequest request) {
        // ...
    }
}
```

另外，如果`Request`线程的子线程访问`XyzIamContextHolder`的话，可以考虑使用`InheritableThreadLocal`
或如下面的 Shiro 的`DelegatingSubject`给`XyzIamSubject`增加`execute`方法来实现子线程的 IAM Context。

> 参考实现:
> - `org.springframework.web.filter.RequestContextFilter`
> - `org.springframework.web.context.request.RequestContextHolder`

## [Spring Security](https://docs.spring.io/spring-security/site/docs/5.2.1.RELEASE/reference/html5/#getting-maven-boot)

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```

> FilterChainProxy > GenericFilterBean : WebSecurity#performBuild > AbstractConfiguredSecurityBuilder#doBuild > SecurityBuilder#build

```java
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        boolean clearContext = request.getAttribute(FILTER_APPLIED) == null;
        if (clearContext) {
            try {
                request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
                doFilterInternal(request, response, chain);
            }
            finally {
                SecurityContextHolder.clearContext();
                request.removeAttribute(FILTER_APPLIED);
            }
        }
        else {
            doFilterInternal(request, response, chain);
        }
    }
```

## [Shiro](https://shiro.apache.org/spring-boot.html)

```xml
    <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-spring-boot-starter</artifactId>
      <version>1.5.0</version>
    </dependency>
```

\1. AbstractShiroFilter#doFilterInternal

```java
    final ServletRequest request = prepareServletRequest(servletRequest, servletResponse, chain);
    final ServletResponse response = prepareServletResponse(request, servletResponse, chain);
    final Subject subject = createSubject(request, response);
    subject.execute(new Callable() {
        public Object call() throws Exception {
            updateSessionLastAccessTime(request, response);
            executeChain(request, response, chain);
            return null;
        }
    });
```

\2. DelegatingSubject#execute > Subject#execute(Callable<V>)

```java
    public <V> V execute(Callable<V> callable) throws ExecutionException {
        Callable<V> associated = associateWith(callable);
        try {
            return associated.call();
        } catch (Throwable t) {
            throw new ExecutionException(t);
        }
    }

    public <V> Callable<V> associateWith(Callable<V> callable) {
        return new SubjectCallable<V>(this, callable);
    }
```

\3. SubjectCallable<V> > Callable<V>

```java
    public SubjectCallable(Subject subject, Callable<V> delegate) {
        this.threadState = new SubjectThreadState(subject);
        this.callable = delegate;
    }

    public V call() throws Exception {
        try {
            threadState.bind();
            return doCall(this.callable);
        } finally {
            threadState.restore();
        }
    }
```
