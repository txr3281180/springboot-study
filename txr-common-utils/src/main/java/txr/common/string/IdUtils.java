package txr.common.string;

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
        System.out.println(instant.toEpochMilli());
        // 微妙 + 纳秒
        System.out.println(instant.getNano());
        return null;
    }

    public static String RANDOM () {
        Random random = new Random();
        int randomNum = random.nextInt(999);
        return String.valueOf(randomNum);
    }


    public static void main(String[] args) {

        int i = 100;
        while (i > 0) {
            // String s = IdUtils.CURRENT_TIME_MILLIS();
            String s = IdUtils.INSTANT();
            //System.out.println(s);
            i--;
        }
    }

}
