package com.fh.taolijie.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wanghongfei on 15-3-30.
 */
public class StringUtils {
    private static final String objAlias = "obj";

    private StringUtils() {
    }

    /**
     * 随机生成一个{@code length}位的字符串
     * @param length
     * @return
     */
    public static String randomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }


    /**
     * 构造 {@code SELECT XXX FROM XXX WHERE XXX LIKE '%XXX%' AND XXX ORDER By XXX}语句
     * @param entityName
     * @param likeParam
     * @param orderBy
     * @return
     */
    public static String buildLikeQuery(String entityName, Map<String, Object> likeParam, Map.Entry<String, String> orderBy) {
        StringBuilder query = new StringBuilder();

        // 构造SELECT语句
        query.append("SELECT").append(" ");
        query.append(objAlias).append(" ");
        query.append("FROM").append(" ");
        query.append(entityName).append(" ");
        query.append(objAlias).append(" ");

        // 构造WHERE语句
        if (false == likeParam.isEmpty()) {
            query.append("WHERE").append(" ");

            Set<Map.Entry<String, Object>> entrySet = likeParam.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                query.append(objAlias).append(".").append(entry.getKey()).append(" ").append("LIKE")
                        .append(" ").append("CONCAT('%', :").append(entry.getKey()).append(", '%' )");
                query.append(" ");
                query.append("AND");
                query.append(" ");
            }

            // 去掉最后的AND
            int len = query.length();
            String ql = query.substring(0, len - 4);
            query = new StringBuilder(ql);

        }

        // 构造ORDER BY
        if (null != orderBy) {
            query.append("ORDER BY").append(" ");
            query.append(objAlias).append(".").append(orderBy.getKey()).append(" ").append(orderBy.getValue());
        }

        return query.toString();
    }

    /**
     * 构造 {@code SELECT XXX FROM XXX WHERE XXX = 'XXX' AND XXX ORDER By XXX}语句
     * @param objAlias
     * @param entityName
     * @param whereParm
     * @param orderBy
     * @return
     */
    public static String buildQuery(String objAlias, String entityName, Map<String, Object> whereParm, Map.Entry<String, String> orderBy) {
        StringBuilder query = new StringBuilder();

        // 构造SELECT语句
        query.append("SELECT").append(" ");
        query.append(objAlias).append(" ");
        query.append("FROM").append(" ");
        query.append(entityName).append(" ");
        query.append(objAlias).append(" ");

        // 构造WHERE语句
        if (false == whereParm.isEmpty()) {
            query.append("WHERE").append(" ");

            Set<Map.Entry<String, Object>> entrySet = whereParm.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                query.append(objAlias).append(".").append(entry.getKey()).append("=").append(":").append(entry.getKey());
                query.append(" ");
                query.append("AND");
                query.append(" ");
            }

            // 去掉最后的AND
            int len = query.length();
            String ql = query.substring(0, len - 4);
            query = new StringBuilder(ql);

        }

        // 构造ORDER BY
        if (null != orderBy) {
            query.append("ORDER BY").append(" ");
            query.append(objAlias).append(".").append(orderBy.getKey()).append(" ").append(orderBy.getValue());
        }

        return query.toString();
    }

    /**
     * "apple" --> apple
     * @param str
     * @return
     */
    public static String trimQuotation(String str) {
        return str.substring(1, str.length() - 1);
    }

    /**
     * 从一个id组成的字符串中去掉指定id.
     * 如，将 "1;2;3;4;5;" 去掉 "2" 后的结果为 "1;3;4;5;".
     * 如果{@code originalStr}中不包含{@code strToBeRemoved}，则直接返回原字符串
     * @param originalStr
     * @param strToBeRemoved
     * @return
     */
    public static String removeFromString(String originalStr, String strToBeRemoved) {
        if (null == originalStr) {
            throw new IllegalArgumentException("original string 不能为null");
        }

        String fullString = strToBeRemoved + Constants.DELIMITER;
        StringBuilder sb = new StringBuilder(originalStr);

        int pos = sb.indexOf(fullString);
        // 原字符串中不包含要删除的内容
        // 直接返回原串
        if (-1 == pos) {
            return originalStr;
        }
        sb.replace(pos, pos + fullString.length(), "");

        return sb.toString();
    }

    public static String addToString(String originalStr, String newStr) {
        StringBuilder sb = new StringBuilder();
        if (null == originalStr) {
            sb.append(newStr);
            sb.append(Constants.DELIMITER);
        } else {
            sb.append(originalStr);
            sb.append(newStr);
            sb.append(Constants.DELIMITER);
        }

        return sb.toString();
    }

    public static String[] splitIds(String ids) {
        if (null == ids) {
            return null;
        }

        return ids.split(Constants.DELIMITER);
    }

    public static List<Integer> toIdList(String idString) {
        String[] ids = splitIds(idString);
        if (null == ids) {
            return null;
        }

        return Arrays.stream(ids)
                .map((idStr) -> Integer.valueOf(idStr))
                .collect(Collectors.toList());
    }

    public static boolean checkIdExists(String[] ids, String targetId) {
        if (null == ids) {
            return false;
        }

        return Arrays.stream(ids)
                .anyMatch( (id) -> id.equals(targetId) );
    }

    public static boolean checkIdExists(String idsString, String idString) {
        if (null == idsString) {
            return false;
        }

        String[] ids = idsString.split(Constants.DELIMITER);

        return Arrays.stream(ids)
                .anyMatch( (id) -> id.equals(idString) );
    }

    public static boolean checkEqualAndNotEmpty(String str1, String str2) {
        if (null == str1 || null == str2) {
            return false;
        }

        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }

        return str1.equals(str2);
    }

    /**
     * 是空串返回false
     * @param str
     * @return
     */
    public static boolean checkNotEmpty(String str) {
        if (null == str || str.isEmpty()) {
            return false;
        }

        return true;
    }
}
