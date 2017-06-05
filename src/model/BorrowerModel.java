package model;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.PreparedStatement;

import controller.DatabaseLibConnection;
import view.User;

public class BorrowerModel {

	public boolean CheckID() throws SQLException, IOException {
		if (User.txtid.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Please input id which you want to verify");
		}
		String checkId = "SELECT * FROM borrowers WHERE identification = '" + Integer.parseInt(User.txtid.getText())
				+ "'";
		Connection connect = DatabaseLibConnection.getConnection();
		Statement stt = connect.createStatement();
		ResultSet rs = stt.executeQuery(checkId);
		boolean idNotExist = true;
		while (rs.next()) {
			idNotExist = false;
			User.txtname.setText(rs.getString("name"));
			User.txtmail.setText(rs.getString("email"));
			User.txtadd.setText(rs.getString("address"));
			User.txtphone.setText(Integer.toString(rs.getInt("phone")));
			if (rs.getInt("status") == 1) {
				User.cbstatus.setSelectedIndex(2);
			} else if (rs.getInt("status") == 0) {
				User.cbstatus.setSelectedIndex(1);
			}
		}
		if (idNotExist) {
			JOptionPane.showMessageDialog(null, "This id is not exist");
		}
		return true;
	}

	public void Insert() throws IOException, SQLException {
		if (User.cbstatus.getSelectedIndex() == 0 || User.cbstatus.getSelectedIndex() == 1) {
			JOptionPane.showMessageDialog(null, "Please change status to 1");
		} else {
			String checkId = "INSERT INTO borrowers (identification, name, email, address, phone, status) VALUES(?,?,?,?,?,?)";
			Connection connect = DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(checkId);
			stt.setInt(1, Integer.parseInt(User.txtid.getText()));
			stt.setString(2, User.txtname.getText());
			stt.setString(3, User.txtmail.getText());
			stt.setString(4, User.txtadd.getText());
			stt.setInt(5, Integer.parseInt(User.txtphone.getText()));
			stt.setObject(6, User.cbstatus.getSelectedItem());
			stt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Success");
		}
	}

	public void Modify() throws IOException, SQLException {
		String mod = "UPDATE borrowers SET name=?, email=?, address=?, phone=? WHERE identification = ?";
		Connection connect = DatabaseLibConnection.getConnection();
		PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
		stt.setString(1, User.txtname.getText());
		stt.setString(2, User.txtmail.getText());
		stt.setString(3, User.txtadd.getText());
		stt.setInt(4, Integer.parseInt(User.txtphone.getText()));
		stt.setInt(5, Integer.parseInt(User.txtid.getText()));
		stt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Success");

	}

	public void Delete() throws IOException, SQLException {
		String mod = "UPDATE borrowers SET status=? WHERE identification = ?";
		Connection connect = DatabaseLibConnection.getConnection();
		PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
		stt.setObject(1, User.cbstatus.getSelectedItem());
		stt.setInt(2, Integer.parseInt(User.txtid.getText()));
		stt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Success");
	}

	public void Reset() throws IOException {
		ArrayList<JTextField> resetList = new ArrayList<JTextField>();
		resetList.add(User.txtid);
		resetList.add(User.txtname);
		resetList.add(User.txtmail);
		resetList.add(User.txtadd);
		resetList.add(User.txtphone);
		for (JTextField item : resetList) {
			item.setText("");
			item.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		User.cbstatus.setSelectedIndex(0);
	}
}
