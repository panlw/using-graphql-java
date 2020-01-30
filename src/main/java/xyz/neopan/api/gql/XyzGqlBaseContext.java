package xyz.neopan.api.gql;

import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.neopan.api.iam.XyzSubject;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@RequiredArgsConstructor
public class XyzGqlBaseContext implements XyzGqlContext {

    /**
     * 认证主体（为空即访客）
     */
    @Nullable
    private final XyzSubject subject;

    @Override
    public Optional<XyzSubject> getXyzSubject() {
        return Optional.ofNullable(subject);
    }

    /**
     * 数据加载器注册表
     */
    @NotNull
    private final DataLoaderRegistry dataLoaderRegistry;

    @Override
    public Optional<DataLoaderRegistry> getDataLoaderRegistry() {
        return Optional.of(dataLoaderRegistry);
    }

}
