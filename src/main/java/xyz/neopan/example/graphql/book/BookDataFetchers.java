package xyz.neopan.example.graphql.book;

import xyz.neopan.example.graphql.book.model.Author;
import xyz.neopan.example.graphql.book.model.Book;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/25
 */
@Component
public class BookDataFetchers {

    private static List<Book> books = Arrays.asList(
        buildBook("book-1", "Harry Potter and the Philosopher's Stone",
            223, "author-1"),
        buildBook("book-2", "Moby Dick",
            635, "author-2"),
        buildBook("book-3", "Interview with the vampire",
            371, "author-x")
    );

    public static Book buildBook(
        String id, String name, int pageCount, String authorId) {
        return Book.builder().id(id).name(name)
            .pageCount(pageCount).authorId(authorId).build();
    }

    private static List<Author> authors = Arrays.asList(
        buildAuthor("author-1", "Joanne", "Rowling"),
        buildAuthor("author-2", "Herman", "Melville"),
        buildAuthor("author-3", "Anne", "Rice")
    );

    public static Author buildAuthor(
        String id, String firstName, String lastName) {
        return Author.builder().id(id)
            .firstName(firstName).lastName(lastName)
            .build();
    }

    public static Book buildBook(String id, String name) {
        return buildBook(id, name, 0, authors.get(0).getId());
    }

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return getBook(bookId).orElse(null);
        };
    }

    public Optional<Book> getBook(String bookId) {
        return books.stream()
            .filter(author -> author.getId().equals(bookId))
            .findFirst();
    }

    public DataFetcher<Author> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            final Book book = dataFetchingEnvironment.getSource();
            return getAuthor(book.getAuthorId()).orElse(null);
        };
    }

    public Optional<Author> getAuthor(String authorId) {
        return authors.stream()
            .filter(author -> author.getId().equals(authorId))
            .findFirst();
    }

    public List<Book> getBooks() {
        return books;
    }

}
