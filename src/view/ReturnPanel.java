package view;

import java.awt.Color;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class ReturnPanel {
	private JPanel panel;
	private JLabel lblBorrowerId;
	private JLabel lblBookId;
	private JLabel lblFine;
	private JButton btnCheckInfo;
	private JButton btnReturn;
	private JButton btnLost;
	private JButton btnCancel;
	private JTextField txtBorrowerId;
	private JTextField txtBookId;
	private JTextField txtFine;
	
	public JPanel panel(){
		panel = new JPanel();
		panel.setBounds(20, 50, 200, 420);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		lblBorrowerId = new JLabel("Borrower ID: ");
		lblBorrowerId.setBounds(10, 10, 100, 30);
		txtBorrowerId = new JTextField();
		txtBorrowerId.setBounds(10, 50, 180, 30);
		btnCheckInfo = new JButton("Check infomation");
		btnCheckInfo.setBounds(35, 90, 135, 30);
		btnCheckInfo.setBackground(new Color(50, 166, 254));
		btnCheckInfo.setForeground(Color.WHITE);
		lblBookId = new JLabel("Book ID: ");
		lblBookId.setBounds(10, 130, 100, 30);
		txtBookId = new JTextField();
		txtBookId.setBounds(10, 170, 180, 30);
		btnReturn = new JButton("Return");
		btnReturn.setBounds(35, 210, 135, 30);
		btnReturn.setBackground(new Color(50, 166, 254));
		btnReturn.setForeground(Color.WHITE);
		btnLost = new JButton("Lost");
		btnLost.setBounds(35, 250, 135, 30);
		btnLost.setBackground(new Color(50, 166, 254));
		btnLost.setForeground(Color.WHITE);
		lblFine = new JLabel("FINE: ");
		lblFine.setBounds(10, 290, 100, 30);
		txtFine = new JTextField();
		txtFine.setBounds(10, 330, 180, 30);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(35, 370, 135, 30);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(239, 3, 3));
		
		panel.add(lblBorrowerId);
		panel.add(txtBorrowerId);
		panel.add(btnCheckInfo);
		panel.add(lblBookId);
		panel.add(txtBookId);
		panel.add(btnReturn);
		panel.add(btnLost);
		panel.add(lblFine);
		panel.add(txtFine);
		panel.add(btnCancel);
		panel.setLayout(null);
		
		return panel;
	}
	
	public JScrollPane table(){
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(240, 50, 920, 420);
		scrollPane.getViewport().setBackground(Color.WHITE);
		model.addColumn("OrderID");
		model.addColumn("BookID");
		model.addColumn("BookName");
		model.addColumn("BorrowDate");
		model.addColumn("Status");
		table.setModel(model);
		
		return scrollPane;
	}
	
	public JPanel returnPanel(){
		ReturnPanel mainPanel = new ReturnPanel();
		JPanel returnBook = new JPanel();
		returnBook.setBackground(Color.WHITE);
		returnBook.add(mainPanel.panel());
		returnBook.add(mainPanel.table());
		returnBook.setLayout(null);
		return returnBook;
	}
}
