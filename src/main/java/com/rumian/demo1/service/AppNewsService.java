package com.rumian.demo1.service;

import com.rumian.demo1.model.Message;
import com.rumian.demo1.model.News;

import java.util.List;

public class AppNewsService implements NewsService {
    @Override
    public News findById(News news) {
        return null;
    }

    @Override
    public List<News> list(News news) {
        return null;
    }

    @Override
    public List<News> appList() {
        return null;
    }

    @Override
    public List<Message> messageList() {
        return null;
    }

    @Override
    public List<Message> marketMessageList() {
        return null;
    }

    @Override
    public Message selectMesssageById(String id) {
        return null;
    }

    @Override
    public int count(News news) {
        return 0;
    }

    @Override
    public int insert(News news) {
        return 0;
    }

    @Override
    public int update(News news) {
        return 0;
    }

    @Override
    public int updateState(News news) {
        return 0;
    }
}
