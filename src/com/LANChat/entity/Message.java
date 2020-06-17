package com.LANChat.entity;


import java.util.Date;

public class Message {

  private Integer messageId;
  private String message;
  private Date messageTime;

  public Message(){}

  public Message(Integer messageId, String message, Date messageTime) {
    this.messageId = messageId;
    this.message = message;
    this.messageTime = messageTime;
  }

  @Override
  public String toString() {
    return "Message{" +
            "messageId=" + messageId +
            ", message='" + message + '\'' +
            ", messageTime=" + messageTime +
            '}';
  }


  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(Integer messageId) {
    this.messageId = messageId;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public Date getMessageTime() {
    return messageTime;
  }

  public void setMessageTime(Date messageTime) {
    this.messageTime = messageTime;
  }

}
