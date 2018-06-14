package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.model.Comment;
import com.rumian.demo1.model.Message;
import com.rumian.demo1.model.User;
import com.rumian.demo1.service.AdminService;
import com.rumian.demo1.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppCommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private AdminService adminService;
    @PostMapping(value = "addComment")
    public void addComment(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            int result = -1;
            InputStream iStream = null;
            try {
                iStream = request.getInputStream();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, "utf-8"));
                String string = null;
                StringBuffer sBuffer = new StringBuffer();
                while((string = bReader.readLine()) != null) {
                    sBuffer.append(string);
                    System.out.println("输入流不为空none");
                }
                String commentStr = sBuffer.toString();
                System.out.println("commentStr++++++++++" + commentStr);
                String jsonString = JSONObject.toJSONString(commentStr);
                System.out.println("jsonString++++++++++" + jsonString);

                Comment comment = new Comment();
                JSONObject jsonObject = JSONObject.parseObject(commentStr);
                comment.setContent(jsonObject.getString("content"));
                comment.setMessageID(Integer.valueOf(jsonObject.getString("messageID")));
                //Date udate = new Date();
                //System.out.println("当前时间++++++" + udate.toString());
                //java.sql.Date date = new java.sql.Date(udate.getTime());
                comment.setSendTime(jsonObject.getString("sendTime"));
                comment.setUserID(jsonObject.getString("userID"));
                String name = adminService.selectNameByUserID(jsonObject.getString("userID"));
                comment.setName(name);
                System.out.println("comment = " + comment.toString());
                result = commentService.insertComment(comment);

                String insertInfo = null;
                if (result > 0){
                    insertInfo = "comment_success";
                    response.getWriter().append(insertInfo);
                }else {
                    insertInfo = "comment_fail";
                    response.getWriter().append(insertInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @GetMapping(value = "getComments")
    public void getComments(HttpServletRequest request, HttpServletResponse response){
        String messageId = request.getParameter("messageId");
        List<Comment> comments =  commentService.findCommentsByMessageId(messageId);
        String jsonString = JSONArray.toJSONString(comments);
        System.out.println("comments+++++++++" + jsonString);
        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().append(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
