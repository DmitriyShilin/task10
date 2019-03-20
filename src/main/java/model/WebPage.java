package model;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "webpage")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebPage {
	
	private String url;
	private String articleId;
	private String name;
	private String price;	
	private String availability;
	private String description;
	private String imgUrl;
	
	@XmlElementWrapper(name = "descriptions")
	@XmlElement(name = "description")
	private LinkedList<String> specifications;
	
	public WebPage() {
		specifications = new LinkedList<>();
	}
	
	public WebPage(String url, String articleId, String name, String price, LinkedList<String> specifications, String availability, String description, String imgUrl) {
		this.url = url;
		this.articleId = articleId;
		this.name = name;
		this.price = price;
		this.specifications = specifications;
		this.availability = availability;
		this.description = description;
		this.imgUrl = imgUrl;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	public String getArticleId() {
		return articleId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setSpecifications(LinkedList<String> specifications) {
		this.specifications = specifications;
	}
	
	public LinkedList<String> getSpecifications() {
		return specifications;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public String getAvailability() {
		return availability;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
}
