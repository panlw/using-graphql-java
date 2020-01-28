package xyz.neopan.example.graphql.blog.kickstart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
@Slf4j
public class BlogKickstartConfig {

    @Bean
    PostFieldResolver postFieldResolver() {
        log.info("[BLOG] postFieldResolver");
        return new PostFieldResolver();
    }

    @Bean
    BlogQueryResolver blogQueryResolver() {
        log.info("[BLOG] blogQueryResolver");
        return new BlogQueryResolver();
    }

    @Bean
    BlogMutationResolver blogMutationResolver() {
        log.info("[BLOG] blogMutationResolver");
        return new BlogMutationResolver();
    }

}
