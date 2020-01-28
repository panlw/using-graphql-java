package xyz.neopan.example.graphql.book.kickstart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import xyz.neopan.example.graphql.book.BookDataFetchers;

/**
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
