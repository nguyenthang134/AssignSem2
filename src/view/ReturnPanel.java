package view;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import model.ReturnModel;

public class ReturnPanel {
	private JPanel panel;
	private JLabel lblBorrowerId;
	private JComboBox cbBorrower;
	private JLabel lblBookId;
	private JLabel lblFine;
	private JComboBox cbBook;
	private JButton btnReturn;
	private JButton btnLost;
	private JButton btnCancel;
	private JLabel lblSearchBy;
	private JTextField txtBorrowerId;
	private JTextField txtBookId;
	private JTextField txtFine;
	private DefaultTableModel model;
	private ReturnPanel returnPanel;
	private ReturnModel returnModel = new ReturnModel();
	
	public JTextField getTxtBorrowerId() {
		return txtBorrowerId;
	}

	public JTextField getTxtBookId() {
		return txtBookId;
	}

	public JTextField getTxtFine() {
		return txtFine;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}

	public ReturnPanel getReturnPanel() {
		return returnPanel;
	}

	public void setReturnPanel(ReturnPanel returnPanel) {
		this.returnPanel = returnPanel;
	}

	//Orders information table
	public JScrollPane table(){
		model = new DefaultTableModel();
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(240, 50, 920, 420);
		scrollPane.getViewport().setBackground(Color.WHITE);
		model.addColumn("Order_ID");
		model.addColumn("Borrower_ID");
		model.addColumn("Borrower_name");		
		model.addColumn("Book_ID");
		model.addColumn("Book_name");
		model.addColumn("Borrow_date");
		model.addColumn("Status");
		table.setModel(model);
		
		return scrollPane;
	}
		
	//Check borrower's order info
	public JPanel panel(){
		panel = new JPanel();
		panel.setBounds(20, 50, 200, 420);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		lblBorrowerId = new JLabel("Borrower: ");
		lblBorrowerId.setBounds(10, 10, 70, 30);
		String[] comboListBorrower = {"------Choose-----","Borrower ID","Borrower name"};
		cbBorrower = new JComboBox(comboListBorrower);
		cbBorrower.setBounds(80, 10, 115, 30);
		txtBorrowerId = new JTextField();
		txtBorrowerId.setBounds(10, 70, 180, 30);
		txtBorrowerId.setEditable(false);
		lblBookId = new JLabel("Book ID: ");
		lblBookId.setBounds(10, 130, 70, 30);
		String[] comboList = {"------Choose-----","Book ID","Book name"};
		cbBook = new JComboBox(comboList);
		cbBook.setBounds(80, 130, 115, 30);
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
		panel.add(cbBorrower);
		panel.add(txtBorrowerId);
		panel.add(lblBookId);
		panel.add(cbBook);
		panel.add(txtBookId);
		panel.add(btnReturn);
		panel.add(btnLost);
		panel.add(lblFine);
		panel.add(txtFine);
		panel.add(btnCancel);
		panel.setLayout(null);
		
		txtBorrowerId.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(!txtBorrowerId.getText().equals("")){
					returnModel.checkBorrowerInfo(returnPanel);
				} else {
					int rowCount = model.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    model.removeRow(i);
					}
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(!txtBorrowerId.getText().equals("")){
					returnModel.checkBorrowerInfo(returnPanel);
				} else {
					int rowCount = model.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    model.removeRow(i);
					}
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		cbBorrower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = cbBorrower.getSelectedIndex();
				if(choice == 1){
					txtBorrowerId.setEditable(true);
					returnModel.setSelectBy("borrowers.identification Like ");
				}else if(choice == 2){
					txtBorrowerId.setEditable(true);
					returnModel.setSelectBy("borrowers.name Like ");
				}else if(choice == 0){
					txtBorrowerId.setEditable(false);
				}
			}
		});
		
		cbBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = cbBook.getSelectedIndex();
				if(choice == 1){
					txtBookId.setEditable(true);
//					returnModel.setSelectBy("borrowers.identification Like ");
				}else if(choice == 2){
					txtBookId.setEditable(true);
//					returnModel.setSelectBy("borrowers.name Like ");
				}else if(choice == 0){
					txtBookId.setEditable(false);
				}
			}
		});
		
		return panel;
	}
	
	//Return the panel so you can add to class BorrowReturnMainPanel
	public JPanel returnPanel(){
		JPanel returnBook = new JPanel();
		returnBook.setBackground(Color.WHITE);
		returnBook.add(returnPanel.panel());
		returnBook.add(returnPanel.table());
		returnBook.setLayout(null);
		return returnBook;
	}
}
