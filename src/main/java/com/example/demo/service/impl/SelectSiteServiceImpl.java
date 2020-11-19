package com.example.demo.service.impl;

import com.example.demo.bean.SysSiteEntity;
import com.example.demo.mapper.SelectSiteMapper;
import com.example.demo.service.SelectSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
/**
 * @author lcz
 * @date 2020/4/23 15:42
 */
public class SelectSiteServiceImpl implements SelectSiteService {
    final
    SelectSiteMapper selectSiteMapper;

    public SelectSiteServiceImpl(SelectSiteMapper selectSiteMapper) {
        this.selectSiteMapper = selectSiteMapper;
    }

    @Override
    public String selectNameById(String id) {
        return selectSiteMapper.selectNameById(id);
    }

    @Override
    public HashMap selectSiteDataById(String id) {
        return selectSiteMapper.selectSiteDataById(id);
    }

    @Override
    public String selectPlaceNameById(String id) {
        return selectSiteMapper.selectPlaceNameById(id);
    }

    @Override
    public String selectIdByName(String name) {
        return selectSiteMapper.selectIdByName(name);
    }

    @Override
    public List<SysSiteEntity> selectNameInId(String idRange) {
        return selectSiteMapper.selectNameInId(idRange);
    }

    @Override
    public List<HashMap> selectSiteIdNameBySiteType(HashMap<String, String> jsonString) {
        return selectSiteMapper.selectSiteIdNameBySiteType(jsonString.get("site_type"), jsonString.get("place_id"), jsonString.get("air_type"), jsonString.get("start_time"), jsonString.get("end_time"));
    }

    @Override
    public List<HashMap> selectSiteIdNameBySiteTypeId(HashMap<String, String> jsonString) {
        return selectSiteMapper.selectSiteIdNameBySiteTypeId(jsonString.get("site_type"), jsonString.get("place_id"));
    }

    @Override
    public List<HashMap> selectHeatMapDataList(HashMap<String, String> jsonString) {
        return selectSiteMapper.selectHeatMapDataList(jsonString.get("site_type"), jsonString.get("place_id"), jsonString.get("air_type"), jsonString.get("start_time"), jsonString.get("end_time"));
    }

    @Override
    public List<HashMap> selectDictionariesByType(HashMap<String, String> jsonString) {
        return selectSiteMapper.selectDictionariesByType(jsonString.get("type"));
    }

    @Override
    public List<SysSiteEntity> getPlaceTreeList(String placeId) {
        return selectSiteMapper.getPlaceTreeList(placeId);
    }

    @Override
    public List<HashMap> getPlaceTreeListQuick(String placeId) {
        return selectSiteMapper.getPlaceTreeListQuick(placeId);
    }

    @Override
    public List<HashMap> getPlaceTreeListInParentId(String placeId) {
        return selectSiteMapper.getPlaceTreeListInParentId(placeId);
    }

    @Override
    public List<HashMap> selectPlaceIdByType(String type) {
        return selectSiteMapper.selectPlaceIdByType(type);
    }

    @Override
    public List<HashMap> selectSiteList(HashMap<String, String> jsonString) {
        return selectSiteMapper.selectSiteList(jsonString.get("site_type"), jsonString.get("place_id"), jsonString.get("lat"), jsonString.get("lng"), jsonString.get("site_name"));
    }

    @Override
    public List<HashMap> getDateBySiteId(HashMap<String, String> jsonString) {
        List<HashMap> hashMapList = selectSiteMapper.getDateBySiteId(jsonString.get("site_id"),
                jsonString.get("data_type"), jsonString.get("start_time"), jsonString.get("end_time"));
        return hashMapList;
    }

    @Override
    public HashMap getLastSiteDataBySiteId(HashMap<String, String> jsonString) {
        return selectSiteMapper.getLastSiteDataBySiteid(jsonString.get("site_id"));
    }


    @Override
    public Integer countSiteNameIsHave(String name) {
        return selectSiteMapper.countSiteNameIsHave(name);
    }

    @Override
    public Integer insertSiteData(HashMap<String, Object> jsonString) {
        return selectSiteMapper.insertSiteData(jsonString.get("place_id"), jsonString.get("site_type"), jsonString.get("name"), jsonString.get("only_name"), jsonString.get("lng"), jsonString.get("lat"), jsonString.get("position"));
    }

    @Override
    public Integer updateSiteData(HashMap<String, String> jsonString) {
        return selectSiteMapper.updateSiteData(jsonString.get("id"), jsonString.get("place_id"), jsonString.get("site_type"), jsonString.get("name"), jsonString.get("only_name"), jsonString.get("lng"), jsonString.get("lat"), jsonString.get("position"), jsonString.get("modify_by"));
    }

    @Override
    public Integer deleteSiteData(String idAll) {
        return selectSiteMapper.deleteSiteData(idAll);
    }


    @Override
    public List<HashMap> getSiteDataByPlaceId(String siteType, String placeId) {
        return selectSiteMapper.getSiteDataByPlaceId(siteType, placeId);
    }

}
