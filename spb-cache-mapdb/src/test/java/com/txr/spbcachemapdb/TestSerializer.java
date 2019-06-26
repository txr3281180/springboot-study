package com.txr.spbcachemapdb;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class TestSerializer {


    @Test
    public void testJavaSerializer() {




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
