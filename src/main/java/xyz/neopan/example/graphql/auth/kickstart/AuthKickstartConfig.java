package xyz.neopan.example.graphql.auth.kickstart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import xyz.neopan.example.graphql.auth.store.AuthUserStore;

/**
 * @author neo.pan
 * @since 2020/1/30
 */
@Slf4j
public class AuthKickstartConfig {

    @Bean
    AuthUserStore authUserStore() {
        log.info("[AUTH] authUserStore");
        return new AuthUserStore();
    }

    @Bean
    AuthMutationResolver authMutationResolver() {
        log.info("[AUTH] authMutationResolver");
        return new AuthMutationResolver();
    }

    @Bean
    AuthQueryResolver authQueryResolver() {
        log.info("[AUTH] authQueryResolver");
        return new AuthQueryResolver();
    }

}
