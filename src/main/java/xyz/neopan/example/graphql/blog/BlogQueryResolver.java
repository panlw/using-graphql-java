package xyz.neopan.example.graphql.blog;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import xyz.neopan.example.graphql.blog.model.Post;
import org.springframework.stereotype.Component;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
@Component
public class BlogQueryResolver implements GraphQLQueryResolver {

    Post getPost(Long id) {
        return Post.builder()
            .id(id)
            .text(id + "-text")
            .build();
    }

}
