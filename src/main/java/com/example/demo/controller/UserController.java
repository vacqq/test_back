package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param jsonString 用户名和密码
     * @return {{@link HashMap}}
     * @description 用户登录校验, 账号密码校验
     * @author lcz
     * @date 2020/7/22 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/UserLoginCheck", method = RequestMethod.POST)
    public HashMap UserLoginCheck(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return userService.SelectUserNumByPassword(jsonString);
    }

    /**
     * @param jsonString
     * @return {{@link HashMap}}
     * @description 根据用户id和用户登录id获取用户相关信息
     * @author lcz
     * @date 2020/7/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/UserDetailByLoginId", method = RequestMethod.POST)
    public HashMap UserDetailByLoginId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return userService.UserDetailByLoginId(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List <HashMap>}}
     * @description 根据不同类型查询站点表, 对站点就行管理
     * @author lcz
     * @date 2020/7/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectUserDetailList", method = RequestMethod.POST)
    public List<HashMap> SelectUserDetailList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return userService.SelectUserDetailList(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 插入数据信息
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/InsertUserDetail", method = RequestMethod.POST)
    public HashMap InsertUserDetail(@RequestBody HashMap<String, Object> jsonString) throws Exception {

        String result_data = "0";
        String result_msg = "插入失败";
        //查看此用户数据库中是否有
        if (userService.countUserNameIsHave(jsonString.get("username").toString()) == 0) {
            /*************************JM statr*****************************/
            String pwYz = "HBHQKJHJJCGFJTYXGS";
            String salt = pwYz + jsonString.get("password").toString() + pwYz;
            String hashAlgorithmName = "MD5";//JMFS
            int hashIterations = 1024;//SL1024次
            Object result = new SimpleHash(hashAlgorithmName, pwYz, salt, hashIterations);
            /*************************JM end*****************************/
            jsonString.put("pass_word", result.toString());
            userService.InsertUserDetail(jsonString);
            result_data = "1";
            result_msg = "插入成功";
        } else {
            result_msg = "此id已被占用请更换用户id";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 更新数据信息
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/UpdateUserDetail", method = RequestMethod.POST)
    public HashMap UpdateUserDetail(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer result_data = 0;
        String result_msg = "更新失败";
        result_data = userService.UpdateUserDetail(jsonString);
        if (result_data != 0) {
            result_msg = "更新成功";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }


    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 删除数据
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/DeleteUserDetail", method = RequestMethod.POST)
    public HashMap DeleteUserDetail(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer result_data = 0;
        String result_msg = "删除失败";
        result_data = userService.DeleteUserDetail(jsonString.get("id_all"));
        if (result_data != 0) {
            result_msg = "删除成功";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 用户修改密码
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/UserUpdatePassword", method = RequestMethod.POST)
    public HashMap UserUpdatePassword(@RequestBody HashMap<String, String> jsonString) throws Exception {
        /*************************JM statr*****************************/
        String pwYz = "HBHQKJHJJCGFJTYXGS";
        String salt = pwYz + jsonString.get("password") + pwYz;
        String hashAlgorithmName = "MD5";//JMFS
        int hashIterations = 1024;//SL1024次
        Object result = new SimpleHash(hashAlgorithmName, pwYz, salt, hashIterations);
        /*************************JM end*****************************/
        jsonString.put("pass_word", result.toString());
        Integer result_data = 0;
        String result_msg = "修改失败";
        result_data = userService.UserUpdatePassword(jsonString);
        if (result_data != 0) {
            result_msg = "修改成功";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }


}
