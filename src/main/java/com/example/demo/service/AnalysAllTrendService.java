package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface AnalysAllTrendService {

    public List<HashMap> Select(HashMap<String, String> jsonString, int site_id);
}