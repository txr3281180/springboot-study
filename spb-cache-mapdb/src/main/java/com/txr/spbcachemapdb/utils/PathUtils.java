package com.txr.spbcachemapdb.utils;

import java.io.File;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class PathUtils {


    /** 当前项目下创建指定路径 */
    public static String createFolderInPorject(String folder) {
        String path = new StringBuilder(System.getProperty("user.dir")).append(folder).toString();
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        return path;
    }
}
