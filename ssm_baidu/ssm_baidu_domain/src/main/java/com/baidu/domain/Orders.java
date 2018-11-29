package com.baidu.domain;

import com.baidu.utils.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Orders {
    // 订单id
    private String id;
    // 订单编号
    private String orderNum;
    // 下单时间
    private Date orderTime;
    private String orderTimeStr;
    // 订单状态：0已支付，1未支付
    private int orderStatus;
    private String orderStatusStr;
    // 人数
    private int peopleCount;
    // 产品实体引用。一对一
    private Product product;
    //
    private List<Traveller> travellers;
    //
    private Member member;
    // 支付方式。0微信1支付宝
    private Integer payType;
    private String payTypeStr;
    // 订单信息说明
    private String orderDesc;

    /**
     * 订单状态
     */
    public String getOrderStatusStr() {
        if(orderStatus == 0){
            orderStatusStr = "未支付";
        }else if(orderStatus == 1){
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }

    /**
     * 下单时间
     * @return
     */
    public String getOrderTimeStr() {
        if(orderTime != null){
           orderTimeStr = DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    /**
     * 支付方式
     * @return
     */
    public String getPayTypeStr() {
        if(payType == 0){
            payTypeStr = "微信";
        }else if(payType == 1){
            payTypeStr = "支付宝";
        }
        return payTypeStr;
    }
}
