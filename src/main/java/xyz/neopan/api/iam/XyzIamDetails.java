package xyz.neopan.api.iam;

/**
 * 主体信息
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzIamDetails {

    /**
     * @return ID
     */
    XyzPrincipal getId();

    /**
     * @return 显示名
     */
    String getName();

}
