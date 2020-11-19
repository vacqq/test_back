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
/**
 * @author lcz
 * @date 2020/9/18
 */
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
    public List<HashMap> selectSiteIdNameBySiteType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        List<HashMap> hashMapList = selectSiteService.selectSiteIdNameBySiteType(jsonString);
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
    public List<HashMap> selectHeatMapDataList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        List<HashMap> hashMapList = selectSiteService.selectHeatMapDataList(jsonString);
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
    public List<HashMap> selectSiteIdNameBySiteTypeId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.selectSiteIdNameBySiteTypeId(jsonString);
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
    public List<HashMap> selectDictionariesByType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.selectDictionariesByType(jsonString);
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
    public HashMap placeTreeList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        //获取默认place_id
        String placeId = jsonString.get("place_id");
        HashMap<String, Object> placeDetail = new HashMap<String, Object>(10);
        List<HashMap> placeDetailList = new ArrayList<HashMap>();
        placeDetail.put("place_father_show", selectSiteService.selectPlaceNameById(placeId));

        List<HashMap> childrenX = selectSiteService.getPlaceTreeListQuick(placeId);
        List<HashMap> childrenDetailList = selectSiteService.getPlaceTreeListInParentId(placeId);

        for (HashMap strList : childrenX) {
            HashMap<String, Object> children = new HashMap<String, Object>(10);
            children.put("value", strList.get("value"));
            children.put("label", strList.get("label"));
            //递归调用
            children.put("children", getQuickList(strList.get("value").toString(), childrenDetailList));
            placeDetailList.add(children);
        }
        placeDetail.put("place_detail", placeDetailList);
        return placeDetail;
    }

    public List<HashMap> getQuickList(String parentId, List<HashMap> childrenDetailList) {
        List<HashMap> placeDetailList = new ArrayList<HashMap>();
        for (HashMap strList : childrenDetailList) {
            if (strList.get("parent_id").toString().equals(parentId)) {
                HashMap<String, Object> children = new HashMap<String, Object>(10);
                children.put("value", strList.get("value"));
                children.put("label", strList.get("label"));
                placeDetailList.add(children);
            }
        }
        return placeDetailList;
    }

    public List<HashMap> getTreeList(String placeId) {
        List<SysSiteEntity> sysSiteEntities = selectSiteService.getPlaceTreeList(placeId);
        if (sysSiteEntities.size() == 0) {
            return null;
        }
        List<HashMap> children = new ArrayList<HashMap>();
        for (SysSiteEntity strList : sysSiteEntities) {
            HashMap<String, Object> placeDetail = new HashMap<String, Object>(10);
            placeDetail.put("value", strList.getId());
            placeDetail.put("label", strList.getName());
            placeDetail.put("children", getTreeChildrenList(String.valueOf(strList.getId())));
            children.add(placeDetail);
        }
        return children;
    }

    public List<HashMap> getTreeChildrenList(String placeId) {
        List<SysSiteEntity> sysSiteEntities = selectSiteService.getPlaceTreeList(placeId);
        if (sysSiteEntities.size() == 0) {
            return null;
        }
        List<HashMap> children = new ArrayList<HashMap>();
        for (SysSiteEntity strList : sysSiteEntities) {
            HashMap<String, Object> placeDetail = new HashMap<String, Object>(10);
            placeDetail.put("value", strList.getId());
            placeDetail.put("label", strList.getName());
            children.add(placeDetail);
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
    public HashMap selectSiteDataById(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.selectSiteDataById(jsonString.get("id"));
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
    public List<HashMap> selectPlaceIdByType(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.selectPlaceIdByType(jsonString.get("type"));
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
    public List<HashMap> selectSiteList(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.selectSiteList(jsonString);
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
        HashMap hashMap = selectSiteService.getLastSiteDataBySiteId(jsonString);
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
    public HashMap insertSiteData(@RequestBody HashMap<String, Object> jsonString) throws Exception {

        String resultData = "0";
        String resultMsg = "插入失败";
        String name = jsonString.get("name").toString();
        //对是否重复插入做出判断
        if (selectSiteService.countSiteNameIsHave(name) == 0) {
            selectSiteService.insertSiteData(jsonString);
            resultData = "1";
            resultMsg = "插入成功";
        } else {
            resultMsg = "此站点名已存在，请更换站点名";
        }
        HashMap<String, Object> addMap = new HashMap<String, Object>(10);
        addMap.put("result_status", true);
        addMap.put("result_msg", resultMsg);
        addMap.put("result_data", resultData);
        return addMap;
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
    public HashMap updateSiteData(@RequestBody HashMap<String, String> jsonString) throws Exception {
        Integer resultData = 0;
        String resultMsg = "更新失败";
        resultData = selectSiteService.updateSiteData(jsonString);
        if (resultData != 0) {
            resultMsg = "更新成功";
        }
        HashMap<String, Object> addMap = new HashMap<String, Object>(10);
        addMap.put("result_status", true);
        addMap.put("result_msg", resultMsg);
        addMap.put("result_data", resultData);
        return addMap;
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
    public Integer deleteSiteData(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.deleteSiteData(jsonString.get("id_all"));
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
    public List<HashMap> getSiteDataByPlaceId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return selectSiteService.getSiteDataByPlaceId(jsonString.get("site_type"), jsonString.get("place_id"));
    }


}