/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.LibraryBranch;

/**
 * @author Meirbek
 *
 */
public class LibraryBranchDAO extends BaseDAO {

	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	public void addLibraryBranch(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		save("insert into tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
	}
	
	public Integer addLibraryBranchWithID(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
	}
	
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress(), libraryBranch.getBranchId()});
	}
	
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		save("delete from tbl_library_branch where branchName = ? and branchAddress = ?", new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
	}
	
	public List<LibraryBranch> readAllLibraryBranches() throws ClassNotFoundException, SQLException {
		return (List<LibraryBranch>) readAll("select * from tbl_library_branch", null);
	}

	public List<LibraryBranch> readLibraryBranchesByName(String name) throws ClassNotFoundException, SQLException {
		return (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchName like ?", new Object[] {name});
	}

	public List<LibraryBranch> readLibraryBranchesByAddress(String address) throws ClassNotFoundException, SQLException {
		return (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchAddress like ?", new Object[] {address});
	}
	
	public LibraryBranch readLibraryBranchByID(Integer branchId) throws ClassNotFoundException, SQLException {
		List<LibraryBranch> libBranches = (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchId = ?", new Object[] {branchId}); 
		if (libBranches != null && libBranches.size() > 0)
			return libBranches.get(0);
		else return null;
	}

	/*public LibraryBranch readLibraryBranchByBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		return ((List<LibraryBranch>) readAll("select * from tbl_library_branch where branchId IN "
										+	" (select branchId from tbl_book_copies where branchId = ?)", new Object[] {bookCopy.getLibraryBranch().getBranchName()})).get(0);
	}*/
	
	/*public LibraryBranch readLibraryBranchByBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException {
		return ((List<LibraryBranch>) readAll("select * from tbl_library_branch where branchId IN "
										+	" (select branchId from tbl_book_loans where branchId = ?)", new Object[] {bookLoan.getLibraryBranch().getBranchName()})).get(0);
	}*/
	
	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> libBranches = new ArrayList<LibraryBranch>();
		//List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		//List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		
		while (rs.next()) {
			LibraryBranch libBranch = new LibraryBranch();
			libBranch.setBranchName(rs.getString("branchName"));
			libBranch.setBranchAddress(rs.getString("branchAddress"));
			libBranch.setBranchId(rs.getInt("branchId"));
			/*try {
				bookLoans = (List<BookLoan>) readFirstLevel("select * from tbl_book_loans where branchId = ?", new Object[] {libBranch.getBranchId()});
				bookCopies = (List<BookCopy>) readFirstLevel("select * from tbl_book_copies where branchId = ?", new Object[] {libBranch.getBranchId()});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			libBranch.setBookLoans(bookLoans);
			libBranch.setBookCopies(bookCopies);*/
			libBranches.add(libBranch);
		}
		if (libBranches.size() > 0)
			return libBranches;
		else return null;
	}

	@Override
	public List<LibraryBranch> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<LibraryBranch> libBranches = new ArrayList<LibraryBranch>();
		while (rs.next()) {
			LibraryBranch libBranch = new LibraryBranch();
			libBranch.setBranchName(rs.getString("branchName"));
			libBranch.setBranchAddress(rs.getString("branchAddress"));
			libBranch.setBranchId(rs.getInt("branchId"));
		}
		if (libBranches.size() > 0)
			return libBranches;
		else return null;
	}

}
