package xyz.neopan.example.graphql.blog.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Data
@Builder
@EqualsAndHashCode
public class Post {
    private long id;
    private String text;

    public static long nextId() {
        return ThreadLocalRandom.current().nextLong();
    }
}
