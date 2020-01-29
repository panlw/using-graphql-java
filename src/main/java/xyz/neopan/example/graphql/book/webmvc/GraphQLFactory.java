package xyz.neopan.example.graphql.book.webmvc;

import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
@RequiredArgsConstructor
public class GraphQLFactory implements FactoryBean<GraphQL> {

    private final GraphQLSchemaBuilder schemaBuilder;

    @Override
    public Class<?> getObjectType() {
        return GraphQL.class;
    }

    @Override
    public GraphQL getObject() throws Exception {
        return GraphQL.newGraphQL(schemaBuilder.build()).build();
    }

}
