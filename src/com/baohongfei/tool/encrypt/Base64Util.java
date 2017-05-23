package com.baohongfei.tool.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by terry on 17/5/23.
 */
public class Base64Util {
    public static String encodeBase64(String str) {
        if (str == null) {
            return null;
        }
        byte[] b;
        String s = null;
        try {
            b = str.getBytes("UTF-8");
        } catch (Exception e) {
            return null;
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String decodeBase64(String s) {
        if (s == null) {
            return null;
        }
        byte[] b;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "UTF-8");
            } catch (Exception e) {
                return null;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(encodeBase64("沧江万里悲南渡，白发几人能北归。"));
        System.out.println(decodeBase64("5rKn5rGf5LiH6YeM5oKy5Y2X5rih77yM55m95Y+R5Yeg5Lq66IO95YyX5b2S44CC"));
    }
}