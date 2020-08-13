package com.txr.spbbasic.global;

import java.util.Map;

/**
 * created by xinrui.tian on 2020/8/12
 */
public class MonitorConfig {
    private String db_name;
    private String source_id;
    private String address;
    // private String table;
    private Map<String, String> table;


    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public String getTable() {
//        return table;
//    }
//
//    public void setTable(String table) {
//        this.table = table;
//    }


    public Map<String, String> getTable() {
        return table;
    }

    public void setTable(Map<String, String> table) {
        this.table = table;
    }
}
