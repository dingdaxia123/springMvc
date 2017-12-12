package com.springapp.mvc;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dinghy on 2017/8/15.
 */
public class PostRequest {
    public static String PostRequest1(String URL,String obj) {
        String jsonString="";
        try {
            //创建连接
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST"); //设置请求方法
            connection.setRequestProperty("Charsert", "UTF-8"); //设置请求编码
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream()); //关键的一步


            out.writeBytes(obj);
            out.flush();
            out.close();

            // 读取响应
            int http = connection.getResponseCode();
            if (http==200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String lines;
                StringBuffer sb = new StringBuffer("");
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sb.append(lines);
                }
                jsonString=sb.toString();
                reader.close();
            }//返回值为200输出正确的响应信息

            if (connection.getResponseCode()==400) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String lines;
                StringBuffer sb = new StringBuffer("");
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sb.append(lines);
                }
                jsonString=sb.toString();
                reader.close();
            }//返回值错误，输出错误的返回信息
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonString;
    }

    public static void main(String[] args){
        String post = PostRequest1("http://localhost:8881/123","key=压脉带");
        System.out.println(post);
    }

}
