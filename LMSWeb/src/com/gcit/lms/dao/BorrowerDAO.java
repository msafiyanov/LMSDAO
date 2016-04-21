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
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

/**
 * @author Meirbek
 *
 */
public class BorrowerDAO extends BaseDAO{

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBorrower(Borrower bor) throws ClassNotFoundException, SQLException {
		save("insert into tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {bor.getBorrowerName(), bor.getBorrowerAddress(), bor.getBorrowerPhone()});
	}
	
	public Integer addBorrowerWithID(Borrower bor) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {bor.getBorrowerName(), bor.getBorrowerAddress(), bor.getBorrowerPhone()});
	}
	
	public void updateBorrower(Borrower bor) throws ClassNotFoundException, SQLException {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", new Object[] {bor.getBorrowerName(), bor.getBorrowerAddress(), bor.getBorrowerPhone(), bor.getCardNo()});
	}
	
	public void deleteBorrower(Borrower bor) throws ClassNotFoundException, SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] {bor.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException {
		return (List<Borrower>) readAll("select * from tbl_borrower", null);
	}

	public List<Borrower> readBorrowerByName(String name) throws ClassNotFoundException, SQLException {
		return (List<Borrower>) readAll("select * from tbl_borrower where name like ?", new Object[] {name});
	}

	public List<Borrower> readBorrowersByAddress(String address) throws ClassNotFoundException, SQLException {
		return (List<Borrower>) readAll("select * from tbl_borrower where address like ?", new Object[] {address});
	}
	
	public List<Borrower> readBorrowersByPhone(String phone) throws ClassNotFoundException, SQLException {
		return (List<Borrower>) readAll("select * from tbl_borrower where phone like ?", new Object[] {phone});
	}

	public Borrower readBorrowerByID(Integer cardNo) throws ClassNotFoundException, SQLException {
		List<Borrower> borrowers = (List<Borrower>) readAll("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		if (borrowers != null && borrowers.size() > 0)
			return borrowers.get(0);
		else return null;
	}
	/*
	public Borrower readBorrowerByBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException {
		return ((List<Borrower>) readAll("select * from tbl_library_branches where branchId IN (select branchId from tbl_book_loans where branchId = ?)", new Object[] {bookLoan.getLibraryBranch().getBranchName()})).get(0);
	}
	*/
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		//List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		
		while (rs.next()) {
			Borrower bor = new Borrower();
			bor.setBorrowerName(rs.getString("name"));
			bor.setBorrowerAddress(rs.getString("address"));
			bor.setBorrowerPhone(rs.getString("phone"));
			bor.setCardNo(rs.getInt("cardNo"));
			
			/*try {
				bookLoans = (List<BookLoan>) readFirstLevel("select * from tbl_book_loans where cardNo = ?", new Object[] {bor.getCardNo()});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bor.setBookLoans(bookLoans);*/
			borrowers.add(bor);
		}
		if (borrowers.size() > 0)
			return borrowers;
		else return null;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		
		while (rs.next()) {
			Borrower bor = new Borrower();
			bor.setBorrowerName(rs.getString("name"));
			bor.setBorrowerAddress(rs.getString("address"));
			bor.setBorrowerPhone(rs.getString("phone"));
			bor.setCardNo(rs.getInt("cardNo"));
			borrowers.add(bor);
		}
		if (borrowers.size() > 0)
			return borrowers;
		else return null;
	}

}
