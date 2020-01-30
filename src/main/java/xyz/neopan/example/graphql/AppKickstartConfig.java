package xyz.neopan.example.graphql;

import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import xyz.neopan.api.gql.XyzDataLoaderRegistryBuilder;
import xyz.neopan.api.gql.XyzGqlContextBuilder;
import xyz.neopan.api.iam.XyzSubjectStore;

/**
 * @author neo.pan
 * @since 2020/1/30
 */
@Slf4j
public class AppKickstartConfig {

    @Bean
    XyzSubjectStore xyzSubjectStore() {
        log.info("[APP] xyzSubjectStore");
        return new XyzSubjectStore.InMemory();
    }

    @Bean
    XyzDataLoaderRegistryBuilder xyzDataLoaderRegistryBuilder() {
        log.info("[APP] xyzDataLoaderRegistryBuilder");
        return XyzDataLoaderRegistryBuilder.newBuilder();
    }

    @Bean
    XyzGqlContextBuilder xyzGqlContextBuilder(
        XyzSubjectStore subjectStore, XyzDataLoaderRegistryBuilder registryBuilder) {
        log.info("[APP] xyzGqlContextBuilder");
        return new XyzGqlContextBuilder(subjectStore, registryBuilder);
    }

    @ConditionalOnProperty("xyz.gql.instrumentation")
    @Bean
    public Instrumentation instrumentation() {
        log.info("[APP] instrumentation");
        val options = DataLoaderDispatcherInstrumentationOptions
            .newOptions().includeStatistics(true);
        return new DataLoaderDispatcherInstrumentation(options);
    }

}
