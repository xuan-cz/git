package com.baidu.service.impl;

import com.baidu.dao.IUsersDao;
import com.baidu.domain.Role;
import com.baidu.domain.Users;
import com.baidu.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
@Transactional
public class UsersService implements IUsersService {

    @Autowired
    private IUsersDao usersDao;
    // 注入加密bean
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = usersDao.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将用户密码解密

        User user = new User(users.getUsername(), users.getPassword(), users.getStatus() == 0 ? false : true,
                true, true, true, getAuthority(users.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<Users> findAll() throws Exception {
        return usersDao.findAll();
    }

    @Override
    public Users findById(String id) throws Exception {
        return usersDao.findById(id);
    }

    @Override
    public void save(Users users) throws Exception {
        // 保存用户时，对用户密码进行加密后再调用dao
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersDao.save(users);
    }

    @Override
    public Users findUserByIdAndAllRole(String id) throws Exception {
        return usersDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String roleId : ids) {
            usersDao.addRoleToUser(userId,roleId);
        }
    }
}
