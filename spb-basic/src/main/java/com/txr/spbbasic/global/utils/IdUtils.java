package com.txr.spbbasic.global.utils;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

/**
 * Created by xinrui.tian on 2018/11/14.
 */
public class IdUtils {

    /*UUID 时间戳 随机数： 都可能出现重复情况*/

    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String CURRENT_TIME_MILLIS () {
        long instant = System.currentTimeMillis();
        return String.valueOf(instant);
    }

    public static String INSTANT () {
        Instant instant = Instant.now();
        return String.valueOf(instant.toEpochMilli());
    }

    public static String RANDOM () {
        Random random = new Random();
        int randomNum = random.nextInt(999);
        return String.valueOf(randomNum);
    }
}
