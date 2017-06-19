package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controller.DatabaseLibConnection;
import entity.Borrowers;
import view.PanelStatistic;

public class StatisticModel {
	
	public ArrayList<Borrowers> Borrower(){
		ArrayList<Borrowers> borrowerList = new ArrayList<Borrowers>();
		try {
			String show = "SELECT DISTINCT borrowers.identification, borrowers.name, borrowers.borrowed_books FROM borrowers JOIN orders ON borrowers.identification = orders.user_id WHERE orders.status = 1";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(show);
			while (rs.next()) {
				Borrowers borrower = new Borrowers();
				borrower.setIdentification(rs.getInt("identification"));
				borrower.setBorrowers_name(rs.getString("name"));
				borrower.setBorrowed_books(rs.getInt("borrowed_books"));
				borrowerList.add(borrower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowerList;
	}
	
	public boolean checkDate(PanelStatistic ps){
		try{
			String checkDate = "SELECT created_at FROM orders WHERE status = 1";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkDate);
			while(rs.next()){
				Date createdDate = rs.getDate("created_at");
				if(createdDate.before(ps.getDay2()) && createdDate.after(ps.getDay1())){
					return false;
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;	
		}
		return true;
	}
}
