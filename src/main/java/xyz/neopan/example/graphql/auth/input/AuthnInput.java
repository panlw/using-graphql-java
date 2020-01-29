package xyz.neopan.example.graphql.auth.input;

import lombok.Data;
import lombok.val;
import xyz.neopan.api.XyzTaggedId;
import xyz.neopan.example.graphql.auth.model.AuthUser;

import java.util.Set;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Data
public class AuthnInput {

    private String id;
    private String name;
    private String phone;
    private Set<String> groups;

    public AuthUser toUser() {
        val idVal = XyzTaggedId.toIdVal(id);
        return AuthUser.builder()
            .idVal(idVal).name(name)
            .phone(phone)
            .build();
    }

}
