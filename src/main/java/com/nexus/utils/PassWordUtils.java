package com.nexus.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

public class PassWordUtils {
    //MD5加密 1.只能加密不能解密2.一一对应
    //hello -> abc123def456
    //hello + 3e4a8(salt随机) -> abd123def456abc
    //StringUtils.isBlank(key)判断String为==null，空串，空格
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

//    public static String MD5Encode(String origin, String charsetname) {
//        String resultString = null;
//        try {
//            resultString = new String(origin);
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            if (charsetname == null || "".equals(charsetname))
//                resultString = byteArrayToHexString(md.digest(resultString
//                        .getBytes()));
//            else
//                resultString = byteArrayToHexString(md.digest(resultString
//                        .getBytes(charsetname)));
//        } catch (Exception exception) {
//        }
//        return resultString;
//    }
}
