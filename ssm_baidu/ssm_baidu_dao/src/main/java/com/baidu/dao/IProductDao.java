package com.baidu.dao;

import com.baidu.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    @Select("select * from product where id = #{id}")
    Product findById(String id)throws Exception;

    @Select("select * from product")
    List<Product> findAll()throws Exception;

    @Insert("insert into product (PRODUCTNUM,PRODUCTNAME,CITYNAME,DEPARTURETIME,PRODUCTPRICE,PRODUCTDESC,PRODUCTSTATUS)" +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
