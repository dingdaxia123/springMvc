package com.springapp.mvc;

import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by dinghy on 2017/8/22.
 */
public class HttpJson {

    public static boolean HttpPostWhitJson(JSONObject jsonObject , String url , String appId){

        boolean isSuccess = false;
        HttpPost httpPost = null;

        try{

            HttpClient httpClient = new DefaultHttpClient();

            //设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT , 2000);

            httpPost = new HttpPost(url);
            //构造消息头
            httpPost.setHeader("Content-type" , "application/json ; charset=utf-8");
            httpPost.setHeader("Connection", "Close");
            String sessionId = getSessionId();
            httpPost.setHeader("SessionId" , sessionId);
            httpPost.setHeader("appId", appId);

            //构造消息实体
            StringEntity entity = new StringEntity(jsonObject.toString() , Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            //发送JSON格式请求
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            //检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                System.out.println("请求出错:"+statusCode);
                isSuccess = false;
            }else{
                int retCode = 0;
                String id = "";
                //返回码中包含resCode及会话Id
                for(Header header: response.getAllHeaders()){
                    if(header.getName().equals("retCode")){
                        retCode = Integer.parseInt(header.getValue());
                    }

                    if(header.getName().equals("SessionId")){
                        id = header.getValue();
                    }

                }


//                if(ErrorCodeHelper.IAS_SUCCESS != retCode ){
//                    // 日志打印
//                    System.out.println("error return code,  sessionId: "+id+"\t" + "retCode: " + retCode);
//                    isSuccess = false;
//                }else{
//                    isSuccess = true;
//                }
            }

        }catch(Exception e){
            isSuccess = false;
        }finally{
            if(httpPost != null){
                try {
                    httpPost.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    // 构建唯一会话Id
    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

}
