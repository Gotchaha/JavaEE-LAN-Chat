package com.LANChat.service;

import com.LANChat.entity.Message;
import com.LANChat.dao.MessageDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletGetNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.获取finalMessageId
		String finalMessageId = request.getParameter("finalMessageId");
		
		//2.根据finalMessageId获取新聊天记录内容
		MessageDao messageDao = new MessageDao();
		List<Message> newMessage = messageDao.getNewMessage(finalMessageId);
		
		//3.转换为JSON字符串返回给浏览器
		Gson gson = new Gson();
		String json = gson.toJson(newMessage);
		
		System.out.println(json);
		
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(json);
		
	}

}
