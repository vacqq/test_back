package com.example.demo.mapper;


import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


/**
 * @author liujj 2020/6/5 14:13
 */
@Repository
public interface UserMapper {

    /**
     * 查询用户,根据用户名
     *
     * @param username 用户名
     * @return User
     */
    @Select({"select d.* from system_shiro_user d " +
            "	where d.username =#{username}"
    })
    User getUserByUserName(@Param("username") String username);

    /**
     * 查询用户,根据用户名map
     *
     * @param username 用户名
     * @return HashMap
     */
    @Select({"select * from system_shiro_user  " +
            "	where username =#{username}"
    })
    HashMap getUserByUserNameMap(@Param("username") String username);


    /**
     * 查询用户数量根据用户和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return HashMap
     */
    @Select({
            "select count(id) as count, name, login_id from sys_user where login_id = #{username} and password = #{password} and is_del=0"
    })
    HashMap selectUserNumByPassword(@Param("username") String username, @Param("password") String password);


    /**
     * 根据用户id和用户登录id获取用户相关信息
     *
     * @param id      id
     * @param loginId 登录的用户id
     * @return HashMap
     */
    @Select({"<script>" +
            "select * from system_shiro_user " +
            "where is_del = 0 " +
            "<if test='id != null and id.length() != 0 '>and id = #{id} </if>" +
            "<if test='loginId != null and loginId.length() != 0 '>and login_id = #{loginId} </if>" +
            "</script>"})
    HashMap userDetailByLoginId(@Param("id") String id, @Param("loginId") String loginId);


    /**
     * 据不同类型查询站点表, 对站点就行管理
     *
     * @param username     用户名
     * @param chineseNames 用户的中文名
     * @param placeId      地区id
     * @return {{@link List <HashMap>}}
     */
    @Select("<script>" +
            "select *, (select only_name from sys_place as b where a.place_id = b.id and b.is_del = 0 ) as position" +
            " from system_shiro_user as a where user_type != '超级管理员' and is_del = 0" +
            "<if test='username != null and username.length() != 0 '>and username like '%${username}%' </if>" +
            "<if test='placeId != null and placeId.length() != 0 '>and place_id = #{placeId}' </if>" +
            "<if test='chineseNames != null and chineseNames.length() != 0 '>and chinese_names like '%${chineseNames}%' </if>" +
            "order by create_at desc" +
            "</script>")
    List<HashMap> selectUserDetailList(@Param("username") String username, @Param("chineseNames") String chineseNames, @Param("placeId") String placeId);


    /**
     * 查看此用户数据库中是否有
     *
     * @param username 唯一标识名称
     * @return {{@link Integer}}
     */
    @Select({"select count(id) as num from system_shiro_user where is_del=0 and username = #{username}"})
    Integer countUserNameIsHave(@Param("username") String username);

    /**
     * 插入数据信息
     *
     * @param username     用户名
     * @param placeId      地区id
     * @param password     密码
     * @param chineseNames 用户中文名称
     * @return {{@link Integer} 1为插入成功}
     */
    @Insert({"insert into system_shiro_user (place_id,password,username,chinese_names) values (#{placeId},#{password},#{username},#{chineseNames} )"})
    Integer insertUserDetail(@Param("placeId") Object placeId, @Param("password") Object password, @Param("username") Object username, @Param("chineseNames") Object chineseNames);


    /**
     * 更新数据信息
     *
     * @param id           id
     * @param username     用户名
     * @param placeId      地区id
     * @param password     密码
     * @param chineseNames 用户中文名称*
     * @return {{@link Integer} 1为更新成功}
     */
    @Update({"<script>" +
            "update system_shiro_user set is_del = 0" +
            "<if test='placeId != null and placeId.length() != 0 '>,place_id = #{placeId}</if>" +
            "<if test='password != null and password.length() != 0 '>,password = #{password}</if>" +
            "<if test='username != null and username.length() != 0 '>,username = #{username}</if>" +
            "<if test='chineseNames != null and chineseNames.length() != 0 '>,chinese_names = #{chineseNames}</if>" +
            "where id= #{id} " +
            "</script>"})
    Integer updateUserDetail(@Param("id") String id, @Param("placeId") Object placeId, @Param("password") Object password, @Param("username") Object username, @Param("chineseNames") Object chineseNames);


    /**
     * 删除数据信息
     *
     * @param idAll id字段
     * @return {{@link Integer} 1为删除成功}
     */
    @Update({"update system_shiro_user set is_del = 1 where id in (${idAll}) "})
    Integer deleteUserDetail(@Param("idAll") String idAll);

    /**
     * 用户修改密码
     *
     * @param id       id
     * @param username 唯一用户名
     * @param modifyBy 修改人
     * @param password 密码
     * @return {{@link Integer} 1为更新成功}
     */
    @Update({"<script>" +
            "update system_shiro_user set is_del = 0" +
            "<if test='modifyBy != null and modifyBy.length() != 0 '>,modify_by = #{modifyBy}</if>" +
            "<if test='password != null and password.length() != 0 '>,password = #{password}</if>" +
            "where username= #{username} " +
            "</script>"})
    Integer userUpdatePassword(@Param("id") String id, @Param("modifyBy") Object modifyBy, @Param("password") Object password, @Param("username") Object username);


}
