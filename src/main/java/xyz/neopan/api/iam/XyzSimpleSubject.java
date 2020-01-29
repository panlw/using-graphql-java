package xyz.neopan.api.iam;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzSimpleSubject implements XyzSubject {

    protected abstract XyzIamGranted getGranted();

    @Override
    public boolean isGranted(String grant) {
        return getGranted().isGranted(grant);
    }

}
