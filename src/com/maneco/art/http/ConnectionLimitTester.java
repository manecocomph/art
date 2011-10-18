package com.maneco.art.http;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ConnectionLimitTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void testLimit(int count) {
		HttpClient http = null;
		try {
			HttpGet get = new HttpGet();
			get.setURI(new URI(uri));
			http = new DefaultHttpClient();
			HttpResponse res = http.execute(get);

			if (res.getStatusLine().getStatusCode() != 200
					&& res.getStatusLine().getStatusCode() != 302) {
				EntityUtils.consume(res.getEntity());
				//throw new IOException(res.getStatusLine().toString());
				//TODO add more check
				return 1;
			}
	}

}
