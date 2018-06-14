package com.rumian.demo1.service;

import com.rumian.demo1.model.Comment;
import com.rumian.demo1.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentService {
    @Insert("INSERT INTO `test`.`comment`(`commentID`,`messageID`,`userID`,`content`,`sendTime`, `name`)VALUES(null, #{messageID}, #{userID}, #{content}, #{sendTime}, #{name});")
    int insertComment(Comment comment);
    @Select("SELECT * FROM `test`.`comment` where messageID = #{messageId} order by sendTime DESC;")
    List<Comment> findCommentsByMessageId(String messageId);
}
