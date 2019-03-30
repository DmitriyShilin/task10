package jdbc;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.AddToCart;

public class AddToCartJDBCDAOTest {
	
	private static Timestamp timestampExp = Timestamp.valueOf(LocalDateTime.now());
	private static Timestamp timestampUpd = Timestamp.valueOf(LocalDateTime.now());
	private static Timestamp timestampDel = Timestamp.valueOf(LocalDateTime.now());
	
	private static AddToCart cartExp = new AddToCart("urlExp", "emailExp", "passwordExp", timestampExp);
	private static Integer idExp;
	
	private static AddToCart cartUpd = new AddToCart("urlUpd", "emailUpd", "passwordUpd", timestampUpd);
	private static Integer idUpd;
	
	private static AddToCart cartDel = new AddToCart("urlDel", "emailDel", "passwordDel", timestampDel);
	private static Integer idDel;

	@BeforeClass
	public static void setUpBeforeClass(){
		AddToCartJDBCDAO jdbcdao = new AddToCartJDBCDAO();
		jdbcdao.insert(cartExp);
		jdbcdao.insert(cartUpd);
		jdbcdao.insert(cartDel);
		idExp = jdbcdao.findByEmail("emailExp").getId();
		idUpd = jdbcdao.findByEmail("emailUpd").getId();
		idDel = jdbcdao.findByEmail("emailDel").getId();
	}

	@AfterClass
	public static void tearDownAfterClass(){
		AddToCartJDBCDAO jdbcdao = new AddToCartJDBCDAO();
		jdbcdao.delete(idExp);
		jdbcdao.delete(idUpd);
	}

	@Test
	public void testFind() {
		AddToCart cartResult = new AddToCartJDBCDAO().find(idExp);
		assertEquals(cartResult.getId(), idExp);
		assertEquals(cartResult.getUrl(), cartExp.getUrl());
		assertEquals(cartResult.getEmail(), cartExp.getEmail());
		assertEquals(cartResult.getPassword(), cartExp.getPassword());
		assertEquals(cartResult.getTimestamp(), cartExp.getTimestamp());
	}
	
	@Test
	public void testFindByEmail() {	
		AddToCart cartResult = new AddToCartJDBCDAO().findByEmail("emailExp");
		assertEquals(cartResult.getId(), idExp);
		assertEquals(cartResult.getUrl(), cartExp.getUrl());
		assertEquals(cartResult.getEmail(), cartExp.getEmail());
		assertEquals(cartResult.getPassword(), cartExp.getPassword());
		assertEquals(cartResult.getTimestamp(), cartExp.getTimestamp());
	}
	
	@Test
	public void testFindAll() {
		List<AddToCart> cartResultList = new AddToCartJDBCDAO().findAll();		
		assertTrue(!cartResultList.isEmpty());
		for(AddToCart cartResult: cartResultList) {
			if(cartResult.getId().equals(idExp)) {
				assertEquals(cartResult.getId(), idExp);
				assertEquals(cartResult.getUrl(), cartExp.getUrl());
				assertEquals(cartResult.getEmail(), cartExp.getEmail());
				assertEquals(cartResult.getPassword(), cartExp.getPassword());
				assertEquals(cartResult.getTimestamp(), cartExp.getTimestamp());
			}
		}		
	}
	
	@Test
	public void testUpdate() {
		AddToCart cartUpdate = new AddToCart(idUpd, "urlUpdate", "emailUpdate", "passwordUpdate", Timestamp.valueOf(LocalDateTime.now()));
		AddToCartJDBCDAO jdbcdao = new AddToCartJDBCDAO();
		jdbcdao.update(cartUpdate);
		AddToCart userResult = jdbcdao.find(idUpd);
		assertEquals(userResult.getId(), cartUpdate.getId());
		assertEquals(userResult.getUrl(), cartUpdate.getUrl());
		assertEquals(userResult.getEmail(), cartUpdate.getEmail());
		assertEquals(userResult.getPassword(), cartUpdate.getPassword());
		assertEquals(userResult.getTimestamp(), cartUpdate.getTimestamp());
	}
	
	@Test
	public void testDelete() {
		AddToCartJDBCDAO jdbcdao = new AddToCartJDBCDAO();
		jdbcdao.delete(idDel);
		AddToCart userResult = jdbcdao.find(idDel);
		assertTrue(userResult.getId()==null);
		assertTrue(userResult.getUrl()==null);
		assertTrue(userResult.getEmail()==null);
		assertTrue(userResult.getPassword()==null);
		assertTrue(userResult.getTimestamp()==null);
	}
}
