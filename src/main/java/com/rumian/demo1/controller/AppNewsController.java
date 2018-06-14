package com.rumian.demo1.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.model.Message;
import com.rumian.demo1.model.News;
import com.rumian.demo1.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AppNewsController {
    @Resource
    private NewsService newsService;

    @GetMapping(value = "messagesList")
    public void getNews(HttpServletRequest request, HttpServletResponse response){
       List<Message> messagesList = newsService.messageList();
       JSONArray jsonArray = (JSONArray) JSONArray.toJSON(messagesList);
       System.out.println(jsonArray);
        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().append(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "marketMessageList")
    public void getMarketMessageList(HttpServletRequest request, HttpServletResponse response){
        List<Message> messagesList = newsService.marketMessageList();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(messagesList);
        System.out.println(jsonArray);
        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().append(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "singleMessage")
    public void getSingleMessage(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("messageId");
        Message message = newsService.selectMesssageById(id);
        System.out.println(message.toString());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
        System.out.println(jsonObject);
        try {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().append("select_success" + jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
