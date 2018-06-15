package com.zhibo8;

import java.util.HashMap;
import java.util.Map;

public class testss {
    public static void main(String[] args) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        for (Map.Entry<String, Object> stringObjectEntry : stringObjectHashMap.entrySet()) {
            String key = stringObjectEntry.getKey();
            String value = String.valueOf(stringObjectEntry.getValue());
        }
    }
}
