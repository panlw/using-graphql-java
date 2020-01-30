package xyz.neopan.api.iam;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * 授权信息
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzIamGranted {

    /**
     * @return 授权域（空表示全局授权域）
     */
    default Optional<String> getScope() {
        return Optional.empty();
    }

    /**
     * @param scope 授权域
     * @return 是指定的授权域或全局域？
     */
    default boolean isScope(@NotNull String scope) {
        return getScope().map(x -> x.equals(scope)).orElse(true);
    }

    /**
     * @param grant 授权标识
     * @return 指定的授权是否属于该授权域？
     */
    default boolean inScope(@NotNull String grant) {
        return getScope().map(grant::startsWith).orElse(true);
    }

    /**
     * @param grant 授权标识
     * @return 是否拥有指定授权？
     */
    boolean isGranted(String grant);

    default void checkGrant(String grant) throws XyzIamException {
        if (!isGranted(grant))
            throw new XyzIamException(grant);
    }

    default boolean isAllGranted(Collection<String> grants) {
        return grants.stream().allMatch(this::isGranted);
    }

    default void checkAllGranted(Collection<String> grants) throws XyzIamException {
        if (!isAllGranted(grants))
            throw new XyzIamException(grants.toString());
    }

    default boolean isAnyGranted(Collection<String> grants) {
        return grants.stream().anyMatch(this::isGranted);
    }

    default void checkAnyGranted(Collection<String> grants) throws XyzIamException {
        if (!isAnyGranted(grants))
            throw new XyzIamException(grants.toString());
    }

    /**
     * {@link XyzIamGranted}的简单实现
     */
    @Value(staticConstructor = "of")
    final class SimpleGranted implements XyzIamGranted {

        /**
         * 授权域（即授权标识的名字前缀）
         */
        private String scope;

        /**
         * 授权标识（含授权域前缀）
         */
        private Set<String> grants;

        @Override
        public Optional<String> getScope() {
            return Optional.of(scope);
        }

        @Override
        public boolean isGranted(String grant) {
            return grants.contains(grant);
        }
    }

}
