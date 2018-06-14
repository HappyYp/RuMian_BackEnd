package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSON;
import com.rumian.demo1.base.ResponseCode;
import com.rumian.demo1.base.ResponseResult;
import com.rumian.demo1.constants.URL;
import com.rumian.demo1.exception.OperationException;
import com.rumian.demo1.model.Admin;
import com.rumian.demo1.service.AdminService;
import com.rumian.demo1.service.AuthHttpService;
import com.rumian.demo1.service.IAdminService;
import com.rumian.demo1.util.DataUtils;
import com.rumian.demo1.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService iAdminService;
    @Autowired
    AuthHttpService authHttpService;
    @Autowired
    private IAdminService iiAdminService;
    @Resource
    private AdminService adminService;
    /**
     * 登录跳转
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginGet(Model model){
        return "login";
    }

    /**
     * 登录
     * @param model,admin,HttpSession
     * @return
     */
    @PostMapping("/login")
    public String loginPost(Admin admin, Model model, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        Admin adminRes = adminService.findByNameAndPassword(admin);
        if (adminRes != null) {
            httpSession.setAttribute("admin", adminRes);
            return "dashboard";

        } else {
            model.addAttribute("error", "用户名或密码错误，请重新登录");
            return "login";
        }
        //redis获取用户信息，存在直接登录
//        Admin currentUser = iAdminService.getAdmin();
//        if (currentUser != null) {
//            return "dashboard";
//        }else {
//            //redis不存在，查询数据库
//            Admin adminRes = adminService.findByNameAndPassword(admin);
//            //写入redis
//            iAdminService.putUserToRedis(adminRes);
//            if(adminRes !=null ){
//                return loginPost(admin, model, httpSession, request, response);
//            }else {
//                model.addAttribute("error","用户名或密码错误，请重新登录");
//                return "login";
//            }
        //写入redis
        // iAdminService.putUserToRedis(adminRes);
   // }
    }
    /**
     * 注册
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String registerGet(Model model){
       // int added = adminService.insert(admin);

        return "register";
    }
    @PostMapping("/register")
    public String registerPost(Model model,Admin admin){
        int added = adminService.insert(admin);
        return "dashboard";
    }
    /**
     * 仪表板页面
     *
     * @param model
     * @return
     */
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
}
