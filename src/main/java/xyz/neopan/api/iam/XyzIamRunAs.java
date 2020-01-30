package xyz.neopan.api.iam;

import java.util.Collection;
import java.util.Collections;

/**
 * 切换运行身份
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
