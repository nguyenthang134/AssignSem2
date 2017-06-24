package model;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import controller.DatabaseLibConnection;
import view.SearchPanel;

public class SearchModel {
	private String searchBy;
	
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
	public void search(SearchPanel searchPanel){
		try {
			searchPanel.getModel().setRowCount(0);
			String field = searchPanel.getTxtSearchby().getText();
			String sql = "Select books.id, books.price, books.status, books.name, authors.name, publishers.name, categories.name  "
					+ "From books " + "Join authors " + "On books.author_id = authors.id "
					+ "Join publishers " + "On books.publisher_id = publishers.id "
					+ "Join book_categories On books.name = book_categories.book_name "
					+ "Join categories On categories.id = book_categories.category_id "
					+ "where " + searchBy + "'%" + field
					+ "%'";
			ResultSet rs = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql);
			while(rs.next()){
				int bookId = rs.getInt("books.id");
				String bookName = rs.getString("books.name");
				String bookCategory = rs.getString("categories.name");
				String bookAuthor = rs.getString("authors.name");
				String bookPublisher = rs.getString("publishers.name");
				double bookPrice = rs.getDouble("books.price");
				int bookStatus = rs.getInt("books.status");
				String status;
				if (bookStatus == 1) {
					status = "Ready";
				} else if (bookStatus == 0) {
					status = "Borrowed";
				} else {
					status = "Overdue";
				}
				Object[] bookInfo = { bookId, bookName, bookCategory, bookAuthor, bookPublisher, bookPrice, bookStatus };
				searchPanel.getModel().addRow(bookInfo);				
			}			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
