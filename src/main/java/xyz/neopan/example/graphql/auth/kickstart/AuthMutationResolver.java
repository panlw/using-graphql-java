package xyz.neopan.example.graphql.auth.kickstart;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.api.gql.XyzGqlContext;
import xyz.neopan.api.iam.XyzSubject;
import xyz.neopan.api.iam.XyzSubjectStore;
import xyz.neopan.example.graphql.auth.input.AuthnInput;
import xyz.neopan.example.graphql.auth.model.AuthSubject;

import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class AuthMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private XyzSubjectStore store;

    CompletableFuture<XyzSubject> signIn(AuthnInput input) {
        return CompletableFuture.supplyAsync(() -> {
            final XyzSubject subject = AuthSubject.newSubject(
                input.toUser(), input.getGroups());
            store.save(subject);
            return subject;
        });
    }

    CompletableFuture<Void> signOut(DataFetchingEnvironment environment) {
        return CompletableFuture.runAsync(() -> {
            final XyzGqlContext context = environment.getContext();
            context.getXyzSubject().ifPresent(x -> store.clear(x.getToken()));
        });
    }

}
