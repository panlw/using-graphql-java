package xyz.neopan.api.iam;

import xyz.neopan.api.XyzTaggedId;

/**
 * 身份
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzPrincipal extends XyzTaggedId {

    /**
     * 缺省的 IdP 标识
     */
    String IAM = "iam";

    /**
     * @return IdP 标识
     */
    default String getIdp() {
        return IAM;
    }

    /**
     * 缺省的身份ID标签
     */
    String TAG = "usr";

    @Override
    default String getIdTag() {
        return TAG;
    }

    /**
     * 访客ID
     */
    long GUEST_ID = 0L;

    /**
     * @return 访客？
     */
    default boolean isGuest() {
        return getIdVal() == GUEST_ID;
    }

}
