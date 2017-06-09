package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.DatabaseLibConnection;
import view.BorrowPanel;

public class BorrowModel {
	private int lineCount;
	private String selectBy;
	private ArrayList<String> arr = new ArrayList<String>();

	public String getSelectBy() {
		return selectBy;
	}

	public void setSelectBy(String selectBy) {
		this.selectBy = selectBy;
	}

	// Check borrower infomation
	public void checkBorrowerInfo(BorrowPanel borrowPanel) {
		try {
			arr.removeAll(arr);
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			String sql = "Select * from borrowers where identification = " + borrowPanel.getTxtUserId().getText()
					+ " and Status = 1";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(2);
				int borrow = rs.getInt(6);
				int overdue = rs.getInt(7);
				borrowPanel.getTxtUserName().setText(name);
				borrowPanel.getTxtBorrowedBooks().setText(String.valueOf(borrow));
				borrowPanel.getTxtOverdueBooks().setText(String.valueOf(overdue));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Check book infomation
	public void checkBookInfo(BorrowPanel borrowPanel) {
		try {
			borrowPanel.getModel().setRowCount(0);
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			String field = borrowPanel.getTxtBookId().getText();
			String sql = "Select books.id, books.name, books.price, books.status, authors.name, publishers.name, books.status "
					+ "From books " + "Join authors " + "On authors.id = books.author_id " + "Join publishers "
					+ "On publishers.id = books.publisher_id where " + selectBy + "'%" + field
					+ "%' order by books.status DESC";
			System.out.println("SQL: " + sql);
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("books.id");
				String name = rs.getString("books.name");
				double price = rs.getDouble("books.price");
				String status;
				if (rs.getInt("books.status") == 1) {
					status = "Ready";
				} else {
					status = "Borrowed";
				}
				String authorName = rs.getString("authors.name");
				String publisherName = rs.getString("publishers.name");

				Object[] bookInfo = { id, name, authorName, publisherName, price, status };
				borrowPanel.getModel().addRow(bookInfo);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	// Add book to text area
	public void addBooksToBorrow(BorrowPanel borrowPanel, JTable table) {
		try {
			if (table.getSelectedRow() == -1) {
				table.setRowSelectionInterval(0, 0);
			}
			String sqlId = table.getValueAt(table.getSelectedRow(), 0).toString();
			String sql = "Select * from books where id = " + table.getValueAt(table.getSelectedRow(), 0)
					+ " and status = 1";
			ResultSet rs = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql);
			if (rs.next()) {
				arr.add(sqlId);
				borrowPanel.getTxtArea().append(sqlId + "\n");
				String[] lines = borrowPanel.getTxtArea().getText().split("\n");
				lineCount = lines.length;
				if (lineCount == 3) {
					borrowPanel.getBtnAdd().setEnabled(false);
				}
			}
			String sql1 = "Update books set status = 0 where id = " + sqlId;
			DatabaseLibConnection.getConnection().createStatement().execute(sql1);
			checkBookInfo(borrowPanel);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Confirm order for the borrower
	public void confirmBooksToBorrow(BorrowPanel borrowPanel) {
		try {
			// Number of rows in the area
			String[] lines = borrowPanel.getTxtArea().getText().split("\n");
			lineCount = lines.length;
			if (borrowPanel.getTxtUserId().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter the borrower identification !");
			} else if (borrowPanel.getTxtBorrowedBooks().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please check the borrower infomation !");
			} else if (borrowPanel.getTxtArea().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please choose books to borrow !");
			} else {
				if (Integer.parseInt(borrowPanel.getTxtBorrowedBooks().getText()) + lineCount > 3) {
					JOptionPane.showMessageDialog(null, "Borrower can only borrow 3 books !");
				} else {
					int maxId = 0;
					String checkMaxId = "Select Max(id) From orders";
					Statement stm = DatabaseLibConnection.getConnection().createStatement();
					stm.execute(checkMaxId);
					ResultSet rs = stm.getResultSet();
					if (rs.next()) {
						maxId = rs.getInt(1);
					}
					maxId++;
					for (String string : arr) {
						Calendar c = Calendar.getInstance();
						Date date = new Date();
						Date returnDate = new Date();
						c.setTime(returnDate);
						c.add(Calendar.MONTH, 1);
						returnDate = c.getTime();
						String sql = "Insert into orders(id, book_id, user_id, created_at, return_date, updated_at) values(?, ?, ?, ?, ?, ?)";
						java.sql.PreparedStatement pstm = DatabaseLibConnection.getConnection().prepareStatement(sql);
						pstm.setInt(1, maxId);
						pstm.setInt(2, Integer.parseInt(string));
						pstm.setInt(3, Integer.parseInt(borrowPanel.getTxtUserId().getText()));
						pstm.setDate(4, new java.sql.Date(date.getTime()));
						pstm.setDate(5, new java.sql.Date(returnDate.getTime()));
						// Day that the order has been updated
						pstm.setDate(6, new java.sql.Date(date.getTime()));
						pstm.execute();
					}
					String currentBorrowedBooks = "Select borrowed_books from borrowers where identification ="
							+ borrowPanel.getTxtUserId().getText();
					ResultSet rs1 = DatabaseLibConnection.getConnection().createStatement()
							.executeQuery(currentBorrowedBooks);
					int current = 0;
					if (rs1.next()) {
						current = rs1.getInt(1);
					}
					int total = current + lineCount;
					String sql1 = "Update borrowers set borrowed_books = " + total + " where identification = "
							+ borrowPanel.getTxtUserId().getText() + " and status = 1";
					DatabaseLibConnection.getConnection().createStatement().execute(sql1);
					String sql2;
					for (String string : arr) {
						sql2 = "Update books set status = 0 where id = " + string;
						DatabaseLibConnection.getConnection().createStatement().execute(sql2);
					}
					JOptionPane.showMessageDialog(null, "Order confirmed !");
					borrowPanel.getModel().setRowCount(0);
					checkBookInfo(borrowPanel);
					checkBorrowerInfo(borrowPanel);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Cancel order
	public void cancelOrder(BorrowPanel borrowPanel) {
		try {
//			System.out.println(borrowPanel.getTxtBookId().getText());
			for (String string : arr) {
				String sql1 = "Update books set status = 1 where id = " + string;
				DatabaseLibConnection.getConnection().createStatement().execute(sql1);
			}
			arr.removeAll(arr);
			borrowPanel.getTxtArea().setText("");
			if (!borrowPanel.getTxtBookId().getText().equals("")) {
				borrowPanel.getBtnAdd().setEnabled(true);
			}
			checkBookInfo(borrowPanel);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Check overdue books
	public void checkOverdueBooks() {
		try {
			Date currentDate = new Date();
			String sql = "Select return_date from orders";
			ResultSet rs = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql);
			while (rs.next()) {
				Date returnDate = rs.getTimestamp(1);
				if (currentDate.after(returnDate)) {
					String sql1 = "Update orders set status = 0 where return_date = '" + rs.getDate(1) + "'";
					DatabaseLibConnection.getConnection().createStatement().execute(sql1);
					String sql2 = "Select borrowers.overdue_books, orders.fine, orders.book_id, borrowers.identification ,books.price, borrowers.overdue_limit "
							+ "From orders " + "Join borrowers " + "On borrowers.identification = orders.user_id "
							+ "Join books " + "On books.id = orders.book_id " + "where orders.return_date= '"
							+ rs.getDate(1) + "'";
					ResultSet rs1 = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql2);
					if (rs1.next()) {
						int borrowerId = rs1.getInt("borrowers.identification");
						int overdueBooks = rs1.getInt("borrowers.overdue_books") + 1;
						double fine = rs1.getDouble("books.price") * 2;
						int ovedueLimit = 10 - overdueBooks;
						DatabaseLibConnection.getConnection().createStatement()
								.execute("Update borrowers set overdue_books = " + overdueBooks + ",overdue_limit ="
										+ ovedueLimit + "  where identification =" + borrowerId);
						DatabaseLibConnection.getConnection().createStatement().execute(
								"Update orders set fine =" + fine + " where return_date ='" + rs.getDate(1) + "'");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
