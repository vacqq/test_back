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
     * @param jsonString rule id
     * @return List<HashMap>
     */
    public int selectCountOrderingDateType(HashMap<String, String> jsonString);

    /**
     * fetch data by rule id
     *
     * @param jsonString rule id
     * @return List<HashMap>
     */
    public List<HashMap> selectOrderingData(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString rule id
     * @return List<HashMap>
     */
    public HashMap selectOrderingById(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString rule id
     * @return List<HashMap>
     */
    public Integer insertOrdering(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString rule id
     * @return List<HashMap>
     */
    public Integer updateOrdering(HashMap<String, String> jsonString);

    /**
     * 查询订餐日期
     *
     * @param jsonString rule id
     * @return List<HashMap>
     */
    public Integer deleteOrdering(HashMap<String, String> jsonString);
}
