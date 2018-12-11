package com.ucar.team.seven.tea.common.util;

import java.util.Collection;
import java.util.Vector;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:50
 * @Version 1.0
 * @Description 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0) ? true : false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 将集合中的元素用分隔符拼成字符串
     *
     * @param coll      集合对象
     * @param separator 分隔符
     * @return String
     */
    public static String join(Collection<? extends Object> coll, String separator) {
        if (coll == null || coll.size() == 0) {
            return "";
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Object obj : coll) {
            sb.append(obj.toString());
            if (i++ != (coll.size() - 1)) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }


    /**
     * 字符串分割 根据delimiter进行数据截取，ignoreEmpty=true 过滤结果集中的前后空字符串
     *
     * @param input       输入字符串
     * @param delimiter   分隔字符
     * @param ignoreEmpty 是否忽略空字符串
     * @return String[]
     */
    public static String[] split(String input, char delimiter, boolean ignoreEmpty) {
        if (input == null) {
            return new String[0];
        }
        input = input.trim();
        Vector<String> v = new Vector<String>();
        boolean moreTokens = true;
        String subString;
        while (moreTokens) {
            int tokenLocation = input.indexOf(delimiter);
            if (tokenLocation == 0) {
                subString = input.substring(0, tokenLocation);
                if (!ignoreEmpty) {
                    v.addElement(subString);
                }
                input = input.substring(tokenLocation + 1);
            } else if (tokenLocation > 0) {
                subString = input.substring(0, tokenLocation);
                v.addElement(subString);
                input = input.substring(tokenLocation + 1);
            } else {
                moreTokens = false;
                if (!ignoreEmpty || input.length() > 0) {
                    v.addElement(input);
                }
            }
        }
        String[] res = new String[v.size()];
        for (int i = 0; i != res.length; i++) {
            res[i] = (String) v.elementAt(i);
        }
        return res;
    }


    /**
     * 将string转为integer数组
     *
     * @param str
     * @return Integer[]
     */
    public static Integer[] parseToIntegerArray(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String[] tmp = str.split(",");
        Integer[] result = new Integer[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            result[i] = Integer.parseInt(tmp[i]);
        }
        return result;
    }

    /**
     * 将string转为long数组
     *
     * @param str
     * @return Integer[]
     */
    public static Long[] parseToLongArray(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String[] tmp = str.split(",");
        Long[] result = new Long[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            result[i] = Long.parseLong(tmp[i]);
        }
        return result;
    }
}
