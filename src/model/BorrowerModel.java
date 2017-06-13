package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.PreparedStatement;

import controller.DatabaseLibConnection;
import entity.Borrowers;

public class BorrowerModel {

	private String searchBy;

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public boolean insert(Borrowers borrower) {
		try {
			String checkId = "INSERT INTO borrowers (identification, name, email, address, phone, status) VALUES(?,?,?,?,?,?)";
			Connection connect = DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(checkId);
			stt.setInt(1, borrower.getIdentification());
			stt.setString(2, borrower.getBorrowers_name());
			stt.setString(3, borrower.getBorrowers_mail());
			stt.setString(4, borrower.getBorrowers_address());
			stt.setInt(5, borrower.getBorrowers_phone());
			stt.setInt(6, 1);
			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean modify(Borrowers borrower) {
		if (borrower.getIdentification() <= 0) {
			System.err.println("Where is your id?");
			return false;
		}
		try {
			String mod = "UPDATE borrowers SET name=?, email=?, address=?, phone=? WHERE identification = ?";
			Connection connect = DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
			stt.setString(1, borrower.getBorrowers_name());
			stt.setString(2, borrower.getBorrowers_mail());
			stt.setString(3, borrower.getBorrowers_address());
			stt.setInt(4, borrower.getBorrowers_phone());
			stt.setInt(5, borrower.getIdentification());
			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int id) {
		try {
			String del = "UPDATE borrowers SET status=? WHERE identification = ?";
			Connection connect = DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(del);
			stt.setInt(1, 0);
			stt.setInt(2, id);
			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<Borrowers> getList() {
		ArrayList<Borrowers> borrowersList = new ArrayList<Borrowers>();
		try {
			String show = "SELECT identification, name, email, address, phone, borrowed_books, overdue_books, overdue_limit FROM borrowers WHERE status = 1";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(show);
			while (rs.next()) {
				Borrowers borrowers = new Borrowers();
				borrowers.setIdentification(rs.getInt("identification"));
				borrowers.setBorrowers_name(rs.getString("name"));
				borrowers.setBorrowers_mail(rs.getString("email"));
				borrowers.setBorrowers_address(rs.getString("address"));
				borrowers.setBorrowers_phone(rs.getInt("phone"));
				borrowers.setBorrowed_books(rs.getInt("borrowed_books"));
				borrowers.setOverdue_books(rs.getInt("overdue_books"));
				borrowers.setOverdue_limit(rs.getDouble("overdue_limit"));
				borrowersList.add(borrowers);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowersList;
	}

	public ArrayList<Borrowers> searchBy(String str) {
		ArrayList<Borrowers> borrowersList = new ArrayList<Borrowers>();
		Borrowers borrower = null;
		try {
			String check = "SELECT identification, name, email, address, phone, borrowed_books, overdue_books, overdue_limit FROM borrowers WHERE " + searchBy + " LIKE '%"
					+ str + "%' AND status = 1";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(check);
			while (rs.next()) {
				borrower = new Borrowers();
				borrower.setIdentification(rs.getInt("identification"));
				borrower.setBorrowers_name(rs.getString("name"));
				borrower.setBorrowers_mail(rs.getString("email"));
				borrower.setBorrowers_address(rs.getString("address"));
				borrower.setBorrowers_phone(rs.getInt("phone"));
				borrower.setBorrowed_books(rs.getInt("borrowed_books"));
				borrower.setOverdue_books(rs.getInt("overdue_books"));
				borrower.setOverdue_limit(rs.getDouble("overdue_limit"));
				borrowersList.add(borrower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowersList;
	}
	
	public Borrowers getById(int id) {
		Borrowers borrower = null;
		try {
			String check = "SELECT identification, name, email, address, phone FROM borrowers WHERE identification = "
					+ id;
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(check);
			if (rs.next()) {
				borrower = new Borrowers();
				borrower.setIdentification(rs.getInt("identification"));
				borrower.setBorrowers_name(rs.getString("name"));
				borrower.setBorrowers_mail(rs.getString("email"));
				borrower.setBorrowers_address(rs.getString("address"));
				borrower.setBorrowers_phone(rs.getInt("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrower;
	}
}
