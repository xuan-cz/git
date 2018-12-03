package com.baidu.service.impl;

import com.baidu.dao.IRoleDao;
import com.baidu.domain.Role;
import com.baidu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findRoleByIdAndAllPermission(String id) throws Exception {

        return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

}
