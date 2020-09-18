package com.example.demo.service.impl;

import com.example.demo.mapper.AnalysSiteRankMapper;
import com.example.demo.service.AnalysSiteRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AnalysSiteRankServiceImpl implements AnalysSiteRankService {
    @Autowired
    AnalysSiteRankMapper analysSiteRankMapper;

    public List<HashMap> Select(HashMap<String, String> jsonString) {
        return analysSiteRankMapper.Select(jsonString.get("type"), jsonString.get("order_type"), jsonString.get("site_type"));
    }

    public List<HashMap> SelectData(HashMap<String, String> jsonString) {
        return analysSiteRankMapper.SelectData(jsonString.get("type"), jsonString.get("order_type"), jsonString.get("site_type"), jsonString.get("decimal_num"), jsonString.get("start_time"), jsonString.get("end_time"), jsonString.get("place_id"));
    }

}
