package com.springapp.mvc;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dinghy on 2017/8/24.
 */
@Controller
@RequestMapping(value="/12345",produces = "application/json;charset=UTF-8")
public class HcServlet {

    private static final long serialVersionUID  = 1 ;

    protected void doGet(HttpServletRequest request , HttpServletResponse response){

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    protected String doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String accptjson = "";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));

        StringBuffer stringBuffer = new StringBuffer("");
        String temp;

        while((temp=bufferedReader.readLine()) != null){
            stringBuffer.append(temp);
        }

        bufferedReader.close();
        accptjson =stringBuffer.toString();

        if(accptjson != ""){

            JSONObject jsonObject = JSONObject.parseObject(accptjson);
            String name = jsonObject.getString("name");
            String age = jsonObject.getString("age");

            return "ok";

        }

        return "false";


    }


}
