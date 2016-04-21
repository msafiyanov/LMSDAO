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

/**
 * @author Meirbek
 *
 */
public class AuthorDAO extends BaseDAO {
	
	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	
	public Integer addAuthorWithID(Author author) throws ClassNotFoundException, SQLException
	{
		return saveWithID("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		save("update tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		save("delete from tbl_author where authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException
	{
		return (List<Author>) readAll("select * from tbl_author", new Object[] {});
	}
	
	public List<Author> readAuthorsByName(String name) throws ClassNotFoundException, SQLException{
		return (List<Author>) readAll("select * from tbl_author where name like ?", new Object[] {name});
	}
	
	public Author readAuthorByID(Integer authorId) throws ClassNotFoundException, SQLException {
		List<Author> authors = (List<Author>) readAll("select * from tbl_author where authorId = ?", new Object[] {authorId});
		if (authors != null && authors.size() > 0)
			return authors.get(0);
		else return null;
	}

	public void linkBooks(Author author) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_authors where authorId = ?", new Object[] {author.getAuthorId()});
		if (author != null && author.getBooks() != null && author.getBooks().size() > 0)
			for (Book b: author.getBooks())
				save("insert into tbl_book_authors (authorId, bookId) values (?,?)", new Object[] {author.getAuthorId(), b.getBookId()});
	}
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		BookDAO bookDAO = new BookDAO(getConnection());
		
		while(rs.next()) {			
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			
			try {
				author.setBooks((List<Book>) bookDAO.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)", new Object[] {author.getAuthorId()}));
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			authors.add(author);			
		}		
		if (authors.size() > 0)
			return authors;
		else return null;		
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		
		while(rs.next()) {			
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));		
			
			authors.add(author);
		}		
		if (authors.size() > 0)
			return authors;
		else return null;		
	}

}
