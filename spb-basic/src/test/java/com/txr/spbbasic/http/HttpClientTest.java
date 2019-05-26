package com.txr.spbbasic.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/5/21
 */
public class HttpClientTest {

    private final String ENCODING = "UTF-8";

    @Test
    public void httpGet() {
        String url = "http://localhost:8280/txr/study/main/bondKeys";

        // 1.创建httpClient对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 2.创建http对象
        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)  //设置请求超时时间
                .setSocketTimeout(10000)    //响应超时时间
                .build();

        httpGet.setConfig(requestConfig);
        httpGet.setHeader("userId", "txr");  //设置请求头

        CloseableHttpResponse httpResponse = null;
        try {
            //3.执行请求
            httpResponse = client.execute(httpGet);

            //4.处理结果
            String result = EntityUtils.toString(httpResponse.getEntity(), ENCODING);;

            JSONObject jsonObject  = JSON.parseObject(result); //fastJson
            JSONArray data = jsonObject.getJSONArray("data");
            for (Object datum : data) {
                System.out.println(datum);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            if (!ObjectUtils.isEmpty(httpResponse)) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!ObjectUtils.isEmpty(client)) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void httpPost() {
        String url = "http://localhost:8280/txr/study/main/xinrui/bondInfo";

        // 1.创建httpClient对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 2.创建http对象
        HttpPost httpPost = new HttpPost(url);

        // 3.设置请求头
        /*httpPost.setHeader("Cookie", "");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");*/
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");

        // 4.设置请求参数
//        Bond bond = new Bond();
//        bond.setBondKey("P0000072015NCD016");
//        String param = JSON.toJSONString(bond);
        Map<String, String> map = new HashMap<>();
        map.put("bondKey", "P0000072015NCD016");
        StringEntity entity = new StringEntity(JSON.toJSONString(map), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        CloseableHttpResponse httpResponse = null;
        try {
            //5.执行请求
            httpResponse = client.execute(httpPost);

            //6.处理结果
            String result = EntityUtils.toString(httpResponse.getEntity(), ENCODING);

            JSONObject jsonObject  = JSON.parseObject(result); //fastJson
            JSONArray data = jsonObject.getJSONArray("data");
            for (Object datum : data) {
                System.out.println(datum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            if (!ObjectUtils.isEmpty(httpResponse)) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!ObjectUtils.isEmpty(client)) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
