package jdbc;

import java.util.List;

import model.BotUser;

public interface BotUserDAO {

	public void insert(BotUser user);
	
	public void delete(Integer id);
	
	public void update(BotUser user);
	
	public BotUser find(Integer id);
	
	public BotUser findByEmail(String email);
	
	public List<BotUser> findByName(String name);
	
	public List<BotUser> findAll();	
}
