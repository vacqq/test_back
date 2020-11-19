package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public interface AnalysisSiteRankService {
    /**
     * 查询排名table信息
     *
     * @param jsonString 传入参数集合
     * @return List<HashMap>
     */
    List<HashMap> select(HashMap<String, String> jsonString);

    /**
     * 查询排名table信息
     *
     * @param jsonString 传入参数集合
     * @return List<HashMap>
     */
    List<HashMap> selectData(HashMap<String, String> jsonString);
}
