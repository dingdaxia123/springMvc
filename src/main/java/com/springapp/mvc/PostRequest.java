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
            //��������
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST"); //�������󷽷�
            connection.setRequestProperty("Charsert", "UTF-8"); //�����������
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.connect();

            //POST����
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream()); //�ؼ���һ��


            out.writeBytes(obj);
            out.flush();
            out.close();

            // ��ȡ��Ӧ
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
            }//����ֵΪ200�����ȷ����Ӧ��Ϣ

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
            }//����ֵ�����������ķ�����Ϣ
            // �Ͽ�����
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
        String post = PostRequest1("http://localhost:8881/123","key=ѹ����");
        System.out.println(post);
    }

}
