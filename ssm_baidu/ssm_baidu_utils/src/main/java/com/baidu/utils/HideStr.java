package com.baidu.utils;

public class HideStr {

    /**
     * 隐藏手机号码的中间4位
     * 前3后4
     *
     * @param phoneNum
     * @return
     */
    public static String hidePhoneNum(String phoneNum) {
        int length = phoneNum.length();
        String start = phoneNum.substring(0, 4);
        String end = phoneNum.substring(length - 4);
        return start + " **** " + end;
    }

    /**
     * 隐藏身份证号码的中间10位
     * 前2后6
     *
     * @param credentialsNum
     * @return
     */
    public static String hideCredentialsNum(String credentialsNum) {
        int length = credentialsNum.length();
        String start = credentialsNum.substring(0, 2);
        String end = credentialsNum.substring(length - 6);
        return start + " ********** " + end;
    }
}
