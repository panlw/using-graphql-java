package xyz.neopan.example.graphql.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.val;
import xyz.neopan.api.iam.XyzIamDetails;
import xyz.neopan.api.iam.XyzSimplexSubject;
import xyz.neopan.api.iam.XyzSubject;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Value
@EqualsAndHashCode(of = "details", callSuper = false)
public class AuthSubject extends XyzSimplexSubject {

    private final String token = UUID.randomUUID().toString();

    @Override
    public final Optional<String> getToken() {
        return Optional.of(token);
    }

    private final XyzIamDetails details;
    private final SimpleGranted granted;

    /**
     * @param details 用户信息
     * @param groups  加入的用户组
     * @return 认证主体上下文
     */
    public static XyzSubject newSubject(XyzIamDetails details, Set<String> groups) {
        val granted = SimpleGranted.of(null, groups);
        return new AuthSubject(details, granted);
    }

}
