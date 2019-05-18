package com.txr.spbbasic.demo.annotation.indexAnno;

import java.io.Serializable;

/**
 * [CDH]CDC中债估值
 * Created by huaijie.chen on 2017/8/28.
 */
@ColumnIndexList(values = {0,3,2,8,4,7,9,10,11,12,13,22,15,16,17,18,19,20,21,28,-1})
public class CdhCdcValuation implements Serializable {

    //债券Key
    @ColumnIndex(value = 0)
    private String bondKey;

    //发行市场
    @ColumnIndex(value = 3)
    private String listedMarket;

    //估值日期
    @ColumnIndex(value = 2)
    private String valuateDate;

    //中债估值
    @ColumnIndex(value = 8)
    private String valYield;

    //剩余期限
    @ColumnIndex(value = 4)
    private String remaintTime;

    //估价净价
    @ColumnIndex(value = 7)
    private String valCleanPrice;

    //估价修正久期
    @ColumnIndex(value = 9)
    private String valModifiedDuration;

    //估价凸性
    @ColumnIndex(value = 10)
    private String valConvexity;

    //估价基点价值
    @ColumnIndex(value = 11)
    private String valBasePointValue;

    //估价利差久期
    @ColumnIndex(value = 12)
    private String valSpreadDuration;

    //估价利差凸性(显示)
    @ColumnIndex(value = 13)
    private String valSpreadConvexity;

    //可信度
    @ColumnIndex(value = 22)
    private String credibility;

    //加权平均结算净价
    @ColumnIndex(value = 15)
    private String marketCleanPrice;

    //加权平均结算价收益率
    @ColumnIndex(value = 16)
    private String marketYield;

    //加权平均结算价修正久期
    @ColumnIndex(value = 17)
    private String marketModifiedDuration;

    //加权平均结算价凸性
    @ColumnIndex(value = 18)
    private String marketConvexity;

    //加权平均结算价基点价值
    @ColumnIndex(value = 19)
    private String marketBasePointValue;

    //加权平均结算价利差久期
    @ColumnIndex(value = 20)
    private String marketSpreadDuration;

    //加权平均结算价利差凸性
    @ColumnIndex(value = 21)
    private String marketSpreadConvexity;

    //收益曲线ID
    @ColumnIndex(value = 28)
    private String yieldCurveID;

    //含权属性
    private String cdcValType;

    public CdhCdcValuation() {}

    public CdhCdcValuation(String cdhCdcValuationStr) {
        if (cdhCdcValuationStr != null) {
            String[] array = cdhCdcValuationStr.split(",", -1);
            this.setBondKey(array[0]);
            this.setListedMarket(array[3]);
            this.setValuateDate(array[2]);
            this.setValYield(array[8]);
            this.setRemaintTime(array[4]);

            this.setValCleanPrice(array[7]);
            this.setValModifiedDuration(array[9]);
            this.setValConvexity(array[10]);
            this.setValBasePointValue(array[11]);
            this.setValSpreadDuration(array[12]);
            this.setValSpreadConvexity(array[13]);
            this.setCredibility(array[22]);
            this.setMarketCleanPrice(array[15]);
            this.setMarketYield(array[16]);
            this.setMarketModifiedDuration(array[17]);
            this.setMarketConvexity(array[18]);
            this.setMarketBasePointValue(array[19]);
            this.setMarketSpreadDuration(array[20]);
            this.setMarketSpreadConvexity(array[21]);
            this.setYieldCurveID(array[28]);
        }
    }

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public String getListedMarket() {
        return listedMarket;
    }

    public void setListedMarket(String listedMarket) {
        this.listedMarket = listedMarket;
    }

    public String getValuateDate() {
        return valuateDate;
    }

    public void setValuateDate(String valuateDate) {
        this.valuateDate = valuateDate;
    }

    public String getValYield() {
        return valYield;
    }

    public void setValYield(String valYield) {
        this.valYield = valYield;
    }

    public String getRemaintTime() {
        return remaintTime;
    }

    public void setRemaintTime(String remaintTime) {
        this.remaintTime = remaintTime;
    }

    public String getValCleanPrice() {
        return valCleanPrice;
    }

    public void setValCleanPrice(String valCleanPrice) {
        this.valCleanPrice = valCleanPrice;
    }

    public String getValModifiedDuration() {
        return valModifiedDuration;
    }

    public void setValModifiedDuration(String valModifiedDuration) {
        this.valModifiedDuration = valModifiedDuration;
    }

