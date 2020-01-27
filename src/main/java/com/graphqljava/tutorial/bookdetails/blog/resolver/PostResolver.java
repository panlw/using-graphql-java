package com.graphqljava.tutorial.bookdetails.blog.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.tutorial.bookdetails.blog.model.Comment;
import com.graphqljava.tutorial.bookdetails.blog.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Component
public class PostResolver implements GraphQLResolver<Post> {

    List<Comment> getComments(Post post) {
        return LongStream.range(1, 11)
            .mapToObj(this::buildComment)
            .collect(Collectors.toList());
    }

    private Comment buildComment(long id) {
        return Comment.builder()
            .id(id).description("comment-" + id)
            .build();
    }

}
