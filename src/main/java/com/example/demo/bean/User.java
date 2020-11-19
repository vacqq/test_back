package com.example.demo.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lcz
 * @description 用户管理,有data注释,取消get,set方法
 * @create 2020/5/27
 */
@Data
public class User {
    /**
     * 主键ID
     */
    private int id;
    private String username;
    private String chineseNames;
    private String userType;
    private String password;
    private String passwordSalt;
    private int placeId;
    private String createBy;
    private Timestamp createAt;
    private Timestamp modifyAt;
    private String modifyBy;
    private Byte isDel;

}
