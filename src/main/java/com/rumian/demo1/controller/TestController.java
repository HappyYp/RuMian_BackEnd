package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.model.Admin;
import com.rumian.demo1.model.User;
import com.rumian.demo1.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/admin")
public class TestController {
    @Resource
    AdminService adminService;
    @PostMapping(value = "testObj")
    public void test(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html; charset=utf-8");
            request.setCharacterEncoding("utf-8");
            InputStream iStream = request.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, "utf-8"));
            String string = null;
            StringBuffer sBuffer = new StringBuffer();
            while((string = bReader.readLine()) != null) {
                sBuffer.append(string);
                System.out.println("输入流不为空none");

            }
            String useStr = sBuffer.toString();
            System.out.println("useStr++++++++++" + useStr);
            String jsonString = JSONObject.toJSONString(useStr);
            System.out.println("jsonString++++++++++" + jsonString);
            User user = new User();
            JSONObject jsonObject = JSONObject.parseObject(useStr);
           // user.setName(jsonObject.getString("name"));
           // user.setAge(jsonObject.getInteger("age"));
            /**
             * DAO层,写入数据库
             */
            int result = adminService.insertUser(user);
            JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(user);
            System.out.println("插入数据库操作结束");
            /**
             * 给手机端返回的数据
             */
            if(result != 0)

                response.getWriter().append(jsonObject1.toString());
            else
                response.getOutputStream().write("0".getBytes("UTF-8"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
