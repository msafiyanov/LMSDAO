/**
 * 
 */
package com.gcit.lms.entity;

import java.util.List;

/**
 * @author Meirbek
 *
 */
public class Book {
	private String title;	
	private int bookId;
	private Publisher publisher;
	private List<Author> authors;
	private List<Genre> genres;
	//private List<BookLoan> bookLoans;
	//private List<BookCopy> bookCopies;
	
	/**
	 * @param title
	 * @param bookId
	 * @param publisher
	 * @param authors
	 * @param genres
	 * @param bookLoans
	 * @param bookCopies
	 */
	public Book(String title, int bookId, Publisher publisher,
			List<Author> authors, List<Genre> genres) {
			//, List<BookLoan> bookLoans,	List<BookCopy> bookCopies) {
		this.title = title;
		this.bookId = bookId;
		this.publisher = publisher;
		this.authors = authors;
		this.genres = genres;
		//this.bookLoans = bookLoans;
		//this.bookCopies = bookCopies;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
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
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		return true;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	/**
	 * @return the bookLoans
	 */
	/*public List<BookLoan> getBookLoans() {
		return bookLoans;
	}
	*//**
	 * @param bookLoans the bookLoans to set
	 *//*
	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}
	*//**
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
	*/
}
