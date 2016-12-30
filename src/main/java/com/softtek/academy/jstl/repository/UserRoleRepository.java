package com.softtek.academy.jstl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.academy.jstl.connection.DriverManagerDatabase;
import com.softtek.academy.jstl.domain.model.UserRole;

public class UserRoleRepository {

	public List<UserRole> list() {
		Connection connection = DriverManagerDatabase.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		 final List<UserRole> userRoles = new ArrayList<UserRole>();
		try {
    		
            preparedStatement = connection.prepareStatement("SELECT * FROM user_role");
            rs = preparedStatement.executeQuery();
        
            while (rs.next()) {
            	UserRole userRole = new UserRole();
            	userRole.setId(rs.getString("user_role_id"));
            	userRole.setDescription(rs.getString("description"));
            	
	            userRoles.add(userRole);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
			this.close(rs, preparedStatement, connection);
		}
	
		return userRoles; 
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
