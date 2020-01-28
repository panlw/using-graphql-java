package xyz.neopan.example.graphql.book.kickstart;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    BookDataFetchers dataFetchers;

    Optional<Book> getBook(String id) {
        return dataFetchers.getBook(id);
    }

    List<Book> allBooks() {
        return dataFetchers.getBooks();
    }

}
