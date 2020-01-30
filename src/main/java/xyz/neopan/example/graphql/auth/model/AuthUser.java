package xyz.neopan.example.graphql.auth.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import xyz.neopan.api.iam.XyzIamDetails;
import xyz.neopan.api.iam.XyzIamUserId;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
@Value
@Builder
@EqualsAndHashCode(of = "id")
public class AuthUser implements XyzIamDetails {

    private XyzIamUserId id;
    private String name;
    private String phone;

}
