package com.baidu.domain;

import com.baidu.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品信息表
 */
@Data
public class Product {
    // 主键
    private String id;
    // 编号 唯一
    private String productNum;
    // 名称
    private String productName;
    // 出发城市
    private String cityName;
    // 出发时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;
    private String departureTimeStr;

    // 产品价格
    private double productPrice;
    // 产品描述
    private String productDesc;
    // 状态 0 关闭 1 开启
    private Integer productStatus;
    private String productStatusStr;

    /**
     * 格式化时间
     * 返回字符串为前台
     */
    public String getDepartureTimeStr() {
        if(departureTime != null){
            departureTimeStr = DateUtils.date2String(departureTime,"yyyy-MM-dd HH:mm");
        }
        return departureTimeStr;
    }

    /**
     * 格式化状态
     * 0为关闭
     * 1为开启
     */
    public String getProductStatusStr() {
        if(productStatus == 0){
            productStatusStr = "关闭";
        }else if(productStatus == 1){
            productStatusStr = "开启";
        }
        return productStatusStr;
    }
}
