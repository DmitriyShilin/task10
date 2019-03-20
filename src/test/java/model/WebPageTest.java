package model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class WebPageTest {
	
	private static LinkedList<String> specifications;
	private static WebPage webPage;
	
	@BeforeClass
	public static void init() {
		specifications = new LinkedList<>();
		specifications.add("specifications");
		webPage = new WebPage("url", "articleId", "name", "price", specifications, "availability", "description", "imgUrl");
	}

	@Test
	public void testGetUrl() {
		String expResult = "url";
		String result = webPage.getUrl();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetUrl() {
		WebPage page = new WebPage();
		String expResult = "urlExpect";
		page.setUrl(expResult);
		String result = page.getUrl();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetArticleId() {
		String expResult = "articleId";
		String result = webPage.getArticleId();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetArticleId() {
		WebPage page = new WebPage();
		String expResult = "articleIdExpect";
		page.setArticleId(expResult);
		String result = page.getArticleId();
		assertEquals(expResult, result);
	}
	
	
	@Test
	public void testGetPrice() {
		String expResult = "price";
		String result = webPage.getPrice();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetPrice() {
		WebPage page = new WebPage();
		String expResult = "priceExpect";
		page.setPrice(expResult);
		String result = page.getPrice();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetSpecifications() {
		LinkedList<String> expResult = specifications;
		LinkedList<String> result = webPage.getSpecifications();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}
	
	@Test
	public void testSetSpecifications() {
		WebPage page = new WebPage();
		LinkedList<String> expResult = new LinkedList<>();
		expResult.add("Specifications");
		expResult.add("Expect");
		page.setSpecifications(expResult);
		LinkedList<String> result = page.getSpecifications();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}
	
	@Test
	public void testGetAvailability() {
		String expResult = "availability";
		String result = webPage.getAvailability();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetAvailability() {
		WebPage page = new WebPage();
		String expResult = "availabilityExpect";
		page.setAvailability(expResult);
		String result = page.getAvailability();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetDescription() {
		String expResult = "description";
		String result = webPage.getDescription();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetDescription() {
		WebPage page = new WebPage();
		String expResult = "descriptionExpect";
		page.setDescription(expResult);
		String result = page.getDescription();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetImgUrl() {
		String expResult = "imgUrl";
		String result = webPage.getImgUrl();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetImgUrl() {
		WebPage page = new WebPage();
		String expResult = "imgUrlExpect";
		page.setImgUrl(expResult);
		String result = page.getImgUrl();
		assertEquals(expResult, result);
	}
}
