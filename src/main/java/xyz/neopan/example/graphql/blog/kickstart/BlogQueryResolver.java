package xyz.neopan.example.graphql.blog.kickstart;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import xyz.neopan.example.graphql.blog.model.Post;

import java.util.concurrent.CompletableFuture;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BlogQueryResolver implements GraphQLQueryResolver {

    CompletableFuture<Post> getPost(Long id) {
        return CompletableFuture.supplyAsync(() ->
            Post.builder()
                .id(id)
                .text(id + "-text")
                .build()
        );
    }

}
