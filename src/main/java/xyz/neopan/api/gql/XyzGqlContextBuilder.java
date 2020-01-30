package xyz.neopan.api.gql;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import xyz.neopan.api.iam.XyzSubject;
import xyz.neopan.api.iam.XyzSubjectStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
@Slf4j
@RequiredArgsConstructor
public class XyzGqlContextBuilder implements GraphQLServletContextBuilder {

    private static final String AUTH_TOKEN_HEADER_NAME = "xyz-token";

    private final XyzSubjectStore subjectStore;
    private final XyzDataLoaderRegistryBuilder registryBuilder;

    @NotNull
    private Optional<XyzSubject> getSubject(String token) {
        if (StringUtils.isEmpty(token))
            return Optional.empty();
        log.info("[APP] load subject of token: {}", token);
        return subjectStore.load(token);
    }

    @Override
    public GraphQLContext build(
        HttpServletRequest request, HttpServletResponse response) {
        val subject = getSubject(request).orElse(null);
        return XyzGqlHttpContext.builder()
            .subject(subject)
            .dataLoaderRegistry(registryBuilder.build())
            .httpServletRequest(request)
            .httpServletResponse(response)
            .build();
    }

    private Optional<XyzSubject> getSubject(HttpServletRequest request) {
        val subject = getSubject(
            request.getHeader(AUTH_TOKEN_HEADER_NAME));
        if (subject.isPresent())
            return subject;

        return getSubject(
            request.getParameter(AUTH_TOKEN_HEADER_NAME));
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        val subject = getSubject(handshakeRequest).orElse(null);
        return XyzGqlSockContext.builder()
            .subject(subject)
            .dataLoaderRegistry(registryBuilder.build())
            .session(session)
            .handshakeRequest(handshakeRequest)
            .build();
    }

    private Optional<XyzSubject> getSubject(HandshakeRequest request) {
        val hvals = request.getHeaders().get(AUTH_TOKEN_HEADER_NAME);
        if (hvals == null || hvals.isEmpty())
            return Optional.empty();
        return getSubject(hvals.get(0));
    }

    @Override
    public GraphQLContext build() {
        return XyzGqlHttpContext.builder()
            .dataLoaderRegistry(registryBuilder.build())
            .build();
    }

}
