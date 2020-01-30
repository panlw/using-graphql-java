package xyz.neopan.example.graphql.auth.kickstart;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import xyz.neopan.api.gql.XyzGqlContext;
import xyz.neopan.api.iam.XyzSubject;

import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/30
 */
class AuthQueryResolver implements GraphQLQueryResolver {

    CompletableFuture<XyzSubject> getSubject(DataFetchingEnvironment environment) {
        return CompletableFuture.supplyAsync(() -> {
            final XyzGqlContext context = environment.getContext();
            return context.getXyzSubject().orElse(XyzSubject.GUEST);
        });
    }

}
