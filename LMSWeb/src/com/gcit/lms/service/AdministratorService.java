/**
 * 
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

/**
 * @author Meirbek
 *
 */
public class AdministratorService {

	public List<BookCopy> getAllNonExistingBookCopies() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookCopyDAO bcDAO = new BookCopyDAO(conn);
			return bcDAO.readNonExistingBookCopies();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void createAuthor(Author author) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO aDAO = new AuthorDAO(conn);
			aDAO.addAuthor(author);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public Integer createAuthorWithID(Author author) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer authorId = null;
		try{
			AuthorDAO aDAO = new AuthorDAO(conn);
			authorId = aDAO.addAuthorWithID(author);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return authorId;
	}
	
	public List<Author> getAllAuthors(Integer pageNo, Integer pageSize) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.readAllAuthors(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			AuthorDAO aDAO = new AuthorDAO(conn);
			aDAO.updateAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
	
		try {
			AuthorDAO aDAO = new AuthorDAO(conn);
			aDAO.deleteAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public Integer getAuthorCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public void createBook(Book book) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BookDAO bDAO = new BookDAO(conn);
			bDAO.addBook(book);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}
	
	public Integer createBookWithID(Book book) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer bookId = null;
		try {
			BookDAO bDAO = new BookDAO(conn);
			bookId = bDAO.addBookWithID(book);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
		return bookId;
	}
	
	public List<Book> getAllBooks(Integer pageNo, Integer pageSize) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			BookDAO bDAO = new BookDAO(conn);
			bDAO.updateBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
	
		try {
			BookDAO bDAO = new BookDAO(conn);
			bDAO.deleteBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public Integer getBookCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			PublisherDAO pDAO = new PublisherDAO(conn);
			pDAO.addPublisher(publisher);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}
	
	public Integer createPublisherWithID(Publisher publisher) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer publisherId = null;
		try {
			PublisherDAO pDAO = new PublisherDAO(conn);
			publisherId = pDAO.addPublisherWithID(publisher);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
		return publisherId;
	}
	
	public List<Publisher> getAllPublishers(Integer pageNo, Integer pageSize) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO pDAO = new PublisherDAO(conn);
			return pDAO.readAllPublishers(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			PublisherDAO pDAO = new PublisherDAO(conn);
			pDAO.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
	
		try {
			PublisherDAO pDAO = new PublisherDAO(conn);
			pDAO.deletePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public Integer getPublisherCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			BorrowerDAO bDAO = new BorrowerDAO(conn);
			bDAO.addBorrower(borrower);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}
	
	public Integer createBorrowerWithID(Borrower borrower) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer cardNo = null;
		try {
			BorrowerDAO pDAO = new BorrowerDAO(conn);
			cardNo = pDAO.addBorrowerWithID(borrower);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
		return cardNo;
	}
	
	public List<Borrower> getAllBorrowers(Integer pageNo, Integer pageSize) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bDAO = new BorrowerDAO(conn);
			return bDAO.readAllBorrowers(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public Borrower getBorrowerByID(Integer cardNo) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bDAO = new BorrowerDAO(conn);
			return bDAO.readBorrowerByID(cardNo);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			BorrowerDAO bDAO = new BorrowerDAO(conn);
			bDAO.updateBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
	
		try {
			BorrowerDAO bDAO = new BorrowerDAO(conn);
			bDAO.deleteBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public Integer getBorrowerCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void createLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try {
			LibraryBranchDAO bDAO = new LibraryBranchDAO(conn);
			bDAO.addLibraryBranch(libBranch);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}
	
	public Integer createLibraryBranchWithID(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer branchId = null;
		try {
			LibraryBranchDAO pDAO = new LibraryBranchDAO(conn);
			branchId = pDAO.addLibraryBranchWithID(libBranch);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
		return branchId;
	}
	
	public List<LibraryBranch> getAllLibraryBranches(Integer pageNo, Integer pageSize) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO bDAO = new LibraryBranchDAO(conn);
			return bDAO.readAllLibraryBranches(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public LibraryBranch getLibraryBranchByID(Integer branchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
			return lbDAO.readLibraryBranchByID(branchId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void updateLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
			lbDAO.updateLibraryBranch(libBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void deleteLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
	
		try {
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
			lbDAO.deleteLibraryBranch(libBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public Integer getLibraryBranchCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			return lbdao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<BookLoan> getAllBookLoans() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			return blDAO.readAllBookLoans();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void updateBookLoanDueDate(BookLoan bL) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		
		try {
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			blDAO.updateBookLoanDueDate(bL);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
		
	}

	public Author getAuthorByID(Integer authorId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.readAuthorByID(authorId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Book getBookByID(Integer bookId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookDAO bDAO = new BookDAO(conn);
			return bDAO.readBookByID(bookId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void linkAuthorWithBooks(Author author) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			AuthorDAO aDAO = new AuthorDAO(conn);
			aDAO.linkBooks(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public void linkBookWithAuthors(Book book) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			BookDAO bDAO = new BookDAO(conn);
			bDAO.linkAuthors(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}

	public void createGenre(Genre genre) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO aDAO = new GenreDAO(conn);
			aDAO.addGenre(genre);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public Integer createGenreWithID(Genre genre) throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		Integer genreId = null;
		try{
			GenreDAO gDAO = new GenreDAO(conn);
			genreId = gDAO.addGenreWithID(genre);
			conn.commit();
		}catch (Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return genreId;
	}

	public List<Genre> getAllGenres() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO gDAO = new GenreDAO(conn);
			return gDAO.readAllGenres();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			GenreDAO gDAO = new GenreDAO(conn);
			gDAO.updateGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}

	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
	
		try {
			GenreDAO gDAO = new GenreDAO(conn);
			gDAO.deleteGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}
	
	public Integer getGenreCount() throws ClassNotFoundException, SQLException{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.getCount();
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}

	public Genre getGenreByID(Integer genreId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			GenreDAO gDAO = new GenreDAO(conn);
			return gDAO.readGenreByID(genreId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public void linkBookWithGenres(Book book) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			BookDAO bDAO = new BookDAO(conn);
			bDAO.linkGenres(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}

	public void linkPublisherWithBooks(Publisher publisher) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
				
		try {
			PublisherDAO pDAO = new PublisherDAO(conn);
			pDAO.linkBooks(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}		
	}

	public Publisher getPublisherByID(Integer publisherId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			PublisherDAO pDAO = new PublisherDAO(conn);
			return pDAO.readPublisherByID(publisherId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public BookLoan getBookLoanByAll(Integer bookId, Integer cardNo, Integer branchId) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			return blDAO.readBookLoanByAll(bookId, cardNo, branchId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public List<Author> getAuthorsBySearch(String searchString, String searchType, Integer pageNo, Integer pageSize) throws ClassNotFoundException, SQLException {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorsBySearch(searchString, searchType, pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			//conn.rollback();
		}finally{
			conn.close();
		}
		return null;
	}

}
