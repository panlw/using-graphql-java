package xyz.neopan.example.graphql.book.kickstart;

import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Author;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * "DataLoaders by default act as caches."
 * -- https://www.graphql-java.com/documentation/v13/batching/
 *
 * @author neo.pan
 * @since 2020/1/29
 */
@Slf4j
abstract class BookDataLoaders {

    @NotNull
    static DataLoader<String, Author> bookAuthorLoader(BookDataFetchers fetchers) {
        final DataLoaderOptions options = DataLoaderOptions.newOptions();
//            .setCachingEnabled(false);
        return new DataLoader<>(authorIds ->
            fetchAuthorsAsync(fetchers, authorIds), options);
    }

    @NotNull
    private static CompletableFuture<List<Author>> fetchAuthorsAsync(
        BookDataFetchers fetchers, List<String> authorIds) {
        return CompletableFuture.supplyAsync(() -> fetchAuthors(fetchers, authorIds));
    }

    @NotNull
    private static List<Author> fetchAuthors(
        BookDataFetchers fetchers, List<String> authorIds) {
        return authorIds.stream()
            .peek(x -> log.info("[BOOK] load data via key: {}", x))
            .map(x -> fetchers.getAuthor(x).orElse(null))
            .collect(Collectors.toList());
    }

}
