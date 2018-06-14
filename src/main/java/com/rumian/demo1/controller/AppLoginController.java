package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.model.User;
import com.rumian.demo1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
@RequestMapping("/app")
public class AppLoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public void appLogin(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        response.setContentType("text/html; charset=utf-8");
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
            bReader.close();
            iStream.close();
            String useStr = sBuffer.toString();
            System.out.println("useStr++++++++++" + useStr);
            String jsonString = JSONObject.toJSONString(useStr);
            System.out.println("jsonString++++++++++" + jsonString);
            JSONObject jsonObject = JSONObject.parseObject(useStr);
            user.setUserAccount(jsonObject.getString("userAccount"));
            user.setPassword(jsonObject.getString("password"));
            System.out.println("user = " + user.toString());


            System.out.println("正在查询数据库");
            User user1 = userService.selectUserByNameAndPwd(user);
            JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(user1);
                System.out.println(user1);
                if (user1!=null){
                    System.out.println(response.getStatus());
                    response.getWriter().append("login_success" + jsonObject1.toString());
                }else {
                    System.out.println("user为空");
                    System.out.println(response.getStatus());
                    response.getWriter().append("login_fail");
                }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
}
