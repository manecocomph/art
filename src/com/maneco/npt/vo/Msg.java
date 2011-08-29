package com.maneco.npt.vo;

import java.util.Date;

import com.maneco.npt.util.XmlConvertor;

public class Msg {
	
	private Integer id;
	private String userName;
	private Integer groupId;
	private String content;
	private Date time;
	
	public Msg(String userName, String content) {
		this.userName = userName;
		this.content = content;
		this.time = new Date();
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toXmlString() throws IllegalArgumentException, IllegalAccessException {
		return XmlConvertor.toXml(this);
	}
}
