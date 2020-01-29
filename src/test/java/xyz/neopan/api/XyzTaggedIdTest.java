package xyz.neopan.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static xyz.neopan.api.XyzTaggedId.parseOrZero;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
class XyzTaggedIdTest {

    @Test
    void parseOrZero_OK() {
        assertThat(parseOrZero("xyz-123")).isEqualTo(123L);
    }

    @Test
    void parseOrZero_NG() {
        assertThat(parseOrZero(null)).isZero();
        assertThat(parseOrZero("")).isZero();
        assertThat(parseOrZero(" xyz-123")).isZero();
        assertThat(parseOrZero("\txyz-123")).isZero();
        assertThat(parseOrZero("a")).isZero();
        assertThat(parseOrZero("123")).isZero();
        assertThat(parseOrZero("xyz-a")).isZero();
        assertThat(parseOrZero("-123")).isZero();
        assertThat(parseOrZero("xyz-123a")).isZero();
        assertThat(parseOrZero("xyz--123")).isZero();
        assertThat(parseOrZero("xyz-12.3")).isZero();
    }

}
