package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import controller.DatabaseLibConnection;
import view.PanelStatistic;

public class StatisticModel {

	public int countBorrower(PanelStatistic ps) {
		int count = 0;
		try {
			String borrower = "SELECT COUNT(DISTINCT orders.user_id) FROM orders WHERE orders.created_at BETWEEN '"
					+ ps.getDay1() + "' AND '" + ps.getDay2() + "'";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(borrower);
			while (rs.next()) {
				count = rs.getInt("COUNT(DISTINCT orders.user_id)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int countBook(PanelStatistic ps) {
		int count = 0;
		try {
			String book = "SELECT COUNT(orders.book_id) FROM orders WHERE orders.created_at BETWEEN '"
					+ ps.getDay1() + "' AND '" + ps.getDay2() + "'";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(book);
			while (rs.next()) {
				count = rs.getInt("COUNT(orders.book_id)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void order(PanelStatistic ps) {
		try {
			String order = "SELECT borrowers.name, books.name, orders.status " + "FROM orders "
					+ "JOIN borrowers ON orders.user_id = borrowers.identification "
					+ "JOIN books ON orders.book_id = books.id " + "WHERE orders.created_at BETWEEN '" + ps.getDay1()
					+ "' AND '" + ps.getDay2() + "'";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(order);
			int i = 1;
			while (rs.next()) {
				Object[] values = null;
				int orders = i++;
				String borrowers = rs.getString("borrowers.name");
				String book = rs.getString("books.name");
				int status = rs.getInt("status");
				values = new Object[]{orders, borrowers, book, status};
				ps.statModel.addRow(values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
