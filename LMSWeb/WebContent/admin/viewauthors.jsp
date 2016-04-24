<%@ include file="/include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>

<%
	AdministratorService service = new AdministratorService();
	List<Author> authors;
	Integer authorsCount;
	Integer pageSize = 5;
	try {
		authorsCount = service.getAuthorCount();
		if (request.getAttribute("authors") != null) {
			authors = (List<Author>) request.getAttribute("authors");
		} else {
			authors = service.getAllAuthors(1, pageSize);
		}
	} catch (Exception e) {
%>
		<h2>We are sorry. Database is down. Please try again later...</h2>
<%
		System.out.println("DB Down");
		return;
	}
%>

<script type="text/javascript">
var curPage = 1;
function deleteAuthor(authorId){
	$.ajax({
		  url: "deleteAuthor",
		  data:{
			  authorId: authorId,
			  pageNo: curPage,
			  pageSize: <%=pageSize%>
		  }
		}).done(function(data) {
		  $('#pageWithTable').html(data);
		});
}
function pageAuthors(pageNo, pageSize){
	if (pageNo >= 1 && pageNo <= (<%=authorsCount%> / pageSize))
		curPage = pageNo;
	$.ajax({
		  url: "pageAuthors",
		  data:{
			  pageNo: pageNo,
			  pageSize: pageSize,
			  dataType: "author"
		  }
		}).done(function(data) {
		  $('#authorsTable').html(data);
		});
}

var sType = "All";

$(function() {
	$('#searchType li a').on('click', function(){

	console.log("Selected Option:"+$(this).text());
    sType = $(this).text();
	});
});

function searchAuthors(){
	if ($('#searchString').val().length < 3 ) return;
	$.ajax({
		  url: "searchAuthors",
		  data:{
			  searchString: $('#searchString').val(),
			  searchType: sType,
			  pageSize: <%=pageSize%>
		  }
		}).done(function(data) {
		  $('#pageWithTable').html(data);
		});
}
$(document).ready(function()
		{
			$('#editAuthor').on('hidden.bs.modal', function(e)
					{
				$(this).removeData();
				});
			});
		
</script>

<h2>Available authors in LMS</h2>

${result}

<form action="searchAuthors">
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="ex. Stephen King" aria-describedby="basic-addon1" name="searchString" id="searchString" onkeyup ="searchAuthors()">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
					</button>  
					<ul id="searchType" class="dropdown-menu dropdown-menu-right">
						<li><a tabindex="-1" href="#">Author Name</a></li>
						<li><a tabindex="-1" href="#">Books</a></li>
						<li role="separator" class="divider"></li>
						<li><a tabindex="-1" href="#">All</a></li>
					</ul>
					<button type="button" class="btn btn-success" onclick="searchAuthors()">Search!</button>
				</div>
			</div>
		</div>
	</div>
</form>

<div class="pageWithTable" id="pageWithTable">
	<nav>
		<ul class="pagination">
			<li><a href="javascript:pageAuthors(curPage-1,<%=pageSize%>)" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
			<%
				if (authorsCount != null && authorsCount > 0) {
					int pages = 0;
					if (authorsCount % pageSize == 0) {
						pages = authorsCount / pageSize;
					} else {
						pages = authorsCount / pageSize + 1;
					}
					for (int i = 1; i <= pages; i++) {
			%>
			<li><a href="javascript:pageAuthors(<%=i%>,<%=pageSize%>)"><%=i%></a></li>
			<!-- <li><button type="button" onclick="pageAuthors(<%=i%>,<%=pageSize%>)"><%=i%></button></li>  -->

			<%
				}

				}
			%>
			<li><a href="javascript:pageAuthors(curPage+1,<%=pageSize%>)" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a>
		</ul>
	</nav>

	<div class="row">
		<div class="col-md-6">
			<table border="3" id="authorsTable" class="table">
				<tr>
					<th>Author Name</th>
					<th>Book Title</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<%
					if (authors != null && authors.size() > 0)
						for (Author a : authors) {
				%>
				<tr>
					<td>
						<%
							out.println(a.getAuthorName());
						%>
					</td>

					<td>
						<%
							List<Book> books = a.getBooks();
									if (books != null && books.size() > 0)
										for (Book b : books) {
											out.print(b.getTitle());
											if (b != books.get(books.size() - 1))
												out.println(", ");
										}
						%>
					</td>
					<td align="center"><button type="button"
							class="btn btn btn-primary" data-toggle="modal"
							data-target="#editAuthor"
							href="editAuthor?authorId=<%=a.getAuthorId()%>">EDIT</button></td>
					<td align="center"><button type="button"
							class="btn btn btn-danger"
							onclick="deleteAuthor(<%=a.getAuthorId()%>)">DELETE</button></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>
	</div>
</div>


<div id="editAuthor" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>