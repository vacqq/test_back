package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface AnalysSiteRankService {
    public List<HashMap> Select(HashMap<String, String> jsonString);
    public List<HashMap> SelectData(HashMap<String, String> jsonString);
}
