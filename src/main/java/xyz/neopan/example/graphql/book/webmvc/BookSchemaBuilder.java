package xyz.neopan.example.graphql.book.webmvc;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.schema.AsyncDataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.neopan.example.graphql.book.store.BookDataFetchers;

import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
public class BookSchemaBuilder implements GraphQLSchemaBuilder {

    public static final String SDL_FILE = "graphql/schema.graphql";

    @Override
    public GraphQLSchema build() throws Exception {
        return buildSchema(loadSchemaSdl());
    }

    private String loadSchemaSdl() throws IOException {
        final URL url = Resources.getResource(SDL_FILE);
        return Resources.toString(url, Charsets.UTF_8);
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    @Autowired
    private BookDataFetchers dataFetchers;

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
            .type(newTypeWiring("Query")
                .dataFetcher("book", AsyncDataFetcher.async(
                    dataFetchers.getBookDataFetcher())))
            .type(newTypeWiring("Book")
                .dataFetcher("author", AsyncDataFetcher.async(
                    dataFetchers.getBookAuthorDataFetcher())))
            .build();
    }

}
