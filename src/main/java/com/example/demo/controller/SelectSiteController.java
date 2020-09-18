package com.example.demo.controller;

import com.example.demo.bean.SysSiteEntity;
import com.example.demo.service.SelectSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RestController
public class SelectSiteController {

    @Autowired
    private SelectSiteService selectSiteService;

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 根据站点类型和所在地区查询站点信息
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectSiteIdNameBySiteType", method = RequestMethod.POST)
    public List<HashMap> SelectSiteIdNameBySiteType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        List<HashMap> hashMapList = selectSiteService.SelectSiteIdNameBySiteType(jsonString);
        return hashMapList;
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 查询热力图数据
     * @author lcz
     * @date 2020/8/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectHeatMapDataList", method = RequestMethod.POST)
    public List<HashMap> SelectHeatMapDataList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        List<HashMap> hashMapList = selectSiteService.SelectHeatMapDataList(jsonString);
        return hashMapList;
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 根据站点类型和所在地区查询站点信息
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectSiteIdNameBySiteTypeId", method = RequestMethod.POST)
    public List<HashMap> SelectSiteIdNameBySiteTypeId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.SelectSiteIdNameBySiteTypeId(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 根据不同类型查询字典类
     * @author lcz
     * @date 2020/5/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectDictionariesByType", method = RequestMethod.POST)
    public List<HashMap> SelectDictionariesByType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.SelectDictionariesByType(jsonString);
    }


    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 树形菜单加载
     * @author lcz
     * @date 2020/7/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/PlaceTreeList", method = RequestMethod.POST)
    public HashMap PlaceTreeList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        String place_id = jsonString.get("place_id");//获取默认place_id
        HashMap<String, Object> place_detail = new HashMap<String, Object>();
        List<HashMap> place_detail_list = new ArrayList<HashMap>();
        place_detail.put("place_father_show", selectSiteService.SelectPlaceNameById(place_id));

        List<HashMap> childrenXQ = selectSiteService.GetPlaceTreeListQuick(place_id);
        List<HashMap> childrenDetailList = selectSiteService.GetPlaceTreeListInParentId(place_id);

        for (HashMap strList : childrenXQ) {
            HashMap<String, Object> children = new HashMap<String, Object>();
            children.put("value", strList.get("value"));
            children.put("label", strList.get("label"));
            children.put("children", GetQuickList(strList.get("value").toString(), childrenDetailList));//递归调用
            place_detail_list.add(children);
        }
//        place_detail.put("place_detail", GetTreeList(place_id));
        place_detail.put("place_detail", place_detail_list);
        return place_detail;
    }

    public List<HashMap> GetQuickList(String parent_id, List<HashMap> childrenDetailList) {
        List<HashMap> place_detail_list = new ArrayList<HashMap>();
        for (HashMap strList : childrenDetailList) {
            if (strList.get("parent_id").toString().equals(parent_id)) {
                HashMap<String, Object> children = new HashMap<String, Object>();
                children.put("value", strList.get("value"));
                children.put("label", strList.get("label"));
                place_detail_list.add(children);
            }
        }
        return place_detail_list;
    }

    public List<HashMap> GetTreeList(String place_id) {
        List<SysSiteEntity> sysSiteEntities = selectSiteService.GetPlaceTreeList(place_id);
        if (sysSiteEntities.size() == 0) {
            return null;
        }
        List<HashMap> children = new ArrayList<HashMap>();
        for (SysSiteEntity strList : sysSiteEntities) {
            HashMap<String, Object> place_detail = new HashMap<String, Object>();
            place_detail.put("value", strList.getId());
            place_detail.put("label", strList.getName());
            //place_detail.put("children", GetTreeList(String.valueOf(strList.getId())));//递归调用
            place_detail.put("children", GetTreeChildrenList(String.valueOf(strList.getId())));//普通调用节省查询时间
            children.add(place_detail);
        }
        return children;
    }

    public List<HashMap> GetTreeChildrenList(String place_id) {
        List<SysSiteEntity> sysSiteEntities = selectSiteService.GetPlaceTreeList(place_id);
        if (sysSiteEntities.size() == 0) {
            return null;
        }
        List<HashMap> children = new ArrayList<HashMap>();
        for (SysSiteEntity strList : sysSiteEntities) {
            HashMap<String, Object> place_detail = new HashMap<String, Object>();
            place_detail.put("value", strList.getId());
            place_detail.put("label", strList.getName());
            children.add(place_detail);
        }
        return children;
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 查询站点信息通过站点id
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectSiteDataById", method = RequestMethod.POST)
    public HashMap SelectSiteDataById(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.SelectSiteDataById(jsonString.get("id"));
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 根据地区类型查询地区id"县区,市,省"
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectPlaceIdByType", method = RequestMethod.POST)
    public List<HashMap> SelectPlaceIdByType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.SelectPlaceIdByType(jsonString.get("type"));
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 根据不同类型查询站点表, 对站点就行管理
     * @author lcz
     * @date 2020/7/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/SelectSiteList", method = RequestMethod.POST)
    public List<HashMap> SelectSiteList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.SelectSiteList(jsonString);
    }


    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link List < HashMap >}}
     * @description 根据不同类型查询站点表, 对站点就行管理
     * @author lcz
     * @date 2020/7/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/getDateBySiteId", method = RequestMethod.POST)
    public List<HashMap> getDateBySiteId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.getDateBySiteId(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 获取最新一条数据从数据库
     * @author lcz
     * @date 2020/7/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/getLastSiteDataBySiteid", method = RequestMethod.POST)
    public HashMap getLastSiteDataBySiteid(@RequestBody HashMap<String, String> jsonString) throws Exception {
        HashMap hashMap = selectSiteService.getLastSiteDataBySiteid(jsonString);
        return hashMap;
    }


    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 插入数据信息
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/InsertSiteData", method = RequestMethod.POST)
    public HashMap InsertSiteData(@RequestBody HashMap<String, Object> jsonString) throws Exception {

        String result_data = "0";
        String result_msg = "插入失败";
        //对是否重复插入做出判断
        if (selectSiteService.countSiteNameIsHave(jsonString.get("name").toString()) == 0) {
            selectSiteService.InsertSiteData(jsonString);
            result_data = "1";
            result_msg = "插入成功";
        } else {
            result_msg = "此站点名已存在，请更换站点名";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }

    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 更新数据信息
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/UpdateSiteData", method = RequestMethod.POST)
    public HashMap UpdateSiteData(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer result_data = 0;
        String result_msg = "更新失败";
        result_data = selectSiteService.UpdateSiteData(jsonString);
        if (result_data != 0) {
            result_msg = "更新成功";
        }
        HashMap<String, Object> add_map = new HashMap<String, Object>();
        add_map.put("result_status", true);
        add_map.put("result_msg", result_msg);
        add_map.put("result_data", result_data);
        return add_map;
    }


    /**
     * @param jsonString 前端传入的相关信息------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 删除数据
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/DeleteSiteData", method = RequestMethod.POST)
    public Integer DeleteSiteData(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.DeleteSiteData(jsonString.get("id_all"));
    }


    /**
     * @param jsonString ------------------------------------------------------------------------------------
     * @return {{@link HashMap}}
     * @description 根据地区id查询此地区的站点
     * @author lcz
     * @date 2020/8/6 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/GetSiteDataByPlaceId", method = RequestMethod.POST)
    public List<HashMap> GetSiteDataByPlaceId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.GetSiteDataByPlaceId(jsonString.get("site_type"), jsonString.get("place_id"));
    }


}