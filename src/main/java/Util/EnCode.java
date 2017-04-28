package Util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by caoxi on 2017/4/28.
 */
public class EnCode {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decodePass(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encodePass(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}
