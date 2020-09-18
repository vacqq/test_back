package com.example.demo.service.impl;

import com.example.demo.mapper.OrderingManageMapper;
import com.example.demo.service.OrderingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Demo class
 *
 * @author lcz
 * @date 2016/10/31
 */
@Service
public class OrderingManageServiceImpl implements OrderingManageService {

    @Autowired
    OrderingManageMapper orderingManageMapper;


    @Override
    public int selectCountOrderingDateType(HashMap<String, String> jsonString) {
        return orderingManageMapper.selectCountOrderingDateType(jsonString.get("type"), jsonString.get("eat_time"), jsonString.get("user"));
    }

    @Override
    public List<HashMap> selectOrderingData(HashMap<String, String> jsonString) {
        return orderingManageMapper.selectOrderingData(jsonString.get("type"), jsonString.get("eat_time"), jsonString.get("user"));
    }

    @Override
    public HashMap selectOrderingById(HashMap<String, String> jsonString) {
        return orderingManageMapper.selectOrderingById(jsonString.get("id"));
    }

    @Override
    public Integer insertOrdering(HashMap<String, String> jsonString) {
        return orderingManageMapper.insertOrdering(jsonString.get("type"), jsonString.get("user"), jsonString.get("eat_time"), jsonString.get("create_by"), jsonString.get("remarks"));
    }

    @Override
    public Integer updateOrdering(HashMap<String, String> jsonString) {
        return orderingManageMapper.updateOrdering(jsonString.get("id"), jsonString.get("type"), jsonString.get("user"), jsonString.get("eat_time"), jsonString.get("modify_by"), jsonString.get("remarks"));
    }

    @Override
    public Integer deleteOrdering(HashMap<String, String> jsonString) {
        return orderingManageMapper.deleteOrdering(jsonString.get("id"));
    }

}