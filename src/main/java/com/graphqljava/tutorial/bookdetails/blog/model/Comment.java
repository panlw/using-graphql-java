package com.graphqljava.tutorial.bookdetails.blog.model;

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
public class Comment {
    private long id;
    private String description;
}
