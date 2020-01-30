package xyz.neopan.api.iam;

/**
 * 单一授权体系（如角色或用户组）的主体上下文
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public abstract class XyzSimplexSubject implements XyzSubject {

    /**
     * @return 授权信息
     */
    protected abstract XyzIamGranted getGranted();

    @Override
    public boolean isGranted(String grant) {
        return getGranted().isGranted(grant);
    }

}
