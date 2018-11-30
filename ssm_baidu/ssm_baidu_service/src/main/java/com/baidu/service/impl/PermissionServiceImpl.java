package com.baidu.service.impl;

import com.baidu.dao.IPermissionDao;
import com.baidu.domain.Permission;
import com.baidu.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deletePermission(String permissionId) throws Exception {
        permissionDao.deletePermissionRole(permissionId);
        permissionDao.deletePermission(permissionId);
    }
}
