package xyz.neopan.example.graphql.book.kickstart;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.jetbrains.annotations.NotNull;
import xyz.neopan.example.graphql.book.model.Author;
import xyz.neopan.example.graphql.book.model.Book;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Field Mapping Priority: https://www.graphql-java-kickstart.com/tools/schema-definition/
 *
 * @author neo.pan
 * @since 2020/1/27
 */
@Slf4j
class BookFieldResolver implements GraphQLResolver<Book> {

    @NotNull
    private DataLoader<String, Author> getAuthorDataLoader(DataFetchingEnvironment environment) {
        return Objects.requireNonNull(environment.getDataLoader("bookAuthorMap"),
            "[BOOK] No data loader named `bookAuthorMap` is registered.");
    }

    CompletableFuture<Author> getAuthor(Book book, DataFetchingEnvironment environment) {
        log.info("[BOOK] resolve author of book ({}): {}",
            book.getId(), book.getAuthorId());
        return getAuthorDataLoader(environment).load(book.getAuthorId());
    }

}
