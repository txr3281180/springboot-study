package com.txr.spbbasic.csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/5/24
 */
public class CsvDemo {

    @Test
    public void testCsv() {
        String paths = Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "fid.csv").toString();
        Map<String, Integer> stringIntegerMap = loadFidsFromCsvFile(paths);
        System.out.println(stringIntegerMap);
    }


    public Map<String, Integer> loadFidsFromCsvFile(String csvFile) {
        try {
            CsvReader csvReader = new CsvReader(csvFile);
            //String s = csvReader.get(0);
            Map<String, Integer> map = new HashMap<>();
            while (csvReader.readRecord()){
                map.put(csvReader.get(0), Integer.valueOf(csvReader.get(1)));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /****
     *  csv 追加
     */
    public void writeFileToCsv(String[] str, String file) {
        File f = new File(file);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f,true));
            CsvWriter cwriter = new CsvWriter(writer,',');
            cwriter.writeRecord(str,false);
            cwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writerTest() {

        String[] content = {"HAHA", "19", "男"};
        writeFileToCsv(content, "D:/demo1.csv");
    }



    @Test
    public void writer() throws IOException {

        // 第一参数：新生成文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        CsvWriter csvWriter = new CsvWriter("D:/demo.csv", ',', Charset.forName("UTF-8"));

        // 表头和内容
        // String[] headers = {"姓名", "年龄", "性别"};
        // String[] content = {"张三", "18", "男"};
        String[] content = {"HAHA", "19", "男"};

        // 写表头和内容，因为csv文件中区分没有那么明确，所以都使用同一函数，写成功就行
        // csvWriter.writeRecord(headers);
        csvWriter.writeRecord(content);
        //csvWriter.writeComment("aaaaa");

        // 关闭csvWriter
        csvWriter.close();

    }

    @Test
    public void read() throws IOException {

        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        CsvReader csvReader = new CsvReader("F:/demo.csv", ',', Charset.forName("UTF-8"));

        // 如果你的文件没有表头，这行不用执行
        // 这行不要是为了从表头的下一行读，也就是过滤表头
        csvReader.readHeaders();

        // 读取每行的内容
        while (csvReader.readRecord()) {
            // 获取内容的两种方式
            // 1. 通过下标获取
            System.out.print(csvReader.get(0));

            // 2. 通过表头的文字获取
            System.out.println(" " + csvReader.get("年龄"));
        }
    }

}
