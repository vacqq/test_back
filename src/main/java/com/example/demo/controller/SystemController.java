package com.example.demo.controller;

import com.example.demo.baseBeanUtIls.BackFormatUtils;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统管理
 *
 * @author lcz 2020/6/4 14:53
 */
@Controller
@RequestMapping("/systemCon")
public class SystemController {

    @Autowired
    private UserService userService;

    /**
     * 登陆校验
     *
     * @param
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/loginForm", method = RequestMethod.POST)
    public HashMap loginForm(@RequestBody User useInput) {
        Map<String, Object> map = new HashMap<String, Object>(10);
        System.out.println(useInput.getUsername() + useInput.getPassword());
        if (null != useInput.getUsername() && !"".equals(useInput.getUsername())) {
            User user = userService.getUserByUserName(useInput.getUsername());
            if (null != user) {
                String userPassword = user.getPassword();
                //获取盐值
                String pwSalt = "HBHQKJHJJCGFJTYXGS";
                String str = pwSalt + useInput.getPassword() + pwSalt;
                String hashAlgorithmName = "MD5";
                int hashIterations = 1024;
                Object result = new SimpleHash(hashAlgorithmName, pwSalt, str, hashIterations);

                //比对数据库hash值和散列后的hash值是否相等 如果相等则登录成功不过不相等则登陆失败
                if (userPassword.equals(result.toString())) {
                    //校验成功获取用户信息+权限信息并返回
                    String token = "token";
                    map.put("code", 200);
                    map.put("message", "success");
                    map.put("data", user);
                    map.put("token", token);
                } else {
                    map.put("code", 1);
                    map.put("message", "账户或密码错误");
                    map.put("data", "no");
                }
            } else {
                map.put("code", 401);
                map.put("message", "账户或密码错误");
                map.put("data", "no");
            }
        } else {
            map.put("code", 1);
            map.put("message", "账户或密码错误");
            map.put("data", "no");
        }
        return BackFormatUtils.Builder(map.get("data"), map.get("message"), map.get("code"), map.get("token"));
    }


    /**
     * 登录成功后获取用户信息(临时)
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public HashMap getUserDetail(@RequestBody HashMap<String, String> jsonString) {
        HashMap<String, Object> user = userService.getUserByUserNameMap(jsonString.get("user_name"));
        String chineseNames = user.get("chinese_names").toString();
        String placeId = user.get("place_id").toString();
        return BackFormatUtils.getUserDetail(jsonString.get("user_name"), chineseNames, placeId);
    }


}
