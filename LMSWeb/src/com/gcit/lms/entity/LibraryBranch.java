/**
 * 
 */
package com.gcit.lms.entity;

import java.util.List;

/**
 * @author Meirbek
 *
 */
public class LibraryBranch {
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryBranch other = (LibraryBranch) obj;
		if (branchId != other.branchId)
			return false;
		return true;
	}

	private int branchId;
	private String branchName;
	private String branchAddress;
	/*private List<BookLoan> bookLoans;
	private List<BookCopy> bookCopies;*/

	
	/**
	 * 
	 */
	public LibraryBranch() {
	}

	/**
	 * @param branchId
	 * @param branchName
	 * @param branchAddress
	 */
	public LibraryBranch(int branchId, String branchName, String branchAddress) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	/**
	 * @return the bookCopies
	 *//*
	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	*//**
	 * @param bookCopies the bookCopies to set
	 *//*
	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	*//**
	 * @return the bookLoans
	 *//*
	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}

	*//**
	 * @param bookLoans the bookLoans to set
	 *//*
	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}
	*/
}
