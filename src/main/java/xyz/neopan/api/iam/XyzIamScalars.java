package xyz.neopan.api.iam;

import graphql.language.StringValue;
import graphql.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * 标量类型
 *
 * @author neo.pan
 * @since 2020/1/30
 */
@Slf4j
public class XyzIamScalars {

    @Bean
    GraphQLScalarType xyzIamUserIdScalar() {
        log.info("[XYZ-GQL] scalar UID = XyzIamUserId <-> String");
        return GraphQLScalarType.newScalar()
            .name("UID")
            .coercing(new Coercing<XyzIamUserId, String>() {

                @Override
                public String serialize(Object dataFetcherResult)
                    throws CoercingSerializeException {
                    if (dataFetcherResult == null)
                        return null;
                    return dataFetcherResult.toString();
                }

                @Override
                public XyzIamUserId parseValue(Object input)
                    throws CoercingParseValueException {
                    if (input == null)
                        return null;
                    final String value = input.toString();
                    return XyzIamUserId.of(value).orElse(null);
                }

                @Override
                public XyzIamUserId parseLiteral(Object input)
                    throws CoercingParseLiteralException {
                    if (!(input instanceof StringValue))
                        return null;
                    final String value = ((StringValue) input).getValue();
                    return XyzIamUserId.of(value).orElse(null);
                }
            })
            .build();
    }

}
