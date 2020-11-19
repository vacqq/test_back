package com.example.demo.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lcz
 * @description 站点管理
 * @create 2020/5/27
 */
@Data
public class SysSiteEntity {
    /**
     * 主键ID
     */
    private int id;
    private String name;
    private String onlyName;
    private String siteType;
    private int placeId;
    private String position;
    private int lat;
    private int lng;
    private int sort;
    private String createBy;
    private Timestamp createAt;
    private Timestamp modifyAt;
    private String modifyBy;
    private Byte isDel;

}
