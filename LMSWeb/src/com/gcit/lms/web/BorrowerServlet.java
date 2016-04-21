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
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.BorrowerService;
import com.sun.xml.internal.ws.client.RequestContext;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({"/borrower/logIn", "/borrower/checkOut", "/borrower/checkIn"})
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerServlet() {
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
		case "/borrower/checkOut":
			checkOut(request, response);
			break;
		case "/borrower/checkIn":
			checkIn(request, response);
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
		case "/borrower/logIn":
			logIn(request, response);
			break;
		default:
			break;
		}
		
	}

	private void logIn(HttpServletRequest request,
			HttpServletResponse response) {
		
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		AdministratorService service = new AdministratorService();
		System.out.println("LOGIN BORROWER CardNo: " + cardNo );
		String returnPath = "/borrower/borrower.jsp";
		
		Borrower borrower = null;

		try {
			borrower = service.getBorrowerByID(cardNo);
			if (borrower != null) {
				request.setAttribute("result", borrower.getBorrowerName());
				request.setAttribute("address", borrower.getBorrowerAddress());
				request.setAttribute("phone", borrower.getBorrowerPhone());
				request.setAttribute("cardNo", cardNo);
				returnPath = "/borrower/borrowerhome.jsp";
			} else
			{
				
				request.setAttribute("result", "Borrower #" + cardNo + " does not exist. Please enter valid Card Number.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Borrower login failed");
			e.printStackTrace();
		}		
		
		RequestDispatcher rd = request.getRequestDispatcher(returnPath);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void checkOut(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Check Out.");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		
		BorrowerService service = new BorrowerService();
		StringBuilder str = new StringBuilder();
		System.out.println("CARD Number. ID: " + cardNo + "\n " + "Branch and book IDS: " + branchId + " " + bookId);
		
		BookLoan bL = null;

		try {
			bL = service.getBookLoanByAll(bookId, branchId, cardNo);
			if (bL == null || bL.getDateIn() != null) {
				Book b = new Book(); b.setBookId(bookId);
				LibraryBranch lb = new LibraryBranch(); lb.setBranchId(branchId);
				Borrower borrower = new Borrower(); borrower.setCardNo(cardNo);
				if (bL == null) {
					bL = new BookLoan();
					bL.setBook(b);
					bL.setLibraryBranch(lb);
					bL.setBorrower(borrower);
				}
				bL.setDateOut(service.getCurrentDate());
				bL.setDueDate(service.getWeekFromDate());
				bL.setDateIn(null);
				service.addBookLoan(bL);
				
				str.append("<tr> <th>Branch Name</th> <th>Book Title</th> <th>Copies</th> <th>Check Out</th> </tr>");
				
				List<BookCopy> bookCopies = service.getExistingBooks();
				BookLoan bookLoan = new BookLoan();
				bookLoan.setBorrower(borrower);
				List<BookLoan> bookLoans = service.getBookLoansByBorrower(borrower);
		    	
				for (BookCopy bc: bookCopies) {
					 bookLoan.setBook(bc.getBook());
					 bookLoan.setLibraryBranch(bc.getLibraryBranch());
					 if (bookLoan.equals(bL)) {
						 bc.setNoOfCopies(bc.getNoOfCopies() - 1);
						 service.updateBookCopies(bc);
					 }
					 if ((bc.getNoOfCopies() > 0) && (bookLoans == null || (bookLoans.contains(bookLoan) && bookLoans.get(bookLoans.indexOf(bookLoan)).getDateIn() != null) || (!bookLoans.contains(bookLoan) )))
					{						 
					str.append("<tr><td>");
					if (bc.getLibraryBranch().getBranchName() != null) 
						str.append("" + bc.getLibraryBranch().getBranchName());
					str.append("</td><td>");
					if (bc.getBook().getTitle() != null) 
						str.append("" + bc.getBook().getTitle());
					str.append("</td><td>");
					str.append("" + bc.getNoOfCopies() + "</td>");
					
					str.append("<td><button type='button' onclick=\"checkOut(" + bc.getBook().getBookId() + "," +bc.getLibraryBranch().getBranchId() + "," + Integer.parseInt(request.getParameter("cardNo")) + ")\">Check Out</button></td></tr>");
					}
				}
				request.setAttribute("result", "Book checked out sucessfully");
			} else
				request.setAttribute("result", "Book already checked out.");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Book check out failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void checkIn(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Check In (return).");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		
		BorrowerService service = new BorrowerService();
		StringBuilder str = new StringBuilder();
		System.out.println("CARD Number. ID: " + cardNo + "\n " + "Branch and book IDS: " + branchId + " " + bookId);
		
		BookLoan bL = null;

		try {
			bL = service.getBookLoanByAll(bookId, cardNo, branchId);
			if (bL != null || bL.getDateIn() != null) {
				
				//Book b = new Book(); b.setBookId(bookId);
				//LibraryBranch lb = new LibraryBranch(); lb.setBranchId(branchId);
				
				
				bL.setDateIn(service.getCurrentDate());
				
				service.returnBookLoan(bL);
				
				BookCopy bc = new BookCopy();
				bc.setBook(bL.getBook());
				bc.setLibraryBranch(bL.getLibraryBranch());
				int noOfCopies = service.getNoOfCopies(bL.getBook(), bL.getLibraryBranch());
				bc.setNoOfCopies(noOfCopies + 1);
				
				service.updateBookCopies(bc);
				
				str.append("<tr> <th>Branch Name</th> <th>Book Title</th> <th>Date Out</th> <th>Due Date</th> <th>Return</th> </tr>");
				
				//List<BookCopy> bookCopies = service.getExistingBooks();
				//BookLoan bookLoan = new BookLoan();
				//bookLoan.setBorrower(borrower);
				Borrower borrower = new Borrower(); 
				borrower.setCardNo(cardNo);
				List<BookLoan> bookLoans = service.getBookLoansByBorrower(borrower);
				if (bookLoans != null && bookLoans.size() > 0)
				for (BookLoan bl: bookLoans) {
				if (bl.getDateIn() == null) {					 
					str.append("<tr><td>");
					if (bl.getLibraryBranch().getBranchName() != null) 
						str.append("" + bl.getLibraryBranch().getBranchName());
					str.append("</td><td>");
					if (bl.getBook().getTitle() != null) 
						str.append("" + bl.getBook().getTitle());
					str.append("</td><td>");
					if (bl.getDateOut() != null) 
						str.append("" + bl.getDateOut());
					str.append("</td><td>");
					if (bl.getDueDate() != null) 
						str.append("" + bl.getDueDate());
					
					str.append("<td><button type='button' onclick=\"checkIn(" + bl.getBook().getBookId() + "," + bl.getLibraryBranch().getBranchId()+ "," + bl.getBorrower().getCardNo()+ ")\">Return Now</button></td></tr>");
					}
				}
				request.setAttribute("result", "Book returned sucessfully");
			} else
				request.setAttribute("result", "Book already checked in.");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Book return failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
