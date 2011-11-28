package com.tianxiaohui.art.hessian.client;

import java.net.MalformedURLException;
import java.util.Date;

import com.caucho.hessian.client.HessianProxyFactory;
import com.tianxiaohui.art.hessian.ComplexService;
import com.tianxiaohui.art.hessian.Employee;
import com.tianxiaohui.art.hessian.Hello;

public class Client {
	
	public static void main(String[] args) {
		String urlName = "http://localhost:8080/trail/hessian/hello";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			Hello hello = (Hello) factory.create(Hello.class, urlName);
			System.out.println("how old" + hello.howOld());
			System.out.println("Bob's fater name" + hello.fatherName("bob"));
			
			urlName = "http://localhost:8080/trail/hessian/complex"; 
			ComplexService cs = (ComplexService) factory.create(ComplexService.class, urlName);
			System.out.println(cs.getClass().getSuperclass());
			Employee employee = cs.getEmployee(9);
			System.out.println(employee.toString());
			Date today = cs.today();
			System.out.println(today);
			
			urlName = "http://localhost:8080/trail/burlap/hello";
			hello = (Hello) factory.create(Hello.class, urlName);
			System.out.println("how old" + hello.howOld());
			System.out.println("Bob's fater name" + hello.fatherName("bob"));
			
			urlName = "http://localhost:8080/trail/burlap/complex"; 
			cs = (ComplexService) factory.create(ComplexService.class, urlName);
			System.out.println(cs.getClass().getSuperclass());
			employee = cs.getEmployee(9);
			System.out.println(employee.toString());
			today = cs.today();
			System.out.println(today);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
