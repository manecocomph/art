package com.tianxiaohui.npt.vo;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.tianxiaohui.npt.util.XmlConvertor;

public class Group {
	private static AtomicInteger GROUP_ID_SEQ = new AtomicInteger(1);
	
	private Integer id;
	private String name;
	private Date createTime;
	private boolean permanent;
	
	
	public Group(String name) {
		this.name = name;
		this.createTime = new Date();
		this.setId(GROUP_ID_SEQ.getAndIncrement());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public boolean isPermanent() {
		return permanent;
	}
	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}
	
	public String toXmlString() throws IllegalArgumentException, IllegalAccessException {
		return XmlConvertor.toXml(this);
	}
}
