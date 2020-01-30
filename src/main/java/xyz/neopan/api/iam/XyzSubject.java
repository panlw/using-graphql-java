package xyz.neopan.api.iam;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * 认证主体上下文
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzSubject extends XyzIamDetails, XyzIamGranted {

    /**
     * 访客
     */
    XyzSubject GUEST = new XyzSubject() {

        @Override
        public Optional<String> getToken() {
            return Optional.empty();
        }

        @Override
        public XyzIamDetails getDetails() {
            return XyzIamDetails.GUEST;
        }

        @Override
        public boolean isGranted(String grant) {
            return false;
        }

        @Override
        public boolean isAllGranted(Collection<String> grants) {
            return false;
        }

        @Override
        public boolean isAnyGranted(Collection<String> grants) {
            return false;
        }
    };

    /**
     * @return 客户端认证令牌（访客没有认证令牌）
     */
    Optional<String> getToken();

    /**
     * @return 访客？
     */
    default boolean isGuest() {
        return !getToken().isPresent();
    }

    /**
     * @return 所有身份
     */
    default Collection<XyzPrincipal> getPrincipals() {
        return Collections.singletonList(getDetails());
    }

    /**
     * @return 主体信息
     */
    XyzIamDetails getDetails();

    @Override
    default String getIdp() {
        return getDetails().getIdp();
    }

    @Override
    default long getIdVal() {
        return getDetails().getIdVal();
    }

    @Override
    default String getName() {
        return getDetails().getName();
    }

}
