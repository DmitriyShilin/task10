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

import model.AddToCart;

public class AddToCartJDBCDAO extends BaseDAO<AddToCart> {
	
	private Logger logger = Logger.getLogger(AddToCartJDBCDAO.class.getName());

	@Override
	public void insert(AddToCart cart) {
		String query = "INSERT INTO cart (url, email, password, timestamp) VALUES (?, ?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection(); 
		    PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, cart.getUrl());
			ps.setString(2, cart.getEmail());
			ps.setString(3, cart.getPassword());
			ps.setTimestamp(4, cart.getTimestamp());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(Integer id) {
		String query = "DELETE FROM cart WHERE id=" + id;
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement()){
			stmt.executeUpdate(query);			
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}		
	}

	@Override
	public void update(AddToCart cart) {
		String query = "UPDATE cart SET url=?, email=?, password=?, timestamp=? WHERE id=?";
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, cart.getUrl());
			ps.setString(2, cart.getEmail());
			ps.setString(3, cart.getPassword());
			ps.setTimestamp(4, cart.getTimestamp());
			ps.setInt(5, cart.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}		
	}
	
	public AddToCart findByEmail(String email) {
		AddToCart cart = new AddToCart();	
		String query = "SELECT * FROM cart WHERE email='" + email + "'";
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {			
			if(rs.next()) {
				cart.setId(rs.getInt("id"));
				cart.setUrl(rs.getString("url"));
				cart.setEmail(rs.getString("email"));
				cart.setPassword(rs.getString("password"));
				cart.setTimestamp(rs.getTimestamp("timestamp"));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return cart;
	}

	@Override
	public AddToCart find(Integer id) {
		AddToCart cart = new AddToCart();	
		String query = "SELECT * FROM cart WHERE id=" + id;
		try(Connection conn = ConnectionFactory.getConnection(); 
			Statement stmt = conn.createStatement(); 
		    ResultSet rs = stmt.executeQuery(query)) {
			if(rs.next()) {
				cart.setId(rs.getInt("id"));
				cart.setUrl(rs.getString("url"));
				cart.setEmail(rs.getString("email"));
				cart.setPassword(rs.getString("password"));
				cart.setTimestamp(rs.getTimestamp("timestamp"));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return cart;
	}

	@Override
	public List<AddToCart> findAll() {
		List<AddToCart> cartList = new LinkedList<>();	
		String query = "SELECT * FROM cart";
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {
			while(rs.next()) {
				AddToCart cart = new AddToCart();
				cart.setId(rs.getInt("id"));
				cart.setUrl(rs.getString("url"));
				cart.setEmail(rs.getString("email"));
				cart.setPassword(rs.getString("password"));
				cart.setTimestamp(rs.getTimestamp("timestamp"));
				cartList.add(cart);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, null, e);
		} 		
		return cartList;
	}
}
