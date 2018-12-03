package com.baidu.dao;

import com.baidu.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissionDao {

    /**
     * 根据角色id查询所具备的权限信息
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in(select permissionId from Role_Permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId)throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Select("select * from permission where id not in(select permissionId from Role_Permission where roleId = #{roleId})")
    List<Permission> findOtherAll(String roleId)throws Exception;

    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission)throws Exception;


    @Update("update permission set permissionName = #{permissionName},url = #{url} where id = #{id}")
    void updateById(Permission permission)throws Exception;


    /**
     * 根据权Id删除权限和角色中间表中数据
     * @param permissionId
     * @throws Exception
     */
    @Delete("delete from role_permission where permissionId = #{permissionId}")
    void deletePermissionRole(String permissionId)throws Exception;

    /**
     * 根据id删除一条权限记录
     * @param permissionId
     * @throws Exception
     */
    @Delete("delete from permission where id = #{permissionId}")
    void deletePermission(String permissionId)throws Exception;

    @Select("select * from permission where id = #{id}")
    Permission findById(String id)throws Exception;
}
