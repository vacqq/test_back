package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AnalysSiteRankMapper {
    /**
     * @param type 参数类型
     * @param order_type  排序方式
     *@description 查询排名table信息
     *@return {{@link java.util.HashMap}}
     *@author lcz
     *@date 2020/4/24 14:39
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
            "       where site_type=${site_type} order by ${type} ${order_type}"})
    List<HashMap> Select(@Param("type") String type, @Param("order_type") String order_type, @Param("site_type") String site_type);


    /**
     * @param type 参数类型
     * @param order_type  排序方式
     * @param site_type  站点类型
     * @param decimal_num  保留到小数几位
     * @param start_time  查询开始时间
     * @param end_time  查询结束时间
     *@description 查询排名table信息
     *@return {{@link java.util.HashMap}}
     *@author lcz
     *@date 2020/4/24 14:39
     */
    @Select({"SELECT *,( @row_num := @row_num + 1 ) AS rank_num from " +
            "(SELECT  b.record_time,a.name as site_name, format(avg(b.${type}),${decimal_num}) as type_value" +
            ", CASE WHEN format(avg(b.${type}),${decimal_num}) < 60 THEN '#58C750' WHEN format(avg(b.${type}),${decimal_num}) >=60 and format(avg(b.${type}),${decimal_num}) < 70 \n" +
            "THEN '#E3CA55' ELSE '#7A3D3D' END as color " +
            "from (SELECT @row_num := 0) rank_num, sys_place as c  " +
            "left join sys_site as a on c.id = a.place_id " +
            "LEFT JOIN et_data_new as b ON a.id=b.site_id " +
            "where b.record_time>=#{start_time} and b.record_time<=#{end_time} and a.site_type = #{site_type} and a.is_del = 0 and c.id = #{place_id} and c.is_del = 0 group by site_name order by avg(b.${type}) ${order_type}) as data_value"})
    List<HashMap> SelectData(@Param("type") String type, @Param("order_type") String order_type, @Param("site_type") String site_type, @Param("decimal_num") String decimal_num, @Param("start_time") String start_time, @Param("end_time") String end_time, @Param("place_id") String place_id);


}
