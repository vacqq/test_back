package com.example.demo.controller;

import com.example.demo.service.YjWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz
 * @description 预警处理
 * @date 2020/7/9
 */
@Controller
@RestController
public class YjWarningController {

    @Autowired
    private YjWarningService yjWarningService;

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 告警管理, 信息查询
     * @author lcz
     * @date 2020/7/9 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/getWarningData", method = RequestMethod.POST)
    public List<HashMap> warningData(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return yjWarningService.getWarningData(jsonString);
    }

    /**
     * @param
     * @return {{@link List < HashMap >}}
     * @description 查询
     * @author lcz
     * @date 2020/7/9 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectRuleTableList", method = RequestMethod.POST)
    public List<HashMap> SelectRuleTableList() throws Exception {
        return yjWarningService.SelectRuleTableList();
    }


    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 更新规则配置信息
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/UpdateRuleWord", method = RequestMethod.POST)
    public HashMap UpdateRuleWord(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer result_data = 0;
        String result_msg = "更新失败";
        result_data = yjWarningService.UpdateRuleWord(jsonString);
        if (result_data != 0) {
            result_msg = "更新成功";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }


}
