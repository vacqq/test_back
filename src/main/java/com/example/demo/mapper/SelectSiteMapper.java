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
public interface SelectSiteMapper {
    /**
     * @param Id 站点id
     * @return {{@link SysSiteEntity}}
     * @description 查询站点名称通过站点id
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"select name from sys_site where is_del=0 and  id = #{Id}"})
    String SelectNameById(@Param("Id") String Id);

    /**
     * @param Id 站点id
     * @return {{@link HashMap}}
     * @description 查询站点信息通过站点id
     * @author lcz
     * @date 2020/8/23 14:39
     */
    @Select({"select * from sys_site where is_del=0 and  id = #{Id}"})
    HashMap SelectSiteDataById(@Param("Id") String Id);


    /**
     * @param Id 地区表id
     * @return {{@link SysSiteEntity}}
     * @description
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"select name from sys_place where is_del=0 and id = #{Id}"})
    String SelectPlaceNameById(@Param("Id") String Id);

    /**
     * @param name 站点name
     * @return {{@link SysSiteEntity}}
     * @description 通过站点结合城市名称做出选择, only_name
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"select id from sys_site where is_del=0 and only_name like '${name}' "})
    String SelectIdByName(@Param("name") String name);

    /**
     * @param IdRange 站点id范围
     * @return {{@link SysSiteEntity}}
     * @description
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"select id ,name from sys_site where is_del=0 and find_in_set(id,#{IdRange})"})
    List<SysSiteEntity> SelectNameInId(@Param("IdRange") String IdRange);

    /**
     * @param site_type 站点类型
     * @return {{@link HashMap}}
     * @description 根据站点类型和所在地区查询站点信息
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"<script>" +
            "select a.site_type as site_type, a.id as site_id, a.name as site_name,a.lng, a.lat, (select b.${air_type} from et_data_new as b where" +
            " b.site_id = a.id and b.is_del = 0 and b.record_time &gt;= #{start_time} and b.record_time &lt;= #{end_time}" +
            " order by record_time desc LIMIT 1 ) as type_value from sys_site as a where a.is_del = 0     " +
            "<if test='site_type != null and site_type.length() != 0 '>and site_type = #{site_type} </if>" +
            "<if test='place_id != null and place_id.length() != 0 '>and place_id = #{place_id} </if>" +
            "</script>"})
    List<HashMap> SelectSiteIdNameBySiteType(@Param("site_type") String site_type, @Param("place_id") String place_id, @Param("air_type") String air_type, @Param("start_time") String start_time, @Param("end_time") String end_time);


    /**
     * @param site_type 站点类型
     * @return {{@link HashMap}}
     * @description 查询热力图数据
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"<script>" +
            "select a.site_type as site_type, a.id as site_id, a.name as site_name,a.lng, a.lat, (select b.${air_type} from et_data_new as b where" +
            " b.site_id = a.id and b.is_del = 0 and b.record_time &gt;= #{start_time} and b.record_time &lt;= #{end_time}" +
            " order by record_time desc LIMIT 1 ) as count from sys_site as a where a.is_del = 0     " +
            "<if test='site_type != null and site_type.length() != 0 '>and site_type = #{site_type} </if>" +
            "<if test='place_id != null and place_id.length() != 0 '>and place_id = #{place_id} </if>" +
            "</script>"})
    List<HashMap> SelectHeatMapDataList(@Param("site_type") String site_type, @Param("place_id") String place_id, @Param("air_type") String air_type, @Param("start_time") String start_time, @Param("end_time") String end_time);


    /**
     * @param site_type 站点类型
     * @return {{@link HashMap}}
     * @description 根据站点类型和所在地区查询站点信息
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select({"<script>" +
            "select a.id, a.name as site_name from sys_site as a where a.is_del = 0     " +
            "<if test='site_type != null and site_type.length() != 0 '>and site_type = #{site_type} </if>" +
            "<if test='place_id != null and place_id.length() != 0 '>and place_id = #{place_id} </if>" +
            "</script>"})
    List<HashMap> SelectSiteIdNameBySiteTypeId(@Param("site_type") String site_type, @Param("place_id") String place_id);


    /**
     * @param type 字典类型
     * @return {{@link HashMap}}
     * @description
     * @author lcz
     * @date 2020/5/6 14:39
     */
    @Select({"select code ,name from tbl_dictionaries where type = #{type}"})
    List<HashMap> SelectDictionariesByType(@Param("type") String type);

    /**
     * @param place_id 地区站点id
     * @return {{@link HashMap}}
     * @description
     * @author lcz
     * @date 2020/7/6 14:39
     */
    @Select({"select id,name,type from sys_place where parent_id = #{place_id} and is_del = 0"})
    List<SysSiteEntity> GetPlaceTreeList(@Param("place_id") String place_id);

    /**
     * @param place_id 地区站点id集合
     * @return {{@link HashMap}}
     * @description 快速查询站点名称集合之1, 查询城市
     * @author lcz
     * @date 2020/7/6 14:39
     */
    @Select({"select id as value ,name as label from sys_place where parent_id = #{place_id} order by id "})
    List<HashMap> GetPlaceTreeListQuick(@Param("place_id") String place_id);


    /**
     * @param place_id 地区站点id集合
     * @return {{@link HashMap}}
     * @description 快速查询站点名称集合之2, 查询县区
     * @author lcz
     * @date 2020/7/6 14:39
     */
    @Select({"select a.id,a.name,b.id as value,b.name as label,b.parent_id from sys_place as a left join sys_place as b on a.id=b.parent_id  where a.parent_id = #{place_id} order by b.id "})
    List<HashMap> GetPlaceTreeListInParentId(@Param("place_id") String place_id);

