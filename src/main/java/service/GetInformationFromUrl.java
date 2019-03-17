package service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInformationFromUrl {
	
	private Document doc;
	
	private Logger logger = Logger.getLogger(GetInformationFromUrl.class.getName());
	
	public GetInformationFromUrl(String url) {
		doc = getDocument(url);
	}
	
	private Document getDocument(String url) {
		try {
			doc = Jsoup.connect(url).userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
		} catch (IOException e) {			
			logger.log(Level.SEVERE, null, e);
		}
		return doc;
	}
	
	public String getUrl() {
		return doc.location();
	}
	
	public String getArticleId() {
		String[] strList = getUrl().split("/");
		return strList[strList.length - 1];
	}
	
	public String getName() {
		Element nameElement = doc.getElementById("productTitle");		
		return nameElement.text();
	}
	
	public String getPrice() {
		Element priceElement = doc.getElementById("priceblock_ourprice");
		return priceElement.text();
	}
	
	public LinkedList<String> getDescription() {
		LinkedList<String> description = new LinkedList<>();
		Element descriptionElement = doc.getElementById("featurebullets_feature_div");
		Elements descriptionElements = descriptionElement.getElementsByClass("a-list-item");
		descriptionElements.remove(0);
		for(Element e: descriptionElements) {
			description.add(e.text());
		}
		return description;
	}
	
	public String getAvailability() {
		Element availabilityElement = doc.getElementById("availability");
		return availabilityElement.text();
	}

}
