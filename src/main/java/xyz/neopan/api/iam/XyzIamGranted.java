package xyz.neopan.api.iam;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 授权信息
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzIamGranted {

    /**
     * 访客授权
     */
    XyzIamGranted GUEST = grant -> false;

    /**
     * @param grant 授权标识
     * @return 是否拥有指定授权？
     */
    boolean isGranted(String grant);

    default void checkGrant(String grant) throws XyzIamException {
        if (!isGranted(grant))
            throw new XyzIamException(grant);
    }

    default boolean isAllGranted(String... grants) {
        return Stream.of(grants).allMatch(this::isGranted);
    }

    default void checkAllGranted(String... grants) throws XyzIamException {
        if (!isAllGranted(grants))
            throw new XyzIamException(Arrays.toString(grants));
    }

    default boolean isAnyGranted(String... grants) {
        return Stream.of(grants).anyMatch(this::isGranted);
    }

    default void checkAnyGranted(String... grants) throws XyzIamException {
        if (!isAnyGranted(grants))
            throw new XyzIamException(Arrays.toString(grants));
    }

    interface NsGranted extends XyzIamGranted {

        /**
         * @return 权限标识的名字空间(简单授权体系可为空)
         */
        @Nullable
        String getGrantNs();

        /**
         * @param grant 授权标识
         * @return 是否支持？
         */
        default boolean isGrantable(@NotNull String grant) {
            final String ns = getGrantNs();
            if (StringUtils.isEmpty(ns))
                return true;
            return grant.startsWith(ns);
        }
    }

    @RequiredArgsConstructor(staticName = "of")
    class SimpleGranted implements NsGranted {

        /**
         * 授权标识的名字空间
         */
        private final String ns;

        /**
         * 授权的对象标识集合(含名字空间)
         */
        private final Set<String> grants;

        @Nullable
        @Override
        public String getGrantNs() {
            return ns;
        }

        @Override
        public boolean isGranted(String grant) {
            return grants.contains(grant);
        }
    }

}
