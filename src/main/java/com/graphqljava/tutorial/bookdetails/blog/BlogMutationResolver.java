package com.graphqljava.tutorial.bookdetails.blog;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqljava.tutorial.bookdetails.blog.model.Post;
import org.springframework.stereotype.Component;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Component
public class BlogMutationResolver implements GraphQLMutationResolver {

    Post createPost(String text) {
        return Post.builder()
            .id(Post.nextId())
            .text(text)
            .build();
    }

}
