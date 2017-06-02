package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.PreparedStatement;

import controller.DatabaseLibConnection;
import view.LibraryFrame;
import view.User;

public class BorrowerModel {
	
	public boolean CheckID() throws SQLException, IOException{
		User user = new User();
		LibraryFrame myfrm = new LibraryFrame();
		
		if(Integer.parseInt(user.txtid.getText()) != 0){
			String checkId = "SELECT * FROM users WHERE identification = '" + Integer.parseInt(user.txtid.getText()) + "'";
			Connection connect =  DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkId);
			while(rs.next()){
				user.txtname.setText(rs.getString("name"));
				user.txtmail.setText(rs.getString("email"));
				user.txtadd.setText(rs.getString("address"));
				user.txtphone.setText(Integer.toString(rs.getInt("phone")));
				user.cbstatus.setSelectedIndex(rs.getInt("status"));
			}
			return true;
		}
		else{
			JOptionPane.showMessageDialog(myfrm, "This id is not exist");
			return false;
		}
	}
	
	public void Insert() throws IOException, SQLException{
		User user = new User();
		LibraryFrame myfrm = new LibraryFrame();
		if(CheckID() == true){
			JOptionPane.showMessageDialog(myfrm, "This id is exist");
		}
		else if(user.cbstatus.getSelectedIndex() == 0 || user.cbstatus.getSelectedIndex() == 1){
			JOptionPane.showMessageDialog(myfrm, "Please change status to 1");
		}
		else{
			String checkId = "INSERT INTO users (identification, name, email, address, phone, overdue_limit, status) VALUES(?,?,?,?,?,?,?)";
			Connection connect =  DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(checkId);
			stt.setInt(1, Integer.parseInt(user.txtid.getText()));
			stt.setString(2, user.txtname.getText());
			stt.setString(3, user.txtmail.getText());
			stt.setString(4, user.txtadd.getText());
			stt.setInt(5, Integer.parseInt(user.txtphone.getText()));
			stt.setObject(7, user.cbstatus.getSelectedIndex());
			stt.executeUpdate();
		}
	}
	
	public void Modify() throws IOException, SQLException{
		User user = new User();
		
		if(CheckID() == true){
			String mod = "UPDATE users SET name=?, email=?, address=?, phone=? WHERE identification = ?";
			Connection connect =  DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
			stt.setString(1, user.txtname.getText());
			stt.setString(2, user.txtmail.getText());
			stt.setString(3, user.txtadd.getText());
			stt.setInt(4, Integer.parseInt(user.txtphone.getText()));
			stt.setInt(5, Integer.parseInt(user.txtid.getText()));
			stt.executeUpdate();
		}
	}
	
	public void Delete() throws IOException, SQLException{
		User user = new User();
		
		if(CheckID() == true){
			String mod = "UPDATE users SET status=? WHERE identification = ?";
			Connection connect =  DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
			stt.setInt(1, user.cbstatus.getSelectedIndex());
			stt.setInt(2, Integer.parseInt(user.txtid.getText()));
			stt.executeUpdate();
		}
	}
	
	public void Reset() throws IOException{
		User user = new User();
		user.txtid.setText("");
		user.txtname.setText("");
		user.txtmail.setText("");
		user.txtadd.setText("");
		user.txtphone.setText("");
		user.cbstatus.setSelectedIndex(0);
	}
}
