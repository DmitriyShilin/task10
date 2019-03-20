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
		return strList[strList.length - 1].substring(0, strList[strList.length - 1].length()-5);
	}
	
	public String getName() {
		Elements nameElement = doc.getElementsByAttributeValue("data-qaid", "product_name");		
		return nameElement.first().text();
	}
	
	public String getPrice() {
		Elements priceElement = doc.getElementsByAttributeValue("itemprop", "price");
		Elements priceCurrencyElement = doc.getElementsByAttributeValue("itemprop", "priceCurrency");
		return priceElement.first().text() + priceCurrencyElement.get(1).text();		
	}
	
	public LinkedList<String> getSpecifications() {
		LinkedList<String> specifications = new LinkedList<>();		
		Elements descriptionElements = doc.getElementsByClass("x-attributes__row js-attributes");
		for(Element e: descriptionElements) {			
			Elements leftElement = e.getElementsByClass("x-attributes__value");
			Elements rightElement = e.getElementsByClass("x-attributes__right");
			specifications.add(leftElement.first().text()+" : "+ rightElement.first().text());
		}
		return specifications;
	}
	
	public String getDescription() {
		Elements descriptionElement = doc.getElementsByAttributeValue("itemprop", "description");		
		return descriptionElement.get(1).text();
	}

	public String getAvailability() {
		Elements availabilityElement = doc.getElementsByAttributeValue("data-qaid", "product_presence");
		return availabilityElement.text();
	}
	
	public String getImgUrl() {
		Elements imgElement = doc.select("img");		
		return imgElement.get(1).absUrl("src");
	}
}
