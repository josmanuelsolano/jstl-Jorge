package com.softtek.academy.jstl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.academy.jstl.connection.DriverManagerDatabase;
import com.softtek.academy.jstl.domain.model.User;
import com.softtek.academy.jstl.domain.model.UserRole;

public class UserRepository {

	public List<User> list() {
		StringBuilder sql = new StringBuilder();
		Connection connection = DriverManagerDatabase.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		sql.append("SELECT u.*, ur.description description");
		sql.append("  FROM user u");
		sql.append("  JOIN user_role ur ON u.user_role_id=ur.user_role_id");     
        
        final List<User> users = new ArrayList<User>();
        
        try {
        		
                preparedStatement = connection.prepareStatement(sql.toString());
                rs = preparedStatement.executeQuery();
            
	            while (rs.next()) {
	            	User user = new User();
	            	user.setUsername(rs.getString("username"));
	            	user.setName(rs.getString("name"));
	            	user.setPassword(rs.getString("password"));
	            	user.setUserRole(new UserRole(rs.getString("user_role_id"), rs.getString("description")));
	            	user.setActive(rs.getString("active"));
		            users.add(user);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
				this.close(rs, preparedStatement, connection);
			}
		
		return users; 
	}
	
	public User findOne(String username) {
		Connection connection = DriverManagerDatabase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            
            rs = preparedStatement.executeQuery();
            
            rs.next();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setUserRole(new UserRole(rs.getString("user_role_id")));
            user.setActive(rs.getString("active"));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
			this.close(rs, preparedStatement, connection);
		}
		
		return user;
	}
	
	public void update(User user) {
		StringBuilder sql = new StringBuilder();
		Connection connection = DriverManagerDatabase.getConnection();
        PreparedStatement preparedStatement = null;

        sql.append("UPDATE user SET password = ?, name = ?, user_role_id = ?, active = ? ");
        sql.append("WHERE username = ?");
        
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUserRole().getId());
            preparedStatement.setString(4, user.getActive());
            preparedStatement.setString(5, user.getUsername());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void close(ResultSet resultSet,
	        PreparedStatement preparedStatement,
	        Connection connection) {

	        if (resultSet != null && preparedStatement != null
	            && connection != null) {

	            try {
	                if (!resultSet.isClosed()) {
	                    resultSet.close();
	                }

	                if (!preparedStatement.isClosed()) {
	                    preparedStatement.close();
	                }

	                if (!connection.isClosed()) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}
