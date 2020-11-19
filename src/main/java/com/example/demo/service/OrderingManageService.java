package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz
 */
public interface OrderingManageService {
    /**
     * 查询订餐日期类型
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    int selectCountOrderingDateType(HashMap<String, String> jsonString);

    /**
     * fetch data by rule id
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectOrderingData(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    HashMap selectOrderingById(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    Integer insertOrdering(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    Integer updateOrdering(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    Integer deleteOrdering(HashMap<String, String> jsonString);
}
