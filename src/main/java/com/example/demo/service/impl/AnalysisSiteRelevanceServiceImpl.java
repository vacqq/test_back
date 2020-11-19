package com.example.demo.service.impl;

import com.example.demo.mapper.AnalysisSiteRelevanceMapper;
import com.example.demo.service.AnalysisSiteRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
@Service
public class AnalysisSiteRelevanceServiceImpl implements AnalysisSiteRelevanceService {
    final
    AnalysisSiteRelevanceMapper analysisSiteRelevanceMapper;

    public AnalysisSiteRelevanceServiceImpl(AnalysisSiteRelevanceMapper analysisSiteRelevanceMapper) {
        this.analysisSiteRelevanceMapper = analysisSiteRelevanceMapper;
    }

    @Override
    public List<HashMap> selectData(HashMap<String, String> jsonString, String dateTime) {
        return analysisSiteRelevanceMapper.selectData(jsonString.get("type"), jsonString.get("site_type"), jsonString.get("decimal_num"), dateTime, jsonString.get("site_id"));
    }

    @Override
    public List<HashMap> selectDataSiteRange(HashMap<String, String> jsonString, String dateTime) {
        return analysisSiteRelevanceMapper.selectDataSiteRange(jsonString.get("type"), jsonString.get("site_type"), jsonString.get("decimal_num"), dateTime, jsonString.get("site_id_range"));
    }

    @Override
    public List<HashMap> selectDateTime(HashMap<String, String> jsonString) {
        return analysisSiteRelevanceMapper.selectDateTime(jsonString.get("start_time"), jsonString.get("end_time"));
    }
}
