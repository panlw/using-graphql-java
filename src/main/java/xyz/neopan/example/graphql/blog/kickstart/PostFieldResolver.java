package xyz.neopan.example.graphql.blog.kickstart;

import com.coxautodev.graphql.tools.GraphQLResolver;
import xyz.neopan.example.graphql.blog.model.Comment;
import xyz.neopan.example.graphql.blog.model.Post;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author neo.pan
 * @since 2020/1/27
 */
class PostFieldResolver implements GraphQLResolver<Post> {

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
