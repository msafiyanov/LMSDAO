/**
 * 
 */
package com.gcit.lms.entity;

/**
 * @author Meirbek
 *
 */
public class BookCopy {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result
				+ ((libraryBranch == null) ? 0 : libraryBranch.hashCode());
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
		BookCopy other = (BookCopy) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (libraryBranch == null) {
			if (other.libraryBranch != null)
				return false;
		} else if (!libraryBranch.equals(other.libraryBranch))
			return false;
		return true;
	}

	private Book book;
	private LibraryBranch libraryBranch;
	private int noOfCopies;
	
	/**
	 * 
	 */
	public BookCopy() {
	}

	/**
	 * @param book
	 * @param libraryBranch
	 * @param noOfCopies
	 */
	public BookCopy(Book book, LibraryBranch libraryBranch, int noOfCopies) {
		this.book = book;
		this.libraryBranch = libraryBranch;
		this.noOfCopies = noOfCopies;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the libraryBranch
	 */
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}

	/**
	 * @param libraryBranch the libraryBranch to set
	 */
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}

	/**
	 * @return the noOfCopies
	 */
	public int getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	
}
