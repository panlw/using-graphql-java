package xyz.neopan.api.iam;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * 多维授权体系（如角色加许可）的主体上下文
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzComplexSubject implements XyzSubject {

    /**
     * @return 所有的授权信息
     */
    protected abstract Collection<? extends XyzIamGranted> getAllGranted();

    @Override
    public boolean isGranted(@NotNull String grant) {
        return getAllGranted().stream()
            .anyMatch(x -> x.inScope(grant) && x.isGranted(grant));
    }

    /**
     * @param scope 授权域
     * @return 授权信息
     */
    public Optional<? extends XyzIamGranted> getGranted(@NotNull String scope) {
        return getAllGranted().stream()
            .filter(x -> x.isScope(scope)).findFirst();
    }

}
