package xyz.neopan.api.gql;

import graphql.servlet.context.GraphQLServletContext;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import org.dataloader.DataLoaderRegistry;
import org.jetbrains.annotations.NotNull;
import xyz.neopan.api.iam.XyzSubject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Getter
class XyzGqlHttpContext extends XyzGqlBaseContext
    implements GraphQLServletContext {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Builder
    XyzGqlHttpContext(
        @NotNull HttpServletRequest httpServletRequest,
        @NotNull HttpServletResponse httpServletResponse,
        @NotNull XyzSubject subject, DataLoaderRegistry dataLoaderRegistry) {
        super(subject, dataLoaderRegistry);
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    @SneakyThrows
    @Override
    public List<Part> getFileParts() {
        return httpServletRequest.getParts().stream()
            .filter(part -> part.getContentType() != null)
            .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Map<String, List<Part>> getParts() {
        return httpServletRequest.getParts().stream()
            .collect(Collectors.groupingBy(Part::getName));
    }

}
