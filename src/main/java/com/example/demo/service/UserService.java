package com.example.demo.service;


import com.example.demo.bean.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz 2020/7/5 14:14
 */
public interface UserService {

    /**
     * 查询用户,根据用户名
     *
     * @param username 用户名
     * @return User
     */
    User getUserByUserName(String username);

    /**
     * 查询用户,根据用户名map
     *
     * @param username 用户名
     * @return HashMap
     */
    HashMap getUserByUserNameMap(String username);

    /**
     * 查询用户数量根据用户和密码
     *
     * @param jsonString 前台传值的json
     * @return HashMap
     */
    HashMap selectUserNumByPassword(HashMap<String, String> jsonString);

    /**
     * 根据用户id和用户登录id获取用户相关信息
     *
     * @param jsonString 前台传值的json
     * @return HashMap
     */
    HashMap userDetailByLoginId(HashMap<String, String> jsonString);

    /**
     * 据不同类型查询站点表, 对站点就行管理
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectUserDetailList(HashMap<String, String> jsonString);

    /**
     * 查看此用户数据库中是否有
     *
     * @param name 用户名
     * @return List<HashMap>
     */
    Integer countUserNameIsHave(String name);

    /**
     * 插入数据信息
     *
     * @param jsonString 前台传值的json
     * @return Integer
     */
    Integer insertUserDetail(HashMap<String, Object> jsonString);

    /**
     * 更新数据信息
     *
     * @param jsonString 前台传值的json
     * @return Integer
     */
    Integer updateUserDetail(HashMap<String, String> jsonString);

    /**
     * 删除数据信息
     *
     * @param idAll 用户id值
     * @return Integer
     */
    Integer deleteUserDetail(String idAll);

    /**
     * 用户修改密码
     *
     * @param jsonString 前台传值的json
     * @return Integer
     */
    Integer userUpdatePassword(HashMap<String, String> jsonString);


}
