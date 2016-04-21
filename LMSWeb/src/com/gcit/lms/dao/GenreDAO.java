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
import com.gcit.lms.entity.Genre;

/**
 * @author Meirbek
 *
 */
public class GenreDAO extends BaseDAO{

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
	}
	
	public Integer addGenreWithID(Genre genre) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_genre (genre_name) values(?)", new Object[] {genre.getGenreName()});
	}
	
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("update tbl_genre set genre_name = ? where genre_id = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("delete from tbl_genre where genre_id = ?", new Object[] {genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException {
		return (List<Genre>) readAll("select * from tbl_genre", new Object[] {});
	}
	
	public List<Genre> readGenresByName(String name) throws ClassNotFoundException, SQLException {
		return (List<Genre>) readAll("select * from tbl_genre where genre_name like ?", new Object[] {name});
	}
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		BookDAO bookDAO = new BookDAO(getConnection());
		
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			
			try {
				genre.setBooks((List<Book>) bookDAO.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_genres where genre_id = ?)", new Object[] {genre.getGenreId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			genres.add(genre);
		}
		
		if (genres.size() > 0)
			return genres;
		else return null;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			
			genres.add(genre);
		}
		if (genres.size() > 0)
			return genres;
		else return null;
	}

	public Genre readGenreByID(Integer genreId) throws ClassNotFoundException, SQLException {
		List<Genre> genres = (List<Genre>) readAll("select * from tbl_genre where genre_id = ?", new Object[] {genreId});
		if (genres != null && genres.size() > 0)
			return genres.get(0);
		else return null;
	}	
}
