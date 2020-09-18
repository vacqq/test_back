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


    @Select({"select d.* from system_shiro_user d " +
            "	where d.username =#{username}"
    })
    User getUserByUserName(@Param("username") String username);

    @Select({"select * from system_shiro_user  " +
            "	where username =#{username}"
    })
    HashMap getUserByUserNameMap(@Param("username") String username);

    /**
     * 查询用户数量根据用户和密码
     *
     * @return
     */
    @Select({
            "select count(id) as count, name, login_id from sys_user where login_id = #{user_name} and password = #{password} and is_del=0"
    })
    HashMap SelectUserNumByPassword(@Param("user_name") String user_name, @Param("password") String password);


    /**
     * 根据用户id和用户登录id获取用户相关信息
     *
     * @return
     */
    @Select({"<script>" +
            "select * from system_shiro_user " +
            "where is_del = 0 " +
            "<if test='id != null and id.length() != 0 '>and id = #{id} </if>" +
            "<if test='login_id != null and login_id.length() != 0 '>and login_id = #{login_id} </if>" +
            "</script>"})
    HashMap UserDetailByLoginId(@Param("id") String id, @Param("login_id") String login_id);


    /**
     * @param
     * @return {{@link List <HashMap>}}
     * @description 据不同类型查询站点表, 对站点就行管理
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select("<script>" +
            "select *, (select only_name from sys_place as b where a.place_id = b.id and b.is_del = 0 ) as position" +
            " from system_shiro_user as a where user_type != '超级管理员' and is_del = 0" +
            "<if test='username != null and username.length() != 0 '>and username like '%${username}%' </if>" +
            "<if test='place_id != null and place_id.length() != 0 '>and place_id = #{place_id}' </if>" +
            "<if test='chinese_names != null and chinese_names.length() != 0 '>and chinese_names like '%${chinese_names}%' </if>" +
            "order by create_at desc" +
            "</script>")
    List<HashMap> SelectUserDetailList(@Param("username") String username, @Param("chinese_names") String chinese_names, @Param("place_id") String place_id);


    /**
     * @param username 唯一标识名称
     * @return {{@link Integer}}
     * @description 查看此用户数据库中是否有
     * @author lcz
     * @date 2020/8/23 14:47
     */
    @Select({"select count(id) as num from system_shiro_user where is_del=0 and username = #{username}"})
    Integer countUserNameIsHave(@Param("username") String username);

    /**
     * @return {{@link Integer} 1为插入成功}
     * @description 插入数据信息
     * @author lcz
     * @date 2020/8/13 15:42
     */
    @Insert({"insert into system_shiro_user (place_id,password,username,chinese_names) values (#{place_id},#{pass_word},#{username},#{chinese_names} )"})
    Integer InsertUserDetail(@Param("place_id") Object place_id, @Param("pass_word") Object pass_word, @Param("username") Object username, @Param("chinese_names") Object chinese_names);


    /**
     * @return {{@link Integer} 1为更新成功}
     * @description 更新数据信息
     * @author lcz
     * @date 2020/7/1 15:42
     */
    @Update({"<script>" +
            "update system_shiro_user set is_del = 0" +
            "<if test='place_id != null and place_id.length() != 0 '>,place_id = #{place_id}</if>" +
            "<if test='password != null and password.length() != 0 '>,password = #{password}</if>" +
            "<if test='username != null and username.length() != 0 '>,username = #{username}</if>" +
            "<if test='chinese_names != null and chinese_names.length() != 0 '>,chinese_names = #{chinese_names}</if>" +
            "where id= #{id} " +
            "</script>"})
    Integer UpdateUserDetail(@Param("id") String id, @Param("place_id") Object place_id, @Param("password") Object password, @Param("username") Object username, @Param("chinese_names") Object chinese_names);


    /**
     * @return {{@link Integer} 1为删除成功}
     * @description 删除数据信息
     * @author lcz
     * @date 2020/8/1 15:42
     */
    @Update({"update system_shiro_user set is_del = 1 where id in (${id_all}) "})
    Integer DeleteUserDetail(@Param("id_all") String id_all);

    /**
     * @return {{@link Integer} 1为更新成功}
     * @description 用户修改密码
     * @author lcz
     * @date 2020/8/1 15:42
     */
    @Update({"<script>" +
            "update system_shiro_user set is_del = 0" +
            "<if test='modify_by != null and modify_by.length() != 0 '>,modify_by = #{modify_by}</if>" +
            "<if test='pass_word != null and pass_word.length() != 0 '>,password = #{pass_word}</if>" +
            "where username= #{username} " +
            "</script>"})
    Integer UserUpdatePassword(@Param("id") String id, @Param("modify_by") Object modify_by, @Param("pass_word") Object pass_word, @Param("username") Object username);


}
