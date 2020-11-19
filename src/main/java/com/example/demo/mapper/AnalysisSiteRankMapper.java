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
public interface AnalysisSiteRankMapper {
    /**
     * 查询排名table信息
     * @param type      参数类型
     * @param siteType  站点类型
     * @param orderType 排序方式
     * @return {{@link java.util.HashMap}}
     */
    @Select({"SELECT ${type} as type_value,staname as site_name,(@rownum := @rownum + 1) AS col_rank ,\n" +
            "        CASE\n" +
            "        WHEN ${type} < 60 THEN '#58C750'\n" +
            "        WHEN ${type} >=60 and ${type} < 70 THEN '#E3CA55'\n" +
            "        ELSE '#7A3D3D'\n" +
            "        END as col_color\n" +
            "        from\n" +
            "        (SELECT @rownum := 0) r, v_data\n" +
            "\n" +
            "       where site_type=${site_type} order by ${type} ${orderType}"})
    List<HashMap> select(@Param("type") String type, @Param("orderType") String orderType, @Param("siteType") String siteType);


    /**
     * 查询排名table信息
     * @param type        参数类型
     * @param orderType  排序方式
     * @param siteType   站点类型
     * @param decimalNum 保留到小数几位
     * @param startTime  查询开始时间
     * @param endTime    查询结束时间
     * @param placeId    站点id
     * @return {{@link java.util.HashMap}}
     */
    @Select({"SELECT *,( @row_num := @row_num + 1 ) AS rank_num from " +
            "(SELECT  b.record_time,a.name as site_name, format(avg(b.${type}),${decimalNum}) as type_value" +
            ", CASE WHEN format(avg(b.${type}),${decimalNum}) < 60 THEN '#58C750' WHEN format(avg(b.${type}),${decimalNum}) >=60 and format(avg(b.${type}),${decimalNum}) < 70 \n" +
            "THEN '#E3CA55' ELSE '#7A3D3D' END as color " +
            "from (SELECT @row_num := 0) rank_num, sys_place as c  " +
            "left join sys_site as a on c.id = a.placeId " +
            "LEFT JOIN et_data_new as b ON a.id=b.site_id " +
            "where b.record_time>=#{startTime} and b.record_time<=#{endTime} and a.site_type = #{siteType} and a.is_del = 0 and c.id = #{placeId} and c.is_del = 0 group by site_name order by avg(b.${type}) ${order_type}) as data_value"})
    List<HashMap> selectData(@Param("type") String type, @Param("orderType") String orderType, @Param("siteType") String siteType, @Param("decimalNum") String decimalNum, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("placeId") String placeId);


}
