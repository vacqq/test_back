package com.example.demo.service;


import com.example.demo.bean.SysSiteEntity;

import java.util.HashMap;
import java.util.List;

public interface SelectSiteService {

    public String SelectNameById(String Id);

    public HashMap SelectSiteDataById(String Id);

    public String SelectPlaceNameById(String Id);

    public String SelectIdByName(String name);

    public List<SysSiteEntity> SelectNameInId(String IdRange);

    public List<HashMap> SelectSiteIdNameBySiteType(HashMap<String, String> jsonString);

    public List<HashMap> SelectSiteIdNameBySiteTypeId(HashMap<String, String> jsonString);

    public List<HashMap> SelectHeatMapDataList(HashMap<String, String> jsonString);

    public List<HashMap> SelectDictionariesByType(HashMap<String, String> jsonString);

    public List<HashMap> SelectPlaceIdByType(String type);

    public List<SysSiteEntity> GetPlaceTreeList(String place_id);

    public List<HashMap> GetPlaceTreeListQuick(String place_id);

    public List<HashMap> GetPlaceTreeListInParentId(String place_id);

    public List<HashMap> SelectSiteList(HashMap<String, String> jsonString);

    public List<HashMap> getDateBySiteId(HashMap<String, String> jsonString);

    public HashMap getLastSiteDataBySiteid(HashMap<String, String> jsonString);

    public Integer countSiteNameIsHave(String name);

    public Integer InsertSiteData(HashMap<String, Object> jsonString);

    public Integer UpdateSiteData(HashMap<String, String> jsonString);

    public Integer DeleteSiteData(String id_all);

    public List<HashMap> GetSiteDataByPlaceId(String site_type, String place_id);

}
