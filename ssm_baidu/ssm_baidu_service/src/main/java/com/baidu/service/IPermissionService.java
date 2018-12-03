package com.baidu.service;

import com.baidu.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll()throws Exception;

    void save(Permission permission)throws Exception;

    void updateById(Permission permission)throws Exception;

    void deletePermission(String id)throws Exception;

    Permission findByid(String id)throws Exception;

    void deleteByIds(String[] ids)throws Exception;
}
