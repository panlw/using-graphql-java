package xyz.neopan.api.iam;

import org.springframework.http.HttpStatus;

/**
 * @author neo.pan
 * @since 2020/1/30
 */
public class XyzAuthnException extends RuntimeException {

    private final HttpStatus status;

    private XyzAuthnException(HttpStatus status) {
        this.status = status;
    }

    public static final XyzAuthnException UNAUTHORIZED =
        new XyzAuthnException(HttpStatus.UNAUTHORIZED);

    public static final XyzAuthnException FORBIDDEN =
        new XyzAuthnException(HttpStatus.FORBIDDEN);

}
