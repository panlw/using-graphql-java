package xyz.neopan.example.graphql.blog.kickstart;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import xyz.neopan.example.graphql.blog.model.Post;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BlogMutationResolver implements GraphQLMutationResolver {

    Post createPost(String text) {
        return Post.builder()
            .id(Post.nextId())
            .text(text)
            .build();
    }

}
