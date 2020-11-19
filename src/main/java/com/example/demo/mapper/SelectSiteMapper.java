package com.example.demo.mapper;

import com.example.demo.bean.SysSiteEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public interface SelectSiteMapper {
    /**
     * 查询站点名称通过站点id
     *
     * @param id 站点id
     * @return {{@link SysSiteEntity}}
     */
    @Select({"select name from sys_site where is_del=0 and  id = #{Id}"})
    String selectNameById(@Param("id") String id);

    /**
     * 查询站点信息通过站点id
     *
     * @param id 站点id
     * @return {{@link HashMap}}
     */
    @Select({"select * from sys_site where is_del=0 and  id = #{Id}"})
    HashMap selectSiteDataById(@Param("id") String id);


    /**
     * 根据id查询地区名
     *
     * @param id 地区表id
     * @return {{@link SysSiteEntity}}
     */
    @Select({"select name from sys_place where is_del=0 and id = #{Id}"})
    String selectPlaceNameById(@Param("id") String id);

    /**
     * 通过站点结合城市名称做出选择, only_name
     *
     * @param name 站点name
     * @return {{@link SysSiteEntity}}
     */
    @Select({"select id from sys_site where is_del=0 and only_name like '${name}' "})
    String selectIdByName(@Param("name") String name);

    /**
     * 根据站点范围id段,查询名称
     *
     * @param idRange 站点id范围
     * @return {{@link SysSiteEntity}}
     */
    @Select({"select id ,name from sys_site where is_del=0 and find_in_set(id,#{IdRange})"})
    List<SysSiteEntity> selectNameInId(@Param("idRange") String idRange);

    /**
     * 根据站点类型和所在地区查询站点信息
     *
     * @param siteType  站点类型
     * @param placeId   地区id
     * @param airType   空气类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return {{@link HashMap}}
     */
    @Select({"<script>" +
            "select a.site_type as site_type, a.id as site_id, a.name as site_name,a.lng, a.lat, (select b.${airType} from et_data_new as b where" +
            " b.site_id = a.id and b.is_del = 0 and b.record_time &gt;= #{startTime} and b.record_time &lt;= #{endTime}" +
            " order by record_time desc LIMIT 1 ) as type_value from sys_site as a where a.is_del = 0     " +
            "<if test='siteType != null and siteType.length() != 0 '>and site_type = #{siteType} </if>" +
            "<if test='placeId != null and placeId.length() != 0 '>and place_id = #{placeId} </if>" +
            "</script>"})
    List<HashMap> selectSiteIdNameBySiteType(@Param("siteType") String siteType, @Param("placeId") String placeId, @Param("airType") String airType, @Param("startTime") String startTime, @Param("endTime") String endTime);


    /**
     * 查询热力图数据
     *
     * @param siteType  站点类型
     * @param placeId   地区id
     * @param airType   空气类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return {{@link HashMap}}
     */
    @Select({"<script>" +
            "select a.site_type as site_type, a.id as site_id, a.name as site_name,a.lng, a.lat, (select b.${airType} from et_data_new as b where" +
            " b.site_id = a.id and b.is_del = 0 and b.record_time &gt;= #{start_time} and b.record_time &lt;= #{endTime}" +
            " order by record_time desc LIMIT 1 ) as count from sys_site as a where a.is_del = 0     " +
            "<if test='siteType != null and siteType.length() != 0 '>and site_type = #{siteType} </if>" +
            "<if test='placeId != null and placeId.length() != 0 '>and place_id = #{placeId} </if>" +
            "</script>"})
    List<HashMap> selectHeatMapDataList(@Param("siteType") String siteType, @Param("placeId") String placeId, @Param("airType") String airType, @Param("startTime") String startTime, @Param("endTime") String endTime);


    /**
     * 根据站点类型和所在地区查询站点信息
     *
     * @param siteType 站点类型
     * @param placeId  地区id
     * @return {{@link List<HashMap>}}
     */
    @Select({"<script>" +
            "select a.id, a.name as site_name from sys_site as a where a.is_del = 0     " +
            "<if test='siteType != null and siteType.length() != 0 '>and site_type = #{siteType} </if>" +
            "<if test='placeId != null and placeId.length() != 0 '>and place_id = #{placeId} </if>" +
            "</script>"})
    List<HashMap> selectSiteIdNameBySiteTypeId(@Param("siteType") String siteType, @Param("placeId") String placeId);


    /**
     * 查询字典类型
     *
     * @param type 字典类型
     * @return {{@link HashMap}}
     */
    @Select({"select code ,name from tbl_dictionaries where type = #{type}"})
    List<HashMap> selectDictionariesByType(@Param("type") String type);

    /**
     * 根据地区类型查询地区id"县区,市,省"
     *
     * @param placeId 地区站点id
     * @return {{@link HashMap}}
     */
    @Select({"select id,name,type from sys_place where parent_id = #{placeId} and is_del = 0"})
    List<SysSiteEntity> getPlaceTreeList(@Param("placeId") String placeId);

    /**
     * 快速查询站点名称集合之1, 查询城市
     *
     * @param placeId 地区站点id集合
     * @return {{@link HashMap}}
     */
    @Select({"select id as value ,name as label from sys_place where parent_id = #{placeId} order by id "})
    List<HashMap> getPlaceTreeListQuick(@Param("placeId") String placeId);


    /**
     * 快速查询站点名称集合之2, 查询县区
     *
     * @param placeId 地区站点id集合
     * @return {{@link HashMap}}
     */
    @Select({"select a.id,a.name,b.id as value,b.name as label,b.parent_id from sys_place as a left join sys_place as b on a.id=b.parent_id  where a.parent_id = #{placeId} order by b.id "})
    List<HashMap> getPlaceTreeListInParentId(@Param("placeId") String placeId);

    /**
     * 根据地区类型查询地区id"县区,市,省"
     *
     * @param type 地区类型
     * @return {{@link List < HashMap >}}
     */
    @Select({"select id,name,only_name,parent_id from sys_place where type = #{type} and is_del = 0"})
    List<HashMap> selectPlaceIdByType(@Param("type") String type);


    /**
     * 据不同类型查询站点表, 对站点就行管理
     *
     * @param siteType 站点类型
     * @param placeId  地区id
     * @param lat      精度值
     * @param lng      纬度值
     * @param siteName 站点名称
     * @return {{@link List < HashMap >}}
     */
    @Select("<script>" +
            "select *, (select name from tbl_dictionaries where type = 2 and id = a.site_type) as site_type_name, " +
            "(select only_name from sys_place where id = a.place_id) as place_id_name from sys_site as a where is_del = 0" +
            "<if test='siteType != null and siteType.length() != 0 '>and site_type = #{siteType} </if>" +
            "<if test='placeId != null and placeId.length() != 0 '>and place_id = #{placeId} </if>" +
            "<if test='lat != null and lat.length() != 0 '>and lat like '%${lat}%' </if>" +
            "<if test='lng != null and lng.length() != 0 '>and lng like '%${lng}%' </if>" +
            "<if test='siteName != null and siteName.length() != 0 '>and name like '%${siteName}%' </if>" +
            "</script>")
    List<HashMap> selectSiteList(@Param("siteType") String siteType, @Param("placeId") String placeId, @Param("lat") String lat, @Param("lng") String lng, @Param("siteName") String siteName);


    /**
     * 查询站点表某站点某时间数据
     *
     * @param siteId    站点id
     * @param dataType  数据类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return {{@link List < HashMap >}}
     */
    @Select({"select aqi,o3,pm25,pm10,no2,so2,co,site_id,(select name from sys_site where id = e.site_id and is_del=0) as site_name," +
            "${dataType} as type_value," +
            " DATE_FORMAT( e.record_time, '%Y-%m-%d \n" + "%H:%i:%S' ) AS record_time  " +
            " from et_data_new e where site_id = #{siteId} and is_del = 0" +
            "  and e.record_time >=#{startTime} and e.record_time <= #{endTime}"})
    List<HashMap> getDateBySiteId(@Param("siteId") String siteId, @Param("dataType") String dataType, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查看此站点数据库中是否有
     *
     * @param name 唯一标识名称
     * @return {{@link Integer}}
     */
    @Select({"select count(id) as num from sys_site where is_del=0 and only_name = #{name}"})
    Integer countSiteNameIsHave(@Param("name") String name);

    /**
     * 插入数据信息
     *
     * @param placeId  地区id
     * @param siteType 站点类型
     * @param name     站点名称
     * @param onlyName 唯一站点名称
     * @param lat      精度值
     * @param lng      纬度值
     * @param position 位置信息
     * @return {{@link Integer} 1为插入成功}
     */
    @Insert({"insert into sys_site (place_id,site_type,name,only_name,lng,lat,position) values (#{placeId},#{siteType},#{name},#{onlyName},#{lng},#{lat},#{position} )"})
    Integer insertSiteData(@Param("placeId") Object placeId, @Param("siteType") Object siteType, @Param("name") Object name, @Param("onlyName") Object onlyName, @Param("lng") Object lng, @Param("lat") Object lat, @Param("position") Object position);

    /**
     * 更新数据信息
     *
     * @param id       id
     * @param placeId  地区id
     * @param siteType 站点类型
     * @param name     站点名称
     * @param onlyName 唯一站点名称
     * @param lat      精度值
     * @param lng      纬度值
     * @param position 位置信息
     * @param modifyBy 修改人信息
     * @return {{@link Integer} 1为更新成功}
     */
    @Update({"<script>" +
            "update sys_site set is_del = 0" +
            "<if test='placeId != null and placeId.length() != 0 '>,place_id = #{placeId}</if>" +
            "<if test='siteType != null and siteType.length() != 0 '>,site_type = #{siteType}</if>" +
            "<if test='name != null and name.length() != 0 '>,name = #{name}</if>" +
            "<if test='name != null and name.length() != 0 '>,only_name = #{name}</if>" +
            "<if test='lng != null and lng.length() != 0 '>,lng = #{lng}</if>" +
            "<if test='lat != null and lat.length() != 0 '>,lat = #{lat}</if>" +
            "<if test='position != null and position.length() != 0 '>,position = #{position}</if>" +
            "<if test='modifyBy != null and modifyBy.length() != 0 '>,modify_by = #{modifyBy}</if>" +
            "where id= #{id} " +
            "</script>"})
    Integer updateSiteData(@Param("id") String id, @Param("placeId") String placeId, @Param("siteType") String siteType, @Param("name") String name, @Param("onlyName") String onlyName, @Param("lng") String lng, @Param("lat") String lat, @Param("position") String position, @Param("modifyBy") String modifyBy);


    /**
     * 获取当前设备最新的一条数据
     *
     * @param siteId 站点id
     * @return {{@link HashMap}}
     */
    @Select({"select * from et_data_new where site_id = ${siteId}  order by create_at desc limit 1"})
    HashMap getLastSiteDataBySiteid(@Param("siteId") String siteId);


    /**
     * 删除数据信息
     *
     * @param idAll id字段
     * @return {{@link Integer} 1为删除成功}
     */
    @Update({"update sys_site set is_del = 1 where id in (${idAll}) "})
    Integer deleteSiteData(@Param("idAll") String idAll);


    /**
     * 根据地区id查询此地区的站点
     *
     * @param siteType 站点类型
     * @param placeId  根据地区id查询此地区的站点
     * @return {{@link HashMap}}
     */
    @Select({"select id as site_id, name as site_name from sys_site where site_type = #{siteType} and place_id = #{placeId} and is_del = 0"})
    List<HashMap> getSiteDataByPlaceId(@Param("siteType") String siteType, @Param("placeId") String placeId);


}
