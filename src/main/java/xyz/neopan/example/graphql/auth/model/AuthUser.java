package xyz.neopan.example.graphql.auth.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import xyz.neopan.api.iam.XyzIamDetails;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Value
@Builder
@EqualsAndHashCode(of = "idVal")
public class AuthUser implements XyzIamDetails {

    private long idVal;
    private String name;
    private String phone;

}
