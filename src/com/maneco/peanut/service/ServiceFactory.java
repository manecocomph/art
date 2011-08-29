package com.maneco.peanut.service;

import org.apache.cxf.Bus;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

import com.maneco.peanut.service.impl.RegionService;

public class ServiceFactory {
	public static boolean isOpen = false;
	
	private ServiceFactory() {
	}
	private static final IRegionService regionService = new RegionService();
	public static IRegionService getRegionService() {
		return regionService;
	}
	
	public static void publishService() {
		if (ServiceFactory.isOpen) {
			return;
		}
		/*ServerFactoryBean svrFactory = new ServerFactoryBean();
		svrFactory.setServiceClass(IRegionService.class);
		svrFactory.setAddress("http://localhost:8080/services/hello");
		svrFactory.setServiceBean(ServiceFactory.getRegionService());
		svrFactory.create();*/
		/*CXFServlet cxf = new CXFServlet();
		Bus bus = cxf.getBus();
		BusFactory.setDefaultBus(bus);
		javax.xml.ws.Endpoint.publish("/region", ServiceFactory.getRegionService());*/
		
		CXFServlet cxf = new CXFServlet();
		Bus bus = cxf.getBus();
		ServerFactoryBean factroy = new ServerFactoryBean();
		factroy.setBus(bus);
		factroy.setServiceClass(ServiceFactory.getRegionService().getClass());
		factroy.setAddress("/hello");
		factroy.create();
		
		ServiceFactory.isOpen = true;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(ServiceFactory.getRegionService().getProvinceDropdownOption(86));
		}
		
		try {
			System.out.println(JCS.getAccess("region_province").getStats());
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}
}
