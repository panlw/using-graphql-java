package xyz.neopan.api.iam;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
public class XyzIamException extends Exception {

    /**
     * @param grants 授权信息
     */
    XyzIamException(String grants) {
        super("[XYZ-AUTH] No authorization: " + grants);
    }

}
