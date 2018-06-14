package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.constants.URL;
import com.rumian.demo1.model.Message;
import com.rumian.demo1.model.User;
import com.rumian.demo1.service.AdminService;
import com.rumian.demo1.service.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AppMessageController {
    @Resource
    private MessageService messageService;
    @Resource
    private AdminService adminService;

    @PostMapping(value = "insertMessage")
    public void insertMessage(HttpServletRequest request, @RequestParam(value = "file")MultipartFile file, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String messagetype = request.getParameter("messagetype");
        String userid = request.getParameter("userid");
        String textcontent = request.getParameter("textcontent");
        String time = request.getParameter("time");
        System.out.println("messagetype++++++++" + messagetype);
        System.out.println("useid++++++++" + userid);
        System.out.println("text++++++++" + textcontent);
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
            try {
                FileOutputStream os = new FileOutputStream(URL.FILE_PATH + "/" + date + file.getOriginalFilename());
                FileInputStream in = (FileInputStream) file.getInputStream();
                int b = 0;
                while((b=in.read()) != -1){
                    os.write(b);
                }
                imgUrl = "pic/" + date + file.getOriginalFilename();
                os.flush();
                os.close();
                in.close();
                System.out.println("imgUrl+++++++++" + imgUrl);
                int result = -1;
                User user = null;
                Message message = new Message();
                List mList = new ArrayList();
                user = adminService.findUserById(Integer.valueOf(userid));
                if(messagetype.equals("0") || messagetype.equals("1")){
                    message.setImgUrl(imgUrl);
                    message.setMessageType(Integer.valueOf(messagetype));
                    message.setUserName(user.getUsername());
                    message.setUserId(Integer.valueOf(userid));
                    message.setPushTime(time);
                    message.setTextContent(textcontent);
                    message.setUserName(user.getUsername());
                    message.setUserImage(user.getUserImg());
                    mList.add(message);
                    user.setMessages(mList);
                   // adminService.updateUserMessageById(user);
                    System.out.println("message+++++++" + message.toString());
                    result = messageService.insertMessage(message);
                }else {
                    String ordercontent = request.getParameter("ordercontent");
                    String ordertextcontent = request.getParameter("ordertextcontent");
                    String price = request.getParameter("orderprice");
                    String deadline = request.getParameter("deadline");
                    message.setPrice(price);
                    message.setOrderTextContent(ordertextcontent);
                    message.setDeadline(deadline);
                    message.setImgUrl(imgUrl);
                    message.setMessageType(Integer.valueOf(messagetype));
                    message.setUserName(user.getUsername());
                    message.setUserId(Integer.valueOf(userid));
                    message.setPushTime(time);
                    message.setTextContent(ordercontent);
                    message.setUserName(user.getUsername());
                    message.setUserImage(user.getUserImg());
                    mList.add(message);
                    user.setMessages(mList);
                   // adminService.updateUserMessageById(user);
                    System.out.println("message+++++++" + message.toString());
                    result = messageService.insertMessage(message);
                }
                if (result > 0){
                    System.out.println("插入信息成功");
                    response.getWriter().append("upload_success");
                }else {
                    System.out.println("插入信息失败");
                    response.getWriter().append("upload_fail");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping(value = "findSingleMessage")
    public void find(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入此方法");
        String userId = request.getParameter("userId");
        List<Message>  messages =  messageService.findMessageByUserId(userId);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(messages);
        System.out.println("userId++++++++++" + userId);
        System.out.println(jsonArray.toString());
        try {
            response.setContentType("text/html; charset=utf-8");
            if (messages == null){
                response.getWriter().append("no_messages");
                System.out.println("messages为空");
            }else {
                response.getWriter().append(jsonArray.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
