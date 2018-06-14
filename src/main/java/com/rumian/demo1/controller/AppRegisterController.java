package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.model.User;
import com.rumian.demo1.service.AdminService;
import com.rumian.demo1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/app")
public class AppRegisterController {
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
    @PostMapping(value = "register")
    public void appRegister(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html; charset=utf-8");
        int result = -1;
        try {
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
            user.setUsername(jsonObject.getString("username"));
            user.setUserAccount(jsonObject.getString("userAccount"));
            user.setPassword(jsonObject.getString("password"));
            user.setUserImg("pic/gou.jpg");

            System.out.println("user = " + user.toString());
            User user2 = userService.selectUserByNameAndPwd(user);
            if (user2 == null){
                /**
                 * DAO层,写入数据库
                 */
                System.out.println("数据库查无此User");
                result = adminService.insertUser2(user);
                System.out.println("插入数据库操作结束");
            }
            System.out.println("result = "+result);
            /**
             * 给手机端返回的数据
             */
            //JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(user);
            String registerInfo = null;
            if(result > 0) {
                registerInfo = "register_success";
                response.getWriter().append(registerInfo);
            }
            else if(result == -1){
                registerInfo = "userAccount_exist";
                response.getWriter().append(registerInfo);
            }else {
                registerInfo = "register_fail";
                response.getWriter().append(registerInfo);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
}
