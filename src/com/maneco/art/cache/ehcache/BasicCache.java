package com.maneco.art.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class BasicCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheManager cm = CacheManager.getInstance();
		
		Cache cache = new Cache("cache_name", 999, false, false, 60, 30);
		cm.addCache(cache);
		
		cache.put(new Element("eric", "qing"));
		
		System.out.println(cache.get("eric"));
		
		try {
			Thread.sleep(20000);
			System.out.println(cache.get("eric"));
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(cache.get("eric"));
	}

}
