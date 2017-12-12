package com.springapp.mvc;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dinghy on 2017/8/21.
 */
public class HttpClientUtil {

    public String doPost(String url , Map<String,String> map , String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;

        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);

            //…Ë÷√≤Œ ˝
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();

            while (iterator.hasNext()){
                Map.Entry<String,String> entry = (Map.Entry<String,String>)iterator.next();
                list.add(new BasicNameValuePair(entry.getKey() , entry.getValue()));
            }

            if(list.size()>0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list , charset);
                httpPost.setEntity(entity);
            }

            HttpResponse response = httpClient.execute(httpPost);

            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }

        return result;


    }


}
