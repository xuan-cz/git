package com.baidu.dao;

import com.baidu.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IRoleDao {

    /**
     * role 、 users多对多关系，有一张中间表user_role
     * 根据userId查询该用户具备的角色信息
     * @param userId
     * @return
     */
    @Select("select * from ROLE where id in(select roleid from users_role where userid = #{userid})")
    @Results(id = "roleMap",value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class,
                    many = @Many(select = "com.baidu.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);

    /**
     * 查询所有角色信息
     * @return
     * @throws Exception
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
    List<Role> findOtherAll(String userId) throws Exception;
    /**
     * 查询所有的角色，并且查询角色赋予的用户，以及角色具备的权限
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class,
                    many = @Many(select = "com.baidu.dao.IPermissionDao.findByRoleId")),
            @Result(column = "id", property = "users", javaType = List.class,
                    many = @Many(select = "com.baidu.dao.IUsersDao.findByRoleId"))
    })
    Role findById(String id)throws Exception;

    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    /**
     * 根据permissionId查询role
     * @param permissionId
     * @return
     */
    @Select("select * from role where id in(select roleId from role_permission where permissionId = #{permissionId})")
    List<Role> findBypermissionId(String permissionId);

    /**
     * 查询出role信息，并查出所有权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from ROLE where id  = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id",property = "permissions", javaType = List.class,
                    many = @Many(select = "com.baidu.dao.IPermissionDao.findOtherAll"))
    })
    Role findRoleByIdAndAllPermission(String id)throws Exception;

    @Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId)throws Exception;
}
