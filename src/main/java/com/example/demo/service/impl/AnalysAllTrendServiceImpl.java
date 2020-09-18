package com.example.demo.service.impl;


import com.example.demo.mapper.AnalysAllTrendMapper;
import com.example.demo.service.AnalysAllTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AnalysAllTrendServiceImpl implements AnalysAllTrendService {
    @Autowired
    AnalysAllTrendMapper analysAllTrendMapper;

    public List<HashMap> Select(HashMap<String, String> jsonString, int site_id) {
        return analysAllTrendMapper.Select(jsonString.get("type"), jsonString.get("weather_type"), jsonString.get("site_type"), site_id, jsonString.get("start_time"), jsonString.get("end_time"), jsonString.get("place_id"));
    }

}
