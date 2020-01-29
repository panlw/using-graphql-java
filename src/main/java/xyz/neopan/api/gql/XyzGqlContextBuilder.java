package xyz.neopan.api.gql;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import xyz.neopan.api.iam.XyzSubject;
import xyz.neopan.api.iam.XyzSubjectStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.function.Supplier;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
@RequiredArgsConstructor
public class XyzGqlContextBuilder implements GraphQLServletContextBuilder {

    private static final String AUTH_TOKEN_HEADER_NAME = "xyz-token";

    private final XyzSubjectStore subjectStore;
    private final Supplier<DataLoaderRegistry> registryBuilder;

    @NotNull
    private XyzSubject getSubject(String token) {
        if (!StringUtils.hasLength(token))
            return XyzSubject.GUEST;
        return subjectStore.load(token)
            .orElse(XyzSubject.GUEST);
    }

    @Override
    public GraphQLContext build(
        HttpServletRequest request, HttpServletResponse response) {
        return XyzGqlHttpContext.builder()
            .subject(getSubject(request))
            .dataLoaderRegistry(registryBuilder.get())
            .httpServletRequest(request)
            .httpServletResponse(response)
            .build();
    }

    private @NotNull
    XyzSubject getSubject(HttpServletRequest request) {
        return getSubject(request.getHeader(AUTH_TOKEN_HEADER_NAME));
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return XyzGqlSockContext.builder()
            .subject(getSubject(handshakeRequest))
            .dataLoaderRegistry(registryBuilder.get())
            .session(session)
            .handshakeRequest(handshakeRequest)
            .build();
    }

    private @NotNull
    XyzSubject getSubject(HandshakeRequest request) {
        val hvals = request.getHeaders().get(AUTH_TOKEN_HEADER_NAME);
        if (hvals == null || hvals.isEmpty())
            return XyzSubject.GUEST;
        return getSubject(hvals.get(0));
    }

    @Override
    public GraphQLContext build() {
        return XyzGqlHttpContext.builder()
            .dataLoaderRegistry(registryBuilder.get())
            .subject(XyzSubject.GUEST)
            .build();
    }

}
