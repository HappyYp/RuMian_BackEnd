package com.rumian.demo1.service;

import com.rumian.demo1.model.Admin;
import com.rumian.demo1.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminService {
    @Select("SELECT * FROM `test`.`admin` where name = #{userName} and password = #{password} and state = 0;")
    Admin findByNameAndPassword(Admin admin);

    @Select("SELECT * FROM `test`.`user` where id = #{id}")
    User findUserById(int id);

    @Select("SELECT * FROM `test`.`admin` where name = #{userName} and password = #{password} and realName = #{realName}")
    List<Admin> findByAdminWithPage(Admin admin, int start, int end);

    @Select("SELECT distinct name FROM `test`.`comment` where userID = #{userID}")
    String selectNameByUserID(String userID);

    @Insert("INSERT INTO `test`.`admin` (`id`, `name`, `password`, `email`, `realName`, `age`, `phoneNumber`, `headPicture`, `addDate`, `updateDate`, `state`) VALUES (null, #{userName}, #{password}, #{email}, #{realName}, #{age}, #{phoneNumber}, #{headPicture}, now(), now(), 0);")
    int insert(Admin admin);

    @Insert("INSERT INTO `test`.`user` (`id`, `name`, `age`, `sex`)VALUES (null, #{name}, #{age}, #{sex});")
    int insertUser(User user);

    @Insert("INSERT INTO `test`.`user` (`id`, `username`, `userAccount`, `password`, `userImg`)VALUES (null, #{username}, #{userAccount}, #{password}, #{userImg});")
    int insertUser2(User user);

    @Update("UPDATE `test`.`user` SET `username` = #{username}, `userImg` = #{userImg};")
    int updateUserInfo(int id);

    @Update("UPDATE `test`.`admin` SET `name` = #{userName}, `password` = #{password}, `email` = #{email}, `realName` = #{realName}, `age` = #{age}, `phoneNumber` = #{phoneNumber}, `headPicture` = #{headPicture}, `updateDate` = now(), `state` = #{state} WHERE `id` = #{id};")
    int updateStateById(int id);

    @Update("UPDATE `test`.`user` SET `username` = #{username}, `userImg` = #{userImg} where `id` = #{id}")
    int updateUserById(User user);

    @Update("UPDATE `test`.`user` SET `messageId` = #{messageId} where `id` = #{id}")
    int updateUserMessageById(User user);

    @Delete("DELETE FROM `test`.`admin` WHERE id  = #{id}")
    int deleteById(int id);
}
