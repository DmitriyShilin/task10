package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BotUserTest {
	
	private BotUser botUser = new BotUser( 25, "name", "email", "password");
	
	@Test
	public void testGetNId() {
		Integer expResult = 25;
		Integer result = botUser.getId();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetId() {
		BotUser bot = new BotUser();
		Integer expResult = 25;
		bot.setId(expResult);
		Integer result = bot.getId();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetName() {
		String expResult = "name";
		String result = botUser.getName();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetName() {
		BotUser bot = new BotUser();
		String expResult = "nameExpect";
		bot.setName(expResult);
		String result = bot.getName();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetEmail() {
		String expResult = "email";
		String result = botUser.getEmail();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetEmail() {
		BotUser bot = new BotUser();
		String expResult = "mailExpect";
		bot.setEmail(expResult);
		String result = bot.getEmail();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetPassword() {
		String expResult = "password";
		String result = botUser.getPassword();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetPassword() {
		BotUser bot = new BotUser();
		String expResult = "passwordExpect";
		bot.setPassword(expResult);
		String result = bot.getPassword();
		assertEquals(expResult, result);
	}
}
