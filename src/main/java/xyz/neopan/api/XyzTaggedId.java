package xyz.neopan.api;

import lombok.val;

/**
 * 带标签的长整型ID
 *
 * @author neo.pan
 * @since 2020/1/29
 */
public interface XyzTaggedId {

    /**
     * @return ID 值(一般用于持久化等空间敏感型场合)
     */
    long getIdVal();

    /**
     * @return ID 标签(用来辨识 ID 的类型)
     */
    default String getIdTag() {
        return "xyz";
    }

    /**
     * 意思是：ID = [tag]-[val]
     */
    char PREFIX_FLAG = '-';

    /**
     * @return ID
     */
    default String getId() {
        return getIdTag() + PREFIX_FLAG + getIdVal();
    }

    /**
     * @param taggedId ID
     * @return ID值
     * @throws IllegalArgumentException 如果ID无效
     */
    static long toIdVal(String taggedId) {
        val idVal = parseOrZero(taggedId);
        if (idVal == 0L)
            throw new IllegalArgumentException(
                "[XYZ] Invalid tagged-id: " + taggedId);
        return idVal;
    }

    /**
     * @param taggedId ID
     * @return ID值 (返回 0 表示无效)
     */
    static long parseOrZero(String taggedId) {
        if (taggedId == null || taggedId.isEmpty())
            return 0L;

        if (Character.isWhitespace(taggedId.charAt(0))) {
            return 0L;
        }

        val flagAt = taggedId.indexOf(PREFIX_FLAG);
        if (flagAt <= 0L)
            return 0L;

        try {
            val idVal = Long.parseLong(taggedId.substring(flagAt + 1));
            return Math.max(idVal, 0L);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

}
