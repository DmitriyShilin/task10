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

public class WebPageJDBCDAO implements WebPageDAO{
	
	private Logger logger = Logger.getLogger(WebPageJDBCDAO.class.getName());

	@Override
	public void insert(WebPage page) {
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("INSERT INTO webpage(URL, NAME, PRICE, AVAILABILITY, DESCRIPTION, IMG_URL, SPECIFICATIONS) VALUES (?, ?, ?, ?, ?, ?, ?)")){
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
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement()){
			stmt.executeUpdate("DELETE FROM WebPage WHERE ID=" + id);			
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}		
	}

	@Override
	public void update(WebPage page) {
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("UPDATE webpage SET URL=?, NAME=?, PRICE=?, AVAILABILITY=?, DESCRIPTION=?, IMG_URL=?, SPECIFICATIONS=? WHERE ID=?")){
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
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery("SELECT * FROM webpage WHERE ID=" + id);) {
			if(rs.next()) {				
				page.setId(rs.getInt("ID"));				
				page.setUrl(rs.getString("URL"));
				page.setName(rs.getString("NAME"));
				page.setPrice(rs.getString("PRICE"));
				page.setAvailability(rs.getString("AVAILABILITY"));
				page.setDescription(rs.getString("DESCRIPTION"));
				page.setImgUrl(rs.getString("IMG_URL"));
				page.setSpecifications(textToSpecifications(rs.getString("SPECIFICATIONS")));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}		
		return page;
	}

	@Override
	public WebPage findByName(String name) { 
		WebPage page = new WebPage();		
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery("SELECT * FROM webpage WHERE NAME='" + name + "'");) {
			if(rs.next()) {
				page.setId(rs.getInt("ID"));
				page.setUrl(rs.getString("URL"));
				page.setName(rs.getString("NAME"));
				page.setPrice(rs.getString("PRICE"));
				page.setAvailability(rs.getString("AVAILABILITY"));
				page.setDescription(rs.getString("DESCRIPTION"));
				page.setImgUrl(rs.getString("IMG_URL"));
				page.setSpecifications(textToSpecifications(rs.getString("SPECIFICATIONS")));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return page;
	}

	@Override
	public List<WebPage> findAll() {
		List<WebPage> pageList = new LinkedList<>();		
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery("SELECT * FROM webpage");) {			
			while(rs.next()) {
				WebPage page = new WebPage();
				page.setId(rs.getInt("ID"));
				page.setUrl(rs.getString("URL"));
				page.setName(rs.getString("NAME"));
				page.setPrice(rs.getString("PRICE"));
				page.setAvailability(rs.getString("AVAILABILITY"));
				page.setDescription(rs.getString("DESCRIPTION"));
				page.setImgUrl(rs.getString("IMG_URL"));
				page.setSpecifications(textToSpecifications(rs.getString("SPECIFICATIONS")));
				pageList.add(page);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return pageList;
	}

	@Override
	public List<WebPage> findAvailability(String availability) {
		List<WebPage> pageList = new LinkedList<>();		
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery("SELECT * FROM webpage WHERE AVAILABILITY='" + availability + "'");) {			
			while(rs.next()) {
				WebPage page = new WebPage();
				page.setId(rs.getInt("ID"));
				page.setUrl(rs.getString("URL"));
				page.setName(rs.getString("NAME"));
				page.setPrice(rs.getString("PRICE"));
				page.setAvailability(rs.getString("AVAILABILITY"));
				page.setDescription(rs.getString("DESCRIPTION"));
				page.setImgUrl(rs.getString("IMG_URL"));
				page.setSpecifications(textToSpecifications(rs.getString("SPECIFICATIONS")));
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
