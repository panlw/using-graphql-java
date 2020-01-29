package xyz.neopan.api.gql;

import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoaderRegistry;
import xyz.neopan.api.iam.XyzSubject;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@RequiredArgsConstructor
public class XyzGqlBaseContext implements XyzGqlContext {

    private final XyzSubject subject;

    @Override
    public Optional<XyzSubject> getXyzSubject() {
        return Optional.ofNullable(subject);
    }

    private final DataLoaderRegistry dataLoaderRegistry;

    @Override
    public Optional<DataLoaderRegistry> getDataLoaderRegistry() {
        return Optional.ofNullable(dataLoaderRegistry);
    }

}
