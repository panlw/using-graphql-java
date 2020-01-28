package xyz.neopan.example.graphql;

import com.coxautodev.graphql.tools.ObjectMapperConfigurer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import graphql.spring.web.servlet.GraphQLEndpointConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import xyz.neopan.example.graphql.blog.kickstart.BlogKickstartConfig;
import xyz.neopan.example.graphql.book.kickstart.BookKickstartConfig;

// https://github.com/graphql-java-kickstart/graphql-java-servlet
@Import({
    BlogKickstartConfig.class,
    BookKickstartConfig.class,
})
@SpringBootApplication(
    exclude = {
        GraphQLEndpointConfiguration.class,
    }
)

//// https://github.com/graphql-java/graphql-java-spring
//// /tree/master/graphql-java-spring-boot-starter-webmvc
//@Import({
//    BookWebMvcConfig.class,
//})
//@SpringBootApplication(
//    exclude = {
//        GraphQLJavaToolsAutoConfiguration.class,
//    }
//)

public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ObjectMapperConfigurer objectMapperConfigurer() {
        return (mapper, context) -> mapper.registerModule(new JavaTimeModule());
    }

}
