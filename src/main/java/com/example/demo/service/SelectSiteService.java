package com.example.demo.service;


import com.example.demo.bean.SysSiteEntity;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public interface SelectSiteService {

    /**
     * 查询站点名称通过站点id
     *
     * @param id id
     * @return String
     */
    String selectNameById(String id);

    /**
     * 查询站点信息通过站点id
     *
     * @param id id
     * @return HashMap
     */
    HashMap selectSiteDataById(String id);

    /**
     * 根据id查询地区名
     *
     * @param id id
     * @return String
     */
    String selectPlaceNameById(String id);

    /**
     * 通过站点结合城市名称做出选择, only_name
     *
     * @param name 唯一的城市名称
     * @return String
     */
    String selectIdByName(String name);

    /**
     * 根据站点范围id段,查询名称
     *
     * @param idRange 站点范围id
     * @return List<SysSiteEntity>
     */
    List<SysSiteEntity> selectNameInId(String idRange);

    /**
     * 根据站点类型和所在地区查询站点信息
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectSiteIdNameBySiteType(HashMap<String, String> jsonString);

    /**
     * 根据站点类型id和所在地区查询站点信息
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectSiteIdNameBySiteTypeId(HashMap<String, String> jsonString);

    /**
     * 查询热力图数据
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectHeatMapDataList(HashMap<String, String> jsonString);

    /**
     * 查询字典类型
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectDictionariesByType(HashMap<String, String> jsonString);

    /**
     * 根据地区类型查询地区id"县区,市,省"
     *
     * @param type 类型
     * @return List<HashMap>
     */
    List<HashMap> selectPlaceIdByType(String type);

    /**
     * 根据地区类型查询地区id"县区,市,省"
     *
     * @param placeId 地区id
     * @return List<SysSiteEntity>
     */
    List<SysSiteEntity> getPlaceTreeList(String placeId);

    /**
     * 快速查询站点名称集合之1, 查询城市
     *
     * @param placeId 地区id
     * @return List<HashMap>
     */
    List<HashMap> getPlaceTreeListQuick(String placeId);

    /**
     * 快速查询站点名称集合之2, 查询县区
     *
     * @param placeId 地区id
     * @return List<HashMap>
     */
    List<HashMap> getPlaceTreeListInParentId(String placeId);

    /**
     * 据不同类型查询站点表, 对站点就行管理
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> selectSiteList(HashMap<String, String> jsonString);

    /**
     * 查询站点表某站点某时间数据
     *
     * @param jsonString 前台传值的json
     * @return List<HashMap>
     */
    List<HashMap> getDateBySiteId(HashMap<String, String> jsonString);

    /**
     * 获取当前设备最新的一条数据
     *
     * @param jsonString 前台传值的json
     * @return HashMap
     */
    HashMap getLastSiteDataBySiteId(HashMap<String, String> jsonString);

    /**
     * 查看此站点数据库中是否有
     *
     * @param name 站点名称
     * @return Integer
     */
    Integer countSiteNameIsHave(String name);

    /**
     * 插入数据信息
     *
     * @param jsonString 前台传值的json
     * @return Integer
     */
    Integer insertSiteData(HashMap<String, Object> jsonString);

    /**
     * 更新数据信息
     *
     * @param jsonString 前台传值的json
     * @return Integer
     */
    Integer updateSiteData(HashMap<String, String> jsonString);

    /**
     * 删除数据信息
     *
     * @param idAll id字段
     * @return Integer
     */
    Integer deleteSiteData(String idAll);

    /**
     * 根据地区id查询此地区的站点
     *
     * @param siteType 站点类型
     * @param placeId  地区id
     * @return List<HashMap>
     */
    List<HashMap> getSiteDataByPlaceId(String siteType, String placeId);

}
