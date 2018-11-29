package com.baidu.dao;

import com.baidu.domain.Member;
import com.baidu.domain.Orders;
import com.baidu.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,
            one = @One(select = "com.baidu.dao.IProductDao.findById"))
    })
    List<Orders> findAll()throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,
                    one = @One(select = "com.baidu.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,
                    one = @One(select = "com.baidu.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType = List.class,
                    many = @Many(select = "com.baidu.dao.ITravellerDao.findById"))
    })
    Orders findById(String id)throws Exception;
}
