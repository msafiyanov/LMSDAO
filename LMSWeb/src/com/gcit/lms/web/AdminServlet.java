package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdministratorService;
import com.sun.xml.internal.ws.client.RequestContext;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/admin/addAuthor", "/admin/addBook", "/admin/addPublisher", "/admin/addBranch", "/admin/addBorrower", 
		"/admin/editAuthor", "/admin/editBook", "/admin/editPublisher", "/admin/editBranch", "/admin/editBorrower", 
		"/admin/updateAuthor", "/admin/updateBook", "/admin/updatePublisher", "/admin/updateBranch", "/admin/updateBorrower", 
		"/admin/deleteAuthor", "/admin/deleteBook", "/admin/deletePublisher", "/admin/deleteBranch", "/admin/deleteBorrower",
		"/admin/updateDueDate" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String requestUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		System.out.println(requestUrl);
		
		switch (requestUrl) {
		case "/admin/editAuthor":
			editAuthor(request, response);
			break;
		case "/admin/deleteAuthor":
			deleteAuthor(request, response);
			break;
		case "/admin/editBook":
			editBook(request, response);
			break;
		case "/admin/deleteBook":
			deleteBook(request, response);
			break;
		case "/admin/editPublisher":
			editPublisher(request, response);
			break;
		case "/admin/deletePublisher":
			deletePublisher(request, response);
			break;
		case "/admin/editBranch":
			editBranch(request, response);
			break;
		case "/admin/deleteBranch":
			deleteBranch(request, response);
			break;
		case "/admin/editBorrower":
			editBorrower(request, response);
			break;
		case "/admin/deleteBorrower":
			deleteBorrower(request, response);
			break;
		case "/admin/updateDueDate":
			updateDueDate(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		System.out.println(requestUrl);
		
		switch (requestUrl) {
		case "/addAuthor":
			addAuthor(request, response);
			break;
		case "/admin/updateAuthor":
			updateAuthor(request, response);
			break;	
		case "/admin/addBook":
			addBook(request, response);
			break;
		case "/admin/updateBook":
			updateBook(request, response);
			break;
		case "/admin/addPublisher":
			addPublisher(request, response);
			break;
		case "/admin/updatePublisher":
			updatePublisher(request, response);
			break;
		case "/admin/addBranch":
			addBranch(request, response);
			break;
		case "/admin/updateBranch":
			updateBranch(request, response);
			break;
		case "/admin/addBorrower":
			addBorrower(request, response);
			break;
		case "/admin/updateBorrower":
			updateBorrower(request, response);
			break;
		default:
			break;
		}
		
	}
	
	private void editAuthor(HttpServletRequest request,
			HttpServletResponse response) {
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		
		AdministratorService service = new AdministratorService();
		Author a = null;
		try {
			a = service.getAuthorByID(authorId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}					
		request.setAttribute("author", a);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editauthor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateAuthor(HttpServletRequest request,
			HttpServletResponse response) {
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");
		AdministratorService service = new AdministratorService();
		
		Author a = new Author();
		a.setAuthorName(authorName);
		a.setAuthorId(authorId);
		
		String returnPath = "/admin/administrator.html";		
		String deleteAuthorResult = "";
		if (authorName != null && authorName.length() >= 3 && authorName.length() < 45) {
			try {
				service.updateAuthor(a);
				
				String[] bookIDs = request.getParameterValues("bookId");
				List<Book> books = new ArrayList<Book>();
				if (bookIDs != null)
				for (int i = 0; i < bookIDs.length; i++) {
					Integer bookId = Integer.parseInt(bookIDs[i]);
					Book b = service.getBookByID(bookId);
					books.add(b);
				}
				a.setBooks(books);
				service.linkAuthorWithBooks(a);
				
				returnPath = "/admin/viewauthors.jsp";
				deleteAuthorResult = "Author updated successfully";
				//request.setAttribute("author", a);
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/editauthor.jsp";
				deleteAuthorResult = "Author edit failed";
				request.setAttribute("author", a);
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/editauthor.jsp";
			request.setAttribute("author", a);
			deleteAuthorResult = "Author name cannot be less than 3 or more than 45 chars in length";
		}
		request.setAttribute("result", deleteAuthorResult);
		System.out.println(deleteAuthorResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Delete Author.");
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		System.out.println("DELETE AUTHOR. ID: " + authorId );
		
		Author author = null;

		try {
			author = service.getAuthorByID(authorId);
			if (author != null) {
				service.deleteAuthor(author);
				//deleteAuthorResult = "Author deleted successfully";
				List<Author> authors = service.getAllAuthors();
				
				str.append("<tr><th>Author Name</th><th>Book Title</th><th>Edit</th><th>Delete</th></tr>");
				for(Author a: authors){
					str.append("<tr><td>"+a.getAuthorName()+"</td><td>");
					List<Book> books = a.getBooks();
					if (books != null && books.size() > 0) {
						for (Book b: books) {
							str.append("" + b.getTitle() + ", "); } }
					str.append("</td><td><button type='button' onclick=\"javascript:location.href='editAuthor?authorId="+a.getAuthorId()+"'\">EDIT</button></td>"
							+ "<td><button type='button' onclick=\"deleteAuthor("+a.getAuthorId()+")\">DELETE</button></td></tr>");
				}
				request.setAttribute("result", "Author deleted sucessfully");
			} else
				request.setAttribute("result", "Author delete failed");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Author delete failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addAuthor(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/admin/administrator.html";
		
		String authorName = request.getParameter("authorName");
		String addAuthorResult = "";
		if (authorName != null && authorName.length() >= 3 && authorName.length() < 45) {
			Author a = new Author();
			a.setAuthorName(authorName);
			try {
				Integer authorId = service.createAuthorWithID(a);
				a.setAuthorId(authorId);
				String[] bookIDs = request.getParameterValues("bookId");
				List<Book> books = new ArrayList<Book>();
				if (bookIDs != null)
				for (int i = 0; i < bookIDs.length; i++) {
					Integer bookId = Integer.parseInt(bookIDs[i]);
					Book b = service.getBookByID(bookId);
					books.add(b);
				}
				a.setBooks(books);
				service.linkAuthorWithBooks(a);
				returnPath = "/admin/viewauthors.jsp";
				addAuthorResult = "Author added successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/addauthor.jsp";
				addAuthorResult = "Author add failed. Please check your DB connection.";
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/addauthor.jsp";
			addAuthorResult = "Author name cannot be less than 3 or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addAuthorResult);
		rd.forward(request, response);
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			AdministratorService service = new AdministratorService();
			String returnPath = "/admin/administrator.html";
			
			String bookTitle = request.getParameter("bookTitle");
			String addBookResult = "";
			if (bookTitle != null && bookTitle.length() >= 3 && bookTitle.length() < 45) {
				Book b = new Book();
				b.setTitle(bookTitle);
				try {					
					
					String pubId_str = request.getParameter("pubId");
					if (pubId_str.equals(""))
						b.setPublisher(null);
					else {
						int pubId = (int) Integer.parseInt(pubId_str);
						b.setPublisher(new Publisher(pubId, null, null, null, null));
					}
					int bookId = (int) service.createBookWithID(b);
					b.setBookId(bookId);
					
					String[] authorIDs = request.getParameterValues("authorId");
					List<Author> authors = new ArrayList<Author>();
					
					if (authorIDs != null)
					for (int i = 0; i < authorIDs.length; i++) {
						Integer authorId = Integer.parseInt(authorIDs[i]);
						Author a = service.getAuthorByID(authorId);
						authors.add(a);
					}
					b.setAuthors(authors);
					
					String[] genreIDs = request.getParameterValues("genreId");
					List<Genre> genres = new ArrayList<Genre>();
					
					if (genreIDs != null)
					for (int i = 0; i < genreIDs.length; i++) {
						Integer genreId = Integer.parseInt(genreIDs[i]);
						Genre g = service.getGenreByID(genreId);
						genres.add(g);
					}
					b.setGenres(genres);
					
					service.linkBookWithAuthors(b);
					service.linkBookWithGenres(b);
					returnPath = "/admin/viewbooks.jsp";
					addBookResult = "Book added successfully";
				} catch (ClassNotFoundException | SQLException e) {
					returnPath = "/admin/addbook.jsp";
					addBookResult = "Book add failed. Please check your DB connection.";
					e.printStackTrace();
				}
			}
			else {
				returnPath = "/admin/addbook.jsp";
				addBookResult = "Book title cannot be less than 3 or more than 45 chars in length";
			}
			RequestDispatcher rd = request.getRequestDispatcher(returnPath);
			request.setAttribute("result", addBookResult);
			rd.forward(request, response);
	}
	
	private void editBook(HttpServletRequest request,
			HttpServletResponse response) {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		
		AdministratorService service = new AdministratorService();
		Book b = null;
		try {
			b = service.getBookByID(bookId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}					
		request.setAttribute("book", b);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editbook.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateBook(HttpServletRequest request,
			HttpServletResponse response) {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("bookTitle");
		AdministratorService service = new AdministratorService();
		
		Book b = new Book();
		b.setTitle(title);
		b.setBookId(bookId);
		
		String returnPath = "/admin/administrator.html";		
		String updateBookResult = "";
		if (title != null && title.length() >= 3 && title.length() < 45) {
			try {
				String pubId_str = request.getParameter("pubId");
				if (pubId_str.equals(""))
					b.setPublisher(null);
				else {
					int pubId = (int) Integer.parseInt(pubId_str);
					b.setPublisher(new Publisher(pubId, null, null, null, null));
				}
				
				service.updateBook(b);
				
				String[] authorIDs = request.getParameterValues("authorId");
				List<Author> authors = new ArrayList<Author>();
				if (authorIDs != null)
				for (int i = 0; i < authorIDs.length; i++) {
					Integer authorId = Integer.parseInt(authorIDs[i]);
					Author a = service.getAuthorByID(authorId);
					authors.add(a);
				}
				b.setAuthors(authors);
				service.linkBookWithAuthors(b);
				
				
				String[] genreIDs = request.getParameterValues("genreId");
				List<Genre> genres = new ArrayList<Genre>();
				if (genreIDs != null)
				for (int i = 0; i < genreIDs.length; i++) {
					Integer genreId = Integer.parseInt(genreIDs[i]);
					Genre a = service.getGenreByID(genreId);
					genres.add(a);
				}
				b.setGenres(genres);
				service.linkBookWithGenres(b);
				
				returnPath = "/admin/viewbooks.jsp";
				updateBookResult = "Book updated successfully";
				//request.setAttribute("author", a);
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/editbook.jsp";
				updateBookResult = "Book edit failed";
				request.setAttribute("book", b);
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/editbook.jsp";
			request.setAttribute("book", b);
			updateBookResult = "Book title cannot be less than 3 or more than 45 chars in length";
		}
		request.setAttribute("result", updateBookResult);
		System.out.println(updateBookResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) {
		int bookId = (int) Integer.parseInt(request.getParameter("bookId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		System.out.println("DELETE BOOK. ID: " + bookId );
		
		Book book = null;

		try {
			book = service.getBookByID(bookId);
			if (book != null) {
				service.deleteBook(book);
				List<Book> books = service.getAllBooks();
				
				str.append("<tr><th>Book Title</th><th>Publisher</th><th>Author Name</th><th>Genre</th><th>Edit</th><th>Delete</th></tr>");
				for(Book b: books){
					str.append("<tr><td>"+b.getTitle()+"</td><td>");
					List<Publisher> publishers = service.getAllPublishers();
					if (b.getPublisher() != null)
						for (Publisher p: publishers)
							if (p.getPublisherId() == b.getPublisher().getPublisherId()) {
								str.append(p.getPublisherName());
								break;
							}
					str.append("</td><td>");
					List<Author> authors = b.getAuthors();
					if (authors != null && authors.size() > 0) {
						for (Author a: authors) {
							str.append("" + a.getAuthorName() + ", "); } }
					str.append("</td><td>");
					List<Genre> genres = b.getGenres();
					if (genres != null && genres.size() > 0) {
						for (Genre g: genres) {
							str.append("" + g.getGenreName() + ", "); } }
					
					str.append("</td><td><button type='button' onclick=\"javascript:location.href='editBook?bookId="+b.getBookId()+"'\">EDIT</button></td>"
							+ "<td><button type='button' onclick=\"deleteBook("+b.getBookId()+")\">DELETE</button></td></tr>");
				}
				request.setAttribute("result", "Book deleted sucessfully");
			} else
				request.setAttribute("result", "Book delete failed");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Book delete failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/admin/administrator.html";
		
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		
		System.out.println(publisherName);
		System.out.println(publisherAddress);
		System.out.println(publisherPhone);
		
		String addBookResult = "";
		if (publisherName != null && publisherName.length() >= 3 && publisherName.length() < 45 
				&& publisherAddress != null && publisherAddress.length() >= 3 && publisherAddress.length() < 45 
				&& publisherPhone != null && publisherPhone.length() >= 3 && publisherPhone.length() < 45) {
			Publisher p = new Publisher();
			p.setPublisherName(publisherName);
			p.setPublisherAddress(publisherAddress);
			p.setPublisherPhone(publisherPhone);
			try {					
				
				
				int publisherId = (int) service.createPublisherWithID(p);
				p.setPublisherId(publisherId);
				
				String[] bookIDs = request.getParameterValues("bookId");
				List<Book> books = new ArrayList<Book>();
				
				if (bookIDs != null)
				for (int i = 0; i < bookIDs.length; i++) {
					Integer bookId = Integer.parseInt(bookIDs[i]);
					Book b = service.getBookByID(bookId);
					books.add(b);
				}
				p.setBooks(books);
												
				service.linkPublisherWithBooks(p);
				returnPath = "/admin/viewpublishers.jsp";
				addBookResult = "Publisher added successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/addpublisher.jsp";
				addBookResult = "Publisher add failed. Please check your DB connection.";
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/addpublisher.jsp";
			addBookResult = "Publisher name, address, phone cannot be less than 3 or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addBookResult);
		rd.forward(request, response);
	}
	
	private void updatePublisher(HttpServletRequest request,
			HttpServletResponse response) {
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		AdministratorService service = new AdministratorService();
		
		System.out.println(publisherName + ". " + publisherAddress + ". " + publisherPhone);
		
		Publisher p = new Publisher();
		p.setPublisherName(publisherName);
		p.setPublisherAddress(publisherAddress);
		p.setPublisherPhone(publisherPhone);
		p.setPublisherId(publisherId);
		
		String returnPath = "/admin/administrator.html";		
		String updatePublisherResult = "";
		if (publisherName != null && publisherName.length() >= 3 && publisherName.length() < 45 
				&& publisherAddress != null && publisherAddress.length() >= 3 && publisherAddress.length() < 45 
				&& publisherPhone != null && publisherPhone.length() >= 3 && publisherPhone.length() < 45) {
			try {
				service.updatePublisher(p);
				
				String[] bookIDs = request.getParameterValues("bookId");
				List<Book> books = new ArrayList<Book>();
				if (bookIDs != null)
				for (int i = 0; i < bookIDs.length; i++) {
					Integer bookId = Integer.parseInt(bookIDs[i]);
					Book b = service.getBookByID(bookId);
					books.add(b);
				}
				p.setBooks(books);
				service.linkPublisherWithBooks(p);
				
				returnPath = "/admin/viewpublishers.jsp";
				updatePublisherResult = "Publisher updated successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/editpublisher.jsp";
				updatePublisherResult = "Publisher edit failed";
				request.setAttribute("publisher", p);
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/editpublisher.jsp";
			request.setAttribute("publisher", p);
			updatePublisherResult = "Publisher name, address and phone cannot be less than 3 or more than 45 chars in length";
		}
		request.setAttribute("result", updatePublisherResult);
		System.out.println(updatePublisherResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editPublisher(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Edit Publisher. " + request.getParameter("publisherId"));
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		
		AdministratorService service = new AdministratorService();
		Publisher p = null;
		try {
			p = service.getPublisherByID(publisherId);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}					
		request.setAttribute("publisher", p);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editpublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Delete Publisher.");
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		System.out.println("DELETE PUBLISHER. ID: " + publisherId );
		
		Publisher publisher = null;

		try {
			publisher = service.getPublisherByID(publisherId);
			if (publisher != null) {
				service.deletePublisher(publisher);
				List<Publisher> publishers = service.getAllPublishers();
				
				str.append("<tr><th>Publisher Name</th><th>Publisher Address</th><th>Publisher Phone</th><th>Books</th><th>Edit</th><th>Delete</th></tr>");
				
				if (publishers != null)
				for(Publisher p: publishers){
					str.append("<tr><td>");
					if (p.getPublisherName() != null) 
						str.append("" + p.getPublisherName());
					str.append("</td><td>");
					if (p.getPublisherAddress() != null)
						str.append(""+p.getPublisherAddress());
					str.append("</td><td>");
					if (p.getPublisherPhone() != null) 
						str.append(""+p.getPublisherPhone());
					str.append("</td><td>");
					List<Book> books = p.getBooks();
					if (books != null && books.size() > 0) {
						for (Book b: books) {
							str.append("" + b.getTitle());
							if (b != books.get(books.size() - 1)) str.append(", "); } }
					System.out.println("PublisherID: " + p.getPublisherId());
				
					str.append("</td><td><button type='button' onclick=\"javascript:location.href='editPublisher?publisherId="+p.getPublisherId()+"'\">EDIT</button></td>"
							+ "<td><button type='button' onclick=\"deletePublisher("+p.getPublisherId()+")\">DELETE</button></td></tr>");
				}
				request.setAttribute("result", "Publisher deleted sucessfully");
			} else
				request.setAttribute("result", "Publisher delete failed");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Publisher delete failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDueDate(HttpServletRequest request,
			HttpServletResponse response) {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		
		StringBuilder str = new StringBuilder();
		
		AdministratorService service = new AdministratorService();
		
		String returnPath = "/admin/administrator.html";		
		String updateBookResult = "";
			try {
				BookLoan bl = service.getBookLoanByAll(bookId, cardNo, branchId);
				LocalDate dueDate = bl.getDueDate().toLocalDate();
				int noOfDays = 1;
				String noOfDays_str = request.getParameter("noOfDays");
				noOfDays = (int) Integer.parseInt(noOfDays_str);
				System.out.println("Extend days: " + noOfDays);
				java.sql.Date sqlDate = java.sql.Date.valueOf(dueDate.plusDays(noOfDays));
				bl.setDueDate(sqlDate);
				System.out.println();
				System.out.println(dueDate);
				System.out.println(sqlDate);				
				service.updateBookLoanDueDate(bl);
				updateBookResult = "Book Loan extended successfully";
				//request.setAttribute("author", a);
				
				str.append("<tr><th>Book Title</th><th>Borrower Name</th><th>Library Branch</th><th>Date Out</th><th>Due Date</th><th>Extend Up to Week</th></tr>");
				
				
				str.append("<tr><td>" + bl.getBook().getTitle() + "</td><td>" + bl.getBorrower().getBorrowerName() + "</td><td>" + bl.getLibraryBranch().getBranchName() +"</td>");
				str.append("<td>" + bl.getDateOut() + "</td><td>" + bl.getDueDate() + "</td>");
				str.append("<td><select name='noOfDays' id='noOfDays'>");
				for(int i = 0; i < 8; i++)
					str.append("<option value=\""+i+"\">Add "+i+" days</option>");
				str.append("</select><button type='button' onclick=\"updateDueDate(" + bl.getBook().getBookId() + "," + bl.getBorrower().getCardNo() + "," + bl.getLibraryBranch().getBranchId() + ")\">EXTEND</button></td></tr>");
				
				
			} catch (ClassNotFoundException | SQLException e) {
				updateBookResult = "Book Loan extend failed";
				//request.setAttribute("book", b);
				e.printStackTrace();
			}
		
			try {
				response.getWriter().append(str.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void addBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/admin/administrator.html";
		
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		
		System.out.println(branchName);
		System.out.println(branchAddress);
		
		String addBookResult = "";
		if (branchName != null && branchName.length() >= 3 && branchName.length() < 45 
				&& branchAddress != null && branchAddress.length() >= 3 && branchAddress.length() < 45) {
			LibraryBranch b = new LibraryBranch();
			b.setBranchName(branchName);
			b.setBranchAddress(branchAddress);
			try {					
				
				int branchId = (int) service.createLibraryBranchWithID(b);
				b.setBranchId(branchId);
				
			
				returnPath = "/admin/viewbranches.jsp";
				addBookResult = "Library Branch added successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/addbranch.jsp";
				addBookResult = "Library Branch add failed. Please check your DB connection.";
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/addbranch.jsp";
			addBookResult = "Library Branch name and address cannot be less than 3 or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addBookResult);
		rd.forward(request, response);
	}
	
	private void updateBranch(HttpServletRequest request,
			HttpServletResponse response) {
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		AdministratorService service = new AdministratorService();
		
		System.out.println(branchName + ". " + branchAddress);
		
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchName(branchName);
		lb.setBranchAddress(branchAddress);
		lb.setBranchId(branchId);
		
		String returnPath = "/admin/administrator.html";		
		String updateBranchResult = "";
		if (branchName != null && branchName.length() >= 3 && branchName.length() < 45 
				&& branchAddress != null && branchAddress.length() >= 3 && branchAddress.length() < 45) {
			try {
				service.updateLibraryBranch(lb);
								
				returnPath = "/admin/viewbranches.jsp";
				updateBranchResult = "Library Branch updated successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/editbranch.jsp";
				updateBranchResult = "Library Branch edit failed";
				request.setAttribute("branch", lb);
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/editbranch.jsp";
			request.setAttribute("branch", lb);
			updateBranchResult = "Branch name and address cannot be less than 3 or more than 45 chars in length";
		}
		request.setAttribute("result", updateBranchResult);
		System.out.println(updateBranchResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editBranch(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Edit Branch. " + request.getParameter("branchId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		
		AdministratorService service = new AdministratorService();
		LibraryBranch lb = null;
		try {
			lb = service.getLibraryBranchByID(branchId);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}					
		request.setAttribute("branch", lb);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editbranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteBranch(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Delete Library Branch.");
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		System.out.println("DELETE LIBRARY BRANCH. ID: " + branchId );
		
		LibraryBranch libBranch = null;

		try {
			libBranch = service.getLibraryBranchByID(branchId);
			if (libBranch != null) {
				service.deleteLibraryBranch(libBranch);
				List<LibraryBranch> libBranches = service.getAllLibraryBranches();
				
				str.append("<tr><th>Branch Name</th><th>Branch Address</th><th>Edit</th><th>Delete</th></tr>");
				
				if (libBranches != null)
				for(LibraryBranch lb: libBranches){
					str.append("<tr><td>");
					if (lb.getBranchName() != null) 
						str.append("" + lb.getBranchName());
					str.append("</td><td>");
					if (lb.getBranchAddress() != null)
						str.append(""+lb.getBranchAddress());
				
					str.append("</td><td><button type='button' onclick=\"javascript:location.href='editBranch?branchId="+lb.getBranchId()+"'\">EDIT</button></td>"
							+ "<td><button type='button' onclick=\"deleteBranch("+lb.getBranchId()+")\">DELETE</button></td></tr>");
				}
				request.setAttribute("result", "Library Branch deleted sucessfully");
			} else
				request.setAttribute("result", "Library Branch delete failed");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Library Branch delete failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdministratorService service = new AdministratorService();
		String returnPath = "/admin/administrator.html";
		
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		
		System.out.println(borrowerName);
		System.out.println(borrowerAddress);
		System.out.println(borrowerPhone);
		
		String addBorrowerResult = "";
		if (borrowerName != null && borrowerName.length() >= 3 && borrowerName.length() < 45 
				&& borrowerAddress != null && borrowerAddress.length() >= 3 && borrowerAddress.length() < 45
				&& borrowerPhone != null && borrowerPhone.length() >= 3 && borrowerPhone.length() < 45) {
			Borrower b = new Borrower();
			b.setBorrowerName(borrowerName);
			b.setBorrowerAddress(borrowerAddress);
			b.setBorrowerPhone(borrowerPhone);
			try {					
				
				int cardNo = (int) service.createBorrowerWithID(b);
				b.setCardNo(cardNo);
			
				returnPath = "/admin/viewborrowers.jsp";
				addBorrowerResult = "Borrower added successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/addborrower.jsp";
				addBorrowerResult = "Borrower add failed. Please check your DB connection.";
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/addborrower.jsp";
			addBorrowerResult = "Borrowers name, phone and address cannot be less than 3 or more than 45 chars in length";
		}
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		request.setAttribute("result", addBorrowerResult);
		rd.forward(request, response);
	}
	
	private void updateBorrower(HttpServletRequest request,
			HttpServletResponse response) {
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		AdministratorService service = new AdministratorService();
		
		System.out.println("UPDATE BOR." + borrowerName + " " + borrowerAddress + " " + borrowerPhone);
		
		Borrower b = new Borrower();
		b.setBorrowerName(borrowerName);
		b.setBorrowerAddress(borrowerAddress);
		b.setBorrowerPhone(borrowerPhone);
		b.setCardNo(cardNo);
		
		String returnPath = "/admin/administrator.html";		
		String updateBorrowerResult = "";

		if (borrowerName != null && borrowerName.length() >= 3 && borrowerName.length() < 45 
				&& borrowerAddress != null && borrowerAddress.length() >= 3 && borrowerAddress.length() < 45
				&& borrowerPhone != null && borrowerPhone.length() >= 3 && borrowerPhone.length() < 45) {
			try {
				service.updateBorrower(b);
								
				returnPath = "/admin/viewborrowers.jsp";
				updateBorrowerResult = "Borrower updated successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/admin/editborrower.jsp";
				updateBorrowerResult = "Borrower edit failed";
				request.setAttribute("borrower", b);
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/admin/editborrower.jsp";
			request.setAttribute("borrower", b);
			updateBorrowerResult = "Borrower name, phone and address cannot be less than 3 or more than 45 chars in length";
		}
		request.setAttribute("result", updateBorrowerResult);
		System.out.println(updateBorrowerResult);
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editBorrower(HttpServletRequest request,
			HttpServletResponse response) {
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		System.out.println("Edit Borrower. CardNo: " + cardNo);
		AdministratorService service = new AdministratorService();
		Borrower b = null;
		try {
			b = service.getBorrowerByID(cardNo);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}					
		request.setAttribute("borrower", b);
		System.out.println(b.getBorrowerName());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editborrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteBorrower(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Delete Borrower.");
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		AdministratorService service = new AdministratorService();
		StringBuilder str = new StringBuilder();
		System.out.println("DELETE BORROWER. ID: " + cardNo );
		
		Borrower borrower = null;

		try {
			borrower = service.getBorrowerByID(cardNo);
			if (borrower != null) {
				service.deleteBorrower(borrower);
				List<Borrower> borrowers = service.getAllBorrowers();
				
				str.append("<tr><th>Borrower Name</th><th>Borrower Address</th><th>Borrower Phone</th><th>Edit</th><th>Delete</th></tr>");
				
				if (borrowers != null)
				for(Borrower b: borrowers){
					str.append("<tr><td>");
					if (b.getBorrowerName() != null) 
						str.append("" + b.getBorrowerName());
					str.append("</td><td>");
					if (b.getBorrowerAddress() != null)
						str.append(""+b.getBorrowerAddress());
					str.append("</td><td>");
					if (b.getBorrowerPhone() != null)
						str.append(""+b.getBorrowerPhone());
					str.append("</td><td><button type='button' onclick=\"javascript:location.href='editBorrower?cardNo="+b.getCardNo()+"'\">EDIT</button></td>"
							+ "<td><button type='button' onclick=\"deleteBorrower("+b.getCardNo()+")\">DELETE</button></td></tr>");
				}
				request.setAttribute("result", "Borrower deleted sucessfully");
			} else
				request.setAttribute("result", "Borrower delete failed");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Borrower delete failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
