package com.example.demo.controller;

import com.example.demo.bean.SysSiteEntity;
import com.example.demo.service.AnalysisSiteRankService;
import com.example.demo.service.AnalysisAllTrendService;
import com.example.demo.service.AnalysisSiteRelevanceService;
import com.example.demo.service.SelectSiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RestController
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public class DataAnalysisController {
    private final AnalysisSiteRankService analysisSiteRankService;
    private final AnalysisAllTrendService analysisAllTrendService;
    private final SelectSiteService selectSiteService;
    private final AnalysisSiteRelevanceService analysisSiteRelevanceService;

    public DataAnalysisController(AnalysisSiteRankService analysisSiteRankService, AnalysisAllTrendService analysisAllTrendService, SelectSiteService selectSiteService, AnalysisSiteRelevanceService analysisSiteRelevanceService) {
        this.analysisSiteRankService = analysisSiteRankService;
        this.analysisAllTrendService = analysisAllTrendService;
        this.selectSiteService = selectSiteService;
        this.analysisSiteRelevanceService = analysisSiteRelevanceService;
    }


    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 站点排名分析, 排名表格显示
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/AnalysisSiteRank", method = RequestMethod.POST)
    public List<HashMap> analysisSiteRank(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return analysisSiteRankService.selectData(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 综合趋势分析
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/AnalysisAllTrend", method = RequestMethod.POST)
    public List<HashMap> analysisAllTrend(@RequestBody HashMap<String, String> jsonString) throws Exception {
        //返回数据类型
        //存放最终返回数据的list集合
        List<HashMap> arrays = new ArrayList<HashMap>();

        //查询site_id(1,2,3)中的站点名称和id值,为下一步遍历查询相关时间和数值做准备
        List<SysSiteEntity> sysSiteEntities = selectSiteService.selectNameInId(jsonString.get("site_id"));
        //遍历返回的List<SysSiteEntity> ,list 集合,根据每一个id做具体查询.,SysSiteEntity为实体类
        for (SysSiteEntity strList : sysSiteEntities) {
            //先将map清空
            //存放站点名称以及相关时间数据的map
            HashMap<String, Object> siteName = new HashMap<String, Object>(10);
            System.out.println(siteName);
            //将站点名称存放在map中
            siteName.put("site_name", strList.getName());
            //打印输出,用于测试
            //将相关时间数据存放在map中
            siteName.put("time_data", analysisAllTrendService.select(jsonString, strList.getId()));
            //将一个完整map存放在list中
            arrays.add(siteName);
        }
        return arrays;
    }


    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 站点相关分析根据id值查询
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/AnalysisSiteRelevance", method = RequestMethod.POST)
    public List<HashMap> analysisSiteRelevanceId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        //存放最终返回数据的list集合
        List<HashMap> arrays = new ArrayList<HashMap>();
        //查询
        List<HashMap> dateTime = analysisSiteRelevanceService.selectDateTime(jsonString);
        //遍历返回的List<HashMap> ,list 集合,根据每一个时间段做具体查询.
        for (Object listNum : dateTime) {
            //先将map清空
            //存放站点名称以及相关时间数据的map
            HashMap<String, Object> baseMap = new HashMap<String, Object>(10);
            baseMap = (HashMap<String, Object>) listNum;
            //取出时间值,为下一步搜索做准备
            for (String key : baseMap.keySet()) {
                baseMap.put("standard_data", analysisSiteRelevanceService.selectData(jsonString, baseMap.get(key).toString() + "-01"));
                baseMap.put("select_data", analysisSiteRelevanceService.selectDataSiteRange(jsonString, baseMap.get(key).toString() + "-01"));
            }
            //将相关时间数据存放在map中
            //将一个完整map存放在list中
            arrays.add(baseMap);
        }
        return arrays;
    }





}