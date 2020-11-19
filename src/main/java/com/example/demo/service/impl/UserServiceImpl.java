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
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public HashMap getUserByUserNameMap(String username) {
        return userMapper.getUserByUserNameMap(username);
    }

    @Override
    public HashMap selectUserNumByPassword(HashMap<String, String> jsonString) {
        return userMapper.selectUserNumByPassword(jsonString.get("user_name"), jsonString.get("password"));
    }

    @Override
    public List<HashMap> selectUserDetailList(HashMap<String, String> jsonString) {
        return userMapper.selectUserDetailList(jsonString.get("username"), jsonString.get("chinese_names"), jsonString.get("place_id"));
    }

    @Override
    public Integer countUserNameIsHave(String username) {
        return userMapper.countUserNameIsHave(username);
    }

    @Override
    public HashMap userDetailByLoginId(HashMap<String, String> jsonString) {
        return userMapper.userDetailByLoginId(jsonString.get("id"), jsonString.get("login_id"));
    }

    @Override
    public Integer insertUserDetail(HashMap<String, Object> jsonString) {
        return userMapper.insertUserDetail(jsonString.get("place_id"), jsonString.get("pass_word"), jsonString.get("username"), jsonString.get("chinese_names"));
    }

    @Override
    public Integer updateUserDetail(HashMap<String, String> jsonString) {
        return userMapper.updateUserDetail(jsonString.get("id"), jsonString.get("place_id"), jsonString.get("password"), jsonString.get("username"), jsonString.get("chinese_names"));
    }

    @Override
    public Integer deleteUserDetail(String idAll) {
        return userMapper.deleteUserDetail(idAll);
    }

    @Override
    public Integer userUpdatePassword(HashMap<String, String> jsonString) {
        return userMapper.userUpdatePassword(jsonString.get("id"), jsonString.get("modify_by"), jsonString.get("pass_word"), jsonString.get("username"));
    }

}
