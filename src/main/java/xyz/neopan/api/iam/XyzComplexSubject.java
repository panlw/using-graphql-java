package xyz.neopan.api.iam;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzComplexSubject implements XyzSubject {

    /**
     * @return 所有的授权信息
     */
    protected abstract Collection<? extends XyzIamGranted> getAllGranted();

    @Override
    public boolean isGranted(String grant) {
        return getAllGranted().stream()
            .filter(x -> isGrantable(x, grant)).findFirst()
            .map(x -> x.isGranted(grant)).orElse(false);
    }

    private boolean isGrantable(XyzIamGranted granted, String grant) {
        return !(granted instanceof NsGranted)
            || ((NsGranted) granted).isGrantable(grant);
    }

    /**
     * @param ns 名字空间
     * @return 授权信息
     */
    public Optional<? extends XyzIamGranted> getGranted(@Nullable String ns) {
        return getAllGranted().stream().filter(x -> nsGrantable(x, ns)).findFirst();
    }

    private boolean nsGrantable(XyzIamGranted granted, @Nullable String ns) {
        return ns == null || !(granted instanceof NsGranted)
            || ns.equals(((NsGranted) granted).getGrantNs());
    }

}
