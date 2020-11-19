package com.example.demo.service.impl;

import com.example.demo.mapper.AnalysisSiteRankMapper;
import com.example.demo.service.AnalysisSiteRankService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public class AnalysisSiteRankServiceImpl implements AnalysisSiteRankService {
    final
    AnalysisSiteRankMapper analysisSiteRankMapper;

    public AnalysisSiteRankServiceImpl(AnalysisSiteRankMapper analysisSiteRankMapper) {
        this.analysisSiteRankMapper = analysisSiteRankMapper;
    }

    @Override
    public List<HashMap> select(HashMap<String, String> jsonString) {
        return analysisSiteRankMapper.select(jsonString.get("type"), jsonString.get("order_type"), jsonString.get("site_type"));
    }

    @Override
    public List<HashMap> selectData(HashMap<String, String> jsonString) {
        return analysisSiteRankMapper.selectData(jsonString.get("type"), jsonString.get("order_type"), jsonString.get("site_type"), jsonString.get("decimal_num"), jsonString.get("start_time"), jsonString.get("end_time"), jsonString.get("place_id"));
    }
}
