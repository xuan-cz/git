package com.baidu.service;

import com.baidu.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll() throws Exception;

    List<Orders> findAll(Integer page, Integer pageSize) throws Exception;

    Orders findById(String id)throws Exception;
}
