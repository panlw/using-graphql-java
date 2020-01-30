package xyz.neopan.api.iam;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * 运营方用户ID
 *
 * @author neo.pan
 * @since 2020/1/30
 */
@RequiredArgsConstructor
public final class XyzIamUserId extends XyzPrincipal {

    @NotNull
    @Override
    public Class<?> getValType() {
        return Long.class;
    }

    /**
     * 缺省的 IdP 标识: iam (一般用于运营方用户体系)
     */
    public static final String IAM = "iam";

    /**
     * 字串 ID 的前缀
     */
    public static final String PREFIX = "usr-";

    /**
     * ID 值 (>0)
     */
    private final long val;

    @NotNull
    @Override
    public String getIdp() {
        return IAM;
    }

    @NotNull
    @Override
    public Object getVal() {
        return val;
    }

    @Override
    public String toString() {
        return PREFIX + val;
    }

    /**
     * @param id 字串 ID
     * @return ID 值
     */
    public static Optional<Long> parse(@Nullable String id) {
        if (id == null || !id.startsWith(PREFIX))
            return Optional.empty();

        long val = 0;
        try {
            val = Long.parseLong(id.substring(PREFIX.length()));
        } catch (NumberFormatException ignored) {
        }
        return val > 0 ? Optional.of(val) : Optional.empty();
    }

    /**
     * @param id 字串 ID
     * @return ID
     */
    public static Optional<XyzIamUserId> of(@Nullable String id) {
        return parse(id).map(XyzIamUserId::new);
    }

}
