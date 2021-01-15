package com.txr.spbbasic;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class SpbBasicApplication {

    public static void main(String[] args) {

        //CommandLineRunner 启动时执行某些方法
        //下面该方式启动后最先执行某些方法：  getBean : 从spring容器中获取该类  .method(): 执行要调用的方法
        //SpringApplication.run(SpbBasicApplication.class, args).getBean(XXXX.class).method();

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        SpringApplication.run(SpbBasicApplication.class, args);

        // =============================获取 pom 信息================================
        String rootPath = System.getProperty("user.dir");
        MavenXpp3Reader reader = new MavenXpp3Reader();
        String myPom = rootPath + File.separator + "pom.xml";
        Model model = null;
        try {
            model = reader.read(new FileReader(myPom));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String projectName =  model.getArtifactId();  //获取当前项目名称
        String version = model.getVersion();//获取版本号
        System.out.println(projectName + " : " + version);

    }

}
