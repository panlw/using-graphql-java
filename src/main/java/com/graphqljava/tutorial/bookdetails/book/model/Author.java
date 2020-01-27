package com.graphqljava.tutorial.bookdetails.book.model;

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
public class Author {
    private String id;
    private String firstName;
    private String lastName;
}
