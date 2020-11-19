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
public interface AnalysisSiteRelevanceMapper {

    /**
     * 查询排名table信息
     * @param type        参数类型
     * @param siteType   站点类型
     * @param decimalNum 保留到小数几位
     * @param dateTime    查询时间'2020-04-01'类型
     * @param siteId     站点id
     * @return {{@link java.util.HashMap}}
     * @author lcz
     * @date 2020/4/24 14:39
     */
    @Select({"select c.name as site_name,format(avg(b.${type}),${decimalNum}) as type_value " +
            "from sys_site as c left join et_data_new as b on b.site_id = c.id " +
            "where month(b.record_time) = month(#{dateTime}) and c.id = #{siteId} " +
            "group by c.id order by avg(b.${type})  "})
    List<HashMap> selectData(@Param("type") String type, @Param("siteType") String siteType, @Param("decimalNum") String decimalNum, @Param("dateTime") String dateTime, @Param("siteId") String siteId);


    /**
     * 查询排名table信息
     * @param type          参数类型
     * @param siteType     站点类型
     * @param decimalNum   保留到小数几位
     * @param dateTime    查询时间'2020-04-01'类型
     * @param siteIdRange 站点id范围值
     * @return {{@link java.util.HashMap}}
     * @author lcz
     * @date 2020/4/24 14:39
     */
    @Select({"select c.name as site_name,format(avg(b.${type}),${decimalNum}) as type_value " +
            "from sys_site as c left join et_data_new as b on b.site_id = c.id  " +
            "where month(b.record_time) = month(#{dateTime}) and c.id in (${siteIdRange}) " +
            "group by c.id order by avg(b.${type})  "})
    List<HashMap> selectDataSiteRange(@Param("type") String type, @Param("siteType") String siteType, @Param("decimalNum") String decimalNum, @Param("dateTime") String dateTime, @Param("siteIdRange") String siteIdRange);

    /**
     * 查询搜索时间段内的时间点值
     * @param startTime    查询开始时间
     * @param endTime      查询结束时间
     * @return {{@link java.util.HashMap}}
     * @author lcz
     * @date 2020/4/28 14:39
     */
    @Select({"select DISTINCT DATE_FORMAT(record_time, '%Y-%m') as date_time " +
            "from et_data_new where record_time>=#{startTime} and record_time<=#{endTime} "})
    List<HashMap> selectDateTime(@Param("startTime") String startTime, @Param("endTime") String endTime);


}
