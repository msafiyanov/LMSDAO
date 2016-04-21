/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

/**
 * @author Meirbek
 *
 */
public class BookDAO extends BaseDAO{
	
	public BookDAO(Connection conn) {
		super(conn);
	}


	public void addBook(Book book) throws ClassNotFoundException, SQLException
	{
		if (book.getPublisher() != null)
			saveWithID("insert into tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
		else saveWithID("insert into tbl_book (title) values (?)", new Object[] {book.getTitle()});
	}
	
	public Integer addBookWithID(Book book) throws ClassNotFoundException, SQLException
	{
		if (book.getPublisher() != null)
			return saveWithID("insert into tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
		else return saveWithID("insert into tbl_book (title) values (?)", new Object[] {book.getTitle()});
	}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException
	{
		if (book.getPublisher() != null)
			save("update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[] {book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()});
		else save("update tbl_book set title = ?, pubId = null where bookId = ?", new Object[] {book.getTitle(), book.getBookId()});
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException
	{
		save("delete from tbl_book where bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException
	{		
		return (List<Book>) readAll("select * from tbl_book", null); 
	}
	
	public List<Book> readBooksByTitle(String title) throws ClassNotFoundException, SQLException {
		return (List<Book>) readAll("select * from tbl_book where title like ?", new Object[] {title});
	}
	
	public Book readBookByID(Integer bookId) throws ClassNotFoundException, SQLException {
		List<Book> books = (List<Book>) readAll("select * from tbl_book where bookId = ?", new Object[] {bookId});
		if (books != null && books.size() > 0)
			return books.get(0);
		else return null;
	}
	
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		AuthorDAO authorDAO = new AuthorDAO(getConnection());
		GenreDAO genreDAO = new GenreDAO(getConnection());
		//BookLoanDAO bLDAO = new BookLoanDAO(getConnection());
		//BookCopyDAO bCDAO = new BookCopyDAO(getConnection());
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			System.out.println(p.getPublisherId());
			b.setPublisher(p);			
			try {
				b.setAuthors((List<Author>) authorDAO.readFirstLevel("select * from tbl_author where authorId in "
													+ " (select authorID from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));
				b.setGenres((List<Genre>) genreDAO.readFirstLevel("select * from tbl_genre where genre_id in "
						+ " (select genre_id from tbl_book_genres where bookId = ?)", new Object[] {b.getBookId()}));
				//b.setBookLoans((List<BookLoan>) bLDAO.readFirstLevel("select * from tbl_book_loans where bookId = ?", new Object[] {b.getBookId()}));
				//b.setBookCopies((List<BookCopy>) bCDAO.readFirstLevel("select * from tbl_book_copies where bookId = ?", new Object[] {b.getBookId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			books.add(b);
		}		
		if (books.size() > 0)
			return books;
		else return null;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		AuthorDAO authorDAO = new AuthorDAO(getConnection());
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			b.setPublisher(p);	
			books.add(b);
		}
		if (books.size() > 0)
			return books;
		else return null;
	}
	
	public void linkAuthors(Book book) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_authors where bookId = ?", new Object[] {book.getBookId()});
		if (book != null && book.getAuthors() != null && book.getAuthors().size() > 0)
			for (Author a: book.getAuthors())
				save("insert into tbl_book_authors (authorId, bookId) values (?,?)", new Object[] {a.getAuthorId(), book.getBookId()});
	}


	public void linkGenres(Book book) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_genres where bookId = ?", new Object[] {book.getBookId()});
		if (book != null && book.getGenres() != null && book.getGenres().size() > 0)
			for (Genre g: book.getGenres())
				save("insert into tbl_book_genres (genre_id, bookId) values (?,?)", new Object[] {g.getGenreId(), book.getBookId()});
	}
}
