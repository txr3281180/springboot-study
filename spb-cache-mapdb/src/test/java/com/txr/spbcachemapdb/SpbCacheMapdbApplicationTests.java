package com.txr.spbcachemapdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbCacheMapdbApplicationTests {

    private String filePath;

    @Test
    public void contextLoads() {
    }

    /** 测试 web 路径与 java程序路径 使用相对路径 */
    @Test
    public void testPath() throws FileNotFoundException {

//        String s = new StringBuilder(System.getProperty("user.dir"))
//                .append(File.separator).append("..")
//                .append(File.separator).append("..")
//                .append(File.separator).append("testapp")
//                .append(File.separator).append("db").toString();

        String servicePath = System.getProperty("user.dir");
        String filePath = Paths.get(servicePath,"..", "..", "data", "spb-cache-mapdb", "db", "text.txt").toString();  //java 运行目录
        System.out.println("============" + filePath);

        //D:\MyProject\Work\SDN\cache_manager\..\testapp\db\version.db

       // String path1 = ResourceUtils.getURL("classpath:").getPath();  //web 运行目录
       // System.out.println(path1);


       // String basePath = new ApplicationHome(this.getClass()).getSource().getParentFile().getPath()+ "/files/";
        //System.out.println(basePath);

//        String filePath2 = Paths.get(path1,"..", "..", "testapp", "db").toString();
//        System.out.println("============" + filePath2);

       // File file = new File("D:\\MyProject\\Work\\SDN\\cache_manager\\..\\..\\TEST");
        File file = new File(filePath);
     //   File file = new File("D:\\MyProject\\IdeaWork\\springboot-study\\spb-cache-mapdb\\..\\..\\..\\data" + File.separator + "db");
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("------------------");
            boolean mkdir = file.mkdir();
            System.out.println(mkdir);
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

}
