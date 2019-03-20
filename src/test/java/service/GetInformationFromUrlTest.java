package service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GetInformationFromUrlTest {
	
	private String url ="https://prom.ua/p895411456-igrovoj-noutbu-omen.html";
	private String articleId ="p895411456-igrovoj-noutbu-omen";
	private String name ="»„Ó‚ÓÈ ÌÓÛÚ·Û Omen by HP 15-dc0018nq, IntelÆ Coreô i5-8300H 4GHz, 15.6\" Full HD, 8GB 1TB, NVIDIA GTX,1050TI 4";	
	private String description = "Gaming Omen by HP 15-dc0018nq, Procesor IntelÆ Coreô i5-8300H pana la 4GHz, 15.6\" Full HD, 8GB, 1TB, NVIDIA GeForce GTX 1050 Ti 4GB, Free Dos";
	private String imgUrl = "https://images.ua.prom.st/1601960705_w640_h640_igrovoj-noutbu-omen.jpg";
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
	public void testGetSpecifications() {
		List<String> result = instance.getSpecifications();
		assertNotNull(result);
		assertTrue(result.size()!=0);
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void testGetDescription() {
		String expResult = description;
		String result = instance.getDescription();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetAvailability() {
		String result = instance.getAvailability();
		assertNotNull(result);
	}
	
	@Test
	public void testGetImgUrl() {
		String expResult = imgUrl;
		String result = instance.getImgUrl();
		assertEquals(expResult, result);
	}
}
