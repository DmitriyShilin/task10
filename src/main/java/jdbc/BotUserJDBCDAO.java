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

public class BotUserJDBCDAO implements BotUserDAO{
	
	private Logger logger = Logger.getLogger(BotUserJDBCDAO.class.getName());

	@Override
	public void insert(BotUser user) {
		try(Connection conn = ConnectionFactory.getConnection(); 
		    PreparedStatement ps = conn.prepareStatement("INSERT INTO botuser(name, email, password) VALUES (?, ?, ?)")){
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
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement()){
			stmt.executeUpdate("DELETE FROM botuser WHERE ID=" + id);			
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void update(BotUser user) {
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("UPDATE botuser SET NAME=?, EMAIL=?, PASSWORD=? WHERE ID=?")){
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
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery("SELECT * FROM botuser WHERE ID=" + id);) {
			if(rs.next()) {
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return user;
	}

	@Override
	public BotUser findByEmail(String email) {
		BotUser user = new BotUser();		
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM botuser WHERE EMAIL='" + email + "'")) {			
			if(rs.next()) {
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return user;
	}

	@Override
	public List<BotUser> findByName(String name) {
		List<BotUser> userList = new LinkedList<>();		
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM botuser WHERE NAME='" + name + "'")) {			
			while(rs.next()) {
				BotUser user = new BotUser();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
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
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM botuser") ) {
			while(rs.next()) {
				BotUser user = new BotUser();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				userList.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		} 		
		return userList;
	}
}
