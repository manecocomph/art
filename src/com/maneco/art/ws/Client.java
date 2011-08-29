package com.maneco.art.ws;

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBElement.GlobalScope;
import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxyFactoryBean;

import com.ebay.ice.api.ProjectHelper;
import com.ebay.ice.api.ProjectHelperPortType;
import com.ebay.ice.api.xsd.ArrayOfDeployObj;
import com.ebay.ice.api.xsd.AuthObj;
import com.ebay.ice.api.xsd.DeployObj;
import com.ebay.ice.api.xsd.ProjectObj;
import com.maneco.peanut.service.IRegionService;

public class Client {

	public static void testJaxws() {
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setServiceClass(JaxwsProvider.class);
		factory.setAddress("http://127.0.0.1:8080/trail/cxf/jaxwsEndpoint");
		JaxwsProvider client = (JaxwsProvider) factory.create();
		System.out.println(client.speakHi("eric"));
	}
	
	public static void testFrontend() {
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setServiceClass(IRegionService.class);
		factory.setAddress("http://l-shc-00425120:8080/trail/cxf/hello");
		factory.setEndpointName(new QName("http://impl.service.peanut.maneco.com/"));
		System.out.println(factory.getEndpointName());
		//factory.setWsdlURL("http://impl.service.peanut.maneco.com/");
		IRegionService client = (IRegionService) factory.create();
		
		System.out.println(client.what(90));
	}
	public static void main(String[] args) {
		//Client.testFrontend();
		try {
			URL wsdlURL = ProjectHelper.WSDL_LOCATION;
			final QName SERVICE_NAME = new QName("http://api.ice.ebay.com",
					"ProjectHelper");
			ProjectHelper ss = new ProjectHelper(wsdlURL, SERVICE_NAME);
			ProjectHelperPortType port = ss
					.getProjectHelperHttpSoap11Endpoint();
			ProjectObj po = port.getProject("1858193", "509983");
			System.out.println(po.getConfigSpec());
			ArrayOfDeployObj doa = po.getDeployObjArr();
			List<DeployObj> doList = doa.getDeployObj();
			for (DeployObj doo: doList) {
				System.out.println(doo.getDeliverable());
				System.out.println(doo.getParam());
			}
			ArrayOfDeployObj aaa = new ArrayOfDeployObj();
			DeployObj e = new DeployObj();
			e.setParam("-force -deliverable Unified -targetos win64-amd64 -javaargs=-Xms3000m@-Xmx3000m@-Xcompressedrefs@-Xgc:preferredHeapBase=100000000");
			if (null == po.getDeployObjArr()) {
				po.setDeployObjArr(aaa);
			}
			
			po.getDeployObjArr().getDeployObj().add(e);
			AuthObj ao = new AuthObj();
			ao.setUserName("tcteam");
			ao.setPassword("tcteam");
			port.updateProjectDeployParam(po, ao);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
