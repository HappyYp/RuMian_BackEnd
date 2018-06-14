package com.rumian.demo1.service;

import com.rumian.demo1.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageService {
    @Insert("INSERT INTO `test`.`message` (`messageId`, `messageType`, `pushTime`, `textContent`, `imgUrl`, `userId`, `userName`, `userImage`, `deadline`, `price`, `orderTextContent`, `likeCount`, `commentCount`, `issueCount`, `acceptCount`, `shareCount`)VALUES (null, #{messageType}, #{pushTime}, #{textContent}, #{imgUrl}, #{userId}, #{userName}, #{userImage}, #{deadline}, #{price}, #{orderTextContent}, 0,0,0,0,0);")
    int insertMessage(Message message);
    @Select("SELECT * FROM `test`.`message` where userId = #{userId};")
    List<Message>  findMessageByUserId(String userId);
}
