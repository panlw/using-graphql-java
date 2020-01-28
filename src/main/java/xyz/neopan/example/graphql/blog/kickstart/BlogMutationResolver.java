package xyz.neopan.example.graphql.blog.kickstart;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import xyz.neopan.example.graphql.blog.model.Post;

import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BlogMutationResolver implements GraphQLMutationResolver {

    CompletableFuture<Post> createPost(String text) {
        return CompletableFuture.supplyAsync(() ->
            Post.builder()
                .id(Post.nextId())
                .text(text)
                .build()
        );
    }

}
