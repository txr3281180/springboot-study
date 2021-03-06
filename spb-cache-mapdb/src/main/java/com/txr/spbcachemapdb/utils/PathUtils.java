package com.txr.spbcachemapdb.utils;

import java.io.File;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class PathUtils {


    /** 当前项目下创建指定路径 */
    public static String createFolderInProject(String folder) {
        //File  System.getProperty 自动根据系统使用分正确的隔符

        String path = new StringBuilder(System.getProperty("user.dir")).append(folder).toString();
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        return path;
    }


    public static String createDbFilePath(String dbFilePath) {
        if (dbFilePath == null || "".equals(dbFilePath.trim())) {
            dbFilePath = System.getProperty("user.dir") + "/db";
        }

        File file = new File(dbFilePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }

        return dbFilePath;
    }

    public static String buildDBFile(String dbPath, String dbName) {
        return dbPath + File.separator + dbName + ".db";
    }

}
