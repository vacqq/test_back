package com.example.demo.service.impl;


import com.example.demo.mapper.AnalysisAllTrendMapper;
import com.example.demo.service.AnalysisAllTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public class AnalysisAllTrendServiceImpl implements AnalysisAllTrendService {
    final
    AnalysisAllTrendMapper analysisAllTrendMapper;

    public AnalysisAllTrendServiceImpl(AnalysisAllTrendMapper analysisAllTrendMapper) {
        this.analysisAllTrendMapper = analysisAllTrendMapper;
    }

    @Override
    public List<HashMap> select(HashMap<String, String> jsonString, int siteId) {
        return analysisAllTrendMapper.select(jsonString.get("type"), jsonString.get("weather_type"), jsonString.get("site_type"), siteId, jsonString.get("start_time"), jsonString.get("end_time"), jsonString.get("place_id"));
    }

}
