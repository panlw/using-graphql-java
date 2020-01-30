package xyz.neopan.api.iam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 三方用户ID
 *
 * @author neo.pan
 * @since 2020/1/30
 */
@Getter
@RequiredArgsConstructor
public final class XyzIdpUserId extends XyzPrincipal {

    @NotNull
    @Override
    public Class<?> getValType() {
        return String.class;
    }

    /**
     * IdP 标识
     */
    private final String idp;

    /**
     * IdP 给出的身份标识
     */
    private final String val;

}
