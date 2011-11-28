package com.tianxiaohui.art;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {
	public static volatile SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//public static final DateFormat df = DateFormat.getDateInstance();
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	/*public static synchronized DateFormat getDateFomrater() {
		return Util.df;
	}*/
	
	public static synchronized Date formatStr(String str) throws ParseException {
		return Util.df.parse(str);
	}
	
	private static final Map<Long, DateFormat> DateFormaterMap = new HashMap<Long, DateFormat>();
	// return date formater by thread Id
	public static DateFormat getDateFormaterByThread() {
		Long tId = Thread.currentThread().getId();
		
		if (Util.DateFormaterMap.containsKey(tId)) {
			return Util.DateFormaterMap.get(tId);
		} else {
			// another strategy: use SimpleDateFormat's clone method with a default one
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Util.DateFormaterMap.put(tId, sdf);
			return sdf;
		}
	}
}
