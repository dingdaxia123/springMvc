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
            //�򿪺�URL֮ǰ������
            URLConnection connection = realUrl.openConnection();
            //����ͨ�õ���������
//            connection.setRequestProperty("contentType", charset);
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //����ʵ�ʵ�����
            connection.connect();
            //��ȡ������Ӧͷ�ֶ�
            Map<String , List<String>> map = connection.getHeaderFields();
            //�������е�ͷ�ֶ�
            for(String key : map.keySet()){
                System.out.println(key + "-->" + map.get(key));
            }
            //����BufferedReader����������ȡURL��Ӧ
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("����get�������쳣"+e);
            e.printStackTrace();
        }
        //ʹ��finally�ر�������
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
            //�򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            //��������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            connection.setRequestProperty("Charsert", "UTF-8");
            //����Post�����������
            connection.setDoOutput(true);
            connection.setDoOutput(true);

            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"utf-8"));
            //�����������
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

        //ʹ��finally�����ر��������������
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
        String s = HttpRequest.sendPost("http://localhost:8881/123","key=������&v=456");
        String get = HttpRequest.sendGet("http://localhost:8881/123","key=ѹ����&v=�����");
        System.out.println(s);
    }

}
