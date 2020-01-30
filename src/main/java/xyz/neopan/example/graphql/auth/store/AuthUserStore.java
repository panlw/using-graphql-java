package xyz.neopan.example.graphql.auth.store;

import xyz.neopan.example.graphql.auth.model.AuthUser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author neo.pan
 * @since 2020/1/30
 */
public class AuthUserStore {

    public Set<String> getUserGroups(AuthUser user) {
        return new HashSet<>(Arrays.asList("g1", "g2"));
    }

}
