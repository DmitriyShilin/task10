package model;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "webpages")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebPages {
	
	@XmlElement(name = "webpage")
	private LinkedList<WebPage> webpages;
	
	public WebPages() {
		webpages = new LinkedList<>();
	}
	
	public WebPages(LinkedList<WebPage> webpages) {
		this.webpages = webpages;
	}
	
	public void setWebPages(LinkedList<WebPage> webpages) {
		this.webpages=webpages;
	}
	
	public LinkedList<WebPage> getWebPages(){
		return webpages;
	}

}
