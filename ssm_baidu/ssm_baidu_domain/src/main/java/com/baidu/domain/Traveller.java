package com.baidu.domain;

import com.baidu.utils.HideStr;
import lombok.Data;

@Data
public class Traveller {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    //证件类型 0身份证 1护照 2军官证
    private Integer credentialsType;
    private String credentialsTypeStr;
    private String credentialsNum;
    //旅客类型(人群) 0 成人 1 儿童
    private Integer travellerType;
    private String travellerTypeStr;

    /**
     * //证件类型 0身份证 1护照 2军官证
     * @return
     */
    public String getCredentialsTypeStr() {
        if(credentialsType == 0){
            credentialsTypeStr = "身份证";
        }else if(credentialsType == 1){
            credentialsTypeStr = "护照";
        }else if(credentialsType == 2){
            credentialsTypeStr = "军官证";
        }
        return credentialsTypeStr;
    }

    /**
     * //旅客类型(人群) 0 成人 1 儿童
     * @return
     */
    public String getTravellerTypeStr() {
        if(travellerType == 0){
            travellerTypeStr = "成人";
        }else if(travellerType == 1){
            travellerTypeStr = "儿童";
        }
        return travellerTypeStr;
    }

    /**
     * 隐藏手机号码
     * @return
     */
    public String getPhoneNum() {
        phoneNum = HideStr.hidePhoneNum(phoneNum);
        return phoneNum;
    }

    /**
     * 隐藏身份证号码
     * @return
     */
    public String getCredentialsNum() {
        credentialsNum = HideStr.hideCredentialsNum(credentialsNum);
        return credentialsNum;
    }
}
