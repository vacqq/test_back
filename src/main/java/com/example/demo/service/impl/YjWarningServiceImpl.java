package com.example.demo.service.impl;

import com.example.demo.mapper.YjWarningMapper;
import com.example.demo.service.YjWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz
 * @description
 * @date 2020/7/28
 */
@Service
public class YjWarningServiceImpl implements YjWarningService {
    @Autowired
    private YjWarningMapper yjWarningMapper;

    public List<HashMap> getWarningData(HashMap<String, String> jsonString) {
        return yjWarningMapper.getWarningData(jsonString.get("start_time"), jsonString.get("end_time"), jsonString.get("data_type"), jsonString.get("site_name"), jsonString.get("site_type"));
    }

    @Override
    public Integer InsertWarningData(HashMap<String, Object> jsonString) {
        return yjWarningMapper.InsertWarningData(jsonString.get("data_id"), jsonString.get("site_id"), jsonString.get("standard_id"), jsonString.get("remarks"), jsonString.get("data_type"), jsonString.get("warning_time"), jsonString.get("air_data"), jsonString.get("create_by"));
    }

    @Override
    public List<HashMap> SelectRuleTableList() {
        return yjWarningMapper.SelectRuleTableList();
    }

    @Override
    public Integer UpdateRuleWord(HashMap<String, String> jsonString) {
        return yjWarningMapper.UpdateRuleWord(jsonString.get("rule_id"));
    }

}
