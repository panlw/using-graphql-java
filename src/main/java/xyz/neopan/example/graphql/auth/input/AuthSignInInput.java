package xyz.neopan.example.graphql.auth.input;

import lombok.Data;
import xyz.neopan.api.iam.XyzIamUserId;
import xyz.neopan.example.graphql.auth.model.AuthUser;

import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Data
public class AuthSignInInput {

    private String id;
    private String name;
    private String phone;

    public Optional<AuthUser> toUser() {
        return XyzIamUserId.of(id).map(id ->
            AuthUser.builder()
                .id(id).name(name)
                .phone(phone)
                .build());
    }

}
