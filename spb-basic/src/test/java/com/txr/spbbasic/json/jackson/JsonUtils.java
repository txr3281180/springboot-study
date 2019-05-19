package com.txr.spbbasic.json.jackson;

import com.fasterxml.jackson.databind.JavaType;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static String toJson(Object object) {
        String jsonString = JsonMapper.alwaysMapper().toJson(object);
        return jsonString;
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        JavaType javaType = JsonMapper.alwaysMapper().createCollectionType(ArrayList.class, clazz);
        List<T> result = JsonMapper.alwaysMapper().fromJson(json, javaType);
        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    public static <T> T fromJsonToEntity(String json, Class<T> clazz) {
        T result = JsonMapper.alwaysMapper().fromJson(json, clazz);
        return result;
    }


    private <T> Map<String, List<T>> formatListMap(Map map, Class<T> clazz) {
        Map<String, List<T>> result = new HashMap<>();
        if (!CollectionUtils.isEmpty(map)) {
            for (Object o : map.keySet()) {
                result.put((String) o, fromJsonToList((String) map.get(o), clazz));
            }
        }
        return result;
    }

    private <T> Map<String, T> formatMap(Map map, Class<T> clazz) {
        Map<String, T> result = new HashMap<>();
        if (!CollectionUtils.isEmpty(map)) {
            for (Object o : map.keySet()) {
                result.put((String) o, fromJsonToEntity((String) map.get(o), clazz));
            }
        }
        return result;
    }
}
