/**
 * 
 */
package com.gcit.lms.entity;

import java.util.List;

/**
 * @author Meirbek
 *
 */
public class Borrower {
	private int cardNo;
	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhone;
	//private List<BookLoan> bookLoans;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cardNo;
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
		Borrower other = (Borrower) obj;
		if (cardNo != other.cardNo)
			return false;
		return true;
	}

	/**
	 * @param cardNo
	 * @param borrowerName
	 * @param borrowerAddress
	 * @param borrowerPhone
	 */
	public Borrower(int cardNo, String borrowerName, String borrowerAddress,
			String borrowerPhone) {
		this.cardNo = cardNo;
		this.borrowerName = borrowerName;
		this.borrowerAddress = borrowerAddress;
		this.borrowerPhone = borrowerPhone;
		//this.bookLoans = bookLoans;
	}

	public Borrower() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cardNo
	 */
	public int getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the borrowerName
	 */
	public String getBorrowerName() {
		return borrowerName;
	}

	/**
	 * @param borrowerName the borrowerName to set
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	/**
	 * @return the borrowerAddress
	 */
	public String getBorrowerAddress() {
		return borrowerAddress;
	}

	/**
	 * @param borrowerAddress the borrowerAddress to set
	 */
	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}

	/**
	 * @return the borrowerPhone
	 */
	public String getBorrowerPhone() {
		return borrowerPhone;
	}

	/**
	 * @param borrowerPhone the borrowerPhone to set
	 */
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}

	/**
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
