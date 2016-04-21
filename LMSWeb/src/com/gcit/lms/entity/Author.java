/**
 * 
 */
package com.gcit.lms.entity;

import java.util.List;

/**
 * @author Meirbek
 *
 */
public class Author {
	private int authorId;
	private String authorName;
	private List<Book> books;
	/**
	 * @param authorID
	 * @param authorName
	 * @param books
	 */
	public Author(int authorId, String authorName, List<Book> books) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.books = books;
	}
	public Author() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the authorID
	 */
	public int getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		return true;
	}
	
}
