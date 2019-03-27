package jdbc;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.WebPage;

public class WebPageJDBCDAOTest {
	
	private static LinkedList<String> specifications;
	private static WebPage pageExp;
	private static Integer id;

	@BeforeClass
	public static void setUpBeforeClass(){
		specifications = new LinkedList<>();
		specifications.add("specificationOne");
		specifications.add("specificationsTwo");
		pageExp = new WebPage("urlExp", "nameExp", "priceExp", specifications, "availabilityExp", "descriptionExp", "imgUrlExp");
		WebPageJDBCDAO jdbcdao = new WebPageJDBCDAO();
		jdbcdao.insert(pageExp);
		id = jdbcdao.findByName("nameExp").getId();
	}

	@Test
	public void testFind() {
		WebPage pageResult = new WebPageJDBCDAO().find(id);
		assertEquals(pageResult.getId(), id);
		assertEquals(pageResult.getUrl(), pageExp.getUrl());
		assertEquals(pageResult.getName(), pageExp.getName());
		assertEquals(pageResult.getPrice(), pageExp.getPrice());
		assertEquals(pageResult.getAvailability(), pageExp.getAvailability());
		assertEquals(pageResult.getDescription(), pageExp.getDescription());
		assertEquals(pageResult.getImgUrl(), pageExp.getImgUrl());
		assertTrue(pageExp.getSpecifications().containsAll(pageResult.getSpecifications()));
	}
	
	@Test
	public void testFindByName() {
		WebPage pageResult = new WebPageJDBCDAO().findByName("nameExp");
		assertEquals(pageResult.getId(), id);
		assertEquals(pageResult.getUrl(), pageExp.getUrl());
		assertEquals(pageResult.getName(), pageExp.getName());
		assertEquals(pageResult.getPrice(), pageExp.getPrice());
		assertEquals(pageResult.getAvailability(), pageExp.getAvailability());
		assertEquals(pageResult.getDescription(), pageExp.getDescription());
		assertEquals(pageResult.getImgUrl(), pageExp.getImgUrl());
		assertTrue(pageExp.getSpecifications().containsAll(pageResult.getSpecifications()));
	}
	
	@Test
	public void testFindAll() {
		List<WebPage> pageResultList = new WebPageJDBCDAO().findAll();
		assertTrue(!pageResultList.isEmpty());
		for(WebPage pageResult: pageResultList) {
			if(pageResult.getId()==id) {
				assertEquals(pageResult.getId(), id);
				assertEquals(pageResult.getUrl(), pageExp.getUrl());
				assertEquals(pageResult.getName(), pageExp.getName());
				assertEquals(pageResult.getPrice(), pageExp.getPrice());
				assertEquals(pageResult.getAvailability(), pageExp.getAvailability());
				assertEquals(pageResult.getDescription(), pageExp.getDescription());
				assertEquals(pageResult.getImgUrl(), pageExp.getImgUrl());
				assertTrue(pageExp.getSpecifications().containsAll(pageResult.getSpecifications()));
			}
		}
	}
	
	@Test
	public void testFindAvailability() {
		List<WebPage> pageResultList = new WebPageJDBCDAO().findAvailability("availabilityExp");
		assertTrue(!pageResultList.isEmpty());
		for(WebPage pageResult: pageResultList) {
			if(pageResult.getId()==id) {
				assertEquals(pageResult.getId(), id);
				assertEquals(pageResult.getUrl(), pageExp.getUrl());
				assertEquals(pageResult.getName(), pageExp.getName());
				assertEquals(pageResult.getPrice(), pageExp.getPrice());
				assertEquals(pageResult.getAvailability(), pageExp.getAvailability());
				assertEquals(pageResult.getDescription(), pageExp.getDescription());
				assertEquals(pageResult.getImgUrl(), pageExp.getImgUrl());
				assertTrue(pageExp.getSpecifications().containsAll(pageResult.getSpecifications()));
			}
		}
	}
	
	@Test
	public void testUpdate() {
		WebPageJDBCDAO jdbcdao = new WebPageJDBCDAO();
		LinkedList<String> specificationsUpdate = new LinkedList<>();		
		specificationsUpdate.add("specificationOneUpdate");
		specificationsUpdate.add("specificationsTwoUpdate");
		WebPage pageExpUpdate = new WebPage(id, "urlUpdate", "nameUpdate", "priceUpdate", specificationsUpdate, "availabilityUpdate", "descriptionUpdate", "imgUrlUpdate");
		jdbcdao.update(pageExpUpdate);
		WebPage pageResult = jdbcdao.find(id);
		assertEquals(pageResult.getId(), id);
		assertEquals(pageResult.getUrl(), pageExpUpdate.getUrl());
		assertEquals(pageResult.getName(), pageExpUpdate.getName());
		assertEquals(pageResult.getPrice(), pageExpUpdate.getPrice());
		assertEquals(pageResult.getAvailability(), pageExpUpdate.getAvailability());
		assertEquals(pageResult.getDescription(), pageExpUpdate.getDescription());
		assertEquals(pageResult.getImgUrl(), pageExpUpdate.getImgUrl());
		assertTrue(pageExpUpdate.getSpecifications().containsAll(pageResult.getSpecifications()));
	}
	
	/*@Test
	public void testDelete() {
		WebPageJDBCDAO jdbcdao = new WebPageJDBCDAO();
		jdbcdao.delete(id);
		WebPage pageResult = jdbcdao.find(id);
		assertTrue(pageResult.getId()==null);
		assertTrue(pageResult.getName()==null);
		assertTrue(pageResult.getUrl()==null);
		assertTrue(pageResult.getPrice()==null);
		assertTrue(pageResult.getAvailability()==null);
		assertTrue(pageResult.getDescription()==null);
		assertTrue(pageResult.getImgUrl()==null);
		assertTrue(pageResult.getSpecifications().isEmpty());
	}*/
	
	@AfterClass
	public static void  tearDownAfterClass() {
		new WebPageJDBCDAO().delete(id);
	}
}
