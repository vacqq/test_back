package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface YjWarningService {
    public List<HashMap> getWarningData(HashMap<String, String> jsonString);

    public Integer InsertWarningData(HashMap<String, Object> jsonString);

    public List<HashMap> SelectRuleTableList();

    public Integer UpdateRuleWord(HashMap<String, String> jsonString);
}
