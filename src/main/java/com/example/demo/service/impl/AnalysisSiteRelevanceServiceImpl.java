package com.example.demo.service.impl;

import com.example.demo.mapper.AnalysisSiteRelevanceMapper;
import com.example.demo.service.AnalysisSiteRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AnalysisSiteRelevanceServiceImpl implements AnalysisSiteRelevanceService {
    @Autowired
    AnalysisSiteRelevanceMapper analysisSiteRelevanceMapper;

    public List<HashMap> SelectData(HashMap<String, String> jsonString, String date_time) {
        return analysisSiteRelevanceMapper.SelectData(jsonString.get("type"), jsonString.get("site_type"), jsonString.get("decimal_num"), date_time, jsonString.get("site_id"));
    }

    public List<HashMap> SelectDataSiteRange(HashMap<String, String> jsonString, String date_time) {
        return analysisSiteRelevanceMapper.SelectDataSiteRange(jsonString.get("type"), jsonString.get("site_type"), jsonString.get("decimal_num"), date_time, jsonString.get("site_id_range"));
    }

    public List<HashMap> SelectDateTime(HashMap<String, String> jsonString) {
        return analysisSiteRelevanceMapper.SelectDateTime(jsonString.get("start_time"), jsonString.get("end_time"));
    }
}
