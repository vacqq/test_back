package com.example.demo.baseBeanUtIls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BackFormatUtils {
    //需要static静态否则调用不到.
    public static HashMap Builder(Object result, Object message, Object code, Object headers) {
        HashMap<String, Object> builder = new HashMap<String, Object>(10);
        builder.put("result", result);
        builder.put("message", message);
        builder.put("code", code);
        builder.put("_status", code);
        builder.put("_headers", headers);
        //获取当前时间戳
        builder.put("timestamp", System.currentTimeMillis());
        return builder;
    }

    //获取用户js信息
    public static HashMap getUserDetail(String message, String chinese_names, String place_id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> builder = new HashMap<String, Object>();
        HashMap<String, Object> Role = new HashMap<String, Object>();
        //插入用户信息
        builder.put("id", "4291d7da9005377ec9aec4a71ea837f");
        builder.put("name", chinese_names);
        builder.put("username", message);
        builder.put("password", "");
        builder.put("place_id", place_id);
        builder.put("avatar", "/avatar2.jpg");
        builder.put("status", 1);
        builder.put("telephone", "");
        builder.put("lastLoginIp", "27.154.74.117");
        builder.put("lastLoginTime", "1534837621348");
        builder.put("creatorId", "admin");
        builder.put("createTime", "1497160610259");
        builder.put("merchantCode", "TLif2btpzg079h15bk");
        builder.put("deleted", 0);
        builder.put("roleId", "admin");
        //插入权限信息
        Role.put("name", "管理员");
        Role.put("describe", "拥有所有权限");
        Role.put("status", 1);
        Role.put("creatorId", "system");
        Role.put("createTime", "1497160610259");
        Role.put("deleted", 0);

        //存放权限字典集合
        List<HashMap> permissions = new ArrayList<HashMap>();

        permissions.add(permissions("dashboard", "仪表盘"));
        permissions.add(permissions("exception", "异常页面"));
        permissions.add(permissions("result", "结果权限"));
        permissions.add(permissions("profile", "详细页权限"));
        permissions.add(permissions("table", "表格权限"));
        permissions.add(permissions("form", "表单权限"));
        permissions.add(permissions("order", "订单管理"));
        permissions.add(permissions("permission", "权限管理"));
        permissions.add(permissions("role", "角色管理"));
        permissions.add(permissions("table", "桌子管理"));
        permissions.add(permissions("user", "用户管理"));
        permissions.add(permissions("support", "超级模块"));

        if (message.equals("超级管理员") || message.equals("admin")) {
            permissions.add(permissions("SiteManage", "站点管理"));
        }

        Role.put("permissions", permissions);
        builder.put("role", Role);
        //返回最终数据
        result.put("result", builder);
        result.put("message", "");
        result.put("code", 0);
        result.put("timestamp", System.currentTimeMillis());//获取当前时间戳
        return result;
    }

    public static HashMap permissions(String permissionId, String permissionName) {
        //统一类似信息配置
        String actions = "[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]";
        //actionList集合添加信息
        List<HashMap> actionEntitySet = new ArrayList<HashMap>();

        actionEntitySet.add(actionEntitySet_detail("add", "新增"));
        actionEntitySet.add(actionEntitySet_detail("query", "查询"));
        actionEntitySet.add(actionEntitySet_detail("get", "详情"));
        actionEntitySet.add(actionEntitySet_detail("update", "修改"));
        actionEntitySet.add(actionEntitySet_detail("delete", "删除"));
        //模块
        HashMap<String, Object> permissions = new HashMap<String, Object>();
        permissions.put("roleId", "admin");
        permissions.put("permissionId", permissionId);
        permissions.put("permissionName", permissionName);
        permissions.put("actions", actions);
        permissions.put("actionEntitySet", actionEntitySet);
        permissions.put("actionList", null);
        permissions.put("dataAccess", null);
        return permissions;
    }

    public static HashMap actionEntitySet_detail(String action, String describe) {
        HashMap<String, Object> actionEntitySet_detail = new HashMap<String, Object>();
        actionEntitySet_detail.put("action", action);
        actionEntitySet_detail.put("describe", describe);
        actionEntitySet_detail.put("defaultCheck", false);
        return actionEntitySet_detail;
    }

    //获取菜单js信息
    public static HashMap getNavDetail(Object result, Object message, Object code, Object headers) {
        HashMap<String, Object> builder = new HashMap<String, Object>();
        builder.put("result", result);
        builder.put("message", message);
        builder.put("code", code);
        builder.put("_status", code);
        builder.put("_headers", headers);
        //获取当前时间戳
        builder.put("timestamp", System.currentTimeMillis());
        return builder;
    }
}
