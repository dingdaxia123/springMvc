package com.springapp.mvc;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by dinghy on 2017/8/8.
 */
public class HttpRequest {

    public static String sendGet(String url , String param){
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = url+"?"+param;
            URL realUrl = new URL(urlNameString);
            //打开和URL之前的链接
            URLConnection connection = realUrl.openConnection();
            //设置通用的请求属性
//            connection.setRequestProperty("contentType", charset);
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立实际的链接
            connection.connect();
            //获取所有响应头字段
            Map<String , List<String>> map = connection.getHeaderFields();
            //遍历所有的头字段
            for(String key : map.keySet()){
                System.out.println(key + "-->" + map.get(key));
            }
            //定义BufferedReader输入流来读取URL响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送get请求发生异常"+e);
            e.printStackTrace();
        }
        //使用finally关闭输入流
        finally {
            try {
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String url , String param){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try{
            URL realUrl = new URL(url);
            //打开和URL之间的链接
            URLConnection connection = realUrl.openConnection();
            //设置属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            connection.setRequestProperty("Charsert", "UTF-8");
            //发送Post必须设的属性
            connection.setDoOutput(true);
            connection.setDoOutput(true);

            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"utf-8"));
            //发送请求参数
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line=in.readLine())!=null){
                line = new String(line.getBytes(), "utf-8");
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args){
        String s = HttpRequest.sendPost("http://localhost:8881/123","key=丁大侠&v=456");
        String get = HttpRequest.sendGet("http://localhost:8881/123","key=压脉带&v=亚麻跌");
        System.out.println(s);
    }

}
