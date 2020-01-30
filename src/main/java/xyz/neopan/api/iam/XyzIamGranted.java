package xyz.neopan.api.iam;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 授权信息
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzIamGranted {

    /**
     * @return 授权域（空表示全局授权域）
     */
    public Optional<String> getScope() {
        return Optional.empty();
    }

    /**
     * @param scope 授权域
     * @return 是指定的授权域或全局域？
     */
    public final boolean isScope(@NotNull String scope) {
        return getScope().map(x -> x.equals(scope)).orElse(true);
    }

    /**
     * @param grant 授权标识
     * @return 指定的授权是否属于该授权域？
     */
    public final boolean inScope(@NotNull String grant) {
        return getScope().map(grant::startsWith).orElse(true);
    }

    /**
     * @param grant 授权标识
     * @return 是否拥有指定授权？
     */
    public abstract boolean isGranted(String grant);

    public final void checkGrant(String grant) {
        if (!isGranted(grant))
            throw new XyzAuthzException(grant);
    }

    public boolean isAllGranted(Collection<String> grants) {
        return grants.stream().allMatch(this::isGranted);
    }

    public final void checkAllGranted(Collection<String> grants) {
        if (!isAllGranted(grants))
            throw new XyzAuthzException(grants.toString());
    }

    public boolean isAnyGranted(Collection<String> grants) {
        return grants.stream().anyMatch(this::isGranted);
    }

    public final void checkAnyGranted(Collection<String> grants) {
        if (!isAnyGranted(grants))
            throw new XyzAuthzException(grants.toString());
    }

    /**
     * 单一授权体系（如：用户组）
     */
    @RequiredArgsConstructor(staticName = "of")
    private static class Simplex extends XyzIamGranted {

        /**
         * 授权域（即授权标识的名字前缀）
         */
        private final String scope;

        /**
         * 授权标识（含授权域前缀）
         */
        private final Set<String> grants;

        @Override
        public Optional<String> getScope() {
            return Optional.of(scope);
        }

        @Override
        public boolean isGranted(String grant) {
            return grants.contains(grant);
        }
    }

    /**
     * 复合授权体系（如：角色+许可）
     */
    @RequiredArgsConstructor(staticName = "of")
    private static class Complex extends XyzIamGranted {

        /**
         * 所有的授权信息
         */
        private final Collection<? extends XyzIamGranted> all;

        /**
         * @param scope 授权域
         * @return 授权信息
         */
        public Optional<? extends XyzIamGranted> getGranted(@NotNull String scope) {
            return all.stream()
                .filter(x -> x.isScope(scope)).findFirst();
        }

        @Override
        public boolean isGranted(@NotNull String grant) {
            return all.stream()
                .anyMatch(x -> x.inScope(grant) && x.isGranted(grant));
        }
    }

    /**
     * @param scope  作用域
     * @param grants 授权列表
     * @return 单一授权体系
     */
    public static XyzIamGranted newGranted(
        String scope, Collection<String> grants) {
        return Simplex.of(scope, new HashSet<>(grants));
    }

    /**
     * @param grants 授权列表
     * @return 全局作用域授权体系
     */
    public static XyzIamGranted newGranted(Collection<String> grants) {
        return newGranted(null, grants);
    }

    /**
     * @param grants 授权体系列表
     * @return 复合授权体系
     */
    public static XyzIamGranted newGranted(XyzIamGranted... grants) {
        return Complex.of(Arrays.asList(grants));
    }

}
