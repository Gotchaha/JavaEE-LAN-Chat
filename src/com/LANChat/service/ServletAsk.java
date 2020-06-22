package com.LANChat.service;

import com.LANChat.dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletAsk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.获取请求参数：finalMessageId
		String finalMessageId = request.getParameter("finalMessageId");
		
		//2.根据finalMessageId查询是否存在最新的聊天记录
		MessageDao messageDao = new MessageDao();
		boolean hasNew = messageDao.hasNew(finalMessageId);
		
		//3.将布尔类型的返回值以Ajax响应的形式返回给浏览器
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(hasNew+"");
		
	}

}
