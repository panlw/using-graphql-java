package com.graphqljava.tutorial.bookdetails.book;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqljava.tutorial.bookdetails.book.input.NewBookInput;
import com.graphqljava.tutorial.bookdetails.book.model.Book;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Component
public class BookMutationResolver implements GraphQLMutationResolver {

    @Autowired
    BookDataFetchers dataFetchers;

    Book addNewBook(NewBookInput input) {
        return BookDataFetchers.buildBook(input.getId(), input.getName());
    }

    boolean setBookName(String id, String name) {
        val book = dataFetchers.getBook(id);
        book.ifPresent(x -> x.setName(name));
        return book.isPresent();
    }

}
