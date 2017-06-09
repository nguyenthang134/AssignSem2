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

	// Check borrower infomation
	public void checkBorrowerInfo(ReturnPanel returnPanel) {
		try {
			returnPanel.getModel().setRowCount(0);
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			String field = returnPanel.getTxtBorrowerId().getText();
			String sql = "Select borrowers.identification, borrowers.name, books.id, books.price, books.status, books.name, orders.id,orders.book_id, orders.created_at "
					+ "From borrowers " + "Join orders " + "On orders.user_id = borrowers.identification "
					+ "Join books " + "On books.id = orders.book_id where " + selectBy + "'" + field + "%'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				// JOptionPane.showMessageDialog(null, bookId);
				int orderId = rs.getInt("orders.id");
				int borrowerId = rs.getInt("borrowers.identification");
				String borrowerName = rs.getString("borrowers.name");
				int bookId = rs.getInt("orders.book_id");
				java.sql.Date borrowDate = rs.getDate("orders.created_at");
				int bookStatus = rs.getInt("books.status");
				String bookName = rs.getString("books.name");
				double bookPrice = rs.getDouble("books.price");
				String status;
				if (bookStatus == 1) {
					status = "Ready";
				} else {
					status = "Borrowed";
				}

				Object[] bookInfo = { orderId, borrowerId, borrowerName, bookId, bookName, borrowDate, status };
				returnPanel.getModel().addRow(bookInfo);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	// Check book infomation
	public void checkBookInfo(ReturnPanel returnPanel) {
		try {
			returnPanel.getModel().setRowCount(0);
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			String field = returnPanel.getTxtBookId().getText();
			String sql = "Select borrowers.identification, borrowers.name, books.id, books.price, books.status, books.name, orders.id,orders.book_id, orders.created_at "
					+ "From borrowers " + "Join orders " + "On orders.user_id = borrowers.identification "
					+ "Join books " + "On books.id = orders.book_id where " + selectBookBy + "'" + field + "%'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				// JOptionPane.showMessageDialog(null, bookId);
				int orderId = rs.getInt("orders.id");
				int borrowerId = rs.getInt("borrowers.identification");
				String borrowerName = rs.getString("borrowers.name");
				int bookId = rs.getInt("orders.book_id");
				java.sql.Date borrowDate = rs.getDate("orders.created_at");
				int bookStatus = rs.getInt("books.status");
				String bookName = rs.getString("books.name");
				double bookPrice = rs.getDouble("books.price");
				String status;
				if (bookStatus == 1) {
					status = "Ready";
				} else {
					status = "Borrowed";
				}

				Object[] bookInfo = { orderId, borrowerId, borrowerName, bookId, bookName, borrowDate, status };
				returnPanel.getModel().addRow(bookInfo);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	//Select book to return
	public void returnBook(ReturnPanel returnPanel, JTable table) {
//		try {
//			if (table.getSelectedRow() == -1) {
//				table.setRowSelectionInterval(0, 0);
//			}
//			String sqlId = table.getValueAt(table.getSelectedRow(), 0).toString();
//			String sql = "Select * from books where id = " + table.getValueAt(table.getSelectedRow(), 0)
//					+ " and status = 1";
//			ResultSet rs = DatabaseLibConnection.getConnection().createStatement().executeQuery(sql);
//			if (rs.next()) {
//				arr.add(sqlId);
//				borrowPanel.getTxtArea().append(sqlId + "\n");
//				String[] lines = borrowPanel.getTxtArea().getText().split("\n");
//				lineCount = lines.length;
//				if (lineCount == 3) {
//					borrowPanel.getBtnAdd().setEnabled(false);
//				}
//			}
//			String sql1 = "Update books set status = 0 where id = " + sqlId;
//			DatabaseLibConnection.getConnection().createStatement().execute(sql1);
//			checkBookInfo(borrowPanel);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}
}
