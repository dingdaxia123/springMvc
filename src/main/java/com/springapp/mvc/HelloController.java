package com.springapp.mvc;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping(value="/123",produces = "application/json;charset=UTF-8")
public class HelloController {
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String printWelcome(ModelMap model,HttpServletRequest request, HttpServletResponse response , String key , String v) throws Exception {
		String CharacterEncoding = "UTF-8";
		request.setCharacterEncoding(CharacterEncoding);
		String BillNo = request.getParameter("key");
		BillNo = URLDecoder.decode(BillNo,"UTF-8");
		String OrderNo=request.getParameter("v");
		String Amount = request.getParameter("name");
		String tradeOrder = request.getParameter("age");
		String Succeed = request.getParameter("Succeed");
		String Result = request.getParameter("Result");
		String SignMD5info = request.getParameter("SignInfo");
		String MD5key = "ecpss123";
//		model.addAttribute("message", "我");
//		String data = (String)request.getAttribute("data");
//		String ds = request.getParameter("name");
//		JSONArray json= JSONArray.fromObject(ds);

//		JSONArray jn =  JSONArray.fromObject(json.toString());
//		if(jn.size()>0){
//			for (int i = 0; i < jn.size(); i++) {
//				JSONObject jo = (JSONObject) jn.get(i);
//				System.out.println(jo.get("id"));
//				System.out.println(jo.get("fdName"));
//			}
//			System.out.println(jn);
//		}
//		System.out.println("数据大小："+jn.size());

		HcServlet json = new HcServlet();
//		json.doPost(request , response);
		return "ok";
	}
}