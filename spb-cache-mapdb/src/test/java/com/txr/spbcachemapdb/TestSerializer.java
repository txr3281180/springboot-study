package com.txr.spbcachemapdb;

import com.alibaba.fastjson.JSON;
import com.txr.spbcachemapdb.utils.MapDbUtils;
import com.txr.spbcachemapdb.utils.PathUtils;
import org.junit.Test;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class TestSerializer {


    @Test
    public void test1() {




    }


    /**
     * 测试：
     * 序列化  ：  Serializer.JAVA  Serializer.String
     * 取值 ：treeMap hashMap
     * 每次清空所有数据，首次存取
     */
    @Test
    public void testJavaSerializer() {

        //最优 Serializer.String     hashMap

        long start3 = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>(20000);
        //Map<String, Object> map = new HashMap<>(20000);

       // Map<String, String> map = new TreeMap<>();
       // Map<String, Object> map = new TreeMap<>();

        int count = 1;
        for (int i = 0; i < 3000; i++) {
            List<Bond> allBond = getAllBond();
            for (int j = 0; j < allBond.size(); j++) {
                map.put(count + "", JSON.toJSONString(allBond.get(j)));
                //map.put(count + "", JSON.toJSON(allBond.get(j)));
                count ++;
            }
        }

        PathUtils.createFolderInPorject("/db");

        DB db = MapDbUtils.mapDbUtils().fileDB(getDBFile("bond"));

        HTreeMap<String, String> bond = db
        //HTreeMap<String, Object> bond = db
        //BTreeMap<String, String> bond = db
        //BTreeMap<String, Object> bond = db
                .hashMap("bond")
                //.treeMap("bond")
                .counterEnable()
                //.keySerializer(Serializer.STRING).valueSerializer(Serializer.JAVA).createOrOpen();
                .keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();

        bond.clear();
        db.commit();

        long start = System.currentTimeMillis();
        bond.putAll(map);
        db.commit();
        long end = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        //Object o = bond.get("888");
        //System.out.println(JSON.toJSONString(o));
        System.out.println(bond.get("19999"));
        long end2 = System.currentTimeMillis();

        long end3 = System.currentTimeMillis();
        //System.out.println("Serializer.JAVA -> hashMap【 ALL:" + (end3 - start3) + "  putAll:" + (end - start) + "  get:" + (end2 - start2) + "】");
        System.out.println("Serializer.STRING -> hashMap【 ALL:" + (end3 - start3) + "  putAll:" + (end - start) + "  get:" + (end2 - start2) + "】");
        //System.out.println("Serializer.JAVA -> treeMap【 ALL:" + (end3 - start3) + "  putAll:" + (end - start) + "  get:" + (end2 - start2) + "】");
        //System.out.println("Serializer.STRING -> treeMap【 ALL:" + (end3 - start3) + "  putAll:" + (end - start) + "  get:" + (end2 - start2) + "】");


        long l = System.currentTimeMillis();
        System.out.println(bond.size());
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        //Serializer.JAVA -> hashMap【 ALL:4797  putAll:3183  get:36】  //JSON.toJSONString
        //Serializer.JAVA -> hashMap【 ALL:4919  putAll:3201  get:36】
        //Serializer.JAVA -> hashMap【 ALL:4965  putAll:3284  get:37】
        //Serializer.JAVA -> hashMap【 ALL:5255  putAll:3608  get:36】
        //Serializer.JAVA -> hashMap【 ALL:5024  putAll:3247  get:37】
        //Serializer.JAVA -> hashMap【 ALL:6547  putAll:5412  get:39】  //JSON.toJSON
        //Serializer.JAVA -> hashMap【 ALL:5354  putAll:3815  get:40】
        //Serializer.JAVA -> hashMap【 ALL:5364  putAll:3827  get:41】
        //Serializer.JAVA -> hashMap【 ALL:5316  putAll:3792  get:40】
        //Serializer.JAVA -> hashMap【 ALL:5870  putAll:3737  get:42】
        //Serializer.JAVA -> hashMap【 ALL:5951  putAll:4775  get:57】  //首次删除文件
        //Serializer.JAVA -> hashMap【 ALL:6897  putAll:5513  get:42】
        //Serializer.JAVA -> hashMap【 ALL:5398  putAll:4291  get:38】
        //Serializer.JAVA -> hashMap【 ALL:6392  putAll:5243  get:39】
        //Serializer.JAVA -> hashMap【 ALL:5015  putAll:3986  get:39】

        //Serializer.STRING -> hashMap【 ALL:5115  putAll:3112  get:0】 //JSON.toJSONString
        //Serializer.STRING -> hashMap【 ALL:4882  putAll:3454  get:0】
        //Serializer.STRING -> hashMap【 ALL:5032  putAll:3268  get:0】
        //Serializer.STRING -> hashMap【 ALL:4868  putAll:3140  get:1】
        //Serializer.STRING -> hashMap【 ALL:4902  putAll:3296  get:1】
        //Serializer.STRING -> hashMap【 ALL:4800  putAll:3513  get:1】  //首次删除文件
        //Serializer.STRING -> hashMap【 ALL:4518  putAll:3359  get:1】
        //Serializer.STRING -> hashMap【 ALL:4700  putAll:3149  get:0】
        //Serializer.STRING -> hashMap【 ALL:4427  putAll:3322  get:1】
        //Serializer.STRING -> hashMap【 ALL:4320  putAll:3173  get:1】

        //Serializer.JAVA -> treeMap【 ALL:7844  putAll:3211  get:0】     //JSON.toJSONString
        //Serializer.JAVA -> treeMap【 ALL:8664  putAll:3714  get:0】
        //Serializer.JAVA -> treeMap【 ALL:9001  putAll:4208  get:0】
        //Serializer.JAVA -> treeMap【 ALL:8098  putAll:3434  get:0】
        //Serializer.JAVA -> treeMap【 ALL:8588  putAll:3876  get:0】
        //Serializer.JAVA -> treeMap【 ALL:12117  putAll:5669  get:4】 //JSON.toJSON
        //Serializer.JAVA -> treeMap【 ALL:13119  putAll:5737  get:4】
        //Serializer.JAVA -> treeMap【 ALL:12408  putAll:5458  get:4】
        //Serializer.JAVA -> treeMap【 ALL:12385  putAll:5529  get:4】
        //Serializer.JAVA -> treeMap【 ALL:13367  putAll:5907  get:4】
        //Serializer.JAVA -> treeMap【 ALL:7292  putAll:6258  get:2】  //首次删除文件
        //Serializer.JAVA -> treeMap【 ALL:8570  putAll:7407  get:5】
        //Serializer.JAVA -> treeMap【 ALL:8653  putAll:7620  get:1】
        //Serializer.JAVA -> treeMap【 ALL:7437  putAll:6146  get:2】
        //Serializer.JAVA -> treeMap【 ALL:7652  putAll:6495  get:1】
        //Serializer.JAVA -> treeMap【 ALL:12030  putAll:11028  get:8】
        //Serializer.JAVA -> treeMap【 ALL:12016  putAll:10998  get:6】

        //Serializer.STRING -> treeMap【 ALL:6518  putAll:5362  get:1】  //JSON.toJSONString
        //Serializer.STRING -> treeMap【 ALL:7585  putAll:3489  get:0】
        //Serializer.STRING -> treeMap【 ALL:7590  putAll:3557  get:0】
        //Serializer.STRING -> treeMap【 ALL:7142  putAll:3242  get:0】
        //Serializer.STRING -> treeMap【 ALL:7326  putAll:2997  get:0】
        //Serializer.STRING -> treeMap【 ALL:6790  putAll:5383  get:1】 //首次删除文件
        //Serializer.STRING -> treeMap【 ALL:6868  putAll:5230  get:1】
        //Serializer.STRING -> treeMap【 ALL:6649  putAll:5288  get:1】
        //Serializer.STRING -> treeMap【 ALL:6889  putAll:5941  get:1】
    }



    @Test
    public void testJavaSerializer2() {
        long start3 = System.currentTimeMillis();

        PathUtils.createFolderInPorject("/db");

        DB db = MapDbUtils.mapDbUtils().fileDB(getDBFile("bond"));

        //HTreeMap<String, String> bond = db
        //HTreeMap<String, Object> bond = db
        BTreeMap<String, String> bond = db
        //BTreeMap<String, Object> bond = db
                //.hashMap("bond")
                .treeMap("bond")
                .counterEnable()
                //.keySerializer(Serializer.STRING).valueSerializer(Serializer.JAVA).createOrOpen();
        .keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();

        bond.clear();
        db.commit();

        long start = System.currentTimeMillis();
        int count = 1;
        for (int i = 0; i < 3000; i++) {
            List<Bond> allBond = getAllBond();
            int len = allBond.size();
            for (int j = 0; j < len; j++) {
                bond.put(count + "", JSON.toJSONString(allBond.get(j)));
                //bond.put(count + "", JSON.toJSON(allBond.get(j)));
                count ++;
                //db.commit();  // time too long
            }
        }

        db.commit();

        long end = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        System.out.println(bond.get("19999"));
        long end2 = System.currentTimeMillis();

        long end3 = System.currentTimeMillis();
        //System.out.println("Serializer.JAVA -> hashMap【 ALL:" + (end3 - start3) + "  put:" + (end - start) + "  get:" + (end2 - start2) + "】");
        //System.out.println("Serializer.STRING -> hashMap【 ALL:" + (end3 - start3) + "  put:" + (end - start) + "  get:" + (end2 - start2) + "】");
        //System.out.println("Serializer.JAVA -> treeMap【 ALL:" + (end3 - start3) + "  put:" + (end - start) + "  get:" + (end2 - start2) + "】");
        System.out.println("Serializer.STRING -> treeMap【 ALL:" + (end3 - start3) + "  put:" + (end - start) + "  get:" + (end2 - start2) + "】");

        //Serializer.JAVA -> hashMap【 ALL:4404  put:3334  get:35】
        //Serializer.JAVA -> hashMap【 ALL:4766  put:3428  get:43】
        //Serializer.JAVA -> hashMap【 ALL:4955  put:3536  get:39】
        //Serializer.JAVA -> hashMap【 ALL:5156  put:3745  get:35】
        //Serializer.JAVA -> hashMap【 ALL:4900  put:3458  get:35】

        //Serializer.STRING -> hashMap【 ALL:4206  put:3333  get:1】
        //Serializer.STRING -> hashMap【 ALL:4662  put:3341  get:1】
        //Serializer.STRING -> hashMap【 ALL:4668  put:3362  get:1】
        //Serializer.STRING -> hashMap【 ALL:4757  put:3417  get:0】
        //Serializer.STRING -> hashMap【 ALL:4762  put:3369  get:0】

        //Serializer.JAVA -> treeMap【 ALL:8585  put:3654  get:0】
        //Serializer.JAVA -> treeMap【 ALL:8651  put:4000  get:0】
        //Serializer.JAVA -> treeMap【 ALL:9232  put:4451  get:0】
        //Serializer.JAVA -> treeMap【 ALL:8706  put:3888  get:0】
        //Serializer.JAVA -> treeMap【 ALL:8351  put:3721  get:0】

        //Serializer.STRING -> treeMap【 ALL:7638  put:6814  get:1】
        //Serializer.STRING -> treeMap【 ALL:7055  put:3140  get:0】
        //Serializer.STRING -> treeMap【 ALL:7538  put:3341  get:0】
        //Serializer.STRING -> treeMap【 ALL:7704  put:3096  get:0】
        //Serializer.STRING -> treeMap【 ALL:7957  put:3337  get:0】


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
