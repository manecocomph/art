package com.tianxiaohui.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		MemcachedClient c = new MemcachedClient(new InetSocketAddress("10.249.65.238", 11211));

		// Store a value (async) for one hour
		for (int i = 0; i < 300; i++) {
			c.set("someKey", 3600, "eric is testing you...");
		}
		Future<Boolean> f1 = c.set("MM", 3600, "eric");
		System.out.println(f1.get());
		System.out.println(c.get("MM"));
		Future<CASValue<Object>> a = c.asyncGetAndLock("MM", 500);
		
		Future<Boolean> f = c.append(1, "MM", "MMvalue");
		System.out.println(f.get());
		
		// System.out.println(c.get("someKey"));
		// Retrieve a value (synchronously).
		Object myObject = c.get("someKey1");
		
		Map<SocketAddress, Map<String, String>> rs = c.getStats();
		
		for (Map.Entry<SocketAddress, Map<String, String>> entry : rs.entrySet()) {
			System.out.println(entry.getKey().toString() + "  : " + entry.getValue());
		}
		
		System.out.println("over");
		if (a.isDone()) {
			System.out.println(a.get());
			System.out.println(a.get().getCas());
			System.out.println(a.get().getValue());
		}
		
	}

}
