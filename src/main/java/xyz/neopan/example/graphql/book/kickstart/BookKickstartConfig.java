package xyz.neopan.example.graphql.book.kickstart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import xyz.neopan.api.gql.XyzDataLoaderRegistryBuilder;
import xyz.neopan.example.graphql.book.store.BookDataFetchers;

/**
 * 1. https://github.com/graphql-java-kickstart/graphql-spring-boot
 * /tree/master/example-request-scoped-dataloader
 * <p>
 * 2. {@link graphql.kickstart.execution.context.ContextSetting}
 * graphql.servlet.contextSetting: PER_REQUEST_WITH_INSTRUMENTATION
 *
 * @author neo.pan
 * @since 2020/1/28
 */
@Slf4j
public class BookKickstartConfig {

    @Bean
    BookDataFetchers bookDataFetchers(XyzDataLoaderRegistryBuilder builder) {
        log.info("[BOOK] bookDataFetchers");
        BookDataFetchers fetchers = new BookDataFetchers();
        builder.register("bookAuthor",
            BookDataLoaders.bookAuthorLoader(fetchers));
        builder.register("bookAuthorMap",
            BookDataLoaders.bookAuthorMapLoader(fetchers));
        return fetchers;
    }

    @Bean
    BookFieldResolver bookFieldResolver() {
        log.info("[BOOK] bookFieldResolver");
        return new BookFieldResolver();
    }

    @Bean
    BookQueryResolver bookQueryResolver() {
        log.info("[BOOK] bookQueryResolver");
        return new BookQueryResolver();
    }

    @Bean
    BookMutationResolver bookMutationResolver() {
        log.info("[BOOK] bookMutationResolver");
        return new BookMutationResolver();
    }

}
