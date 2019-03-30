package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.BotUser;

public class BotUserJDBCDAO extends BaseDAO<BotUser>{
	
	private Logger logger = Logger.getLogger(BotUserJDBCDAO.class.getName());

	@Override
	public void insert(BotUser user) {
		String query = "INSERT INTO botuser(name, email, password) VALUES (?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection(); 
		    PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(Integer id) {
		String query = "DELETE FROM botuser WHERE id=" + id;
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement()){
			stmt.executeUpdate(query);			
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void update(BotUser user) {
		String query = "UPDATE botuser SET name=?, email=?, password=? WHERE id=?";
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public BotUser find(Integer id) {
		BotUser user = new BotUser();	
		String query = "SELECT * FROM botuser WHERE id=" + id;
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query)) {
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return user;
	}
	
	public BotUser findByEmail(String email) {
		BotUser user = new BotUser();	
		String query = "SELECT * FROM botuser WHERE email='" + email + "'";
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return user;
	}
	
	public List<BotUser> findByName(String name) {
		List<BotUser> userList = new LinkedList<>();
		String query = "SELECT * FROM botuser WHERE name='" + name + "'";
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {			
			while(rs.next()) {
				BotUser user = new BotUser();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return userList;
	}

	@Override
	public List<BotUser> findAll() {
		List<BotUser> userList = new LinkedList<>();	
		String query = "SELECT * FROM botuser";
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {
			while(rs.next()) {
				BotUser user = new BotUser();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				userList.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		} 		
		return userList;
	}
}
