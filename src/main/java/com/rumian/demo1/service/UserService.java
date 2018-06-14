package com.rumian.demo1.service;

import com.rumian.demo1.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
@Mapper
public interface UserService {
    @Cacheable(value="users", key="'user_'+#id")
    User getUser(String id);

    @CacheEvict(value="users", key="'user_'+#id",condition="#id!=1")
    void deleteUser(String id);

    //@Select("SELECT * FROM `test`.`user` where name = #{username} and password = #{password};")
    @Select("SELECT * FROM `test`.`user` where userAccount = #{userAccount} and password = #{password};")
    User selectUserByNameAndPwd(User user);

    @Insert("INSERT INTO `test`.`user` (`id`, `username`, `userAccount`, `password`, `userImg`)VALUES (null, #{username}, #{userAccount}, #{password}), #{userImg};")
    int insertUser(User user);
}
