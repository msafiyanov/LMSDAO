/**
 * 
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.LibraryBranch;

/**
 * @author Meirbek
 *
 */
public class LibrarianService {
	
	public List<LibraryBranch> getAllLibraryBranches() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO bDAO = new LibraryBranchDAO(conn);
			return bDAO.readAllLibraryBranches();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void updateLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
			lbDAO.updateLibraryBranch(libBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void addBookCopies(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			BookCopyDAO lbDAO = new BookCopyDAO(conn);
			lbDAO.addBookCopy(bookCopy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}	
	}
	
	public List<Book> getAllBooks() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

}
