package com.txr.spbcachemapdb.utils;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

/**
 * Created by xinrui.tian on 2019/6/21
 */
public class MapDbUtils {

    private static MapDbUtils mapDbUtils = new MapDbUtils();

    private MapDbUtils() { }

    public static MapDbUtils MapDbUtils() {
        return mapDbUtils;
    }

    public DB makeDB(String dbFile) {
        return DBMaker.fileDB(dbFile).fileChannelEnable().closeOnJvmShutdown().fileMmapEnableIfSupported().fileMmapPreclearDisable().transactionEnable().make();
    }

    public DB.HashMapMaker<String, Object> hashMap(DB db) {
        return hashMap(db, "hashMap");
    }

    public DB.HashSetMaker<String> hashSet(DB db) {
        return hashSet(db, "hashSet");
    }

    public DB.TreeMapMaker<String, Object> treeMap(DB db) {
        return treeMap(db, "treeMap");
    }

    public DB.TreeSetMaker<String> treeSet(DB db) {
        return treeSet(db, "treeSet");
    }

    //counterEnable() :启用大小计数器，在这种情况下map.size()是即时的，但插入有一些开销
    public DB.HashMapMaker<String, Object> hashMap(DB db, String collectionName) {
        return db.hashMap(collectionName).keySerializer(Serializer.STRING).valueSerializer(Serializer.ILLEGAL_ACCESS).counterEnable();
    }

    public DB.HashSetMaker<String> hashSet(DB db, String collectionName) {
        return db.hashSet(collectionName).serializer(Serializer.STRING).counterEnable();
    }

    public DB.TreeMapMaker<String, Object> treeMap(DB db, String collectionName) {
        return db.treeMap(collectionName).keySerializer(Serializer.STRING).valueSerializer(Serializer.ILLEGAL_ACCESS).counterEnable();
    }

    public DB.TreeSetMaker<String> treeSet(DB db, String collectionName) {
        return db.treeSet(collectionName).serializer(Serializer.STRING).counterEnable();
    }

}
