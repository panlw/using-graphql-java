package xyz.neopan.example.graphql.book.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Author;
import xyz.neopan.example.graphql.book.model.Book;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Component
public class BookResolver implements GraphQLResolver<Book> {

    @Autowired
    BookDataFetchers dataFetchers;

    @Nullable
    Optional<Author> getAuthor(Book book) {
        return dataFetchers.getAuthor(book.getAuthorId());
    }

}
