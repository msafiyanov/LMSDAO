/**
 * 
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

/**
 * @author Meirbek
 *
 */
public class BorrowerService {
	
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
	
	public List<BookCopy> getExistingBooks() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookCopyDAO bcDAO = new BookCopyDAO(conn);
			return bcDAO.readExistingBookCopies();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public void addBookLoan(BookLoan bL) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			if (bL != null)
				blDAO.deleteBookLoan(bL);
			
			blDAO.addBookLoan(bL);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void returnBookLoan(BookLoan bL) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			blDAO.deleteBookLoan(bL);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public List<BookLoan> getBookLoansByBorrower(Borrower bor) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			return blDAO.readBookLoansByBorrower(bor);
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}	
		return null;
	}
	
	public void updateBookCopies(BookCopy bC) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookCopyDAO blDAO = new BookCopyDAO(conn);
			blDAO.updateBookCopy(bC);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}	
	}

	public BookLoan getBookLoanByAll(Integer bookId,
			Integer cardNo, Integer branchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			return blDAO.readBookLoanByAll(bookId, cardNo, branchId);
		} catch (Exception e) {
		}
		finally {
			conn.close();
		}	
		return null;
	}
	
	public BookCopy getBookCopyByAll(Integer bookId, Integer branchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookCopyDAO blDAO = new BookCopyDAO(conn);
			return blDAO.getBookCopy(bookId, branchId);
		} catch (Exception e) {
		}
		finally {
			conn.close();
		}	
		return null;
	}
		
	public static java.sql.Date getCurrentDate() {		
	    return java.sql.Date.valueOf(java.time.LocalDate.now());
	}
	
	public static java.sql.Date getWeekFromDate() {
		return java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7));	
	}

	public int getNoOfCopies(Book book, LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookCopyDAO blDAO = new BookCopyDAO(conn);
			return blDAO.getNoOfCopies(book, libraryBranch);
		} catch (Exception e) {
		}
		finally {
			conn.close();
		}	
		return 0;
	}
}
