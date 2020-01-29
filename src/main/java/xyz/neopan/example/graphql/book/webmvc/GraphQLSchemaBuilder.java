package xyz.neopan.example.graphql.book.webmvc;

import graphql.schema.GraphQLSchema;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
@FunctionalInterface
public interface GraphQLSchemaBuilder {

    GraphQLSchema build() throws Exception;

}
