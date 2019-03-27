package jdbc;

import java.util.List;

import model.WebPage;

public interface WebPageDAO {
	
	public void insert(WebPage page);
	
	public void delete(Integer id);
	
	public void update(WebPage page);
	
	public WebPage find(Integer id);
	
	public WebPage findByName(String name);
	
	public List<WebPage> findAll();
	
	public List<WebPage> findAvailability(String availability);
}
