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
 * Servlet implementation class LibrarianServlet
 */
@WebServlet({"/librarian/editBranch", "/librarian/updateBranch", "/librarian/addCopies"})
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
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
		case "/librarian/editBranch":
			editBranch(request, response);
			break;
		case "/librarian/addCopies":
			addCopies(request, response);
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
		case "/librarian/updateBranch":
			updateBranch(request, response);
			break;
		default:
			break;
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
		RequestDispatcher rd = request.getRequestDispatcher("/librarian/editbranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		String returnPath = "/librarian/administrator.html";		
		String updateBranchResult = "";
		if (branchName != null && branchName.length() >= 3 && branchName.length() < 45 
				&& branchAddress != null && branchAddress.length() >= 3 && branchAddress.length() < 45) {
			try {
				service.updateLibraryBranch(lb);
								
				returnPath = "/librarian/updatebranch.jsp";
				updateBranchResult = "Library Branch updated successfully";
			} catch (ClassNotFoundException | SQLException e) {
				returnPath = "/librarian/editbranch.jsp";
				updateBranchResult = "Library Branch edit failed";
				request.setAttribute("branch", lb);
				e.printStackTrace();
			}
		}
		else {
			returnPath = "/librarian/editbranch.jsp";
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
	
	private void addCopies(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Add copies.");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		String noOfCopies_str = request.getParameter("noOfCopies");
		System.out.println(noOfCopies_str);
		BorrowerService service = new BorrowerService();
		StringBuilder str = new StringBuilder();
		
		BookCopy bc = null;

		try {
			bc = service.getBookCopyByAll(bookId, branchId);
			//bc.setNoOfCopies(noOfCopies);
			if (bc != null) {
				service.updateBookCopies(bc);
				
				AdministratorService serviceA = new AdministratorService();
		    	List<BookCopy> bookCopies = serviceA.getAllNonExistingBookCopies();
				
				str.append("<tr> <th>Branch Name</th> <th>Book Title</th> <th># of Copies</th> <th>Copies</th>  </tr>");
				
				if (bookCopies != null && bookCopies.size() > 0)
					for (BookCopy bookCopy: bookCopies) { 
						str.append("<tr><td>");
						if (bc.getLibraryBranch().getBranchName() != null)
							str.append(""+bc.getLibraryBranch().getBranchName());
						str.append("</td><td>");
						if (bc.getBook().getTitle() != null)
							str.append(""+bc.getBook().getTitle());
						str.append("</td><td>");
						
						str.append("<input type='text' name='noOfCopies' value=" + bc.getNoOfCopies()+ "></td>");
						str.append("<td><button type='button' onclick=\"addCopies(" + bc.getBook().getBookId() + "," + bc.getLibraryBranch().getBranchId() + ")\">ADD</button></td></tr>");
					}
				request.setAttribute("result", "Book copies changed sucessfully");
			} else
				request.setAttribute("result", "Book Copy update failed");
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("result", "Book copy update failed");
			e.printStackTrace();
		}		
		
		try {
			response.getWriter().append(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
