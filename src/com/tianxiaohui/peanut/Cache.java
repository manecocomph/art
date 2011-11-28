package com.tianxiaohui.peanut;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	public static final Map<String, Map<Object, Object>> cache = new HashMap<String, Map<Object, Object>>();

	public static void putToCache(String domain, Object key, Object value) {
		Map<Object, Object> domainMap = getDomainMap(domain);
		domainMap.put(key, value);
	}
	
	public static Object get(String domain, Object key) {
		if (Cache.cache.containsKey(domain)) {
			return Cache.cache.get(domain).get(key);
		}
		
		return null;
	}
	
	private static Map<Object, Object> getDomainMap(String domain) {
		if (Cache.cache.containsKey(domain)) {
			return Cache.cache.get(domain);
		} else {
			Map<Object, Object> domainMap = new HashMap<Object, Object>();
			Cache.cache.put(domain, domainMap);
			return domainMap;
		}
	}
}
