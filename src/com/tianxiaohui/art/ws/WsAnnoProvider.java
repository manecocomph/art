package com.tianxiaohui.art.ws;

import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import org.apache.cxf.ws.rm.Source;

@ServiceMode(value=Service.Mode.PAYLOAD)
@WebServiceProvider
public class WsAnnoProvider implements Provider {

	@Override
	public Object invoke(Object request) {
		System.err.println("in my invoke method ...");
		return null;
	}
}
