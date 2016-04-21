/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

/**
 * @author Meirbek
 *
 */
public class BookLoanDAO extends BaseDAO{

	public BookLoanDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookLoan(BookLoan bL) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)",
				new Object[] {bL.getBook().getBookId(), bL.getLibraryBranch().getBranchId(), 
				bL.getBorrower().getCardNo(), bL.getDateOut(), bL.getDueDate(), bL.getDateIn()});
	}
	
	/*public Integer addBookLoanWithID(BookLoan bL) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)",
				new Object[] {bL.getBook().getBookId(), bL.getLibraryBranch().getBranchId(), 
				bL.getBorrower().getCardNo(), bL.getDateOut(), bL.getDueDate(), bL.getDateIn()});
	}*/
	
	public void updateBookLoan(BookLoan bL) throws ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bL.getDateOut(), bL.getDueDate(), bL.getDateIn(), bL.getBook().getBookId(), 
				bL.getLibraryBranch().getBranchId(), bL.getBorrower().getCardNo()});
	}
	
	public void updateBookLoanDueDate(BookLoan bL) throws ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bL.getDueDate(), bL.getBook().getBookId(), bL.getLibraryBranch().getBranchId(), bL.getBorrower().getCardNo()});
	}
	
	public void deleteBookLoan(BookLoan bL) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?", new Object[] {bL.getBook().getBookId(), bL.getLibraryBranch().getBranchId(),bL.getBorrower().getCardNo()});
	}
	
	public List<BookLoan> readAllBookLoans() throws ClassNotFoundException, SQLException
	{		
		return (List<BookLoan>) readAll("select * from tbl_book_loans as bl join tbl_book as b "
				+ " on bl.bookId = b.bookId join tbl_borrower as bor on bl.cardNo = bor.cardNo "
				+ " join tbl_library_branch as lb on bl.branchId = lb.branchId", null); 
	}
	
	public List<BookLoan> readBookLoansByBook(Book book) throws ClassNotFoundException, SQLException
	{		
		return (List<BookLoan>) readAll("select * from tbl_book_loans as bl join tbl_book as b "
				+ " on bl.bookId = b.bookId join tbl_borrower as bor on bl.cardNo = bor.cardNo "
				+ " join tbl_library_branch as lb on bl.branchId = lb.branchId where bl.bookId = ?", new Object[] {book.getBookId()}); 
	}
	
	public List<BookLoan> readBookLoansByBorrower(Borrower bor) throws ClassNotFoundException, SQLException
	{		
		return (List<BookLoan>) readAll("select * from tbl_book_loans as bl join tbl_book as b "
				+ " on bl.bookId = b.bookId join tbl_borrower as bor on bl.cardNo = bor.cardNo "
				+ " join tbl_library_branch as lb on bl.branchId = lb.branchId where bl.cardNo = ?", new Object[] {bor.getCardNo()}); 
	}
	
	public List<BookLoan> readBookLoansByBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException
	{		
		return (List<BookLoan>) readAll("select * from tbl_book_loans as bl join tbl_book as b "
				+ " on bl.bookId = b.bookId join tbl_borrower as bor on bl.cardNo = bor.cardNo "
				+ " join tbl_library_branch as lb on bl.branchId = lb.branchId where bl.branchId = ?", new Object[] {libBranch.getBranchId()}); 
	}
	
	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
/*		BookDAO bookDAO = new BookDAO(getConnection());
		BorrowerDAO borDAO = new BorrowerDAO(getConnection());
		LibraryBranchDAO libDAO = new LibraryBranchDAO(getConnection());
	*/	
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setDateOut(rs.getDate("dateOut"));
			bookLoan.setDueDate(rs.getDate("dueDate"));
			bookLoan.setDateIn(rs.getDate("dateIn"));
			bookLoan.setBook(new Book(rs.getString("title"), rs.getInt("bookId"), null, null, null));
			bookLoan.setBorrower(new Borrower(rs.getInt("cardNo"), rs.getString("name"), rs.getString("address"), rs.getString("phone")));
			bookLoan.setLibraryBranch(new LibraryBranch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress")));
			bookLoans.add(bookLoan);
		}
		if (bookLoans.size() > 0)
			return bookLoans;
		else return null;
	}

	@Override
	public List<BookLoan> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setDateOut(rs.getDate("dateOut"));
			bookLoan.setDueDate(rs.getDate("dueDate"));
			bookLoan.setDateIn(rs.getDate("dateIn"));
			bookLoan.setBook(new Book(rs.getString("title"), rs.getInt("bookId"), null, null, null));
			bookLoan.setBorrower(new Borrower(rs.getInt("cardNo"), rs.getString("name"), rs.getString("address"), rs.getString("phone")));
			bookLoan.setLibraryBranch(new LibraryBranch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress")));
			bookLoans.add(bookLoan);
		}
		
		if (bookLoans.size() > 0)
			return bookLoans;
		else return null;
	}

	public BookLoan readBookLoanByAll(Integer bookId, Integer cardNo,
			Integer branchId) throws ClassNotFoundException, SQLException {
		List<BookLoan> bl = (List<BookLoan>) readAll("select * from tbl_book_loans as bl join tbl_book as b "
				+ " on bl.bookId = b.bookId join tbl_borrower as bor on bl.cardNo = bor.cardNo "
				+ " join tbl_library_branch as lb on bl.branchId = lb.branchId where bl.bookId = ? and bl.cardNo = ? and bl.branchId = ?", new Object[] {bookId, cardNo, branchId});
		if (bl != null && bl.size() > 0) 
			return bl.get(0);
		else return null;
	}
	
}
