package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public interface AnalysisAllTrendService {
    /**
     * 根据地区id查询数据, 只根据站点id查询即可
     * @param siteId 站点id信息
     * @param jsonString 传入参数集合
     * @return List<HashMap>
     */
     List<HashMap> select(HashMap<String, String> jsonString, int siteId);
}