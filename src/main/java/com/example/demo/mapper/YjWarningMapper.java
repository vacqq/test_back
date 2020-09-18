package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author lcz
 * @description 预警记录Mapper
 * @date 2020/4/28
 */
@Repository
public interface YjWarningMapper {

    /**
     * @param start_time 查询开始时间
     * @param end_time   查询结束时间
     * @return {{@link java.util.HashMap}}
     * @description 查询所有预警信息, 根据不同参数查询不同数据
     * @author lcz
     * @date 2020/7/28 14:39
     */
    @Select("<script>" +
            "select a.id as warning_id,data_id,DATE_FORMAT(warning_time, '%Y-%m-%d %H:%i:%S') as warning_time, remarks,data_type ,air_data, b.name as site_name, b.position" +
            " FROM yj_warning as a " +
            "left join sys_site as b on a.site_id = b.id " +
            " where warning_time &gt;= #{start_time} and warning_time &lt;= #{end_time} and a.is_del = 0 " +
            "<if test='data_type != null and data_type.length() != 0 '>and data_type = #{data_type} </if>" +
            "<if test='site_name != null and site_name.length() != 0 '>and b.name like '%${site_name}%' </if>" +
            "<if test='site_type != null and site_type.length() != 0 '>and b.site_type = #{site_type} </if>" +
            "order by warning_time desc" +
            "</script>")
    List<HashMap> getWarningData(@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("data_type") String data_type, @Param("site_name") String site_name, @Param("site_type") String site_type);


    /**
     * @return {{@link Integer} 1为插入成功}
     * @description 插入报警数据信息
     * @author lcz
     * @date 2020/8/3 15:42
     */
    @Insert({"insert into yj_warning (data_id,site_id,standard_id,remarks,data_type,warning_time,air_data,create_by) values (#{data_id},#{site_id},#{standard_id},#{remarks},#{data_type},#{warning_time},#{air_data},#{create_by} )"})
    Integer InsertWarningData(@Param("data_id") Object data_id, @Param("site_id") Object site_id, @Param("standard_id") Object standard_id, @Param("remarks") Object remarks, @Param("data_type") Object data_type, @Param("warning_time") Object warning_time, @Param("air_data") Object air_data, @Param("create_by") Object create_by);


    /**
     * @param
     * @return {{@link HashMap}}
     * @description 通过站点结合城市名称做出选择, only_name
     * @author lcz
     * @date 2020/8/23 14:39
     */
    @Select({"select name from tbl_dictionaries where type = 'yj' and id = 15 "})
    String SelectRuleWord();

    /**
     * @return {{@link Integer} 1为更新成功}
     * @description 更新规则配置信息
     * @author lcz
     * @date 2020/8/1 15:42
     */
    @Update({"<script>" +
            "update tbl_dictionaries set name = #{rule_id}" +
            "where type = 'yj' and id= 15 " +
            "</script>"})
    Integer UpdateRuleWord(@Param("rule_id") String rule_id);

    /**
     * @param
     * @return {{@link HashMap}}
     * @description 通过站点结合城市名称做出选择, only_name
     * @author lcz
     * @date 2020/8/23 14:39
     */
    @Select({"select name from tbl_dictionaries where type = 'yj' and id = 15 "})
    List<HashMap> SelectRuleTableList();


}
