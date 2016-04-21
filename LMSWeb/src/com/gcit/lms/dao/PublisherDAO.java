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
import com.gcit.lms.entity.Publisher;

/**
 * @author Meirbek
 *
 */
public class PublisherDAO  extends BaseDAO {

	public PublisherDAO(Connection conn) {
		super(conn);
	}
	
	public void addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?) ", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}
	
	public Integer addPublisherWithID(Publisher publisher) throws SQLException, ClassNotFoundException {
		return saveWithID("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		if (publisher.getPublisherName() != null && publisher.getPublisherAddress() != null && publisher.getPublisherPhone() != null)
			save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ? ", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
		else if (publisher.getPublisherName() != null && publisher.getPublisherAddress() != null)
			save("update tbl_publisher set publisherName = ?, publisherAddress = ? where publisherId = ? ", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherId()});
		else if (publisher.getPublisherName() != null && publisher.getPublisherPhone() != null)
			save("update tbl_publisher set publisherName = ?, publisherPhone = ? where publisherId = ? ", new Object[] {publisher.getPublisherName(), publisher.getPublisherPhone(), publisher.getPublisherId()});
		else if (publisher.getPublisherAddress() != null && publisher.getPublisherPhone() != null)
			save("update tbl_publisher set publisherAddress = ?, publisherPhone = ? where publisherId = ? ", new Object[] {publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
		else if (publisher.getPublisherName() != null)
			save("update tbl_publisher set publisherName = ? where publisherId = ? ", new Object[] {publisher.getPublisherName(), publisher.getPublisherId()});
		else if (publisher.getPublisherAddress() != null)
			save("update tbl_publisher set publisherAddress = ? where publisherId = ? ", new Object[] {publisher.getPublisherAddress(), publisher.getPublisherId()});
		else
			save("update tbl_publisher set publisherPhone = ? where publisherId = ? ", new Object[] {publisher.getPublisherPhone(), publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("delete from tbl_publisher where publisherId = ?", new Object[] {publisher.getPublisherId()});
	}
	
	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException {
		return (List<Publisher>) readAll("select * from tbl_publisher", null);
	}
	
	public List<Publisher> readPublishersByName(String name) throws ClassNotFoundException, SQLException {
		return (List<Publisher>) readAll("select * from tbl_publisher where publisherName like ?", new Object[] {name});
	}
	
	public List<Publisher> readPublishersByAddress(String address) throws ClassNotFoundException, SQLException {
		return (List<Publisher>) readAll("select * from tbl_publisher where publisherAddress like ?", new Object[] {address});
	}
	
	public List<Publisher> readPublishersByPhone(String phone) throws ClassNotFoundException, SQLException {
		return (List<Publisher>) readAll("select * from tbl_publisher where publisherPhone like ?", new Object[] {phone});
	}
	
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		BookDAO bookDAO = new BookDAO(getConnection());
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setPublisherId(rs.getInt("publisherId"));
			
			try {
				p.setBooks((List<Book>) bookDAO.readFirstLevel("select * from tbl_book where pubId = ?", new Object[] {p.getPublisherId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publishers.add(p);
		}
		if (publishers.size() > 0)
			return publishers;
		else return null;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		BookDAO bookDAO = new BookDAO(getConnection());
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setPublisherId(rs.getInt("publisherId"));
			
			publishers.add(p);
		}
		if (publishers.size() > 0)
			return publishers;
		else return null;
	}

	public void linkBooks(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("update tbl_book set pubId = null where pubId = ?", new Object[] {publisher.getPublisherId()});
		if (publisher != null && publisher.getBooks() != null && publisher.getBooks().size() > 0)
			for (Book b: publisher.getBooks())
				save("update tbl_book set pubId = ? where bookId = ?", new Object[] {publisher.getPublisherId(), b.getBookId()});
	}

	public Publisher readPublisherByID(Integer publisherId) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = (List<Publisher>) readAll("select * from tbl_publisher where publisherId = ?", new Object[] {publisherId});
		if (publishers != null && publishers.size() > 0)
			return publishers.get(0);
		else return null;
	}

}
