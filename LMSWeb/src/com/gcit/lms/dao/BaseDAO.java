/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Meirbek
 *
 */
public abstract class BaseDAO {
	
	private Connection conn;
	
	public BaseDAO(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void save(String query, Object[] vals) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		int count = 0;
		if (vals != null)
			for (Object o: vals) {
				count++;
				pstmt.setObject(count, o);
			}
		
		pstmt.executeUpdate();
		
	}
	
	public Integer saveWithID(String query, Object[] vals) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
		int count = 0;
		if (vals != null)
			for (Object o: vals) {
				count++;
				pstmt.setObject(count, o);
			}
		
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		if (rs.next()) {
			return rs.getInt(1);
		}
		else return null;		
	}
	
	public int getCount(String query) throws SQLException, ClassNotFoundException
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
			return rs.getInt(1);
		else
			return 0;
		
	}
	
	public List<?> readAll(String query, Object[] vals) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		int count = 0;
		if (vals != null)
			for (Object o: vals) {
				count++;
				pstmt.setObject(count, o);
			}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
				
	}
	
	public abstract List<?> extractData(ResultSet rs) throws SQLException;
	
	public List<?> readFirstLevel(String query, Object[] vals) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		int count = 0;
		if (vals != null)
			for (Object o: vals) {
				count++;
				pstmt.setObject(count, o);
			}
		ResultSet rs = pstmt.executeQuery();
		return extractDataFirstLevel(rs);
				
	}
	
	public abstract List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
}
