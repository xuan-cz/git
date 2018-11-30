package com.baidu.dao;

import com.baidu.domain.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUsersDao {

    /**
     * 根据用户名查出用户所有信息，以及具备的角色信息
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results(
            id = "result",
            value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "email", property = "email"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "com.baidu.dao.IRoleDao.findByUserId"))
    })
    Users findByName(String username)throws Exception;

    /**
     * 查询所有的用户信息
     * @return
     * @throws Exception
     */
    @Select("select * from users")
    List<Users> findAll() throws Exception;

    /**
     * 根据用户id查询用户的所有信息，以及具备的角色信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @ResultMap("result")
    Users findById(String id) throws Exception;

    /**
     * 插入一条用户记录
     * @param users
     * @throws Exception
     */
    @Insert("insert into users (email,username,password,phoneNum,status)" +
            " values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(Users users) throws Exception;

    /**
     * 根据角色id查询用户信息
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from users where id in(select userId from users_role where roleId = #{roleId})")
    List<Users> findByRoleId(String roleId)throws Exception;

    /**
     * 查询单个用户信息，并且查出所有角色信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "phoneNum", property = "phoneNum"),
                    @Result(column = "status", property = "status"),
                    @Result(column = "id", property = "roles", javaType = List.class,
                            many = @Many(select = "com.baidu.dao.IRoleDao.findAll"))
            })
    Users findUserByIdAndAllRole(String id)throws Exception;

    /**
     * 为用户添加角色权限
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
