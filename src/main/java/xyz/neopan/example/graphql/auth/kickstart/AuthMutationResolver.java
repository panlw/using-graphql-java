package xyz.neopan.example.graphql.auth.kickstart;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.api.gql.XyzGqlContext;
import xyz.neopan.api.iam.XyzAuthnException;
import xyz.neopan.api.iam.XyzIamGranted;
import xyz.neopan.api.iam.XyzSubject;
import xyz.neopan.api.iam.XyzSubjectStore;
import xyz.neopan.example.graphql.auth.input.AuthSignInInput;
import xyz.neopan.example.graphql.auth.model.AuthSubject;
import xyz.neopan.example.graphql.auth.store.AuthUserStore;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class AuthMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private XyzSubjectStore store;

    @Autowired
    private AuthUserStore userStore;

    CompletableFuture<XyzSubject> signIn(AuthSignInInput input) {
        return CompletableFuture.supplyAsync(() -> {
            val subject = buildSubject(input).orElseThrow(() ->
                XyzAuthnException.UNAUTHORIZED);
            store.save(subject);
            return subject;
        });
    }

    @NotNull
    private Optional<XyzSubject> buildSubject(AuthSignInInput input) {
        return userStore.getUser(input).map(user -> {
            val groups = userStore.getUserGroups(user);
            val granted = XyzIamGranted.newGranted(groups);
            return new AuthSubject(granted, user);
        });
    }

    CompletableFuture<XyzSubject> signOut(DataFetchingEnvironment environment) {
        return CompletableFuture.supplyAsync(() -> {
            final XyzGqlContext context = environment.getContext();
            context.getXyzSubject()
                .flatMap(XyzSubject::getToken)
                .ifPresent(store::clear);
            return XyzSubject.GUEST;
        });
    }

}
