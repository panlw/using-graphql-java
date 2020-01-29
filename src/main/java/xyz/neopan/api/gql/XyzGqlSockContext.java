package xyz.neopan.api.gql;

import graphql.servlet.context.GraphQLWebSocketContext;
import lombok.Builder;
import lombok.Getter;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.NotNull;
import xyz.neopan.api.iam.XyzSubject;

import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Getter
class XyzGqlSockContext extends XyzGqlBaseContext
    implements GraphQLWebSocketContext {

    private final Session session;
    private final HandshakeRequest handshakeRequest;

    @Builder
    XyzGqlSockContext(
        @NotNull Session session, @NotNull HandshakeRequest handshakeRequest,
        @NotNull XyzSubject subject, DataLoaderRegistry dataLoaderRegistry) {
        super(subject, dataLoaderRegistry);
        this.session = session;
        this.handshakeRequest = handshakeRequest;
    }

}
