package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface AnalysisSiteRelevanceService {

    public List<HashMap> SelectData(HashMap<String, String> jsonString, String date_time);

    public List<HashMap> SelectDataSiteRange(HashMap<String, String> jsonString, String date_time);

    public List<HashMap> SelectDateTime(HashMap<String, String> jsonString);
}
