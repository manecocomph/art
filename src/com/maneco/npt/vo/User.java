package com.maneco.npt.vo;

import java.util.Date;

import com.maneco.npt.util.XmlConvertor;

public class User {

	private Integer id;
	private Integer groupId;
	private String name;
	private String nickName;
	private Date lastActiveTime;
	private String role;
	
	public User(String name, String nickName) {
		this.name = name;
		this.nickName = nickName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getLastActiveTime() {
		return lastActiveTime;
	}
	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String toXmlString() throws IllegalArgumentException, IllegalAccessException {
		return XmlConvertor.toXml(this);
	}
}
