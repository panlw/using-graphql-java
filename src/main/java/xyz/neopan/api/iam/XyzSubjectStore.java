package xyz.neopan.api.iam;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全上下文存储服务
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzSubjectStore {

    /**
     * @param subject 认证上下文
     */
    void save(XyzSubject subject);

    /**
     * @param token 客户端认证令牌
     * @return 认证上下文
     */
    Optional<XyzSubject> load(@Nullable String token);

    /**
     * 清除认证上下文
     *
     * @param token 客户端认证令牌
     */
    void clear(@NotNull String token);

    /**
     * 内存存储
     */
    class InMemory implements XyzSubjectStore {

        private Map<String, XyzSubject> store = new ConcurrentHashMap<>();

        @Override
        public void save(XyzSubject subject) {
            subject.getToken().ifPresent(token ->
                store.put(token, subject));
        }

        @Override
        public Optional<XyzSubject> load(@Nullable String token) {
            return StringUtils.isEmpty(token) ? Optional.empty()
                : Optional.ofNullable(store.get(token));
        }

        @Override
        public void clear(@NotNull String token) {
            store.remove(token);
        }
    }

}
