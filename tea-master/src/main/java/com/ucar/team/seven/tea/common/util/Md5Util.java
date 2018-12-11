package com.ucar.team.seven.tea.common.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:49
 * @Version 1.0
 * @Description Md5工具类
 */
public class Md5Util {

    /**
     * 获取str的md5值
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String pwd = null;
        try {
            pwd = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pwd;
    }

    public static void main(String[] args) {
        System.out.println(Md5Util.getMD5("123456"));
    }
}
