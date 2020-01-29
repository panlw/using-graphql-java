package xyz.neopan.example.graphql.book.webmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import xyz.neopan.example.graphql.book.BookDataFetchers;

/**
 * 依赖: {@code com.graphql-java:graphql-java-spring-boot-starter-webmvc}
 *
 * @author neo.pan
 * @since 2020/1/28
 */
@Slf4j
public class BookWebMvcConfig {

    @Bean
    BookDataFetchers bookDataFetchers() {
        log.info("[BOOK] bookDataFetchers");
        return new BookDataFetchers();
    }

    @Bean
    GraphQLSchemaBuilder bookSchemaBuilder() {
        log.info("[BOOK] bookSchemaBuilder");
        return new BookSchemaBuilder();
    }

    @Bean
    GraphQLFactory graphQLFactory(GraphQLSchemaBuilder schemaBuilder) {
        log.info("[BOOK] graphQLFactory");
        return new GraphQLFactory(schemaBuilder);
    }

}
