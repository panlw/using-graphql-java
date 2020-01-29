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

    @Override
    default Optional<Subject> getSubject() {
        return Optional.empty();
    }

    /**
     * @return 认证主体上下文
     */
    Optional<XyzSubject> getXyzSubject();

}
