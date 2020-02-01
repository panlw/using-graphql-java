# [GraphQL](https://graphql.github.io/learn/)

## [GraphQL Spring Boot](https://github.com/graphql-java-kickstart/graphql-spring-boot)

> groupId: `com.graphql-java-kickstart`

- starter: `graphql-spring-boot-starter` -> /graphql
- unit test: `graphql-spring-boot-starter-test`
- schema viewer: `voyager-spring-boot-starter` -> /voyager
- clients:
    - `playground-spring-boot-starter` -> /playground -> recommended (BETTER than Playground Application)
    - `graphiql-spring-boot-starter` -> /graphiql
    - `altair-spring-boot-starter` -> /altair


## [Using Resolver (by graphql-tools)](https://github.com/graphql-java-kickstart/graphql-spring-boot/blob/master/example-graphql-tools/)

> BETTER than using `DataFetcher`


## [Using Data Loader](https://github.com/graphql-java/java-dataloader)

> Resolve "N+1" fetch problem

- https://github.com/graphql-java-kickstart/graphql-spring-boot/tree/master/example-request-scoped-dataloader
- graphql.servlet.contextSetting: PER_REQUEST_WITH_INSTRUMENTATION (graphql.kickstart.execution.context.ContextSetting)
- https://www.graphql-java.com/documentation/v13/data-fetching/
- https://www.graphql-java.com/documentation/v13/batching/

> Resolve authentication & authorization

- https://github.com/graphql-java-kickstart/samples/blob/master/subscription-with-authentication
- https://www.howtographql.com/graphql-java/5-authentication/

> Resolve custom scalar

- `graphql.schema.GraphQLEnumType`

> Resolve custom directives (TODO)

> https://www.graphql-java-kickstart.com/tools/directives/

## Using WebSocket Subscription (TODO)

- https://www.graphql-java.com/documentation/v13/subscriptions/
- https://github.com/graphql-java-kickstart/samples/blob/master/subscription-with-authentication/
- https://github.com/graphql-java-kickstart/graphql-spring-boot/blob/master/example-graphql-subscription/

## Unrecommended

- [Using DataFetcher](https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/)
- [Graphql Playground Application](https://github.com/prisma/graphql-playground)
