package service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GetInformationFromUrlTest {
	
	private String url ="https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4";
	private String articleId ="B078WRSHV4";
	private String name ="LG gram Thin and Light Laptop - 15.6\" Full HD IPS Display, Intel Core i5 (8th Gen), 8GB RAM, 256GB SSD, Back-lit Keyboard - Dark Silver – 15Z980-U.AAS5U1";	
	private GetInformationFromUrl instance = new GetInformationFromUrl(url);

	@Test
	public void testGetUrl() {		
		String expResult = url;
		String result = instance.getUrl();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetArticleId() {
		String expResult = articleId;
		String result = instance.getArticleId();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetName() {
		String expResult = name;
		String result = instance.getName();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetPrice() {
		String result = instance.getPrice();
		assertNotNull(result);
	}
	
	@Test
	public void testGetDescription() {
		List<String> result = instance.getDescription();
		assertNotNull(result);
		assertTrue(result.size()!=0);
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void testGetAvailability() {
		String result = instance.getAvailability();
		assertNotNull(result);
	}

}
