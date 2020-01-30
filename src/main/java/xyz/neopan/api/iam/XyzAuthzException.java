package xyz.neopan.api.iam;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
public class XyzAuthzException extends RuntimeException {

    /**
     * @param grants 授权信息
     */
    XyzAuthzException(String grants) {
        super("[XYZ-AUTH] No authorization: " + grants);
    }

}
