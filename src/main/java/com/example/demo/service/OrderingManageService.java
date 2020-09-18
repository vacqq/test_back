package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface OrderingManageService {
    public int SelectCountOrderingDateType(HashMap<String, String> jsonString);

    public List<HashMap> SelectOrderingData(HashMap<String, String> jsonString);

    public HashMap SelectOrderingById(HashMap<String, String> jsonString);

    public Integer InsertOrdering(HashMap<String, String> jsonString);

    public Integer UpdateOrdering(HashMap<String, String> jsonString);

    public Integer DeleteOrdering(HashMap<String, String> jsonString);
}
