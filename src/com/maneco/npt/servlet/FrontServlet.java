package com.maneco.npt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maneco.npt.model.GroupHouseKeeper;
import com.maneco.npt.vo.Task;
import com.maneco.npt.vo.User;

public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = -4921594140884989484L;
	private static final Logger log = LoggerFactory.getLogger(FrontServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		for (int i = 0; i < 5; i++) {
			log.error("This is a mes g................" );
		}
		String respMsg = "";
		try {
			if (uri.contains("login_")) {
				respMsg = login(req);
			} else if (uri.contains("task_")) {
				respMsg = fetchTasks(req);
			} else if (uri.contains("msg_")) {
				respMsg = processMsg(req);
			} else if (uri.contains("user_")) {
				respMsg = listAllUser(req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = resp.getWriter();
		pw.write("<?xml version='1.0' encoding='ISO-8859-1'?><npt>" + respMsg + "</npt>");
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private static String login(HttpServletRequest req) {
		Object obj = req.getParameter("name");
		String name = null;
		if (null != obj) {
			name = obj.toString();
		} else {
			return "<result>1</result><errMsg>User name can't be null</errMsg>";
		}
		
		obj = req.getParameter("nickName");
		String nickName = null;
		if (null != obj) {
			nickName = obj.toString();
		} else {
			nickName = name;
		}
		
		obj = req.getParameter("groupName");
		String groupName = null;
		if (null != obj) {
			groupName = obj.toString();
		} else {
			return "<result>2</result><errMsg>Must give a group name</errMsg>";
		}
		
		User user = new User(name, nickName);
		
		GroupHouseKeeper groupHk = GroupHouseKeeper.getGroupHouseKeeper(groupName);
		if (null == groupHk) {
			groupHk = new GroupHouseKeeper(groupName);
		}
		
		groupHk.addUser(user);
		
		return "<result>0</result><userId>" + user.getId() + "</userId><groupId>" + groupHk.getGroup().getId() + "</groupId>";
	}
	
	private static String fetchTasks (HttpServletRequest req) throws IllegalArgumentException, IllegalAccessException {
		Object obj = req.getParameter("groupName");
		String groupName = null;
		if (null != obj) {
			groupName = obj.toString();
		} else {
			return "<result>2</result><errMsg>Must give a group name</errMsg>";
		}
		
		GroupHouseKeeper groupHk = GroupHouseKeeper.getGroupHouseKeeper(groupName);
		if (null == groupHk) {
			return "<result>2</result><errMsg>No such group</errMsg>";
		}
		
		obj = req.getParameter("userId");
		Integer userId = null;
		if (null != obj) {
			userId = Integer.parseInt(obj.toString());
		} else {
			return "<result>2</result><errMsg>please give user info</errMsg>";
		}
		
		obj = req.getParameter("taskName");
		String taskName = null;
		if (null != obj) {
			taskName = obj.toString();
		}
		
		Task newTask = new Task(taskName);
		
		obj = req.getParameter("taskNote");
		String taskNote = null;
		if (null != obj) {
			taskNote = obj.toString();
			newTask.setNotes(taskNote);
		}
		groupHk.addTask(userId, newTask);
		
		List<Task> submitTasks = groupHk.getMySubmitedTasks(userId);
		List<Task> workingTasks = groupHk.getMyWorkingTasks(userId);
		List<Task> allTasks = groupHk.getTaskList();
		
		StringBuffer sb = new StringBuffer("<result>0</result><tasks>");
		sb.append("<mySubmitedTask>");
		for (Task task : submitTasks) {
			sb.append(task.toXmlString());
		}
		sb.append("</mySubmitedTask>");
		sb.append("<myWorkingTask>");
		for (Task task : workingTasks) {
			sb.append(task.toXmlString());
		}
		sb.append("</myWorkingTask>");
		sb.append("<allTask>");
		for (Task task : allTasks) {
			sb.append(task.toXmlString());
		}
		sb.append("</allTask>");
		sb.append("</tasks>");
		
		return sb.toString();
	}
	
	private static String listAllUser(HttpServletRequest req) throws IllegalArgumentException, IllegalAccessException {
		Object obj = req.getParameter("groupName");
		String groupName = null;
		if (null != obj) {
			groupName = obj.toString();
		} else {
			return "<result>2</result><errMsg>Must give a group name</errMsg>";
		}
		
		GroupHouseKeeper groupHk = GroupHouseKeeper.getGroupHouseKeeper(groupName);
		if (null == groupHk) {
			return "<result>2</result><errMsg>Must give a group name</errMsg>";
		}
		
		StringBuffer sb = new StringBuffer("<result>0</result><users>");
		for (User user : groupHk.getUsers().values()) {
			sb.append(user.toXmlString());
		}
		sb.append("</users>");
		
		return sb.toString();
	}
	
	private static String processMsg(HttpServletRequest req) {
		Object obj = req.getParameter("groupName");
		String groupName = null;
		if (null != obj) {
			groupName = obj.toString();
		} else {
			return "<result>2</result><errMsg>Must give a group name</errMsg>";
		}
		
		GroupHouseKeeper groupHk = GroupHouseKeeper.getGroupHouseKeeper(groupName);
		if (null == groupHk) {
			return "<result>2</result><errMsg>No such group</errMsg>";
		}
		
		obj = req.getParameter("userId");
		Integer userId = null;
		if (null != obj) {
			userId = Integer.parseInt(obj.toString());
		} else {
			return "<result>2</result><errMsg>Must give a user info</errMsg>";
		}
		
		obj = req.getParameter("msg");
		String msg = null;
		if (null != obj) {
			msg = obj.toString();
			groupHk.addMsg(userId, msg);
		}
		
		obj = req.getParameter("lastMsgId");
		int lastMsgId = 1;
		if (null != obj) {
			lastMsgId = Integer.parseInt(obj.toString());
		}
		
		return "<result>0</result><msgs>" + groupHk.getLatestMsgs(lastMsgId) + "</msgs>";
	}
}
