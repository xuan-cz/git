package com.baidu.service.impl;

import com.baidu.dao.IOrdersDao;
import com.baidu.domain.Orders;
import com.baidu.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll()throws Exception {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }


}
