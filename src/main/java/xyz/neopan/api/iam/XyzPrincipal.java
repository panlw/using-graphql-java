package xyz.neopan.api.iam;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 认证身份
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzPrincipal {

    /**
     * @return IdP 给出的身份标识数据类型
     */
    @NotNull
    public abstract Class<?> getValType();

    /**
     * @return IdP 标识
     */
    @NotNull
    public abstract String getIdp();

    /**
     * @return IdP 给出的身份标识
     */
    @NotNull
    public abstract Object getVal();

    @Override
    public String toString() {
        return getVal().toString();
    }

    @Override
    public int hashCode() {
        return getVal().hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof XyzPrincipal))
            return false;
        return getVal().equals(((XyzPrincipal) obj).getVal());
    }

}
