package xyz.neopan.example.graphql.book.kickstart;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.servlet.context.DefaultGraphQLServletContext;
import graphql.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.security.Principal;
import java.util.function.Supplier;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
@RequiredArgsConstructor
public class BookServletContextBuilder implements GraphQLServletContextBuilder {

    private final Supplier<DataLoaderRegistry> registryBuilder;

    private @Nullable
    Subject getSubject(HttpServletRequest request) {
        return null;
    }

    private @Nullable
    Subject getSubject(@Nullable Principal principal) {
        return null;
    }

    @Override
    public GraphQLContext build(
        HttpServletRequest request, HttpServletResponse response) {
        return DefaultGraphQLServletContext
            .createServletContext(registryBuilder.get(), getSubject(request))
            .with(request).with(response).build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext
            .createWebSocketContext(registryBuilder.get(), getSubject(session.getUserPrincipal()))
            .with(session).with(handshakeRequest).build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(registryBuilder.get(), null);
    }

}
