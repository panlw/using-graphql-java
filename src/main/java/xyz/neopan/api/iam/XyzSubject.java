package xyz.neopan.api.iam;

import java.util.Collection;
import java.util.Collections;

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
        public String getToken() {
            return "";
        }

        @Override
        public XyzIamDetails getDetails() {
            return XyzIamDetails.GUEST;
        }

        @Override
        public boolean isGranted(String grant) {
            return false;
        }
    };

    /**
     * @return 客户端令牌
     */
    String getToken();

    /**
     * @return 所有身份
     */
    default Collection<XyzPrincipal> getPrincipals() {
        return Collections.singletonList(getDetails());
    }

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

    /**
     * @return 主体信息
     */
    XyzIamDetails getDetails();

}