    /**
     * @param type 地区类型
     * @return {{@link List < HashMap >}}
     * @description 根据地区类型查询地区id"县区,市,省"
     * @author lczplace_id
     * @date 2020/7/6 14:39
     */
    @Select({"select id,name,only_name,parent_id from sys_place where type = #{type} and is_del = 0"})
    List<HashMap> SelectPlaceIdByType(@Param("type") String type);


    /**
     * @param
     * @return {{@link List < HashMap >}}
     * @description 据不同类型查询站点表, 对站点就行管理
     * @author lcz
     * @date 2020/4/23 14:39
     */
    @Select("<script>" +
            "select *, (select name from tbl_dictionaries where type = 2 and id = a.site_type) as site_type_name, " +
            "(select only_name from sys_place where id = a.place_id) as place_id_name from sys_site as a where is_del = 0" +
            "<if test='site_type != null and site_type.length() != 0 '>and site_type = #{site_type} </if>" +
            "<if test='place_id != null and place_id.length() != 0 '>and place_id = #{place_id} </if>" +
            "<if test='lat != null and lat.length() != 0 '>and lat like '%${lat}%' </if>" +
            "<if test='lng != null and lng.length() != 0 '>and lng like '%${lng}%' </if>" +
            "<if test='site_name != null and site_name.length() != 0 '>and name like '%${site_name}%' </if>" +

            "</script>")
    List<HashMap> SelectSiteList(@Param("site_type") String site_type, @Param("place_id") String place_id, @Param("lat") String lat, @Param("lng") String lng, @Param("site_name") String site_name);


    /**
     * @param site_id 站点id
     * @return {{@link List < HashMap >}}
     * @description 查询站点表某站点某时间数据
     * @author lcz
     * @date 2020/7/6 14:39
     */
    @Select({"select aqi,o3,pm25,pm10,no2,so2,co,site_id,(select name from sys_site where id = e.site_id and is_del=0) as site_name," +
            "${data_type} as type_value," +
            " DATE_FORMAT( e.record_time, '%Y-%m-%d \n" + "%H:%i:%S' ) AS record_time  " +
            " from et_data_new e where site_id = #{site_id} and is_del = 0" +
            "  and e.record_time >=#{start_time} and e.record_time <= #{end_time}"})
    List<HashMap> getDateBySiteId(@Param("site_id") String site_id, @Param("data_type") String data_type, @Param("start_time") String start_time, @Param("end_time") String end_time);

    /**
     * @param name 唯一标识名称
     * @return {{@link Integer}}
     * @description 查看此站点数据库中是否有
     * @author lcz
     * @date 2020/8/23 14:47
     */
    @Select({"select count(id) as num from sys_site where is_del=0 and only_name = #{name}"})
    Integer countSiteNameIsHave(@Param("name") String name);

    /**
     * @return {{@link Integer} 1为插入成功}
     * @description 插入数据信息
     * @author lcz
     * @date 2020/8/13 15:42
     */
    @Insert({"insert into sys_site (place_id,site_type,name,only_name,lng,lat,position) values (#{place_id},#{site_type},#{name},#{only_name},#{lng},#{lat},#{position} )"})
    Integer InsertSiteData(@Param("place_id") Object place_id, @Param("site_type") Object site_type, @Param("name") Object name, @Param("only_name") Object only_name, @Param("lng") Object lng, @Param("lat") Object lat, @Param("position") Object position);

    /**
     * @return {{@link Integer} 1为更新成功}
     * @description 更新数据信息
     * @author lcz
     * @date 2020/7/1 15:42
     */
    @Update({"<script>" +
            "update sys_site set is_del = 0" +
            "<if test='place_id != null and place_id.length() != 0 '>,place_id = #{place_id}</if>" +
            "<if test='site_type != null and site_type.length() != 0 '>,site_type = #{site_type}</if>" +
            "<if test='name != null and name.length() != 0 '>,name = #{name}</if>" +
            "<if test='name != null and name.length() != 0 '>,only_name = #{name}</if>" +
            "<if test='lng != null and lng.length() != 0 '>,lng = #{lng}</if>" +
            "<if test='lat != null and lat.length() != 0 '>,lat = #{lat}</if>" +
            "<if test='position != null and position.length() != 0 '>,position = #{position}</if>" +
            "<if test='modify_by != null and modify_by.length() != 0 '>,modify_by = #{modify_by}</if>" +
            "where id= #{id} " +
            "</script>"})
    Integer UpdateSiteData(@Param("id") String id, @Param("place_id") String place_id, @Param("site_type") String site_type, @Param("name") String name, @Param("only_name") String only_name, @Param("lng") String lng, @Param("lat") String lat, @Param("position") String position, @Param("modify_by") String modify_by);


    /**
     * @param site_id
     * @return {{@link Integer}}
     * @description 获取当前设备最新的一条数据
     * @author lcz
     * @date 2020/7/23 14:47
     */
    @Select({"select * from et_data_new where site_id = ${site_id}  order by create_at desc limit 1"})
    HashMap getLastSiteDataBySiteid(@Param("site_id") String site_id);


    /**
     * @return {{@link Integer} 1为删除成功}
     * @description 删除数据信息
     * @author lcz
     * @date 2020/8/1 15:42
     */
    @Update({"update sys_site set is_del = 1 where id in (${id_all}) "})
    Integer DeleteSiteData(@Param("id_all") String id_all);


    /**
     * @param place_id 根据地区id查询此地区的站点
     * @return {{@link HashMap}}
     * @description
     * @author lcz
     * @date 2020/8/6 14:39
     */
    @Select({"select id as site_id, name as site_name from sys_site where site_type = #{site_type} and place_id = #{place_id} and is_del = 0"})
    List<HashMap> GetSiteDataByPlaceId(@Param("site_type") String site_type, @Param("place_id") String place_id);


}
