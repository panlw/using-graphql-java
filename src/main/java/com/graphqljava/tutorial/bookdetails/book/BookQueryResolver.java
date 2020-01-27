package com.graphqljava.tutorial.bookdetails.book;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphqljava.tutorial.bookdetails.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    BookDataFetchers dataFetchers;

    Optional<Book> getBook(String id) {
        return dataFetchers.getBook(id);
    }

    List<Book> allBooks() {
        return dataFetchers.getBooks();
    }

}
