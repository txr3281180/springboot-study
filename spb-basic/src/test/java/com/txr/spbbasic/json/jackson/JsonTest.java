package com.txr.spbbasic.json.jackson;

import com.txr.spbbasic.json.jackson.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/5/19.
 */
public class JsonTest {


    @Test
    public void testJackson() {
        Map<Object, Object> map = new HashMap<>();
        map.put("Type", "55617");
        map.put("Subject", "172.16.73.144:50110_7488");
        map.put("MessageID", "172.16.73.144:50110_7488");
        map.put("ReplyTo", "QB.GATEWAY.AckQueue.dfzqr.18888_1");

        Map<Object, Object> meg = new HashMap<>();
        meg.put("UnderwriterID", "402880f034219aed0134219e1f500795");
        map.put("xUPPCnewBondMarginGuideInfoReq", meg);


        String s = JsonUtils.toJson(map);
        System.out.println(s);
    }

}
