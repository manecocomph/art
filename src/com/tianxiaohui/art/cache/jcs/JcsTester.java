package com.tianxiaohui.art.cache.jcs;

import java.util.Random;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

public class JcsTester {
	public static String result = "now result:";
	public static JCS jcs = null;
	
	public static synchronized void ensureCache() {
		if (null == JcsTester.jcs) {
			try {
				jcs = JCS.getInstance("max_objects");
			} catch (CacheException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void testMaxObjects() {
		try {
			ensureCache();
			jcs.put("key1", "value1");
			jcs.put("key2", "value2");
			jcs.put("key3", "value3");
			jcs.put("key1", "value4");
			jcs.put("key5", "value5");
			
			Random r = new Random();
			for (int i = 0; i < 3000; i++) {
				jcs.put(Integer.valueOf(r.nextInt()), Integer.valueOf(r.nextInt()));
				
			}
			
			JcsTester.result += JCS.getAccess("").getStats() + "\n";
			JcsTester.result += JCS.getAccess("").getCacheAttributes().toString();
		} catch (CacheException e) {
			JcsTester.result += e.getMessage();
			e.printStackTrace();
		}
	}
}
