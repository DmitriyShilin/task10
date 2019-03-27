package jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.BotUser;

public class BotUserJDBCDAOTest {
	
	private static BotUser user = new BotUser("nameExp", "emailExp", "passwordExp");
	private static Integer id;

	@BeforeClass
	public static void setUpBeforeClass(){
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.insert(user);	
		id = jdbcdao.findByEmail("emailExp").getId();
	}	

	@Test
	public void testFind() {
		BotUser userResult = new BotUserJDBCDAO().find(id);
		assertEquals(userResult.getName(), user.getName());
		assertEquals(userResult.getEmail(), user.getEmail());
		assertEquals(userResult.getPassword(), user.getPassword());
	}
	
	@Test
	public void testFindByName() {
		List<BotUser> userResultList = new BotUserJDBCDAO().findByName("nameExp");
		assertTrue(!userResultList.isEmpty());
		assertEquals(userResultList.get(0).getName(), user.getName());
		assertEquals(userResultList.get(0).getEmail(), user.getEmail());
		assertEquals(userResultList.get(0).getPassword(), user.getPassword());
	}
	
	@Test
	public void testFindAll() {
		List<BotUser> userResultList = new BotUserJDBCDAO().findAll();
		assertTrue(!userResultList.isEmpty());
		for(BotUser userResult: userResultList) {
			if(userResult.getId().equals(id)) {
				assertEquals(userResult.getName(), user.getName());
				assertEquals(userResult.getEmail(), user.getEmail());
				assertEquals(userResult.getPassword(), user.getPassword());
			}
		}		
	}
	
	@Test
	public void testFindByEmail() {		
		BotUser userResult = new BotUserJDBCDAO().findByEmail("emailExp");
		assertEquals(userResult.getName(), user.getName());
		assertEquals(userResult.getEmail(), user.getEmail());
		assertEquals(userResult.getPassword(), user.getPassword());
	}
	
	@Test
	public void testUpdate() {
		BotUser userUpdate = new BotUser(id, "nameUpdate", "emailUpdate", "passwordUpdate");
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.update(userUpdate);
		BotUser userResult = jdbcdao.find(id);
		assertEquals(userResult.getName(), userUpdate.getName());
		assertEquals(userResult.getEmail(), userUpdate.getEmail());
		assertEquals(userResult.getPassword(), userUpdate.getPassword());
	}
	
	/*@Test
	public void testDelete() {
		BotUserJDBCDAO jdbcdao = new BotUserJDBCDAO();
		jdbcdao.delete(id);
		BotUser userResult = jdbcdao.find(id);
		assertTrue(userResult.getId()==null);
		assertTrue(userResult.getName()==null);
		assertTrue(userResult.getEmail()==null);
		assertTrue(userResult.getPassword()==null);
	}*/
	
	@AfterClass
	public static void  tearDownAfterClass() {
		new BotUserJDBCDAO().delete(id);
	}
}
