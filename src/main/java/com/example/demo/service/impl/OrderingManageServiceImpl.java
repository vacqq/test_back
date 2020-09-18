package com.example.demo.service.impl;

import com.example.demo.mapper.OrderingManageMapper;
import com.example.demo.service.OrderingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderingManageServiceImpl implements OrderingManageService {
    @Autowired
    OrderingManageMapper orderingManageMapper;


    @Override
    public int SelectCountOrderingDateType(HashMap<String, String> jsonString) {
        return orderingManageMapper.SelectCountOrderingDateType(jsonString.get("type"), jsonString.get("eat_time"), jsonString.get("user"));
    }

    public List<HashMap> SelectOrderingData(HashMap<String, String> jsonString) {
        return orderingManageMapper.SelectOrderingData(jsonString.get("type"), jsonString.get("eat_time"), jsonString.get("user"));
    }

    public HashMap SelectOrderingById(HashMap<String, String> jsonString) {
        return orderingManageMapper.SelectOrderingById(jsonString.get("id"));
    }

    public Integer InsertOrdering(HashMap<String, String> jsonString) {
        return orderingManageMapper.InsertOrdering(jsonString.get("type"), jsonString.get("user"), jsonString.get("eat_time"), jsonString.get("create_by"), jsonString.get("remarks"));
    }

    public Integer UpdateOrdering(HashMap<String, String> jsonString) {
        return orderingManageMapper.UpdateOrdering(jsonString.get("id"), jsonString.get("type"), jsonString.get("user"), jsonString.get("eat_time"), jsonString.get("modify_by"), jsonString.get("remarks"));
    }

    public Integer DeleteOrdering(HashMap<String, String> jsonString) {
        return orderingManageMapper.DeleteOrdering(jsonString.get("id"));
    }

}