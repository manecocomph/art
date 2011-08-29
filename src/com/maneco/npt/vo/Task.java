package com.maneco.npt.vo;

import java.util.Date;

import com.maneco.npt.util.XmlConvertor;

public class Task {

	public static int STATUS_SUBMIT = 0;
	public static int STATUS_WORKING = 1;
	public static int STATUS_DONE = 2;
	
	private Integer id;
	private Integer groupId;
	private String name;
	private Date createTime;
	private Date endTime;
	private int submitUserId;
	private int workUserId;
	private int status; //0, 1, 2;
	private String notes;
	
	public Task(String name) {
		this.name = name;
		this.createTime = new Date();
		this.status = 0;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getSubmitUserId() {
		return submitUserId;
	}
	public void setSubmitUserId(int submitUserId) {
		this.submitUserId = submitUserId;
	}
	public int getWorkUserId() {
		return workUserId;
	}
	public void setWorkUserId(int workUserId) {
		this.workUserId = workUserId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String toXmlString() throws IllegalArgumentException, IllegalAccessException {
		return XmlConvertor.toXml(this);
	}
}
