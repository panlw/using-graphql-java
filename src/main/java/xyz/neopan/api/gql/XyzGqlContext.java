package xyz.neopan.api.gql;

import graphql.kickstart.execution.context.GraphQLContext;
import xyz.neopan.api.iam.XyzSubject;

import javax.security.auth.Subject;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzGqlContext extends GraphQLContext {

    /**
     * @deprecated 始终返回空，请使用 {@link #getXyzSubject()}
     */
    @Deprecated
    @Override
    default Optional<Subject> getSubject() {
        return Optional.empty();
    }

    /**
     * @return 认证主体（为空即访客）
     */
    Optional<XyzSubject> getXyzSubject();

}
