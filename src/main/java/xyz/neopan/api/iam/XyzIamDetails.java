package xyz.neopan.api.iam;

/**
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzIamDetails extends XyzPrincipal {

    /**
     * 访客
     */
    XyzIamDetails GUEST = new XyzIamDetails() {

        @Override
        public long getIdVal() {
            return GUEST_ID;
        }

        @Override
        public String getName() {
            return "";
        }
    };

    /**
     * @return 显示名
     */
    String getName();

}
