package xyz.neopan.example.graphql.book.kickstart;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.example.graphql.book.BookDataFetchers;
import xyz.neopan.example.graphql.book.model.Author;
import xyz.neopan.example.graphql.book.model.Book;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Slf4j
class BookFieldResolver implements GraphQLResolver<Book> {

    @Autowired
    BookDataFetchers dataFetchers;

    @Nullable
    Optional<Author> getAuthor(Book book) {
        log.info("[BOOK] resolve author of book {}: {}",
            book.getId(), book.getAuthorId());
        return dataFetchers.getAuthor(book.getAuthorId());
    }

}
