package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AnalysisSiteRelevanceMapper {

    /**
     * @param type        参数类型
     * @param site_type   站点类型
     * @param decimal_num 保留到小数几位
     * @param date_time    查询时间'2020-04-01'类型
     * @param site_id     站点id
     * @return {{@link java.util.HashMap}}
     * @description 查询排名table信息
     * @author lcz
     * @date 2020/4/24 14:39
     */
    @Select({"select c.name as site_name,format(avg(b.${type}),${decimal_num}) as type_value " +
            "from sys_site as c left join et_data_new as b on b.site_id = c.id " +
            "where month(b.record_time) = month(#{date_time}) and c.id = #{site_id} " +
            "group by c.id order by avg(b.${type})  "})
    List<HashMap> SelectData(@Param("type") String type, @Param("site_type") String site_type, @Param("decimal_num") String decimal_num, @Param("date_time") String date_time, @Param("site_id") String site_id);


    /**
     * @param type          参数类型
     * @param site_type     站点类型
     * @param decimal_num   保留到小数几位
     * @param date_time    查询时间'2020-04-01'类型
     * @param site_id_range 站点id范围值
     * @return {{@link java.util.HashMap}}
     * @description 查询排名table信息
     * @author lcz
     * @date 2020/4/24 14:39
     */
    @Select({"select c.name as site_name,format(avg(b.${type}),${decimal_num}) as type_value " +
            "from sys_site as c left join et_data_new as b on b.site_id = c.id  " +
            "where month(b.record_time) = month(#{date_time}) and c.id in (${site_id_range}) " +
            "group by c.id order by avg(b.${type})  "})
    List<HashMap> SelectDataSiteRange(@Param("type") String type, @Param("site_type") String site_type, @Param("decimal_num") String decimal_num, @Param("date_time") String date_time, @Param("site_id_range") String site_id_range);

    /**
     * @param start_time    查询开始时间
     * @param end_time      查询结束时间
     * @return {{@link java.util.HashMap}}
     * @description 查询搜索时间段内的时间点值
     * @author lcz
     * @date 2020/4/28 14:39
     */
    @Select({"select DISTINCT DATE_FORMAT(record_time, '%Y-%m') as date_time " +
            "from et_data_new where record_time>=#{start_time} and record_time<=#{end_time} "})
    List<HashMap> SelectDateTime(@Param("start_time") String start_time, @Param("end_time") String end_time);


}
