package com.txr.spbcachemapdb;


import com.txr.spbcachemapdb.utils.MapDbUtils;
import com.txr.spbcachemapdb.utils.PathUtils;
import org.junit.Test;
import org.mapdb.Atomic;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by xinrui.tian on 2019/4/24
 */
public class TestMapDB {

    @Test
    public void test() {
        //主项目下创建路径
        PathUtils.createFolderInProject("/db");
        getDBFile("test");  //db名称
    }

    /** 测试 File PATH  */
    @Test
    public void testPath3() {

        //String path = "/opt/sumscope/cdh";
        String path = "\\opt\\sumscope/cdh";

        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            //file.mkdir();
        }

        System.out.println(file);

        System.out.println(file.toPath());
    }

    /** 测试 web 路径与 java程序路径 使用相对路径 */
    @Test
    public void testPath() {

//        String s = new StringBuilder(System.getProperty("user.dir"))
//                .append(File.separator).append("..")
//                .append(File.separator).append("..")
//                .append(File.separator).append("testapp")
//                .append(File.separator).append("db").toString();

        String servicePath = System.getProperty("user.dir");
        String filePath = Paths.get(servicePath,"..", "..", "testapp", "db").toString();

        //D:\MyProject\Work\SDN\cache_manager\..\..\testapp\db\version.db

        System.out.println(filePath);

        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("------------------");
        } else {
            String path = file.getPath();
            System.out.println(path);

            DB db = DBMaker.fileDB(path + File.separator + "test.db")
                    .closeOnJvmShutdown()
                    .fileMmapEnableIfSupported()
                    .fileMmapPreclearDisable()
                    .fileChannelEnable()
                    .checksumHeaderBypass()
                    .make();
        }

    }

    /** 测试 File PATH 根据系统自动转换路径分隔符  */
    @Test
    public void testPath2() {

        File f = new File("D:\\MyProject\\My\\springboot-study\\spb-cache-mapdb\\src\\main\\java\\com\\txr\\spbcachemapdb\\utils\\PathUtils.java");

        //File[] files = f.listFiles();
        Path path = f.toPath();
        System.out.println(path);



        File f2 = new File("D:/MyProject/My/springboot-study/spb-cache-mapdb/src/main/java/com/txr/spbcachemapdb/utils/PathUtils.java");
        Path path2 = f2.toPath();
        System.out.println(path2);


        String str = "d:/abc";
        String str2 = "d:\\abc";

        File file = new File(str2);
        System.out.println(file.toPath());
    }

    private String getDBFile(String dbName) {
        return new StringBuilder("db/").append(dbName).append(".db").toString();
    }

    /** Atomic */
    @Test
    public void test1() {
        DB db = DBMaker.fileDB(getDBFile("test1"))
                .closeOnJvmShutdown()     //在JVM退出之前有一个自动关闭数据库的关闭钩子，但是如果JVM崩溃或被杀死，这将无法保护您的数据。使用DBMaker.closeOnJvmShutdown()选项启用它。
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .fileChannelEnable()
                //.readOnly()  // 文件必须存在
                .checksumHeaderBypass()
                //.transactionEnable()  //开启事务 （WAL 提前事务。但是WAL速度较慢，必须在文件之间复制和同步数据。）
                .make();

       // .fileChannelEnable() /  .readOnly()  //只能用一个
        Atomic.Long aa = db.atomicLong("aa").createOrOpen();
        System.out.println(aa);
        aa.incrementAndGet();

        System.out.println(aa);
        db.commit();

        Atomic.Long bb = db.atomicLong("bb").createOrOpen();

        bb.set(10);
        db.commit();
        System.out.println(bb);

        db.close();
    }

    /** 测试强转后是否为引用赋值：结论否 */
    @Test
    public void test4() {
        DB db = DBMaker.fileDB(getDBFile("test4"))
                .closeOnJvmShutdown()     //在JVM退出之前有一个自动关闭数据库的关闭钩子，但是如果JVM崩溃或被杀死，这将无法保护您的数据。使用DBMaker.closeOnJvmShutdown()选项启用它。
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .checksumHeaderBypass()
                .transactionEnable()  //开启事务 （WAL 提前事务。但是WAL速度较慢，必须在文件之间复制和同步数据。）
                .make();

        ConcurrentMap map = db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .counterEnable().valueLoader(s -> "")
                .createOrOpen();

        Map<String, String> mapR = new HashMap<>();
        mapR.put("a", "A");
        map.put("abc", mapR);
        db.commit();

        Map<String, String> mapZ = (Map<String, String>)map.get("abc");
        System.out.println(mapZ);
        mapZ.put("b", "B");

        db.commit();

        Map<String, String> mapX = (Map<String, String>)map.get("abc");
        System.out.println(mapX);

    }


    @Test
    public void test5() {
        PathUtils.createFolderInProject("/db");

        DB db = MapDbUtils.mapDbUtils().fileDB(getDBFile("test"));

        HTreeMap<String, String> map = MapDbUtils.mapDbUtils().hashMap(db).createOrOpen();

        map.put("1", "abc");
        map.put("2", "efc");

        db.commit();
        System.out.println(map.get("2"));

        map.clear();
        System.out.println(map.get("2"));
        db.commit();

        map.put("1", "abc");
        map.put("2", "efc");

        db.commit();
        System.out.println(map.get("2"));

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

        map2.put("aaa", "bbbb");

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

        Map<Integer, String> javaMap = new HashMap<>();
        javaMap.put(3, null);
        //map.put(4, null);   // map db 值为null 报空指针
        map.putAll(javaMap);

        //   map.keySet(); //is now [1,2] even before commit

        db.commit();  //persist changes into disk

        map.put(3, "three");
    //    map.keySet(); //is now [1,2,3]
        db.rollback(); //revert recent changes
     //   map.keySet(); //is now [1,2]

        db.close();
    }
}
