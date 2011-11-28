package com.tianxiaohui.npt.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianxiaohui.npt.vo.User;

public class XmlConvertor {
	private static final Logger log = LoggerFactory.getLogger(XmlConvertor.class);
	
	public static String toXml(Object obj) throws IllegalArgumentException, IllegalAccessException {
		if (null == obj)  {
			return null;
		}
		
		StringBuffer sb = new StringBuffer("<" + obj.getClass().getSimpleName() + ">");
		Field[] fields = obj.getClass().getDeclaredFields();
		Object tmp = null;
		for (Field field : fields) {
			field.setAccessible(true);
			tmp = field.get(obj);
			sb.append("<" + field.getName() + ">" + (null == tmp ? "" : tmp.toString()) + "</" + field.getName() + ">");
			//System.out.println(field.getInt(obj));
			
			//sb.append("<" + field.getName() + "></" + field.getName() + ">");
		}
		sb.append("</" + obj.getClass().getSimpleName() + ">");
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		User user = new User("eric", "tian");
		log.error("...............");
		//LoggerFactory.getILoggerFactory()
		System.out.println(XmlConvertor.toXml(user));
	}
}
