package com.txr.spbbasic.json.jackson;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by xinrui.tian on 2018/12/18
 */
public class JsonMapper {
    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private ObjectMapper mapper;

    public JsonMapper() {
        this((Include)null);
    }

    public JsonMapper(Include include) {
        this.mapper = new ObjectMapper();
        if (include != null) {
            this.mapper.setSerializationInclusion(include);
        }

        this.mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(Include.NON_EMPTY);
    }

    public static JsonMapper nonDefaultMapper() {
        return new JsonMapper(Include.NON_DEFAULT);
    }

    public static JsonMapper alwaysMapper() {
        return new JsonMapper(Include.ALWAYS);
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }

    public String toJson(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (IOException var3) {
            logger.warn("write to json string error:" + object, var3);
            return null;
        }
    }

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, clazz);
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, javaType);
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public JavaType createCollectionType(Class<?> collectionClass, Class... elementClasses) {
        return this.mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public <T> T update(String jsonString, T object) {
        try {
            return this.mapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException var4) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", var4);
        } catch (IOException var5) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", var5);
        }

        return null;
    }
}
