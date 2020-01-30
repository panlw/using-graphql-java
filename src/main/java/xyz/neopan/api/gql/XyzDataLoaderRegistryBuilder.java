package xyz.neopan.api.gql;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据加载器注册表构建器
 *
 * @author neo.pan
 * @since 2020/1/30
 */
public interface XyzDataLoaderRegistryBuilder {

    /**
     * @return 构建器
     */
    static XyzDataLoaderRegistryBuilder newBuilder() {
        return new DefaultBuilder();
    }

    /**
     * @param loaderName 数据加载器名
     * @param loader     数据加载器
     */
    void register(String loaderName, DataLoader<?, ?> loader);

    /**
     * @return 数据加载器注册表
     */
    @NotNull
    DataLoaderRegistry build();

    /**
     * 缺省构建器
     */
    class DefaultBuilder implements XyzDataLoaderRegistryBuilder {

        private Map<String, DataLoader<?, ?>> loaderMap = new HashMap<>();

        @Override
        public void register(
            String loaderName, DataLoader<?, ?> loader) {
            loaderMap.put(loaderName, loader);
        }

        @NotNull
        @Override
        public DataLoaderRegistry build() {
            DataLoaderRegistry registry = new DataLoaderRegistry();
            loaderMap.forEach(registry::register);
            return registry;
        }
    }

}
