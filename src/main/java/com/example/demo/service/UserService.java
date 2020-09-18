package com.example.demo.service;


import com.example.demo.bean.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz 2020/7/5 14:14
 */
public interface UserService {

    User getUserByUserName(String username);

    HashMap getUserByUserNameMap(String username);

    HashMap SelectUserNumByPassword(HashMap<String, String> jsonString);

    HashMap UserDetailByLoginId(HashMap<String, String> jsonString);

    public List<HashMap> SelectUserDetailList(HashMap<String, String> jsonString);

    public Integer countUserNameIsHave(String name);

    public Integer InsertUserDetail(HashMap<String, Object> jsonString);

    public Integer UpdateUserDetail(HashMap<String, String> jsonString);

    public Integer DeleteUserDetail(String id_all);

    public Integer UserUpdatePassword(HashMap<String, String> jsonString);


}