    public String getValConvexity() {
        return valConvexity;
    }

    public void setValConvexity(String valConvexity) {
        this.valConvexity = valConvexity;
    }

    public String getValBasePointValue() {
        return valBasePointValue;
    }

    public void setValBasePointValue(String valBasePointValue) {
        this.valBasePointValue = valBasePointValue;
    }

    public String getValSpreadDuration() {
        return valSpreadDuration;
    }

    public void setValSpreadDuration(String valSpreadDuration) {
        this.valSpreadDuration = valSpreadDuration;
    }

    public String getValSpreadConvexity() {
        return valSpreadConvexity;
    }

    public void setValSpreadConvexity(String valSpreadConvexity) {
        this.valSpreadConvexity = valSpreadConvexity;
    }

    public String getCredibility() {
        return credibility;
    }

    public void setCredibility(String credibility) {
        this.credibility = credibility;
    }

    public String getMarketCleanPrice() {
        return marketCleanPrice;
    }

    public void setMarketCleanPrice(String marketCleanPrice) {
        this.marketCleanPrice = marketCleanPrice;
    }

    public String getMarketYield() {
        return marketYield;
    }

    public void setMarketYield(String marketYield) {
        this.marketYield = marketYield;
    }

    public String getMarketModifiedDuration() {
        return marketModifiedDuration;
    }

    public void setMarketModifiedDuration(String marketModifiedDuration) {
        this.marketModifiedDuration = marketModifiedDuration;
    }

    public String getMarketConvexity() {
        return marketConvexity;
    }

    public void setMarketConvexity(String marketConvexity) {
        this.marketConvexity = marketConvexity;
    }

    public String getMarketBasePointValue() {
        return marketBasePointValue;
    }

    public void setMarketBasePointValue(String marketBasePointValue) {
        this.marketBasePointValue = marketBasePointValue;
    }

    public String getMarketSpreadDuration() {
        return marketSpreadDuration;
    }

    public void setMarketSpreadDuration(String marketSpreadDuration) {
        this.marketSpreadDuration = marketSpreadDuration;
    }

    public String getMarketSpreadConvexity() {
        return marketSpreadConvexity;
    }

    public void setMarketSpreadConvexity(String marketSpreadConvexity) {
        this.marketSpreadConvexity = marketSpreadConvexity;
    }

    public String getYieldCurveID() {
        return yieldCurveID;
    }

    public void setYieldCurveID(String yieldCurveID) {
        this.yieldCurveID = yieldCurveID;
    }

    public String getCdcValType() {
        return cdcValType;
    }

    public void setCdcValType(String cdcValType) {
        this.cdcValType = cdcValType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdhCdcValuation that = (CdhCdcValuation) o;

        if (bondKey != null ? !bondKey.equals(that.bondKey) : that.bondKey != null) return false;
        if (listedMarket != null ? !listedMarket.equals(that.listedMarket) : that.listedMarket != null) return false;
        if (valuateDate != null ? !valuateDate.equals(that.valuateDate) : that.valuateDate != null) return false;
        if (valYield != null ? !valYield.equals(that.valYield) : that.valYield != null) return false;
        if (remaintTime != null ? !remaintTime.equals(that.remaintTime) : that.remaintTime != null) return false;
        if (valCleanPrice != null ? !valCleanPrice.equals(that.valCleanPrice) : that.valCleanPrice != null)
            return false;
        if (valModifiedDuration != null ? !valModifiedDuration.equals(that.valModifiedDuration) : that.valModifiedDuration != null)
            return false;
        if (valConvexity != null ? !valConvexity.equals(that.valConvexity) : that.valConvexity != null) return false;
        if (valBasePointValue != null ? !valBasePointValue.equals(that.valBasePointValue) : that.valBasePointValue != null)
            return false;
        if (valSpreadDuration != null ? !valSpreadDuration.equals(that.valSpreadDuration) : that.valSpreadDuration != null)
            return false;
        if (valSpreadConvexity != null ? !valSpreadConvexity.equals(that.valSpreadConvexity) : that.valSpreadConvexity != null)
            return false;
        if (credibility != null ? !credibility.equals(that.credibility) : that.credibility != null) return false;
        if (marketCleanPrice != null ? !marketCleanPrice.equals(that.marketCleanPrice) : that.marketCleanPrice != null)
            return false;
        if (marketYield != null ? !marketYield.equals(that.marketYield) : that.marketYield != null) return false;
        if (marketModifiedDuration != null ? !marketModifiedDuration.equals(that.marketModifiedDuration) : that.marketModifiedDuration != null)
            return false;
        if (marketConvexity != null ? !marketConvexity.equals(that.marketConvexity) : that.marketConvexity != null)
            return false;
        if (marketBasePointValue != null ? !marketBasePointValue.equals(that.marketBasePointValue) : that.marketBasePointValue != null)
            return false;
        if (marketSpreadDuration != null ? !marketSpreadDuration.equals(that.marketSpreadDuration) : that.marketSpreadDuration != null)
            return false;
        if (marketSpreadConvexity != null ? !marketSpreadConvexity.equals(that.marketSpreadConvexity) : that.marketSpreadConvexity != null)
            return false;
        if (yieldCurveID != null ? !yieldCurveID.equals(that.yieldCurveID) : that.yieldCurveID != null) return false;
        return cdcValType != null ? cdcValType.equals(that.cdcValType) : that.cdcValType == null;
    }

