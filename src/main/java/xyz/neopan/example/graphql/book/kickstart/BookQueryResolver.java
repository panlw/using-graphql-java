package xyz.neopan.example.graphql.book.kickstart;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    BookDataFetchers dataFetchers;

    CompletableFuture<Optional<Book>> getBook(String id) {
        return CompletableFuture.completedFuture(dataFetchers.getBook(id));
    }

    CompletableFuture<List<Book>> allBooks() {
        return CompletableFuture.completedFuture(dataFetchers.getBooks());
    }

}
