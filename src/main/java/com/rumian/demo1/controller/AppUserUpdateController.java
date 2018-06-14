package com.rumian.demo1.controller;


import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.constants.URL;
import com.rumian.demo1.model.User;
import com.rumian.demo1.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/app")
public class AppUserUpdateController {
    @Resource
    private AdminService adminService;

    @PostMapping(value = "update")
    public void updateUser(HttpServletRequest request, @RequestParam(value = "file")MultipartFile file, HttpServletResponse response) throws IOException {
        //解析数据
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("userid");
        String username = request.getParameter("username");
        //String newUsername = new String(username.getBytes("UTF-8"),"UTF-8");
        System.out.println("id++++++++++++" + id);
        System.out.println("name++++++++++++" + username);
        String imgUrl = null;

        System.out.println("文件的大小:"+file.getSize());
        System.out.println("文件的类型:"+file.getContentType());
        System.out.println("文件的名称:"+file.getName());
        String [] contentType = file.getContentType().split("/");
        if (file.isEmpty()){
            System.out.println("文件空");
        }
       if (!file.isEmpty()){
            final long date = new Date().getTime();
            FileOutputStream os = new FileOutputStream(URL.FILE_PATH + "/" + date + file.getOriginalFilename() /*+ "." +contentType[1]*/);
            FileInputStream in = (FileInputStream) file.getInputStream();
           int b = 0;
           while((b=in.read()) != -1){
               os.write(b);
           }
           imgUrl = "pic/" + date + file.getOriginalFilename()/* + "." +contentType[1]*/;
           os.flush();
           os.close();
           in.close();
       }
        User user = null;
        user = adminService.findUserById(Integer.valueOf(id));
        System.out.println("user+++++++++" + user.toString());
        if (user != null){
            user.setUsername(username);
            user.setUserImg(imgUrl);
            System.out.println("updatedUser++++++++++" + user.toString());
            int updateResult = -1;
            updateResult = adminService.updateUserById(user);
            if (updateResult > 0){
                System.out.println(user.toString());
                JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(user);
                response.getWriter().append("update_success" + jsonObject1.toString());
            }else {
                System.out.println("更新失败");
                response.getWriter().append("update_fail");
            }
        }
    }
}
