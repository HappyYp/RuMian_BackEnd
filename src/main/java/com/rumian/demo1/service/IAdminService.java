package com.rumian.demo1.service;

import com.rumian.demo1.model.Admin;
import com.rumian.demo1.model.User;
import com.rumian.demo1.redis.RedisPojoService;
import com.rumian.demo1.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class IAdminService implements AdminService {
    @Autowired
    RedisPojoService redisPojoService;
    @Override
    public Admin findByNameAndPassword(Admin admin) {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }


    @Override
    public List<Admin> findByAdminWithPage(Admin admin, int start, int end) {
        return null;
    }

    @Override
    public String selectNameByUserID(String userID) {
        return null;
    }

    @Override
    public int insert(Admin admin) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int insertUser2(User user) {
        return 0;
    }

    @Override
    public int updateUserInfo(int id) {
        return 0;
    }

    @Override
    public int updateStateById(int id) {
        return 0;
    }

    @Override
    public int updateUserById(User user) {
        return 0;
    }

    @Override
    public int updateUserMessageById(User user) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    /**
     * 获取当前sessionId对应用户信息
     * @return CurrentUser
     */
    public Admin getAdmin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        Admin admin = DataUtils.getData(redisPojoService.get(sessionId), Admin.class);
        System.out.println(sessionId);
//        System.out.println(admin.getUserName());
        return admin;
    }

    /**
     * 当前用户写入redis，sessionId作为key
     * @return CurrentUser
     */
    public void putUserToRedis(Admin admin){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        redisPojoService.set(sessionId, admin, 1800);
    }

    /**
     * 延长当前用户过期时间，sessionId作为key
     * @return CurrentUser
     */
    public void updateUserTime(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        redisPojoService.setExpire(sessionId, 1800);
    }

}
