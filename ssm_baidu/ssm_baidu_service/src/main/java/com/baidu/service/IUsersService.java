package com.baidu.service;

import com.baidu.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsersService extends UserDetailsService {

    List<Users> findAll()throws Exception;

    Users findById(String id)throws Exception;

    void save(Users users)throws Exception;

    Users findUserByIdAndAllRole(String id)throws Exception;

    void addRoleToUser(String userId, String[] ids)throws Exception;
}
