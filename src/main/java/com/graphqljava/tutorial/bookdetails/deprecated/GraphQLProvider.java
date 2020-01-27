package com.graphqljava.tutorial.bookdetails.deprecated;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.graphqljava.tutorial.bookdetails.book.BookDataFetchers;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

//@Component
@Deprecated
public class GraphQLProvider {

    private GraphQLSchema schema;

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("graphql/schema/hello.graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        this.schema = buildSchema(sdl);
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    @Autowired
    BookDataFetchers dataFetchers;

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
            .type(newTypeWiring("Query")
                .dataFetcher("bookById", dataFetchers.getBookByIdDataFetcher()))
            .type(newTypeWiring("Book")
                .dataFetcher("author", dataFetchers.getAuthorDataFetcher()))
            .build();
    }

    @Bean
    public GraphQLSchema getSchema() {
        return schema;
    }

}
