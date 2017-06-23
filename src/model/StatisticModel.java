package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import controller.DatabaseLibConnection;
import entity.Borrowers;
import view.PanelStat1;
import view.PanelStat2;

public class StatisticModel {

	public ArrayList<Borrowers> checkDate(PanelStat1 p1) {
		ArrayList<Borrowers> borrowerList = new ArrayList<Borrowers>();
		try {
			String checkDate = "SELECT DISTINCT borrowers.identification, borrowers.name, borrowers.borrowed_books "
					+ "FROM borrowers " + "JOIN orders ON borrowers.identification = orders.user_id "
					+ "WHERE orders.status = 1 AND orders.created_at BETWEEN " + p1.getDay1() + " AND " + p1.getDay2()
					+ " OR orders.return_date BETWEEN " + p1.getDay1() + " AND " + p1.getDay2();
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
					+ "WHERE orders.status = 1 AND MONTH(created_at) = " + p1.getMc() + " AND YEAR(created_at) = "
					+ p1.getYc();
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

	public ArrayList<Borrowers> checkDate1(PanelStat2 p2) {
		ArrayList<Borrowers> borrowerList = new ArrayList<Borrowers>();
		try {
			String checkDate = "SELECT DISTINCT borrowers.identification, borrowers.name, borrowers.borrowed_books "
					+ "FROM borrowers " + "JOIN orders ON borrowers.identification = orders.user_id "
					+ "WHERE orders.status = 1 AND orders.created_at BETWEEN " + p2.getDay1() + " AND " + p2.getDay2()
					+ " OR orders.return_date BETWEEN " + p2.getDay1() + " AND " + p2.getDay2();
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
	
	public static void main(String[] args) {
		// StatisticModel sm = new StatisticModel();
		// sm.checkMonth();
	}
}
