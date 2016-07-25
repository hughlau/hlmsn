package com.hl.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CiphertextUtil {

	/**
     * 加密字符串
     * 
     * @param sourceStr    需要加密目标字符串
     * @param algorithmsName 算法名称(如:MD2,SHA,SHA256,SHA384,SHA512)
     * @return
     */
    public static String passAlgorithmsCiphering(String sourceStr,String salt,String algorithmsName){
        sourceStr=sourceStr+salt;
        String password = "";
        switch(algorithmsName){
            case "MD5":
                password = DigestUtils.md5Hex(sourceStr);
                break;
            case "SHA":
                password = DigestUtils.shaHex(sourceStr);
                break;
            case "SHA256":
                password = DigestUtils.sha256Hex(sourceStr);
                break;
            case "SHA384":
                password = DigestUtils.sha384Hex(sourceStr);
                break;
            case "SHA512":
                password = DigestUtils.sha512Hex(sourceStr);
                break;
        }
        return password;
    }
}
