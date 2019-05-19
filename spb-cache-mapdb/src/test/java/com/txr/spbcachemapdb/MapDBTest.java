package com.txr.spbcachemapdb;


import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by xinrui.tian on 2019/4/24
 */
public class MapDBTest {

    @Test
    public void test() {
        //主项目下创建路径
        String path = new StringBuilder(System.getProperty("user.dir")).append("/db").toString();
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        getDBFile("test");  //db名称
    }

    private String getDBFile(String dbName) {
        return new StringBuilder("db/").append(dbName).append(".db").toString();
    }

    @Test
    public void test2() {
        //创建db
        DB db = DBMaker.fileDB(getDBFile("test"))
                .closeOnJvmShutdown()     //在JVM退出之前有一个自动关闭数据库的关闭钩子，但是如果JVM崩溃或被杀死，这将无法保护您的数据。使用DBMaker.closeOnJvmShutdown()选项启用它。
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .checksumHeaderBypass()
                .transactionEnable()  //开启事务 （WAL 提前事务。但是WAL速度较慢，必须在文件之间复制和同步数据。）
                .make();

        //DBMaker.memoryDirectDB().make();  //直接内存


        //.encryptionEnable("password")

        // todoSomething

        db.commit();

        // todoSomething

        db.rollback();

        db.close();
    }

    @Test
    public void test3() {
        DB db = DBMaker.memoryDB().make();
        db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                //.counterEnable().valueLoader(s -> "")
                .createOrOpen();

        db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
               //.counterEnable().valueLoader(s -> 0L)
                .open();


        db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .create();
    }


    @Test
    public void testMemoryDb() {
        DB db = DBMaker.memoryDB().make();  //一个DB实例代表了一个打开的数据库（或单个事务会话）
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");

        for (Object o : map.keySet()) {
            System.out.println(o);
            System.out.println(map.get(o));
        }
    }

    /** read only */
    @Test
    public void testMapDb() {
        DB db = DBMaker.fileDB("test.db").make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        db.commit();
        db.close();

        DB db2 = DBMaker.fileDB("test.db").readOnly().make();
        ConcurrentMap map2 = db2.hashMap("map").createOrOpen();

        Object something = map2.get("something");
        System.out.println(something);

        map2.put("aaa", "bbbb");  //拒绝访问

    }

    //文件mapdb 可用于存储关键启动项

    /** map name */
    @Test
    public void testMapDb3() {
        DB db = DBMaker.fileDB("test1.db").make();
        ConcurrentMap map = db.hashMap("1", Serializer.STRING, Serializer.STRING).createOrOpen();
        map.put("key", "val1");

        db.commit();

        map = db.hashMap("2").createOrOpen();
        map.put("key", "val2");

        db.commit();
        db.close();

        DB db2 = DBMaker.fileDB("test1.db").readOnly().make();

        ConcurrentMap map2 = db2.hashMap("1", Serializer.STRING, Serializer.STRING).createOrOpen();

        Object key = map2.get("key");

        System.out.println(key);

        Map<String, Object> all = db2.getAll();
        System.out.println(all);  //{1=org.mapdb.HTreeMap@207f2c7b, 2=org.mapdb.HTreeMap@34b42295}

        Object o = db2.get("1");
        System.out.println(o);

        Map<String, String> result = (Map<String, String>)o;
        System.out.println(result);
    }

    @Test
    public void testFileDb() {
        DB db = DBMaker.fileDB("file.db").make();
     /*   ConcurrentMap map2 = db.hashMap("map").createOrOpen();
        map2.put("something", "here");
        db.close();*/
        ConcurrentNavigableMap<Integer, String> map = db
                .treeMap("collectionName", Serializer.INTEGER, Serializer.STRING)
                .createOrOpen();

        map.put(1, "one");
        map.put(2, "two");
     //   map.keySet(); //is now [1,2] even before commit

        db.commit();  //persist changes into disk

        map.put(3, "three");
    //    map.keySet(); //is now [1,2,3]
        db.rollback(); //revert recent changes
     //   map.keySet(); //is now [1,2]

        db.close();
    }
}
