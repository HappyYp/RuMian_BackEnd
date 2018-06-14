package com.rumian.demo1.model;

public class Message {
    private int messageId;
    private String userImage;
    private String userName;
    private String pushTime;
    private String textContent;
    private String likeCount;
    private String commentCount;
    private String shareCount;
    private String issueCount;//其取值小于等于库存时，方可点按
    private String deadline;
    private String price;
    private String orderTextContent;
    private String acceptCount;//其取值仅为0或1，分别表示可点按或不可点按
    private int messageType;//其取值为0，1，2，分表表示三种不同的消息类型
    private String imgUrl;
    private int userId;
    private User user;


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getIssueCount() {
        return issueCount;
    }

    public void setIssueCount(String issueCount) {
        this.issueCount = issueCount;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderTextContent() {
        return orderTextContent;
    }

    public void setOrderTextContent(String orderTextContent) {
        this.orderTextContent = orderTextContent;
    }

    public String getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(String acceptCount) {
        this.acceptCount = acceptCount;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userImage='" + userImage + '\'' +
                ", userName='" + userName + '\'' +
                ", pushTime='" + pushTime + '\'' +
                ", textContent='" + textContent + '\'' +
                ", likeCount='" + likeCount + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", shareCount='" + shareCount + '\'' +
                ", issueCount='" + issueCount + '\'' +
                ", deadline='" + deadline + '\'' +
                ", price='" + price + '\'' +
                ", orderTextContent='" + orderTextContent + '\'' +
                ", acceptCount='" + acceptCount + '\'' +
                ", messageType=" + messageType +
                ", imgUrl='" + imgUrl + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}
