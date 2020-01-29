package xyz.neopan.api.iam;

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
     * @param subject 安全上下文
     */
    void save(XyzSubject subject);

    /**
     * @param token 安全令牌
     * @return 安全上下文
     */
    Optional<XyzSubject> load(String token);

    /**
     * 清除安全上下文（即退出）
     *
     * @param token 安全令牌
     */
    void clear(String token);

    /**
     * 内存存储
     */
    class InMemory implements XyzSubjectStore {

        private Map<String, XyzSubject> store = new ConcurrentHashMap<>();

        @Override
        public void save(XyzSubject subject) {
            store.put(subject.getToken(), subject);
        }

        @Override
        public Optional<XyzSubject> load(String token) {
            return Optional.ofNullable(store.get(token));
        }

        @Override
        public void clear(String token) {
            store.remove(token);
        }
    }

}
