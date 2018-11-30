package com.baidu.domain;

import com.baidu.utils.HideStr;
import lombok.Data;

@Data
public class Member {
    private String id;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;

    /**
     * 隐藏手机号码
     * @return
     */
    public String getPhoneNum() {
        phoneNum = HideStr.hidePhoneNum(phoneNum);
        return phoneNum;
    }
}
