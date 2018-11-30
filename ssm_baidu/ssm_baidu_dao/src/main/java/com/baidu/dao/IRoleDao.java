package com.baidu.dao;

import com.baidu.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    @Select("select * from ROLE where id in(select roleid from users_role where userid = #{userid})")
    List<Role> findById(String userId);
}
