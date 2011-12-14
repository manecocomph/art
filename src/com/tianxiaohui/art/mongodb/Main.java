package com.tianxiaohui.art.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.tianxiaohui.art.mongodb.vo.Tweet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tweet myTweet = new Tweet();
		myTweet.put("user", "eric");
		myTweet.put("password", "notPassword");
		
		/*DB db = DBConnector;
		DBCollection dbCollection = DBCollection
		
		dbCollection.insert(myTweet);*/
		try {
			Mongo m = new Mongo("d-shc-00425541", 27017);
			DB db = m.getDB("test");
			
			Set<String> colls = db.getCollectionNames();

			for (String s : colls) {
			    System.out.println(s);
			}
			
			DBCollection test = db.getCollection("test");
			test.insert(myTweet);
			
			//db.cl
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
