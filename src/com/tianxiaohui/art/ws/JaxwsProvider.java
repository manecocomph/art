package com.tianxiaohui.art.ws;

import javax.jws.WebService;

@WebService(targetNamespace="http://ws.art.tianxiaohui.com/")
public interface JaxwsProvider {

	//public String speakHi();
	public String speakHi(String name);
	//public String speakHi(int age);
}
