package model;

import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.DatabaseLibConnection;
import view.BorrowPanel;
import view.ReturnPanel;

public class ReturnModel {
	private String selectBy;
	private String selectBookBy;

	public void setSelectBy(String selectBy) {
		this.selectBy = selectBy;
	}

	public void setSelectBookBy(String selectBookBy) {
		this.selectBookBy = selectBookBy;
	}

	// Check order infomation by borrower
	public void checkBorrowerInfo(ReturnPanel returnPanel) {
		// JOptionPane.showMessageDialog(null, "Hellu");
		try {
			returnPanel.getModel().setRowCount(0);
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			String field = returnPanel.getTxtBorrowerId().getText();
			String sql = "Select borrowers.identification, borrowers.name, books.id, books.price, books.status, books.name, orders.id,orders.book_id, orders.created_at, orders.status, orders.fine "
					+ "From borrowers " + "Join orders " + "On orders.user_id = borrowers.identification "
					+ "Join books " + "On books.id = orders.book_id where " + selectBy + "'%" + field
					+ "%' and orders.status = 1";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				// JOptionPane.showMessageDialog(null, bookId);
				int orderId = rs.getInt("orders.id");
				int borrowerId = rs.getInt("borrowers.identification");
				String borrowerName = rs.getString("borrowers.name");
				int bookId = rs.getInt("orders.book_id");
				java.sql.Date borrowDate = rs.getDate("orders.created_at");
				int bookStatus = rs.getInt("books.status");
				double fine = rs.getDouble("orders.fine");
				String bookName = rs.getString("books.name");
				double bookPrice = rs.getDouble("books.price");
				String status;
				if (bookStatus == 1) {
					status = "Ready";
				} else if (bookStatus == 0) {
					status = "Borrowed";
				} else {
					status = "Overdue";
				}

				Object[] bookInfo = { orderId, borrowerId, borrowerName, bookId, bookName, borrowDate, status, fine };
				returnPanel.getModel().addRow(bookInfo);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	// Check order infomation by book
	public void checkBookInfo(ReturnPanel returnPanel) {
		try {
			returnPanel.getModel().setRowCount(0);
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			String field = returnPanel.getTxtBookId().getText();
			String sql = "Select borrowers.identification, borrowers.name, books.id, books.price, books.status, books.name, orders.id,orders.book_id, orders.created_at, orders.status, orders.fine "
					+ "From borrowers " + "Join orders " + "On orders.user_id = borrowers.identification "
					+ "Join books " + "On books.id = orders.book_id where " + selectBookBy + "'" + field
					+ "%' and status = 1";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				// JOptionPane.showMessageDialog(null, bookId);
				int orderId = rs.getInt("orders.id");
				int borrowerId = rs.getInt("borrowers.identification");
				String borrowerName = rs.getString("borrowers.name");
				int bookId = rs.getInt("orders.book_id");
				java.sql.Date borrowDate = rs.getDate("orders.created_at");
				int bookStatus = rs.getInt("books.status");
				double fine = rs.getDouble("orders.fine");
				String bookName = rs.getString("books.name");
				double bookPrice = rs.getDouble("books.price");
				String status;
				if (bookStatus == 1) {
					status = "Ready";
				} else {
					status = "Borrowed";
				}

				Object[] bookInfo = { orderId, borrowerId, borrowerName, bookId, bookName, borrowDate, status, fine };
				returnPanel.getModel().addRow(bookInfo);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	// Select book to return
	public void returnBook(ReturnPanel returnPanel, JTable table) {
		try {
			String bookId = table.getValueAt(table.getSelectedRow(), 3).toString();
			String borrowerId = table.getValueAt(table.getSelectedRow(), 1).toString();
			// Set book status to 1
			String bookStatus = "Update books set status = 1 where id = " + bookId;
			DatabaseLibConnection.getConnection().createStatement().execute(bookStatus);
			// Set borrowed book of that borrower
			String getBorrowedBook = "Select borrowed_books from borrowers where identification = " + borrowerId;
			ResultSet rs = DatabaseLibConnection.getConnection().createStatement().executeQuery(getBorrowedBook);
			if (rs.next()) {
				int minusOneBook = rs.getInt(1) - 1;
				String updateBorrowedBook = "Update borrowers set borrowed_books = " + minusOneBook
						+ " where identification = " + borrowerId;
				DatabaseLibConnection.getConnection().createStatement().execute(updateBorrowedBook);
			}
			// Remove that book from current order
			String removeBookFromOrder = "Update orders set status = 0 where book_id = " + bookId;
			DatabaseLibConnection.getConnection().createStatement().execute(removeBookFromOrder);
			JOptionPane.showMessageDialog(null, "Returned book: " + bookId);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Check overdue books by status 1:Ready 0:Borrowed -1:Overdue
	public void checkOverdueBooks(ReturnPanel returnPanel) {
		try {
			int totalFine = 0;
			Date currentDate = new Date();
			String sql = "Select return_date from orders";
			ResultSet rs = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql);
			while (rs.next()) {
				Date returnDate = rs.getTimestamp(1);
				if (currentDate.after(returnDate)) {
					// String sql1 = "Update orders set status = 0 where
					// return_date = '" + rs.getDate(1) + "'";
					// DatabaseLibConnection.getConnection().createStatement().execute(sql1);
					String sql2 = "Select borrowers.overdue_books, orders.fine, orders.book_id, borrowers.identification ,books.price, borrowers.overdue_limit "
							+ "From orders " + "Join borrowers " + "On borrowers.identification = orders.user_id "
							+ "Join books " + "On books.id = orders.book_id " + "where orders.return_date= '"
							+ rs.getDate(1) + "' and books.status = 0";
					ResultSet rs1 = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql2);
					if (rs1.next()) {
						int bookId = rs1.getInt("orders.book_id");
						String sql3 = "Update books set status = -1 where id = " + bookId;
						DatabaseLibConnection.getConnection().createStatement().execute(sql3);
						int borrowerId = rs1.getInt("borrowers.identification");
						int overdueBooks = rs1.getInt("borrowers.overdue_books") + 1;
						double fine = rs1.getDouble("books.price") * 2;
						int ovedueLimit = 10 - overdueBooks;
						DatabaseLibConnection.getConnection().createStatement()
								.execute("Update borrowers set overdue_books = " + overdueBooks + ",overdue_limit ="
										+ ovedueLimit + "  where identification =" + borrowerId);
						DatabaseLibConnection.getConnection().createStatement().execute(
								"Update orders set fine =" + fine + " where return_date ='" + rs.getDate(1) + "'");
						
//						totalFine += fine;
//						
//						returnPanel.getTxtFine().setText(String.valueOf(totalFine));
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
