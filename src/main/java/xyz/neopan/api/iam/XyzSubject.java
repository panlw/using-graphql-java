package xyz.neopan.api.iam;

import java.util.Optional;

/**
 * 认证主体
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzSubject {

    /**
     * @return 会话令牌（为空即访客）
     */
    public abstract Optional<String> getToken();

    /**
     * @return 访客？
     */
    public final boolean isGuest() {
        return !getToken().isPresent();
    }

    /**
     * @return 授权信息
     */
    public abstract XyzIamGranted getGranted();

    /**
     * @return 主体信息
     */
    public abstract XyzIamDetails getDetails();

    /**
     * @return ID
     */
    public XyzPrincipal getId() {
        return getDetails().getId();
    }

    /**
     * @return 显示名
     */
    public String getName() {
        return getDetails().getName();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XyzSubject))
            return false;
        return getId().equals(((XyzSubject) obj).getId());
    }

    /**
     * 访客
     */
    public static final XyzSubject GUEST = new XyzSubject() {

        @Override
        public Optional<String> getToken() {
            return Optional.empty();
        }

        @Override
        public XyzPrincipal getId() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public XyzIamGranted getGranted() {
            throw new UnsupportedOperationException("[XYZ-IAM] I am GUEST.");
        }

        @Override
        public XyzIamDetails getDetails() {
            throw new UnsupportedOperationException("[XYZ-IAM] I am GUEST.");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("[XYZ-IAM] I am GUEST.");
        }

        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("[XYZ-IAM] I am GUEST.");
        }
    };

}
