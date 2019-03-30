package model;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

public class AddToCartTest {
	private Timestamp timestampExp = Timestamp.valueOf(LocalDateTime.now());
	private AddToCart cartExp = new AddToCart(10, "urlExp", "emailExp", "passwordExp", timestampExp);

	@Test
	public void testGetNId() {
		Integer expResult = 10;
		Integer result = cartExp.getId();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetId() {
		AddToCart cart = new AddToCart();
		Integer expResult = 25;
		cart.setId(expResult);
		Integer result = cart.getId();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetUrl() {
		String expResult = "urlExp";
		String result = cartExp.getUrl();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetUrl() {
		AddToCart cart = new AddToCart();
		String expResult = "urlExpect";
		cart.setUrl(expResult);
		String result = cart.getUrl();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetEmail() {
		String expResult = "emailExp";
		String result = cartExp.getEmail();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetEmail() {
		AddToCart cart = new AddToCart();
		String expResult = "mailExpect";
		cart.setEmail(expResult);
		String result = cart.getEmail();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetPassword() {
		String expResult = "passwordExp";
		String result = cartExp.getPassword();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetPassword() {
		AddToCart cart = new AddToCart();
		String expResult = "passswordExpect";
		cart.setPassword(expResult);
		String result = cart.getPassword();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetTimestamp() {
		Timestamp expResult = timestampExp;
		Timestamp result = cartExp.getTimestamp();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetTimestamp() {
		AddToCart cart = new AddToCart();
		Timestamp expResult = Timestamp.valueOf(LocalDateTime.now());
		cart.setTimestamp(expResult);
		Timestamp result = cart.getTimestamp();
		assertEquals(expResult, result);
	}
}
