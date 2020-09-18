package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author liujj 2020/6/5 14:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public HashMap getUserByUserNameMap(String username) {
        return userMapper.getUserByUserNameMap(username);
    }

    @Override
    public HashMap SelectUserNumByPassword(HashMap<String, String> jsonString) {
        return userMapper.SelectUserNumByPassword(jsonString.get("user_name"), jsonString.get("password"));
    }

    public List<HashMap> SelectUserDetailList(HashMap<String, String> jsonString) {
        return userMapper.SelectUserDetailList(jsonString.get("username"), jsonString.get("chinese_names"), jsonString.get("place_id"));
    }

    @Override
    public Integer countUserNameIsHave(String username) {
        return userMapper.countUserNameIsHave(username);
    }

    @Override
    public HashMap UserDetailByLoginId(HashMap<String, String> jsonString) {
        return userMapper.UserDetailByLoginId(jsonString.get("id"), jsonString.get("login_id"));
    }

    @Override
    public Integer InsertUserDetail(HashMap<String, Object> jsonString) {
        return userMapper.InsertUserDetail(jsonString.get("place_id"), jsonString.get("pass_word"), jsonString.get("username"), jsonString.get("chinese_names"));
    }

    @Override
    public Integer UpdateUserDetail(HashMap<String, String> jsonString) {
        return userMapper.UpdateUserDetail(jsonString.get("id"), jsonString.get("place_id"), jsonString.get("password"), jsonString.get("username"), jsonString.get("chinese_names"));
    }

    @Override
    public Integer DeleteUserDetail(String id_all) {
        return userMapper.DeleteUserDetail(id_all);
    }

    @Override
    public Integer UserUpdatePassword(HashMap<String, String> jsonString) {
        return userMapper.UserUpdatePassword(jsonString.get("id"), jsonString.get("modify_by"), jsonString.get("pass_word"), jsonString.get("username"));
    }

}
