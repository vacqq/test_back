package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AnalysAllTrendMapper {
    /**
     * @param type      设备类型
     * @param site_type 站点类型
     * @param site_id   站点id
     * @return {{@link java.util.HashMap}}
     * @description 不需要根据地区id查询数据, 只根据站点id查询即可(目前使用rand () limit 24随机查询24条数据,并没有使用整点查询)
     * @author lcz
     * @date 2020/7/24 14:39
     */
    @Select({"select b.${type} as type_value," +
            "DATE_FORMAT(b.record_time, '%Y-%m-%d %H:%i:%S') as record_time  from sys_place as a " +
            "LEFT JOIN sys_site as c ON a.id = c.place_id " +
            "LEFT JOIN et_data_new as b ON c.id=b.site_id " +
            "where b.record_time>=#{start_time} and b.record_time<=#{end_time} and c.id = ${site_id} and a.is_del=0 and c.is_del=0 " +
            "ORDER BY rand() limit 24 "})
    List<HashMap> Select(@Param("type") String type, @Param("weather_type") String weather_type, @Param("site_type") String site_type, @Param("site_id") int site_id, @Param("start_time") String start_time, @Param("end_time") String end_time, @Param("place_id") String place_id);
}
