package com.maneco.art.ws.impl;

import javax.jws.WebService;

import com.maneco.art.ws.JaxwsProvider;
@WebService(name="eric's jaxwsimple", serviceName="service anme", portName="portName")
public class JaxwsProviderImpl implements JaxwsProvider {

	/*@Override
	public String speakHi() {
		return "No para speakHi";
	}*/

	@Override
	public String speakHi(String name) {
		return "with String para: " + name;
	}

	//@Override
	/*public String speakHi(int age) {
		return "with int para: " + age;
	}*/

}
