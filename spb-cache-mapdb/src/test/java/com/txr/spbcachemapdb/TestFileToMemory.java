package com.txr.spbcachemapdb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.txr.spbcachemapdb.utils.PathUtils;
import kotlin.Pair;
import org.junit.Test;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.mapdb.Store;

import java.io.Serializable;
import java.util.*;

/**
 * Created by xinrui.tian on 2019/7/2
 */
public class TestFileToMemory {

    @Test
    public void test1() {
        PathUtils.createFolderInProject("/db");

        DB db = DBMaker.fileDB(getDBFile("bond"))
                .fileChannelEnable()
                .closeOnJvmShutdown()
                .fileMmapEnable()
                //.transactionEnable()
                .cleanerHackEnable()
                .allocateStartSize(10 * 1024*1024*1024)  // 10GB
                .allocateIncrement(512 * 1024*1024)       // 512MB
                .fileMmapPreclearDisable()
                .make();


       /* Map<String,String> map = new HashMap<>(20000);
        int pageNo = 1;
        int count = 1;
        for (int i = 0; i < 10000; i++) {
            List<Bond> allBond = getAllBond();
            int len = allBond.size();
            for (int j = 0; j < len; j++) {

                if (map.size() == 20000) {
                    HTreeMap<String, String> bond = db
                            .hashMap(String.valueOf(pageNo))
                            .counterEnable()
                            .keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
                    bond.putAll(map);
                    db.commit();
                    ++pageNo;
                    map.clear();
                }

                map.put(count + "", JSON.toJSONString(allBond.get(j)));
                count ++;
            }

        }

        if (!map.isEmpty()) {
            HTreeMap<String, String> bond = db
                .hashMap(String.valueOf(pageNo))
                .counterEnable()
                .keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
            bond.putAll(map);
            db.commit();
            ++pageNo;
            map.clear();
        }

*/
        long start = System.currentTimeMillis();
        Iterable<String> allNames = db.getAllNames();
        Iterator<String> iterator = allNames.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        long end = System.currentTimeMillis();
        System.out.println("========》1 :" + (end -start));


        //Spliterator接口是1.8新加的接口，字面意思可分割的迭代器
        Spliterator<String> spliterator = allNames.spliterator();
        System.out.println("estimateSize :" + spliterator.estimateSize());
        System.out.println("characteristics :" + spliterator.characteristics());
//        System.out.println("getComparator :" + spliterator.getComparator());
        System.out.println("getExactSizeIfKnown :" + spliterator.getExactSizeIfKnown());

        Spliterator<String> stringSpliterator = spliterator.trySplit();
        System.out.println("trySplit :" + stringSpliterator);

        /*如果有剩余的元素存在，执行参数给定的操作，并返回true，否则就返回false。
            如果Spliterator对象具有ORDERED属性，那么tryAdvance也会按照相应的顺序去执行。*/
        spliterator.tryAdvance((x) -> {
            System.out.println(x);
        });


        long start1 = System.currentTimeMillis();
        //获取所有分片  速度延时
        Map<String, Object> all = db.getAll();

        long end1 = System.currentTimeMillis();
        System.out.println("========》2 :" + (end1-start1));



        System.out.println(db.exists("11"));
        System.out.println(db.exists("10"));



        long start2 = System.currentTimeMillis();
        Map<String, String> o = (Map<String, String>)db.get("10");

        long end2 = System.currentTimeMillis();
        System.out.println("========》3 :" + (end2 -start2));


        String nameForObject = db.getNameForObject(JSONObject.class);


        db.getAllNames().forEach(x -> System.out.println(x));

        System.out.println("ddddd");


    }


    @Test
    public void test5() {
        PathUtils.createFolderInProject("/db");
        DB db = DBMaker.fileDB(getDBFile("bondStore"))
                .fileChannelEnable()
                .closeOnJvmShutdown()
                .fileMmapEnable()
                //.transactionEnable()
                .cleanerHackEnable()
                .allocateStartSize(10 * 1024*1024*1024)  // 10GB
                .allocateIncrement(512 * 1024*1024)       // 512MB
                .fileMmapPreclearDisable()
                .make();

     /*   int count = 1;
        for (int i = 0; i < 100; i++) {
            Map<String,String> map = new HashMap<>(10000);
            for (int j = 0; j < 500; j++) {
                List<Bond> allBond = getAllBond();
                int len = allBond.size();
                for (int k = 0; k < len; k++) {
                    map.put(count + "", JSON.toJSONString(allBond.get(k)));
                    count ++;
                }
            }

            HTreeMap<String, String> bond = db
                    .hashMap(String.valueOf((i + 1)))
                    .counterEnable()
                    .keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();

            bond.putAll(map);
            db.commit();
        }*/

        long start5 = System.currentTimeMillis();
        /*Object o = db.get("1");
        Map<String, JSON> o1 = (Map<String, JSON>) o;*/


        Iterable<String> allNames = db.getAllNames();
        allNames.forEach(x -> System.out.println(x));


        // JSON json = o1.get("1");
       // System.out.println(json.toJSONString());
        long end5 = System.currentTimeMillis();
        System.out.println("get data:" + (end5 - start5));
    }



    private String getDBFile(String dbName) {
        return new StringBuilder("db/").append(dbName).append(".db").toString();
    }


