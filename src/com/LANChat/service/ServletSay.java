package com.LANChat.service;

import com.LANChat.entity.Message;
import com.LANChat.dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ServletSay extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 获取聊天记录消息的值
		String msg = request.getParameter("message");

		Message message = new Message(null, msg, new Date());

		System.out.println(message);
		// 2. 将聊天记录的值保存到数据库中
		MessageDao messageDao = new MessageDao();
		messageDao.saveMessage(message);
	}

}
