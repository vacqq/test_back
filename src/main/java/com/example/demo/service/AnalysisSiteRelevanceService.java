package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public interface AnalysisSiteRelevanceService {

    /**
     * 查询数据
     * @param dateTime 站点id信息
     * @param jsonString 传入参数集合
     * @return List<HashMap>
     */
     List<HashMap> selectData(HashMap<String, String> jsonString, String dateTime);

    /**
     * 查询排名table信息
     * @param dateTime 根据时间查询
     * @param jsonString 传入参数集合
     * @return List<HashMap>
     */
     List<HashMap> selectDataSiteRange(HashMap<String, String> jsonString, String dateTime);

    /**
     * 查询搜索时间段内的时间点值
     * @param jsonString 传入参数集合
     * @return List<HashMap>
     */
     List<HashMap> selectDateTime(HashMap<String, String> jsonString);
}
