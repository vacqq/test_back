package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public interface AnalysisAllTrendMapper {
    /**
     * 不需要根据地区id查询数据, 只根据站点id查询即可(目前使用rand () limit 24随机查询24条数据,并没有使用整点查询)
     * @param type      设备类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param placeId   地点id
     * @param siteId   站点id
     * @param weatherType 天气类型
     * @param siteType 站点类型
     * @return {{@link java.util.HashMap}}
     */
    @Select({"select b.${type} as type_value," +
            "DATE_FORMAT(b.record_time, '%Y-%m-%d %H:%i:%S') as record_time  from sys_place as a " +
            "LEFT JOIN sys_site as c ON a.id = c.place_id " +
            "LEFT JOIN et_data_new as b ON c.id=b.site_id " +
            "where b.record_time>=#{startTime} and b.record_time<=#{endTime} and c.id = ${siteId} and a.is_del=0 and c.is_del=0 " +
            "ORDER BY rand() limit 24 "})
    List<HashMap> select(@Param("type") String type, @Param("weatherType") String weatherType, @Param("site_type") String siteType, @Param("siteId") int siteId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("placeId") String placeId);
}
