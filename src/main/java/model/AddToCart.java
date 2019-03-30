package model;

import java.sql.Timestamp;

public class AddToCart {
	
	private Integer id;
	private String url;	
	private String email;
	private String password;
	private Timestamp timestamp;
	
	public AddToCart() {
		
	}
	
	public AddToCart(String url, String email, String password, Timestamp timestamp) {		
		this.url = url;
		this.email = email;
		this.password = password;
		this.timestamp = timestamp;
	}
	
	public AddToCart(Integer id, String url, String email, String password, Timestamp timestamp) {
		this.id = id;		
		this.url = url;
		this.email = email;
		this.password = password;
		this.timestamp = timestamp;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		 this.id = id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		 this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		 this.password = password;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		 this.timestamp = timestamp;
	}
}
