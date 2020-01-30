package xyz.neopan.api.iam;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static xyz.neopan.api.iam.XyzIamUserId.parse;

/**
 * @author neo.pan
 * @since 2020/1/30
 */
class XyzIamUserIdTest {

    @Test
    void parse_OK() {
        assertThat(parse("usr-1")).isPresent()
            .get().isEqualTo(1L);
    }

    @Test
    void parse_NG() {
        assertThat(parse(null)).isNotPresent();
        assertThat(parse("")).isNotPresent();
        assertThat(parse(" usr-123")).isNotPresent();
        assertThat(parse("\tusr-123")).isNotPresent();
        assertThat(parse("a")).isNotPresent();
        assertThat(parse("123")).isNotPresent();
        assertThat(parse("usr-a")).isNotPresent();
        assertThat(parse("usr-0")).isNotPresent();
        assertThat(parse("-123")).isNotPresent();
        assertThat(parse("usr-123a")).isNotPresent();
        assertThat(parse("usr--123")).isNotPresent();
        assertThat(parse("usr-12.3")).isNotPresent();
    }

}
