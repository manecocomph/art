package com.tianxiaohui.art.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperties().toString());
		Main.dynamicInvoke();
	}
	
	public static void dynamicInvoke() throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:9898/jaxwsEndpoint?wsdl");

		Object[] res = client.invoke("speakHi", "test echo");
		System.out.println("Echo response: " + res[0]);
	}
	
	public static void test() throws MalformedURLException {
		URL wsdlURL = new URL("http://localhost:9898/jaxwsEndpoint?wsdl");
		QName SERVICE_NAME = new QName("http://impl.ws.art.tianxiaohui.com/", "service anme");
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		System.out.println(service.getClass());
		//service.
		//service.getPort(serviceEndpointInterface)
		/*service.get
		Greeter client = service.getPort(Greeter.class);
		String result = client.greetMe("test");*/
	}
}
