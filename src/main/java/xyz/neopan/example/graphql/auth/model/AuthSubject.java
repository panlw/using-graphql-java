package xyz.neopan.example.graphql.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import xyz.neopan.api.iam.XyzIamDetails;
import xyz.neopan.api.iam.XyzIamGranted;
import xyz.neopan.api.iam.XyzSubject;

import java.util.Optional;
import java.util.UUID;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class AuthSubject extends XyzSubject {

    private final String token = UUID.randomUUID().toString();

    @Override
    public final Optional<String> getToken() {
        return Optional.of(token);
    }

    /**
     * 授权信息
     */
    private final XyzIamGranted granted;

    /**
     * 主体信息
     */
    private final XyzIamDetails details;

}
