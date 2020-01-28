package xyz.neopan.example.graphql.book.kickstart;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Root Resolvers: https://www.graphql-java-kickstart.com/tools/schema-definition/
 *
 * @author neo.pan
 * @since 2020/1/27
 */
@Slf4j
class BookQueryResolver implements GraphQLQueryResolver {

    private static String getType(Object object) {
        return object == null ? "null" : object.getClass().toString();
    }

    private static void log(DataFetchingEnvironment environment) {
        log.info("[BOOK] environment.executionId: {}", environment.getExecutionId());
        log.info("[BOOK] environment.context: {}", getType(environment.getContext()));
        log.info("[BOOK] environment.root: {}", getType(environment.getRoot()));
        log.info("[BOOK] environment.source: {}", getType(environment.getSource()));
        log.info("[BOOK] environment.document: {}", getType(environment.getDocument()));
        log.info("[BOOK] environment.parentType: {}", environment.getParentType().getName());
        log.info("[BOOK] environment.fieldType: {}", environment.getFieldType().getName());
        log.info("[BOOK] environment.variables: {}", environment.getVariables());
        log.info("[BOOK] environment.arguments: {}", environment.getArguments());
    }

    @Autowired
    private BookDataFetchers dataFetchers;

    CompletableFuture<Optional<Book>> getBook(String id, DataFetchingEnvironment environment) {
        //log(environment);
        return CompletableFuture.supplyAsync(() -> dataFetchers.getBook(id))
            .thenApply(x -> {
                log.info("[BOOK] book ({}) is resolved.", id);
                return x;
            });
    }

    CompletableFuture<List<Book>> allBooks(DataFetchingEnvironment environment) {
        return CompletableFuture.supplyAsync(() -> dataFetchers.getBooks())
            .thenApply(x -> {
                log.info("[BOOK] books is resolved.");
                return x;
            });
    }

}
