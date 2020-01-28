package xyz.neopan.example.graphql.book.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Book {
    private String id;
    private String name;
    private int pageCount;
    private String authorId;
}
