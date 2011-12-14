package com.tianxiaohui.art.ws;

import javax.xml.ws.Endpoint;

import com.tianxiaohui.art.ws.impl.JaxwsProviderImpl;

public class Publisher {

	public static boolean isOpen = false;
	
	public static void publishWithEndpoint() {
		Endpoint.publish("http://localhost:9898/jaxwsEndpoint", new JaxwsProviderImpl());
		//Endpoint.publish("http://localhost:9898/anno", new WsAnnoProvider());
		System.err.println("publish service to: /cxf/jaxwsEndpoint");
	}
	
	
	public static void publish() {
		if (Publisher.isOpen) {
			return;
		}
		
		
		Publisher.isOpen = true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Publisher.publishWithEndpoint();

	}

}
