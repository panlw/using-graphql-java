package xyz.neopan.example.graphql.book.kickstart;

import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import xyz.neopan.example.graphql.book.BookDataFetchers;

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
    BookDataFetchers bookDataFetchers() {
        log.info("[BOOK] bookDataFetchers");
        return new BookDataFetchers();
    }

    @NotNull
    private DataLoaderRegistry buildDataLoaderRegistry(BookDataFetchers fetchers) {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register("bookAuthor", BookDataLoaders.bookAuthorLoader(fetchers));
        registry.register("bookAuthorMap", BookDataLoaders.bookAuthorMapLoader(fetchers));
        return registry;
    }

    @Bean
    BookServletContextBuilder bookServletContextBuilder(BookDataFetchers fetchers) {
        log.info("[BOOK] bookServletContextBuilder");
        return new BookServletContextBuilder(() -> buildDataLoaderRegistry(fetchers));
    }

    @Bean
    public Instrumentation bookInstrumentation() {
        log.info("[BOOK] bookInstrumentation");
        val options = DataLoaderDispatcherInstrumentationOptions
            .newOptions().includeStatistics(true);
        return new DataLoaderDispatcherInstrumentation(options);
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
