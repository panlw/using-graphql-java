package com.graphqljava.tutorial.bookdetails.book.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.tutorial.bookdetails.book.BookDataFetchers;
import com.graphqljava.tutorial.bookdetails.book.model.Author;
import com.graphqljava.tutorial.bookdetails.book.model.Book;
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