    @Override
    public int hashCode() {
        int result = bondKey != null ? bondKey.hashCode() : 0;
        result = 31 * result + (listedMarket != null ? listedMarket.hashCode() : 0);
        result = 31 * result + (valuateDate != null ? valuateDate.hashCode() : 0);
        result = 31 * result + (valYield != null ? valYield.hashCode() : 0);
        result = 31 * result + (remaintTime != null ? remaintTime.hashCode() : 0);
        result = 31 * result + (valCleanPrice != null ? valCleanPrice.hashCode() : 0);
        result = 31 * result + (valModifiedDuration != null ? valModifiedDuration.hashCode() : 0);
        result = 31 * result + (valConvexity != null ? valConvexity.hashCode() : 0);
        result = 31 * result + (valBasePointValue != null ? valBasePointValue.hashCode() : 0);
        result = 31 * result + (valSpreadDuration != null ? valSpreadDuration.hashCode() : 0);
        result = 31 * result + (valSpreadConvexity != null ? valSpreadConvexity.hashCode() : 0);
        result = 31 * result + (credibility != null ? credibility.hashCode() : 0);
        result = 31 * result + (marketCleanPrice != null ? marketCleanPrice.hashCode() : 0);
        result = 31 * result + (marketYield != null ? marketYield.hashCode() : 0);
        result = 31 * result + (marketModifiedDuration != null ? marketModifiedDuration.hashCode() : 0);
        result = 31 * result + (marketConvexity != null ? marketConvexity.hashCode() : 0);
        result = 31 * result + (marketBasePointValue != null ? marketBasePointValue.hashCode() : 0);
        result = 31 * result + (marketSpreadDuration != null ? marketSpreadDuration.hashCode() : 0);
        result = 31 * result + (marketSpreadConvexity != null ? marketSpreadConvexity.hashCode() : 0);
        result = 31 * result + (yieldCurveID != null ? yieldCurveID.hashCode() : 0);
        result = 31 * result + (cdcValType != null ? cdcValType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CdhCdcValuation{" +
                "bondKey='" + bondKey + '\'' +
                ", listedMarket='" + listedMarket + '\'' +
                ", valuateDate='" + valuateDate + '\'' +
                ", valYield='" + valYield + '\'' +
                ", remaintTime='" + remaintTime + '\'' +
                ", valCleanPrice='" + valCleanPrice + '\'' +
                ", valModifiedDuration='" + valModifiedDuration + '\'' +
                ", valConvexity='" + valConvexity + '\'' +
                ", valBasePointValue='" + valBasePointValue + '\'' +
                ", valSpreadDuration='" + valSpreadDuration + '\'' +
                ", valSpreadConvexity='" + valSpreadConvexity + '\'' +
                ", credibility='" + credibility + '\'' +
                ", marketCleanPrice='" + marketCleanPrice + '\'' +
                ", marketYield='" + marketYield + '\'' +
                ", marketModifiedDuration='" + marketModifiedDuration + '\'' +
                ", marketConvexity='" + marketConvexity + '\'' +
                ", marketBasePointValue='" + marketBasePointValue + '\'' +
                ", marketSpreadDuration='" + marketSpreadDuration + '\'' +
                ", marketSpreadConvexity='" + marketSpreadConvexity + '\'' +
                ", yieldCurveID='" + yieldCurveID + '\'' +
                ", cdcValType='" + cdcValType + '\'' +
                '}';
    }
}
