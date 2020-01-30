package xyz.neopan.example.graphql.auth.input;

import lombok.Data;
import lombok.val;
import xyz.neopan.api.XyzTaggedId;
import xyz.neopan.example.graphql.auth.model.AuthUser;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Data
public class AuthSignInInput {

    private String id;
    private String name;
    private String phone;

    public AuthUser toUser() {
        val idVal = XyzTaggedId.toIdVal(id);
        return AuthUser.builder()
            .idVal(idVal).name(name)
            .phone(phone)
            .build();
    }

}
