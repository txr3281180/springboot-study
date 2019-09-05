package com.txr.spbcachemapdb.component;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xinrui.tian on 2019/5/19.
 */
@Component
public class MapdbFactory {

    private String getDBFile(String dbName) {
        return new StringBuilder("db/").append(dbName).append(".db").toString();
    }

    //创建 db 文件
    private DB createDB(String dbName) {
        //在主程序根路径下，创建db存放的目录
        String path = new StringBuilder(System.getProperty("user.dir")).append("/db").toString();
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }

        return DBMaker.fileDB(getDBFile(dbName))
                .closeOnJvmShutdown()    //指的是jvm正常关闭时，将会关闭数据库，但是如果使用kill -9等强制关闭措施将导致mapdb的校验和出现问题，下次打开时将出现异常，虽然可以使用checksumHeaderBypass参数规避这个问题，但是官方还是建议使用事务来保证数据的安全性
                .fileMmapEnableIfSupported() //表示如果支持的话使用mmap，也就是在64为操作系统开启，32位不开启，因为太小了
                .fileMmapPreclearDisable()          //是对使用mmap的优化
                .checksumHeaderBypass()        //这是针对使用mmap时，jvm所出现的bug所做的处理，实际上也还有其他问题，具体可参考官方文档和http://www.mapdb.org/blog/mmap_file_and_jvm_crash/
                .transactionEnable()        //开启事务，写的速度下降，但是数据安全了
                .concurrencyScale(128)      //数据库内部本质还是读写锁，因此更高的并发度设置在并发写的时候可以提供写性能
                .make();
    }

    //创建或打开map
    protected ConcurrentMap createOrOpen(DB db) {
        return db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    //创建map", "map存在则抛出异常
    protected ConcurrentMap createMap(DB db) {
        return db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .create();
    }

    //打开map，map不存在则抛出DBException.WrongConfiguration异常
    protected ConcurrentMap openMap(DB db) {
        return db.hashMap("map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
                .open();
    }

}