    public List<Bond> getAllBond () {
        List<Bond> bondList = new ArrayList<>();
        bondList.add(new Bond("E0000062014CORLEB01", "14恩施城投债", "恩施城市建设投资有限公司", "LEB", 110000L, 1.2, "2020-09-25"));
        bondList.add(new Bond("L0000532014CORPPN01", "14兴泸PPN001", "泸州市兴泸投资集团有限公司", "PPN", 100000L, 7.1, "2021-10-20"));
        bondList.add(new Bond("H0001112014CORPPN03", "14鄂联投PPN003", "湖北省联合发展投资集团有限公司", "PPN", 200000L, 2.2, "2020-09-10"));
        bondList.add(new Bond("H0001792014CORPPN02", "14淮南矿PPN002", "淮南矿业(集团)有限责任公司", "PPN", 200000L, 6.5, "2018-08-12"));
        bondList.add(new Bond("G0000472015NCD070", "15广发银行CD070", "广发银行股份有限公司", "SHD", 255000L, 7.5, "2018-07-03"));
        bondList.add(new Bond("J0002642015NCD082", "15江苏江南农村商业银行CD082", "江苏江南农村商业银行股份有限公司", "RRD", 10000L, 3.2, "2020-09-09"));
        bondList.add(new Bond("Z0000152015NCD140", "15招行CD140", "招商银行股份有限公司", "SHD", 50000L, 6.5, "2020-09-25"));
        bondList.add(new Bond("J0002642015NCD081", "15江苏江南农村商业银行CD081", "江苏江南农村商业银行股份有限公司", "RRD", 5000L, 6.1, "2020-09-25"));
        bondList.add(new Bond("N0000722015NCD152", "15宁波银行CD152", "宁波银行股份有限公司", "CCD", 100000L, 4.5, "2020-09-25"));
        bondList.add(new Bond("P0000072015NCD015", "15平安CD015", "平安银行股份有限公司", "SHD", 10000L, 1.2, "2030-10-01"));
        bondList.add(new Bond("P0000072015NCD016", "15平安CD016", "平安银行股份有限公司", "SHD", 10000L, 1.2, "2030-10-01"));
        bondList.add(new Bond("Z0010312016NCD002", "16珠海农商行CD002", "珠海农村商业银行股份有限公司", "RRD", 100000L, 5.0, "2019-03-23"));
        bondList.add(new Bond("Z0010312016NCD004", "16珠海农商行CD004", "珠海农村商业银行股份有限公司", "RRD", 50000L, 5.0, "2018-01-10"));
        bondList.add(new Bond("Z0010312016NCD003", "16珠海农商行CD003", "珠海农村商业银行股份有限公司", "RRD", 110000L, 5.0, "2017-05-23"));
        bondList.add(new Bond("Z0001302015NCD013", "15光大CD013", "中国光大银行股份有限公司", "SHD", 200000L, 7.2, "2019-09-25"));
        bondList.add(new Bond("Z0010312016NCD001", "16珠海农商行CD001", "珠海农村商业银行股份有限公司", "RRD", 120000L, 7.8, "2017-03-10"));
        bondList.add(new Bond("B0000892016NCD002", "16渤海银行CD002", "渤海银行股份有限公司", "SHD", 200000L, 2.4, "2018-09-25"));
        bondList.add(new Bond("H0000912016NCD018", "16恒丰银行CD018", "恒丰银行股份有限公司", "SHD", 130000L, 5.6, "2020-01-09"));
        bondList.add(new Bond("Z0001302015NCD012", "15光大CD012", "中国光大银行股份有限公司", "SHD", 50000L, 0.0, "2019-01-25"));
        bondList.add(new Bond("Z0001302015NCD012", "15光大CD012", "中国光大银行股份有限公司", "SHD", 50000L, 0.0, "2019-01-25"));
        return bondList;
    }

    class Bond implements Serializable {

        private String bondKey;
        private String bondName;
        private String issuerName;
        private String bondType;
        private Long issuePrice;
        private Double couponRate;
        private String maturityDate;

        public Bond() {
        }

        public Bond(String bondKey, String bondName, String issuerName, String bondType, Long issuePrice, Double couponRate, String maturityDate) {
            this.bondKey = bondKey;
            this.bondName = bondName;
            this.issuerName = issuerName;
            this.bondType = bondType;
            this.issuePrice = issuePrice;
            this.couponRate = couponRate;
            this.maturityDate = maturityDate;
        }

        public String getBondKey() {
            return bondKey;
        }

        public void setBondKey(String bondKey) {
            this.bondKey = bondKey;
        }

        public String getBondName() {
            return bondName;
        }

        public void setBondName(String bondName) {
            this.bondName = bondName;
        }

        public String getIssuerName() {
            return issuerName;
        }

        public void setIssuerName(String issuerName) {
            this.issuerName = issuerName;
        }

        public String getBondType() {
            return bondType;
        }

        public void setBondType(String bondType) {
            this.bondType = bondType;
        }

        public Long getIssuePrice() {
            return issuePrice;
        }

        public void setIssuePrice(Long issuePrice) {
            this.issuePrice = issuePrice;
        }

        public Double getCouponRate() {
            return couponRate;
        }

        public void setCouponRate(Double couponRate) {
            this.couponRate = couponRate;
        }

        public String getMaturityDate() {
            return maturityDate;
        }

        public void setMaturityDate(String maturityDate) {
            this.maturityDate = maturityDate;
        }

        @Override
        public String toString() {
            return "Bond{" +
                    "bondKey='" + bondKey + '\'' +
                    ", bondName='" + bondName + '\'' +
                    ", issuerName='" + issuerName + '\'' +
                    ", bondType='" + bondType + '\'' +
                    ", issuePrice=" + issuePrice +
                    ", couponRate=" + couponRate +
                    ", maturityDate='" + maturityDate + '\'' +
                    '}';
        }
    }
}
