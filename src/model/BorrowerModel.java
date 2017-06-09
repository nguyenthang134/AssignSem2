package model;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.PreparedStatement;

import controller.DatabaseLibConnection;
import view.PanelBorrowers;

public class BorrowerModel {
	
	public void CheckID() throws SQLException, IOException {
		if (PanelBorrowers.cbId.getEditor().getItem().toString().length() == 0) {
			JOptionPane.showMessageDialog(null, "Please input id which you want to verify");
		}else{
			String checkId = "SELECT * FROM borrowers WHERE identification = '" + Integer.parseInt(PanelBorrowers.cbId.getEditor().getItem().toString()) + "'";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(checkId);
			boolean idNotExist = true;
			while (rs.next()) {
				idNotExist = false;
				PanelBorrowers.txtname.setText(rs.getString("name"));
				PanelBorrowers.txtmail.setText(rs.getString("email"));
				PanelBorrowers.txtadd.setText(rs.getString("address"));
				PanelBorrowers.txtphone.setText(Integer.toString(rs.getInt("phone")));
				if (rs.getInt("status") == 1) {
					PanelBorrowers.cbstatus.setSelectedIndex(2);
				} else if (rs.getInt("status") == 0) {
					PanelBorrowers.cbstatus.setSelectedIndex(1);
				}
			}
			if (idNotExist) {
				JOptionPane.showMessageDialog(null, "This id is not exist");
			}
		}	
	}

	public void Insert() throws IOException, SQLException {
		if (PanelBorrowers.cbstatus.getSelectedIndex() == 0 || PanelBorrowers.cbstatus.getSelectedIndex() == 1) {
			JOptionPane.showMessageDialog(null, "Please change status to 1");
		} else {
			String checkId = "INSERT INTO borrowers (identification, name, email, address, phone, status) VALUES(?,?,?,?,?,?)";
			Connection connect = DatabaseLibConnection.getConnection();
			PreparedStatement stt = (PreparedStatement) connect.prepareStatement(checkId);
			stt.setInt(1, Integer.parseInt(PanelBorrowers.cbId.getEditor().getItem().toString()));
			stt.setString(2, PanelBorrowers.txtname.getText());
			stt.setString(3, PanelBorrowers.txtmail.getText());
			stt.setString(4, PanelBorrowers.txtadd.getText());
			stt.setInt(5, Integer.parseInt(PanelBorrowers.txtphone.getText()));
			stt.setObject(6, PanelBorrowers.cbstatus.getSelectedItem());
			stt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Success");
		}
	}

	public void Modify() throws IOException, SQLException {
		String mod = "UPDATE borrowers SET name=?, email=?, address=?, phone=? WHERE identification = ?";
		Connection connect = DatabaseLibConnection.getConnection();
		PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
		stt.setString(1, PanelBorrowers.txtname.getText());
		stt.setString(2, PanelBorrowers.txtmail.getText());
		stt.setString(3, PanelBorrowers.txtadd.getText());
		stt.setInt(4, Integer.parseInt(PanelBorrowers.txtphone.getText()));
		stt.setInt(5, Integer.parseInt(PanelBorrowers.cbId.getEditor().getItem().toString()));
		stt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Success");

	}

	public void Delete() throws IOException, SQLException {
		String mod = "UPDATE borrowers SET status=? WHERE identification = ?";
		Connection connect = DatabaseLibConnection.getConnection();
		PreparedStatement stt = (PreparedStatement) connect.prepareStatement(mod);
		stt.setObject(1, PanelBorrowers.cbstatus.getSelectedItem());
		stt.setInt(2, Integer.parseInt(PanelBorrowers.cbId.getEditor().getItem().toString()));
		stt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Success");
	}

	public void Reset() throws IOException {
		ArrayList<JTextField> resetList = new ArrayList<JTextField>();
//		resetList.add(PanelBorrowers.txtId);
		resetList.add(PanelBorrowers.txtname);
		resetList.add(PanelBorrowers.txtmail);
		resetList.add(PanelBorrowers.txtadd);
		resetList.add(PanelBorrowers.txtphone);
		for (JTextField item : resetList) {
			item.setText("");
			item.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		PanelBorrowers.cbId.setSelectedIndex(-1);;
		PanelBorrowers.cbstatus.setSelectedIndex(0);
	}

	public void Show() {
//		ArrayList<String> borrowersList = new ArrayList<String>();
		try{
			String show = "SELECT * FROM borrowers";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(show);
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("identification"));
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = Integer.toString(rs.getInt("phone"));
				String borrowed_books = Integer.toString(rs.getInt("borrowed_books"));
				String overdue_books = Integer.toString(rs.getInt("overdue_books"));
				String overdue_limit = Double.toString(rs.getDouble("overdue_limit"));
				String status = Integer.toString(rs.getInt("status"));
				String[] values = { id, name, email, address, phone, borrowed_books, overdue_books, overdue_limit, status };
//				for(int i = 0; i<borrowersList.size();i++){
//					borrowersList.add(borrowersList.get(i));
//				}
				PanelBorrowers.tableModel.addRow(values);
		}
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}
//		return borrowersList;
	}
	
	public DefaultComboBoxModel<String> getList(String s) {
		DefaultComboBoxModel<String> cbmodel = new DefaultComboBoxModel<String>();
		try {
			String query = "SELECT identification FROM borrowers WHERE identification LIKE '" + Integer.parseInt(s) + "%';";
			Connection connect = DatabaseLibConnection.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery(query);
			while (rs.next()) {
				cbmodel.addElement(Integer.toString(rs.getInt("identification")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(BorrowerModel.class.getName()).log(Level.SEVERE, null, ex);
		}
		return cbmodel;
	}
}
