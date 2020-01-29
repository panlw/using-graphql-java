package xyz.neopan.example.graphql.book.kickstart;

import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.jetbrains.annotations.NotNull;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Author;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
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
        return DataLoader.newDataLoader(authorIds ->
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

    @NotNull
    static DataLoader<String, Author> bookAuthorMapLoader(BookDataFetchers fetchers) {
        final DataLoaderOptions options = DataLoaderOptions.newOptions();
        return DataLoader.newMappedDataLoader(authorIds ->
            fetchAuthorMapAsync(fetchers, authorIds), options);
    }

    private static CompletionStage<Map<String, Author>> fetchAuthorMapAsync(
        BookDataFetchers fetchers, Set<String> authorIds) {
        return CompletableFuture.supplyAsync(() -> fetchAuthorMap(fetchers, authorIds));
    }

    private static Map<String, Author> fetchAuthorMap(
        BookDataFetchers fetchers, Set<String> authorIds) {
        return authorIds.stream()
            .peek(x -> log.info("[BOOK] load data via key: {}", x))
            .map(x -> fetchers.getAuthor(x).orElse(null))
            .filter(Objects::nonNull).collect(Collectors.toMap(
                Author::getId, Function.identity()
            ));
    }

}
