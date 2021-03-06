package com.txr.spbbasic.csv;

import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/5/24
 */
public class CsvDemo {



    @Test
    public void testFile() {
        //File f = new File("D:/a.txt");
        //File f = new File("D:/a/a.txt");
        //File f = new File("D:/a");
        String fName = new StringBuilder("D:/lcfx_data").append("/").append("TFBONDHISTORY").append(".csv").toString();
        File f = new File(fName);
//        if (f.exists()) {
//            f.delete();
//        }

        System.out.println("是否是存在"+ f.exists());
        System.out.println("是否是文件"+ f.isFile());
        System.out.println("是否是文件夹"+ f.isDirectory());

//        if (f.exists() && f.isFile()) {
//            f.delete();
//        }

        //f.deleteOnExit();  //当程序停止时才删除

//        File f1 = new File("D:/a");
//
//        try {
//            FileUtils.deleteDirectory(f1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCsv() {
        String paths = Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "fid.csv").toString();
        //Map<String, Integer> stringIntegerMap = loadFidsFromCsvFile(paths);
        //System.out.println(stringIntegerMap);
        updateCsv(paths);
    }

    public void updateCsv(String csvFile) {
        CsvWriter cwriter = null;
        CsvReader csvReader = null;
        try {
            csvReader = new CsvReader(csvFile, ',', Charset.forName("utf-8"));
            List<String []> list = new ArrayList<>();
            while (csvReader.readRecord()){
                //System.out.println(csvReader.getRawRecord());
                if (!csvReader.get(0).equals("3")) {
                    String rawRecord = csvReader.getRawRecord();
                    String[] split = rawRecord.split(",", -1);
                    list.add(split);
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(csvFile, false);
            cwriter = new CsvWriter(fileOutputStream,',', Charset.forName("utf-8"));
            for (String[] s : list) {
                cwriter.writeRecord(s,true);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cwriter != null) {
                cwriter.close();
            }
            if (csvReader != null) {
                csvReader.close();
            }
        }
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

    /** 从文件中删除内容 */
    @Test
    public void testDeleteDataFromFile() {
        File f = new File("d:/macro_base_index_values.csv");
        //delFormCsv(f, 7, "2012-03-27");
        try {
            delFormCsv2(f, 7, "2012-04-24");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void delFormCsv2(File f, int index, String val) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));

        BufferedWriter bw = new BufferedWriter(new FileWriter("temp.csv"));
        String str;
        while((str = br.readLine()) != null){
            CsvReader csvReader = new CsvReader(str);
            if (!csvReader.get(index).contains(val)) {
                bw.write(str);
                bw.newLine(); //也可以直接bw.write(str+"\n");
                bw.flush();
            }
        }
    }


    public static void delFormCsv(File f, int index, String val) {
        CsvWriter cscWriter = null;
        CsvReader csvReader = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(f);
            csvReader = new CsvReader(fileInputStream, ',', Charset.forName("utf-8"));
            List<String []> list = new ArrayList<>();
            csvReader.getHeaders();
            while (csvReader.readRecord()){
                if (!csvReader.get(index).contains(val)) {
                    String [] values = csvReader.getValues();
                    list.add(values);
                }
            }

            fileOutputStream = new FileOutputStream(f, false);
            cscWriter = new CsvWriter(fileOutputStream,',', Charset.forName("utf-8"));
            for (String[] s : list) {
                cscWriter.writeRecord(s,true);
            }
        } catch (Exception e) {
            System.out.println("Csv del error");
        } finally {
            if (cscWriter != null) {
                cscWriter.close();
            }
            if (csvReader != null) {
                csvReader.close();
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     *  csv 追加
     */
    public void writeFileToCsv(String[] str, String file) {
        File f = new File(file);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f,true)); // 是否追加
            CsvWriter cwriter = new CsvWriter(writer,',');
            cwriter.writeRecord(str,false);  //是否写入后指针在下一行
            //cwriter.write(str, false);  //写入单个
            cwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  追加解决中文乱码
     */
    public static void writeFileToCsv(List<String[]> str, File f) {
        CsvWriter cwriter = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(f, true);
            cwriter = new CsvWriter(fileOutputStream,',', Charset.forName("utf-8"));
            for (String[] s : str) {
                cwriter.writeRecord(s,false);
            }
        } catch (IOException e) {
            //logger.error("Write csv error. file name {},  data {}",  f.getName(), str.toString());
        } finally {
            if (cwriter != null) {
                cwriter.close();
            }
        }
    }

    @Test
    public void testFile2() {
        File f = new File("D:/lcfx_data/a.txt");
        String fileName = "cdc_bond_valuation.csv";
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            recursiveFile(files, fileName);
        }
    }

    /**
     *  递归逐层删除指定文件
     */
    private void recursiveFile(File[] files, String fileName) {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] files1 = file.listFiles();
                recursiveFile(files1, fileName);
            } else {
                if (file.isFile() && file.getName().equals(fileName)) {
                    file.delete();
                    File parent = file.getParentFile();
                    String[] list = parent.list();
                    if(list == null || list.length == 0) {
                        System.out.println(parent);
                        try {
                            FileUtils.deleteDirectory(parent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * 测试字段中有逗号的情况
     */
    @Test
    public void writerTest() {
//        String[] content = new String[3];
//        String a = "Ha,Ha";
//        String b = "19";
//        String c = "男";
//        content[0] = a;
//        content[1] = b;
//        content[2] = c;
//        writeFileToCsv(content, "D:/demo1.csv");

        JSONObject line = new JSONObject();
        line.put("名称", "Ha,Ha");
        line.put("年龄", "19");
        line.put("名称2", "Ha,Ha");
        line.put("性别", "男");

        String [] str = new String[4];
        str[0] = line.getString("名称");
        str[1] = line.getString("年龄");
        str[2] = line.getString("名称2");
        str[3] = line.getString("性别");

        writeFileToCsv(str, "e:/lcfx_data/demo1.csv");

        // 写入时为改值添加双引号

//        try {
////            CsvReader csvReader = new CsvReader("D:/demo1.csv", ',', Charset.forName("UTF-8"));
////            while (csvReader.readRecord()) {
////                System.out.print(csvReader.get(0) + " " +  csvReader.get(1) +  " " +  csvReader.get(2));
////                System.out.println();
////                System.out.println(csvReader.getRawRecord());
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        try {
//            CsvReader csvReader = new CsvReader("e:/lcfx_data/demo1.csv", ',', Charset.forName("UTF-8"));
//            while (csvReader.readRecord()) {
//                System.out.print(csvReader.get(0) + " " +  csvReader.get(1) +  " " +  csvReader.get(2) + "\n");
//                System.out.println(csvReader.getRawRecord());  // 获取整行string
//
//               // String[] values = csvReader.getValues();
//                //writeFileToCsv(values, "D:/demo2.csv");
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        csvWriter.writeRecord(content, true);  // true 换行
        //csvWriter.writeComment("aaaaa");

        // 关闭csvWriter
        csvWriter.close();
    }

    @Test
    public void read() throws IOException {
        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        CsvReader csvReader = new CsvReader("D:/demo.csv", ',', Charset.forName("UTF-8"));

        // 如果你的文件没有表头，这行不用执行
        // 这行不要是为了从表头的下一行读，也就是过滤表头
        boolean b = csvReader.readHeaders();

        //csvReader.getHeaders();

        // 读取每行的内容
        while (csvReader.readRecord()) {
            // 获取内容的两种方式
            // 1. 通过下标获取
            System.out.print(csvReader.get(0));

            // 2. 通过表头的文字获取
            System.out.println(" " + csvReader.get("年龄"));
        }
    }

    @Test
    public void read2() {
        StringBuilder sb = new StringBuilder("e:/lcfx_data");
        String path = sb.toString();
        File file = new File(path);

        String f = sb.append(File.separator).append("demo1").append(".csv").toString();

        try {
            new CsvReader(f);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}
