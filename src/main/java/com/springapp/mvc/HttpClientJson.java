package com.springapp.mvc;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.utils.RsaUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dinghy on 2017/8/22.
 */
public class HttpClientJson {

    public static String HttpPostWhitJson(String url) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String resContent = null;


        //Json提交方式

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name" , "丁大侠");
        jsonObject.put("age" , "24");

        StringEntity entity = new StringEntity(jsonObject.toString() , "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        httpPost.setEntity(entity);

        //form表单提交方式
//        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
//        list.add(new BasicNameValuePair("key" , "丁大侠"));
//        list.add(new BasicNameValuePair("age", "23"));
//        httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));


        HttpResponse response = client.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity httpEntity = response.getEntity();
            resContent = EntityUtils.toString(httpEntity , "utf-8");

        }

        return resContent;
    }


    public static void main(String[] args) throws IOException {

//        String type = HttpClientJson.HttpPostWhitJson("http://localhost:8881/12345");
//        System.out.println(type);
//        String aa="Gr1UHIOP7Zy0QiaLiQYfSV+o5XAynMiToa4+bLhtlJsc3rTUKwCgj1SgcrwEgEyXJ6mXcuar8ZNz" +
//                "hBa542nz5LMWpuJj43d7CnoXrBDQaVTTMw2cW53+hmitpL7VbSGQcZZVnPJSkTfns1LN2XSycWrf" +
//                "xtbS8AM1vAFFJDERRMI=";
        String data="transId=daiba_201709215510098978784&accountNumber=43207&cardNo=6227001217260372471&amount=1.0";
        String sign = "uko4oNsf7yQD3lInEfBqRrPV45bUHyOUjox2fZQncSbwLJFFXqBVdBG015Qk9EMSwZWokcnFF5qFsQHv1k8D0B/S/zC6sB2WX4zjm4NZoM8Q2C81UO2igGwxTYH50VSxQV7oONM/6LGKK1z1WJd9GACJUbohAbQS4vbSnsLvKV4=";
        RsaUtils rsaUtils = RsaUtils.getInstance();
        String rsa = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFJx/frL3ceIaummvG4LemJpzW" +
                "xRkcAy6VLkMTwaM0kM3tFV2DRqsRaGxsbNfqYwl64TbLvXYhAoPrC3Hgvurvmyrl" +
                "TqbnaLIpQokjDTiJLi6EO8psR1ltXvWNNLQisMwCxBKob6wIZt7I3K5O2HHTrlkn" +
                "+Emnbo7H8QPKp4QvwQIDAQAB";
//        System.out.println(rsaUtils.signData(data, rsa));
        System.out.println(rsaUtils.verifySignature(sign, data, rsa));
//        System.out.println(aa.equals(sign));
        String one = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI3LtmN0zP5W6XgqYzE5k0mnwicscftX32swq6" +
                "qVhxJBWzCHFP4Ql3wqmFnjSm1LjA26zhcgrokl6kRvkGbVd2erSwTakWesiNgeUCMtNQV5J39Xe6" +
                "BwyctpVXKLGS4UXD5E9c1uiCDw7WQitz5YyCuQNPBz6on7rdn2w1g5HjTQIDAQAB";
        String tue = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI3LtmN0zP5W6XgqYzE5k0mnwicscftX32swq6" +
                "qVhxJBWzCHFP4Ql3wqmFnjSm1LjA26zhcgrokl6kRvkGbVd2erSwTakWesiNgeUCMtNQV5J39Xe6" +
                "BwyctpVXKLGS4UXD5E9c1uiCDw7WQitz5YyCuQNPBz6on7rdn2w1g5HjTQIDAQAB";

        String sui = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI3LtmN0zP5W6XgqYzE5k0mnwicscftX32swq6" +
                "qVhxJBWzCHFP4Ql3wqmFnjSm1LjA26zhcgrokl6kRvkGbVd2erSwTakWesiNgeUCMtNQV5J39Xe6" +
                "BwyctpVXKLGS4UXD5E9c1uiCDw7WQitz5YyCuQNPBz6on7rdn2w1g5HjTQIDAQAB";

        System.out.println(one.equals(tue)&&one.equals(sui));
//        System.out.println(rsa.length());

    }

}
