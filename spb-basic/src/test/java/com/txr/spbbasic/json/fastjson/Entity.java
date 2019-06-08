package com.txr.spbbasic.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by xinrui.tian on 2019/6/4
 */
public class Entity {

    @JSONField(name = "bondKey")
    private String key;

    @JSONField(name = "bondName")
    private String name;


    @JSONField(name = "issuerName")
    private String issuer;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", issuer='" + issuer + '\'' +
                '}';
    }
}
