package jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.BotUser;

public class BotUserJDBCDAOTest {
	
	private static BotUser userExp = new BotUser("nameExp", "emailExp", "passwordExp");
	private static Integer idExp;
	
	private static BotUser userUpd = new BotUser("nameUpd", "emailUpd", "passwordUpd");
	private static Integer idUpd;
	
	private static BotUser userDel = new BotUser("nameDel", "emailDel", "passwordDel");
	private static Integer idDel;

	@BeforeClass
	public static void setUpBeforeClass(){
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.insert(userExp);
		jdbcdao.insert(userUpd);
		jdbcdao.insert(userDel);
		idExp = jdbcdao.findByEmail("emailExp").getId();
		idUpd = jdbcdao.findByEmail("emailUpd").getId();
		idDel = jdbcdao.findByEmail("emailDel").getId();
	}	

	@Test
	public void testFind() {
		BotUser userResult = new BotUserJDBCDAO().find(idExp);
		assertEquals(userResult.getId(), idExp);
		assertEquals(userResult.getName(), userExp.getName());
		assertEquals(userResult.getEmail(), userExp.getEmail());
		assertEquals(userResult.getPassword(), userExp.getPassword());
	}
	
	@Test
	public void testFindByName() {
		List<BotUser> userResultList = new BotUserJDBCDAO().findByName("nameExp");
		assertTrue(!userResultList.isEmpty());
		for(BotUser userResult: userResultList) {
			if(userResult.getId().equals(idExp)) {
				assertEquals(userResult.getId(), idExp);
				assertEquals(userResult.getName(), userExp.getName());
				assertEquals(userResult.getEmail(), userExp.getEmail());
				assertEquals(userResult.getPassword(), userExp.getPassword());
			}
		}
	}
	
	@Test
	public void testFindAll() {
		List<BotUser> userResultList = new BotUserJDBCDAO().findAll();
		assertTrue(!userResultList.isEmpty());
		for(BotUser userResult: userResultList) {
			if(userResult.getId().equals(idExp)) {
				assertEquals(userResult.getId(), idExp);
				assertEquals(userResult.getName(), userExp.getName());
				assertEquals(userResult.getEmail(), userExp.getEmail());
				assertEquals(userResult.getPassword(), userExp.getPassword());
			}
		}		
	}
	
	@Test
	public void testFindByEmail() {	
		BotUser userResult = new BotUserJDBCDAO().findByEmail("emailExp");
		assertEquals(userResult.getId(), idExp);
		assertEquals(userResult.getName(), userExp.getName());
		assertEquals(userResult.getEmail(), userExp.getEmail());
		assertEquals(userResult.getPassword(), userExp.getPassword());
	}
	
	@Test
	public void testUpdate() {
		BotUser userUpdate = new BotUser(idUpd, "nameUpdate", "emailUpdate", "passwordUpdate");
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.update(userUpdate);
		BotUser userResult = jdbcdao.find(idUpd);
		assertEquals(userResult.getId(), userUpdate.getId());
		assertEquals(userResult.getName(), userUpdate.getName());
		assertEquals(userResult.getEmail(), userUpdate.getEmail());
		assertEquals(userResult.getPassword(), userUpdate.getPassword());
	}
	
	@Test
	public void testDelete() {
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.delete(idDel);
		BotUser userResult = jdbcdao.find(idDel);
		assertTrue(userResult.getId()==null);
		assertTrue(userResult.getName()==null);
		assertTrue(userResult.getEmail()==null);
		assertTrue(userResult.getPassword()==null);
	}
	
	@AfterClass
	public static void  tearDownAfterClass() {
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.delete(idExp);
		jdbcdao.delete(idUpd);
	}
}
