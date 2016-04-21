/**
 * 
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Meirbek
 *
 */
public class ConnectionUtil {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/library";
	private String user = "root";
	private String pass = "root";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, pass);
		conn.setAutoCommit(false);
		return conn;
	}
}
