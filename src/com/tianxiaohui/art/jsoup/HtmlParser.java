package com.tianxiaohui.art.jsoup;

import java.io.IOException;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.sherdog.com/fighter/Georges-St-Pierre-3500").get();
			Elements newsHeadlines = doc.select("#fighter_stat table tbody tr");
			ListIterator<Element> iterator = newsHeadlines.listIterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
