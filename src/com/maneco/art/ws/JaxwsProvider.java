package com.maneco.art.ws;

import javax.jws.WebService;

@WebService(targetNamespace="http://impl.service.peanut.maneco.com/")
public interface JaxwsProvider {

	//public String speakHi();
	public String speakHi(String name);
	//public String speakHi(int age);
}
