package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import controller.DatabaseLibConnection;
import entity.Books;
import entity.Borrowers;
import view.PanelStat1;
import view.PanelStat2;

public class StatisticModel {

	public ArrayList<Borrowers> checkDate(PanelStat1 p1) {
		ArrayList<Borrowers> borrowerList = new ArrayList<Borrowers>();
		try {
			String checkDate = "SELECT DISTINCT borrowers.identification, borrowers.name, borrowers.borrowed_books "
					+ "FROM borrowers " + "JOIN orders ON borrowers.identification = orders.user_id "
					+ "WHERE orders.created_at BETWEEN '" + p1.getDay1() + "' AND '" + p1.getDay2() + "'";
			// System.out.println(checkDate);
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkDate);
			while (rs.next()) {
				Borrowers borrower = new Borrowers();
				borrower.setIdentification(rs.getInt("identification"));
				borrower.setBorrowers_name(rs.getString("name"));
				borrower.setBorrowed_books(rs.getInt("borrowed_books"));
				borrowerList.add(borrower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrowerList;
	}

	public ArrayList<Borrowers> checkMonth(PanelStat1 p1) {
		ArrayList<Borrowers> borrowerList = new ArrayList<Borrowers>();
		try {
			String checkMonth = "SELECT DISTINCT borrowers.identification, borrowers.name, borrowers.borrowed_books "
					+ "FROM borrowers " + "JOIN orders ON borrowers.identification = orders.user_id "
					+ "WHERE MONTH(orders.created_at) = " + p1.getMc()
					+ " AND YEAR(orders.created_at) = " + p1.getYc();
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkMonth);
			while (rs.next()) {
				Borrowers borrower = new Borrowers();
				borrower.setIdentification(rs.getInt("identification"));
				borrower.setBorrowers_name(rs.getString("name"));
				borrower.setBorrowed_books(rs.getInt("borrowed_books"));
				borrowerList.add(borrower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrowerList;
	}

	public ArrayList<Books> checkDate1(PanelStat1 p1) {
		ArrayList<Books> bookList = new ArrayList<Books>();
		try {
			String checkDate = "SELECT DISTINCT books.id, books.name, books.price " + "FROM books "
					+ "JOIN orders ON books.id = orders.book_id "
					+ "WHERE orders.created_at BETWEEN '" + p1.getDay1() + "' AND '" + p1.getDay2()
					+ "'";
			System.out.println(checkDate);
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkDate);
			while (rs.next()) {
				Books book = new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public ArrayList<Books> checkMonth1(PanelStat1 p1) {
		ArrayList<Books> bookList = new ArrayList<Books>();
		try {
			String checkMonth = "SELECT DISTINCT books.id, books.name, books.price " + "FROM books "
					+ "JOIN orders ON books.id = orders.book_id "
					+ "WHERE MONTH(orders.created_at) = " + p1.getMc()
					+ " AND YEAR(orders.created_at) = " + p1.getYc();
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkMonth);
			while (rs.next()) {
				Books book = new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public static void main(String[] args) {
		// StatisticModel sm = new StatisticModel();
		// sm.checkMonth();
	}
}
