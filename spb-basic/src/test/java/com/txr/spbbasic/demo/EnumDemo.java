package com.txr.spbbasic.demo;

import org.junit.Test;

/**
 * Created by xinrui.tian on 2018/12/20
 */
public class EnumDemo {

    @Test
    public void testEnum() {
        System.out.println(RankingEnum.S.name());
        System.out.println(RankingEnum.S.toString());
        System.out.println(RankingEnum.S.getRanking());
        System.out.println(RankingEnum.S.getOrder());

        System.out.println("===============================");

        RankingEnum s = RankingEnum.valueOf("S");
        System.out.println(s);

        //Ranking.valueOf(null);  // 空指针
        //Ranking.valueOf("aa");  //找不到参数报参数错误

        //抓取异常封装
        RankingEnum rankingEnum = RankingEnum.valueOfEnum(null);
        System.out.println(rankingEnum);
        System.out.println(rankingEnum.getRanking());

        System.out.println("================================");

        RankingEnum[] values = RankingEnum.values();
        for (RankingEnum value : values) {
            System.out.print(value);
        }

    }

    enum RankingEnum {

        S("最优", 1),  //有参构造器
        A("优", 2),
        B("良", 3),
        C("中", 4),
        D("差", 5),
        OTHER;      //使用无参构造器

        private String ranking;
        private int order;

        RankingEnum() { }

        RankingEnum(String ranking, int order) {
            this.ranking = ranking;
            this.order = order;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public static RankingEnum valueOfEnum(String val) {
            try {
                return valueOf(val);
            } catch (Exception e) {
                //return null;
                return OTHER;
            }
        }
    }
}

