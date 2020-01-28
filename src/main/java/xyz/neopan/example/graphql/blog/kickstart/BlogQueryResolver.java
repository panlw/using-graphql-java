package xyz.neopan.example.graphql.blog.kickstart;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import xyz.neopan.example.graphql.blog.model.Post;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class BlogQueryResolver implements GraphQLQueryResolver {

    Post getPost(Long id) {
        return Post.builder()
            .id(id)
            .text(id + "-text")
            .build();
    }

}
