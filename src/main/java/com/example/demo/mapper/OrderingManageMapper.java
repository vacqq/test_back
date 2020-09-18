package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Demo class
 *
 * @author lcz
 * @date 2020/10/31
 */
@Repository
public interface OrderingManageMapper {

    /**
     * @return {{@link int}}
     * @description 查询订餐信息
     * @author lcz
     * @date 2020/5/26 14:39
     */
    @Select({"<script>" +
            "select count(*) as num from b_ordering " +
            "where is_del = 0 " +
            "<if test='user != null and user.length() != 0 '>and user = #{user} </if>" +
            "<if test='type != null and type.length() != 0 '>and type = #{type} </if>" +
            "<if test='eatTime != null and eatTime.length() != 0 '>and eat_time = #{eatTime} </if>" +
            "order by eatTime,type " +
            "</script>"})
    int selectCountOrderingDateType(@Param("type") String type, @Param("eatTime") String eatTime, @Param("user") String user);

    /**
     * @return {{@link HashMap}}
     * @description 查询订餐信息
     * @author lcz
     * @date 2020/5/26 14:39
     */
    @Select({"<script>" +
            "select * from b_ordering " +
            "where is_del = 0 " +
            "<if test='user != null and user.length() != 0 '>and user = #{user} </if>" +
            "<if test='type != null and type.length() != 0 '>and type = #{type} </if>" +
            "<if test='eatTime != null and eatTime.length() != 0 '>and eat_time = #{eatTime} </if>" +
            "order by eatTime,type " +
            "</script>"})
    List<HashMap> selectOrderingData(@Param("type") String type, @Param("eatTime") String eatTime, @Param("user") String user);

    /**
     * @return {{@link HashMap}}
     * @description 根据订餐id获取订餐信息
     * @author lcz
     * @date 2020/5/26 14:39
     */
    @Select({"select * from b_ordering where is_del = 0 and id = #{id} "})
    HashMap selectOrderingById(@Param("id") String id);

    /**
     * @return {{@link Integer} 1为插入成功}
     * @description 插入订餐信息
     * @author lcz
     * @date 2020/5/26 14:39
     */
    @Insert({"insert into b_ordering (type,user,eat_time,create_by,remarks) values (#{type},#{user},#{eatTime},#{createBy},#{remarks}) "})
    Integer insertOrdering(@Param("type") String type, @Param("user") String user, @Param("eatTime") String eatTime, @Param("createBy") String createBy, @Param("remarks") String remarks);


    /**
     * @return {{@link Integer} 1为更新成功}
     * @description 更新订餐信息
     * @author lcz
     * @date 2020/5/26 14:39
     */
    @Update({"<script>" +
            "update b_ordering set is_del = 0" +
            "<if test='type != null and type.length() != 0 '>,type = #{type}</if>" +
            "<if test='user != null and user.length() != 0 '>,user = #{user}</if>" +
            "<if test='eatTime != null and eatTime.length() != 0 '>,eat_time = #{eatTime}</if>" +
            "<if test='remarks != null and remarks.length() != 0 '>,remarks = #{remarks}</if>" +
            "<if test='modifyBy != null and modifyBy.length() != 0 '>,modify_by = #{modifyBy}</if>" +
            "where id= #{id} " +
            "</script>"})
    Integer updateOrdering(@Param("id") String id, @Param("type") String type, @Param("user") String user, @Param("eatTime") String eatTime, @Param("modifyBy") String modifyBy, @Param("remarks") String remarks);

    /**
     * @return {{@link Integer} 1为删除成功}
     * @description 删除订餐信息
     * @author lcz
     * @date 2020/5/26 14:39
     */
    @Update({"update b_ordering set is_del = 1 where id= #{id} "})
    Integer deleteOrdering(@Param("id") String id);
}
