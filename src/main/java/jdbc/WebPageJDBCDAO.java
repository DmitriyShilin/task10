package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.WebPage;

public class WebPageJDBCDAO extends BaseDAO<WebPage>{
	
	private Logger logger = Logger.getLogger(WebPageJDBCDAO.class.getName());

	@Override
	public void insert(WebPage page) {
		String query = "INSERT INTO webpage(url, name, price, availability, description, img_url, specifications) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, page.getUrl());
			ps.setString(2, page.getName());
			ps.setString(3, page.getPrice());
			ps.setString(4, page.getAvailability());
			ps.setString(5, page.getDescription());
			ps.setString(6, page.getImgUrl());
			ps.setString(7, specificationsToText(page.getSpecifications()));
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(Integer id) {
		String query = "DELETE FROM webpage WHERE id=" + id;
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement()){
			stmt.executeUpdate(query);			
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}		
	}

	@Override
	public void update(WebPage page) {
		String query = "UPDATE webpage SET url=?, name=?, price=?, availability=?, description=?, img_url=?, specifications=? WHERE id=?";
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, page.getUrl());
			ps.setString(2, page.getName());
			ps.setString(3, page.getPrice());
			ps.setString(4, page.getAvailability());
			ps.setString(5, page.getDescription());
			ps.setString(6, page.getImgUrl());
			ps.setString(7, specificationsToText(page.getSpecifications()));
			ps.setInt(8, page.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public WebPage find(Integer id) { 
		WebPage page = new WebPage();
		String query = "SELECT * FROM webpage WHERE id=" + id;
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query)) {
			if(rs.next()) {				
				page.setId(rs.getInt("id"));				
				page.setUrl(rs.getString("url"));
				page.setName(rs.getString("name"));
				page.setPrice(rs.getString("price"));
				page.setAvailability(rs.getString("availability"));
				page.setDescription(rs.getString("description"));
				page.setImgUrl(rs.getString("img_url"));
				page.setSpecifications(textToSpecifications(rs.getString("specifications")));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}		
		return page;
	}
	
	public WebPage findByName(String name) { 
		WebPage page = new WebPage();
		String query = "SELECT * FROM webpage WHERE name='" + name + "'";
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query)) {
			if(rs.next()) {
				page.setId(rs.getInt("id"));
				page.setUrl(rs.getString("url"));
				page.setName(rs.getString("name"));
				page.setPrice(rs.getString("price"));
				page.setAvailability(rs.getString("availability"));
				page.setDescription(rs.getString("description"));
				page.setImgUrl(rs.getString("img_url"));
				page.setSpecifications(textToSpecifications(rs.getString("specifications")));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return page;
	}

	@Override
	public List<WebPage> findAll() {
		List<WebPage> pageList = new LinkedList<>();	
		String query = "SELECT * FROM webpage";
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query);) {			
			while(rs.next()) {
				WebPage page = new WebPage();
				page.setId(rs.getInt("id"));
				page.setUrl(rs.getString("url"));
				page.setName(rs.getString("name"));
				page.setPrice(rs.getString("price"));
				page.setAvailability(rs.getString("availability"));
				page.setDescription(rs.getString("description"));
				page.setImgUrl(rs.getString("img_url"));
				page.setSpecifications(textToSpecifications(rs.getString("specifications")));
				pageList.add(page);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return pageList;
	}
	
	public List<WebPage> findAvailability(String availability) {
		List<WebPage> pageList = new LinkedList<>();	
		String query = "SELECT * FROM webpage WHERE availability='" + availability + "'";
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query);) {
			while(rs.next()) {
				WebPage page = new WebPage();
				page.setId(rs.getInt("id"));
				page.setUrl(rs.getString("url"));
				page.setName(rs.getString("name"));
				page.setPrice(rs.getString("price"));
				page.setAvailability(rs.getString("availability"));
				page.setDescription(rs.getString("description"));
				page.setImgUrl(rs.getString("img_url"));
				page.setSpecifications(textToSpecifications(rs.getString("specifications")));
				pageList.add(page);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return pageList;
	}
	
	private String specificationsToText(List<String> specifications) {
		String text = "";
		for(int i=0; i<specifications.size(); i++) {
			text +=specifications.get(i);
			if(i<specifications.size()-1) text +="\n";
		}
		return text;
	}
	
	private LinkedList<String> textToSpecifications(String specification){
		LinkedList<String> strList = new LinkedList<>();
		strList.addAll(Arrays.asList(specification.split("\n")));
		return strList;
	}	
}
