package xyz.neopan.example.graphql.book.kickstart;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.example.graphql.book.input.NewBookInput;
import xyz.neopan.example.graphql.book.model.Book;
import xyz.neopan.example.graphql.book.store.BookDataFetchers;

import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BookMutationResolver implements GraphQLMutationResolver {

    @Autowired
    BookDataFetchers dataFetchers;

    CompletableFuture<Book> addNewBook(NewBookInput input) {
        return CompletableFuture.completedFuture(
            BookDataFetchers.buildBook(input.getId(), input.getName()));
    }

    CompletableFuture<Boolean> setBookName(String id, String name) {
        return CompletableFuture.supplyAsync(() -> {
            val book = dataFetchers.getBook(id);
            book.ifPresent(x -> x.setName(name));
            return book.isPresent();
        });
    }

}
