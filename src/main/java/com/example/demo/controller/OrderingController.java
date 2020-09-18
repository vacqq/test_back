package com.example.demo.controller;

import com.example.demo.service.OrderingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RestController
@RequestMapping("/api")
public class OrderingController {

    @Autowired
    private OrderingManageService orderingManageService;


    /**
     * @return {{@link HashMap}}
     * @description 获取规定日期不同用餐类型用餐人数信息
     * @author lcz
     * @date 2020/5/26 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/v1/SelectCountOrderingDateType", method = RequestMethod.POST)
    public HashMap SelectCountOrderingDateType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", "查询成功");
        add_map.put("result_data", orderingManageService.SelectCountOrderingDateType(jsonString));
        return add_map;
    }

    /**
     * @return {{@link HashMap}}
     * @description 获取订餐信息
     * @author lcz
     * @date 2020/5/26 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/v1/SelectOrdering", method = RequestMethod.POST)
    public HashMap SelectOrdering(@RequestBody HashMap<String, String> jsonString) throws Exception {
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", "查询成功");
        add_map.put("result_data", orderingManageService.SelectOrderingData(jsonString));
        return add_map;
    }

    /**
     * @return {{@link HashMap}}
     * @description 根据订餐id获取订餐信息
     * @author lcz
     * @date 2020/5/26 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/v1/SelectOrderingById", method = RequestMethod.POST)
    public HashMap SelectOrderingById(@RequestBody HashMap<String, String> jsonString) throws Exception {
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", "查询成功");
        add_map.put("result_data", orderingManageService.SelectOrderingById(jsonString));
        return add_map;
    }

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link HashMap} result_data 1为插入成功}
     * @description 插入订餐信息
     * @author lcz
     * @date 2020/5/26 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/v1/InsertOrdering", method = RequestMethod.POST)
    public HashMap InsertOrdering(@RequestBody HashMap<String, String> jsonString) throws Exception {

        String result_data = "1";
        String result_msg = "插入成功";
        String type_all = jsonString.get("type_all");
        String substring = type_all.substring(0, type_all.length() - 1);
        System.out.println(substring);
        String[] split = substring.split(",");//以逗号分割
        for (String type : split) {
            jsonString.put("type", type);
            //对是否重复插入做出判断
            int num = orderingManageService.SelectCountOrderingDateType(jsonString);
            if (num == 0) {
                orderingManageService.InsertOrdering(jsonString);
            }
            else {
                //对重复数据不做任何处理
            }
            jsonString.remove("type");
            System.out.println("数据-->>>" + type);
        }

        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link HashMap} result_data 1为插入成功}
     * @description 更新订餐信息
     * @author lcz
     * @date 2020/5/26 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/v1/UpdateOrdering", method = RequestMethod.POST)
    public HashMap UpdateOrdering(@RequestBody HashMap<String, String> jsonString) throws Exception {
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", "更新成功");
        add_map.put("result_data", orderingManageService.UpdateOrdering(jsonString));
        return add_map;
    }

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link HashMap} result_data 1为删除成功}
     * @description 删除订餐信息
     * @author lcz
     * @date 2020/5/26 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/v1/DeleteOrdering", method = RequestMethod.POST)
    public HashMap DeleteOrdering(@RequestBody HashMap<String, String> jsonString) throws Exception {
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", "删除成功");
        add_map.put("result_data", orderingManageService.DeleteOrdering(jsonString));
        return add_map;
    }


}