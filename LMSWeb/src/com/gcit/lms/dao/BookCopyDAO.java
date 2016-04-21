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
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

/**
 * @author Meirbek
 *
 */
public class BookCopyDAO extends BaseDAO{

	public BookCopyDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)", new Object[] {bookCopy.getBook().getBookId(), bookCopy.getLibraryBranch().getBranchId(), bookCopy.getNoOfCopies()});
	}

	public Integer addBookCopyWithID(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)", new Object[] {bookCopy.getBook().getBookId(), bookCopy.getLibraryBranch().getBranchId(), bookCopy.getNoOfCopies()});
	}
	
	public void updateBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?", new Object[] {bookCopy.getNoOfCopies(), bookCopy.getBook().getBookId(), bookCopy.getLibraryBranch().getBranchId()});
	}
	
	public void deleteBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_copies where bookId = ? and branchId =?", new Object[] {bookCopy.getBook().getBookId(), bookCopy.getLibraryBranch().getBranchId()});
	}
	
	public List<BookCopy> readAllBookCopies() throws ClassNotFoundException, SQLException {
		return (List<BookCopy>) readAll("select * from tbl_book_copies as bc "
				+ "join tbl_book as b on bc.bookId = b.bookId "
				+ "join tbl_library_branch as lb on bc.branchId = lb.branchId", null);
	}
	
	public List<BookCopy> readExistingBookCopies() throws ClassNotFoundException, SQLException {
		return (List<BookCopy>) readAll("select * from tbl_book_copies as bc "
				+ "join tbl_book as b on bc.bookId = b.bookId "             // ??????????????????????????? JOIN or LEFT JOIN
				+ "join tbl_library_branch as lb on bc.branchId = lb.branchId where bc.noOfCopies > 0", null);
	}
	
	public List<BookCopy> readBookCopiesByBook(Book book) throws ClassNotFoundException, SQLException {
		return (List<BookCopy>) readAll("select * from tbl_book_copies as bc "
				+ "join tbl_book as b on bc.bookId = b.bookId "
				+ "join tbl_library_branch as lb on bc.branchId = lb.branchId where bc.bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<BookCopy> readBookCopiesByBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		return (List<BookCopy>) readAll("select * from tbl_book_copies as bc "
				+ "join tbl_book as b on bc.bookId = b.bookId "
				+ "join tbl_library_branch as lb on bc.branchId = lb.branchId where bc.branchId = ?", new Object[] {libBranch.getBranchId()});
	}	
	
	public int getNoOfCopies(Book book, LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		List<BookCopy> bc = (List<BookCopy>) readAll("select * from tbl_book_copies as bc "
				+ "join tbl_book as b on bc.bookId = b.bookId "
				+ "join tbl_library_branch as lb on bc.branchId = lb.branchId where bc.bookId = ? and bc.branchId = ?", new Object[] {book.getBookId(), libBranch.getBranchId()});
		if (bc != null && bc.size() > 0)
			return bc.get(0).getNoOfCopies();
		return 0;
	}
	
	public BookCopy getBookCopy(Integer bookId, Integer branchId) throws ClassNotFoundException, SQLException {
		List<BookCopy> bc = (List<BookCopy>) readAll("select * from tbl_book_copies as bc "
				+ "join tbl_book as b on bc.bookId = b.bookId "
				+ "join tbl_library_branch as lb on bc.branchId = lb.branchId where bc.bookId = ? and bc.branchId = ?", new Object[] {bookId, branchId});
		if (bc != null && bc.size() > 0)
			return bc.get(0);
		return null;
	}
	
	@Override
	public List<BookCopy> extractData(ResultSet rs) throws SQLException {
		List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		
		while (rs.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopy.setLibraryBranch(new LibraryBranch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress")));
			bookCopy.setBook(new Book(rs.getString("title"), rs.getInt("bookId"), null, null, null));
			bookCopies.add(bookCopy);
		}
		if (bookCopies.size() > 0)
			return bookCopies;
		else return null;
	}

	@Override
	public List<BookCopy> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		
		while (rs.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopy.setLibraryBranch(new LibraryBranch(rs.getInt("branchId"), rs.getString("branchName"), rs.getString("branchAddress")));
			bookCopy.setBook(new Book(rs.getString("title"), rs.getInt("bookId"), null, null, null));
			bookCopies.add(bookCopy);
		}
		if (bookCopies.size() > 0)
			return bookCopies;
		else return null;
	}

	public List<BookCopy> readNonExistingBookCopies() throws ClassNotFoundException, SQLException {
		return (List<BookCopy>) readAll("select b.bookId, lb.branchId, bc.noOfCopies, b.title, lb.branchName, lb.branchAddress from tbl_book_copies as bc "
				+ "left outer join tbl_book as b on bc.bookId = b.bookId "
				+ "left outer join tbl_library_branch as lb on bc.branchId = lb.branchId", null);
	}


}
