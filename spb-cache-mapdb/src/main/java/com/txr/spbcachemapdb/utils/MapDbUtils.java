package com.txr.spbcachemapdb.utils;

import org.mapdb.*;
import org.mapdb.volume.Volume;

import java.util.NavigableSet;

/**
 * Created by xinrui.tian on 2019/6/21
 */
public class MapDbUtils {

    /*
    * MapDB是一个开源（Apache 2.0许可），嵌入式Java数据库引擎和集合框架。
    *
    * 它提供带有范围查询，到期，压缩，堆外存储和流式传输的Map，Set，List，队列，位图。
    * MapDB可能是速度最快的Java数据库，其性能可与java.util集合相媲美。它还提供高级功能，如ACID事务，快照，增量备份等等。
    */

    public void testMap() {
        //concurrency 并发性
        HTreeMap<String, String> hashMap = DBMaker.memoryShardedHashMap(8).keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
        HTreeMap.KeySet<String> hashSet = DBMaker.memoryShardedHashSet(8).serializer(Serializer.STRING).createOrOpen();
        HTreeMap<String, String> treeMap = DBMaker.heapShardedHashMap(8).keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
        HTreeMap.KeySet<String> treeSet = DBMaker.heapShardedHashSet(8).serializer(Serializer.STRING).createOrOpen();

        DB memoryDB = DBMaker.memoryDB().make();
        Atomic.Long atomic = memoryDB.atomicLong("name1").createOrOpen();

        // .valueLoader(x -> { System.out.println(x);  return "AAA"; })  //当值获取不到时指定返回值，默认为null; x - map.get的参数
        //.counterEnable() :启用大小计数器，在这种情况下map.size()是即时的，但插入有一些开销  //60000的性能影响不大

        HTreeMap<String, String> hashMap2 = memoryDB.hashMap("name2").keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
        HTreeMap.KeySet<String> hashSet2 = memoryDB.hashSet("name3").serializer(Serializer.STRING).createOrOpen();
        BTreeMap<String, String> treeMap2 = memoryDB.treeMap("name4").keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
        NavigableSet<String> treeSet2 = memoryDB.treeSet("name5").serializer(Serializer.STRING).createOrOpen();
    }

    public void testDB() {
        DB fileDB = DBMaker.fileDB("db/test.db").fileChannelEnable().closeOnJvmShutdown().fileMmapEnableIfSupported().fileMmapPreclearDisable().transactionEnable().make();

        //在临时文件夹中创建新数据库。关闭存储后将删除文件
        DB tempFileDB = DBMaker.tempFileDB().make();

        //创建新的内存数据库。JVM退出后，更改将丢失。此选项将数据序列化为{@code byte[]}，因此它们不受垃圾收集器的影响。
        DB memoryDB = DBMaker.memoryDB().make();
        DB memoryDirectDB = DBMaker.memoryDirectDB().make();

        //创建新的内存数据库，该数据库在没有序列化的情况下将所有数据存储在堆上。这种模式应该非常快，但是数据将以与传统Java收集相同的方式影响垃圾收集器。
        DB heapDB = DBMaker.heapDB().make();

        //DBMaker.volumeDB()  //Volume.UNSAFE_VOL_FACTORY
    }


    private static MapDbUtils mapDbUtils = new MapDbUtils();

    public static MapDbUtils mapDbUtils() {
        return mapDbUtils;
    }

    private MapDbUtils() { }

    public DB fileDB(String dbFile) {
        //.readOnly() // 使用写操作会 拒绝访问
        return DBMaker.fileDB(dbFile).fileChannelEnable().closeOnJvmShutdown().fileMmapEnableIfSupported().fileMmapPreclearDisable().transactionEnable().make();
    }

    public DB.HashMapMaker<String, String> hashMap(DB db) {
        return hashMap(db, "hashMap");
    }
    public DB.HashSetMaker<String> hashSet(DB db) {
        return hashSet(db, "hashSet");
    }

    public DB.TreeMapMaker<String, String> treeMap(DB db) {
        return treeMap(db, "treeMap");
    }

    public DB.TreeSetMaker<String> treeSet(DB db) {
        return treeSet(db, "treeSet");
    }

    public DB.HashMapMaker<String, String> hashMap(DB db, String collectionName) {
        return db.hashMap(collectionName).keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).counterEnable();
    }

    public DB.HashSetMaker<String> hashSet(DB db, String collectionName) {
        return db.hashSet(collectionName).serializer(Serializer.STRING).counterEnable();
    }

    public DB.TreeMapMaker<String, String> treeMap(DB db, String collectionName) {
        return db.treeMap(collectionName).keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).counterEnable();
    }

    public DB.TreeSetMaker<String> treeSet(DB db, String collectionName) {
        return db.treeSet(collectionName).serializer(Serializer.STRING).counterEnable();
    }

}
