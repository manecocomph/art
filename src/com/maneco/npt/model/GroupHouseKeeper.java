package com.maneco.npt.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.maneco.npt.util.MsgQueue;
import com.maneco.npt.vo.Group;
import com.maneco.npt.vo.Msg;
import com.maneco.npt.vo.Task;
import com.maneco.npt.vo.User;

public class GroupHouseKeeper {
	
	//TODO delete this
	private AtomicInteger userId = new AtomicInteger(1);
	private AtomicInteger taskId = new AtomicInteger(1);
	
	public GroupHouseKeeper(String groupName) {
		if (null == groupName) {
			throw new RuntimeException("the group name is invalid" + groupName);
		}
		
		this.group = new Group(groupName);
		GroupHouseKeeper.groupHks.put(groupName, this);
	}

	private Group group;
	private Map<Integer, User> users = new HashMap<Integer, User>();
	private List<Task> taskList = new ArrayList<Task>();
	private MsgQueue msgList = new MsgQueue(100);
	private List<MsgInterseptor> interseptorList = new ArrayList<MsgInterseptor>();
	private static int TASK_THRESHOLD = 99;
	private static Map<String, GroupHouseKeeper> groupHks = new HashMap<String, GroupHouseKeeper>();
	
	public static GroupHouseKeeper getGroupHouseKeeper(String groupName) {
		return GroupHouseKeeper.groupHks.get(groupName);
	}
	
	
	public void addTask(Integer userId, Task t) {
		if (TASK_THRESHOLD <= this.taskList.size()) {
			throw new RuntimeException("Too much tasks, can't add more!");
		}
		
		if (this.checkUserExist(userId)) {
			t.setSubmitUserId(userId);
			t.setStatus(Task.STATUS_SUBMIT);
			t.setId(taskId.getAndIncrement());
			t.setGroupId(this.getGroup().getId());
			this.taskList.add(t);
		} else {
			throw new RuntimeException("User not exist, please log in first");
		}
	}
	
	public List<Task> getMySubmitedTasks(int userId) {
		List<Task> submitedTaskList = new ArrayList<Task>();
		
		for (Task task : this.taskList) {
			if (userId == task.getSubmitUserId()) {
				submitedTaskList.add(task);
			}
		}
		
		return submitedTaskList;
	}
	
	public List<Task> getMyWorkingTasks(int userId) {
		List<Task> workingTaskList = new ArrayList<Task>();
		
		for (Task task : this.taskList) {
			if (userId == task.getWorkUserId()) {
				workingTaskList.add(task);
			}
		}
		
		return workingTaskList;
	}
	
	
	public void addMsgInterseptor(MsgInterseptor e) {
		this.interseptorList.add(e);
	}
	
	public void addUser(User user) {
		if (null == user.getId()) {
			//TODO this not work in production
			user.setId(userId.getAndIncrement());
		}
		user.setGroupId(this.getGroup().getId());
		this.users.put(user.getId(), user);
	}
	
	public void addMsg(Integer userId, String msgContent) {
		if (this.checkUserExist(userId)) {
			Msg msg = new Msg(this.getUser(userId).getName(), msgContent);
			msg.setGroupId(this.getGroup().getId());
			this.msgList.put(msg);
			for (MsgInterseptor interseptor : interseptorList) {
				this.msgList.put(interseptor.process(msg));
			}
		} else {
			throw new RuntimeException("User not exist, please log in first");
		}
	}
	
	public String getLatestMsgs(int lastId) {
		return this.msgList.getLastest(lastId);
	}
	
	public boolean checkUserExist(Integer userId) {
		if(!this.users.containsKey(userId)) {
			return false;
		} else {
			return true;
		}
	}

	public void markTaskEnd(int userId, int taskId) {
		for (Task t : this.taskList) {
			if (taskId == t.getId()) {
				t.setStatus(Task.STATUS_DONE);
				t.setWorkUserId(userId);
				return;
			}
		}
	}
	
	public void workOnTask(int userId, int taskId) {
		for (Task t : this.taskList) {
			if (taskId == t.getId()) {
				t.setStatus(Task.STATUS_WORKING);
				t.setWorkUserId(userId);
				return;
			}
		}
	}
	
	public Group getGroup() {
		return group;
	}	
	
	public static void main(String[] args) {
		User u = new User("eric", "eric");
		u.setId(0);
		GroupHouseKeeper ghk = new GroupHouseKeeper("eric_test_group");
		ghk.addMsgInterseptor(new MyMsgInterseptor());  
		ghk.addUser(u);
		for (int i = 0; i < 300; i++) {
			ghk.addMsg(0, "msg" + i);
		}
		
		System.out.println(ghk.getLatestMsgs(268));
		
		User u1 = new User("eric1", "eric1");
		u1.setId(8);
		ghk.addUser(u1);
		for (int i = 0; i < 30; i++) {
			if (i % 9 == 8) 
				ghk.addTask(8, new Task("this is task oooo" + i));
			
			ghk.addTask(8, new Task("this is task " + i));
		}
		
		List<Task> tl = ghk.getMySubmitedTasks(8);
		System.out.println(tl.size());
		
		for (Task t : tl) {
			System.out.println(t.getName());
			t.setWorkUserId(0);
		}
		
		tl = ghk.getMyWorkingTasks(8);
		System.out.println(tl.size());
	}


	public List<Task> getTaskList() {
		return taskList;
	}


	public Map<Integer, User> getUsers() {
		return users;
	}
	
	public User getUser(int userId) {
		return this.users.get(userId);
	}
}
