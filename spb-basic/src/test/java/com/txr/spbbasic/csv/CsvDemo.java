package com.txr.spbbasic.csv;

import com.csvreader.CsvReader;
import org.junit.jupiter.api.Test;

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

}
