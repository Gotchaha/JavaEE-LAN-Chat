package com.LANChat.dao;

import com.LANChat.entity.Message;

import java.util.List;

public class MessageDao extends BaseDao<Message>{
	
	//将聊天记录信息保存到数据库中
	public void saveMessage(Message message) {
		String sql = "INSERT INTO MESSAGE (MESSAGE_CONTENT,MESSAGE_TIME) VALUES(?,?)";
		this.update(sql, message.getMessage(), message.getMessageTime());
	}
	
	//根据浏览器传入的本地最新消息ID查询比本地消息还要新的聊天记录
	public List<Message> getNewMessage(String finalMessageId) {
		String sql = "SELECT `MESSAGE_ID` messageId,`MESSAGE_CONTENT` message,`MESSAGE_TIME` messageTime FROM `message` WHERE MESSAGE_ID>? ORDER BY MESSAGE_ID";
		return this.getBeanList(sql, finalMessageId);
	}
	
	//根据浏览器传入的本地最新消息ID查询是否存在新的聊天记录
	public boolean hasNew(String finalMessageId) {
		String sql = "SELECT COUNT(*) FROM `message` WHERE `MESSAGE_ID`>?";
		long count = this.getSingleValue(sql, finalMessageId);
		return count > 0;
	}

}
