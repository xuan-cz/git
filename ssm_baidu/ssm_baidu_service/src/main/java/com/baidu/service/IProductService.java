package com.baidu.service;

import com.baidu.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll() throws Exception;

    void save(Product product);
}
