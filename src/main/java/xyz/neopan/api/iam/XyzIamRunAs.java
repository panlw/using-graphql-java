package xyz.neopan.api.iam;

import java.util.Collection;
import java.util.Collections;

/**
 * 切换运行的身份信息及安全上下文
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzIamRunAs {

    boolean isRunAs();

    void runAs(Collection<XyzPrincipal> principals);

    default void runAs(XyzPrincipal principals) {
        runAs(Collections.singletonList(principals));
    }

    void releaseRunAs();

}
