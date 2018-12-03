package com.baidu.service;

import com.baidu.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll()throws Exception;

    Role findById(String id)throws Exception;

    void save(Role role)throws Exception;

    Role findRoleByIdAndAllPermission(String id)throws Exception;

    void addPermissionToRole(String roleId, String[] ids)throws Exception;
}
