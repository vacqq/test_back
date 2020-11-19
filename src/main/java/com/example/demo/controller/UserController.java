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
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
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
    public HashMap userLoginCheck(@RequestBody HashMap<String, String> jsonString) throws Exception {
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
    public HashMap userDetailByLoginId(@RequestBody HashMap<String, String> jsonString) throws Exception {
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
    public List<HashMap> selectUserDetailList(@RequestBody HashMap<String, String> jsonString) throws Exception {
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
    public HashMap insertUserDetail(@RequestBody HashMap<String, Object> jsonString) throws Exception {

        String resultData = "0";
        String resultMsg = "插入失败";
        //查看此用户数据库中是否有
        String userName = jsonString.get("username").toString();
        if (userService.countUserNameIsHave(userName) == 0) {
            /*************************JM statr*****************************/
            String pwYz = "HBHQKJHJJCGFJTYXGS";
            String salt = pwYz + jsonString.get("password").toString() + pwYz;
            //JMFS
            String hashAlgorithmName = "MD5";
            //SL1024次
            int hashIterations = 1024;
            Object result = new SimpleHash(hashAlgorithmName, pwYz, salt, hashIterations);
            /*************************JM end*****************************/
            jsonString.put("pass_word", result.toString());
            userService.InsertUserDetail(jsonString);
            resultData = "1";
            resultMsg = "插入成功";
        } else {
            resultMsg = "此id已被占用请更换用户id";
        }
        HashMap<String, Object> addMap = new HashMap<String, Object>(10);
        addMap.put("result_status", true);
        addMap.put("result_msg", resultMsg);
        addMap.put("result_data", resultData);
        return addMap;
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
    public HashMap updateUserDetail(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer resultData = 0;
        String resultMsg = "更新失败";
        resultData = userService.UpdateUserDetail(jsonString);
        if (resultData != 0) {
            resultMsg = "更新成功";
        }
        HashMap<String, Object> addMap = new HashMap<String, Object>(10);
        addMap.put("result_status", true);
        addMap.put("result_msg", resultMsg);
        addMap.put("result_data", resultData);
        return addMap;
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
    public HashMap deleteUserDetail(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer resultData = 0;
        String resultMsg = "删除失败";
        resultData = userService.DeleteUserDetail(jsonString.get("id_all"));
        if (resultData != 0) {
            resultMsg = "删除成功";
        }
        HashMap<String, Object> addMap = new HashMap<String, Object>(10);
        addMap.put("result_status", true);
        addMap.put("result_msg", resultMsg);
        addMap.put("result_data", resultData);
        return addMap;
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
    public HashMap userUpdatePassword(@RequestBody HashMap<String, String> jsonString) throws Exception {
        /*************************JM statr*****************************/
        String pwYz = "HBHQKJHJJCGFJTYXGS";
        String salt = pwYz + jsonString.get("password") + pwYz;
        // JMFS
        String hashAlgorithmName = "MD5";
        //SL1024次
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, pwYz, salt, hashIterations);
        /*************************JM end*****************************/
        jsonString.put("pass_word", result.toString());
        Integer resultData = 0;
        String resultMsg = "修改失败";
        resultData = userService.UserUpdatePassword(jsonString);
        if (resultData != 0) {
            resultMsg = "修改成功";
        }
        HashMap<String, Object> addMap = new HashMap<String, Object>(10);
        addMap.put("result_status", true);
        addMap.put("result_msg", resultMsg);
        addMap.put("result_data", resultData);
        return addMap;
    }


}
