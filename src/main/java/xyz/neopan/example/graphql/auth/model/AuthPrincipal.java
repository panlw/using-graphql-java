package xyz.neopan.example.graphql.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import xyz.neopan.api.iam.XyzPrincipal;

import static xyz.neopan.api.XyzTaggedId.toIdVal;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Value
@EqualsAndHashCode(of = "idVal")
class AuthPrincipal implements XyzPrincipal {

    private String idp;
    private long idVal;

    static XyzPrincipal newPrincipal(String idp, long idVal) {
        return new AuthPrincipal(idp, idVal);
    }

    static XyzPrincipal newPrincipal(long idVal) {
        return newPrincipal(IAM, idVal);
    }

    static XyzPrincipal newPrincipal(String idp, String id) {
        return newPrincipal(idp, toIdVal(id));
    }

    static XyzPrincipal newPrincipal(String id) {
        return newPrincipal(IAM, toIdVal(id));
    }

}
