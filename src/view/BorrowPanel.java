package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.api.mysqla.result.Resultset;

import controller.ButtonController;
import controller.DatabaseLibConnection;
import model.BorrowModel;

public class BorrowPanel{
	private JPanel user;
	private JLabel lblUserId;
	private JComboBox cbBorrower;
	private JTextField txtUserId;
	private JTable borrowerTable;
	private DefaultTableModel borrowerModel;
	private JLabel lblDisplayUserId;
	private JTextField txtDisplayUserId;
	private JLabel lblUserName;
	private JTextField txtUserName;
	private JLabel lblBorrowedBooks;
	private JTextField txtBorrowedBooks;
	private JLabel lblOverdueBooks;
	private JTextField txtOverdueBooks;
	private JLabel lblLimit;
	private JTextField txtLimit;
	private JTable table;
	private DefaultTableModel model;
	private JPanel book;
	private JLabel lblBookId;
	private JComboBox cb;
	private JLabel lblSearchBy;
	private JTextField txtBookId;
	private JButton btnAdd;
	private JButton btnConfirm;
	private JTextArea txtArea;
	private JButton btnCancel;
	private BorrowPanel borrow;
	private BorrowModel borrowModel = new BorrowModel();
	private ButtonController bc = new ButtonController();
	
	public JTextField getTxtDisplayUserId() {
		return txtDisplayUserId;
	}

	public JTable getBorrowerTable() {
		return borrowerTable;
	}

	public DefaultTableModel getBorrowerModel() {
		return borrowerModel;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTextField getTxtUserId() {
		return txtUserId;
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public JTextField getTxtBorrowedBooks() {
		return txtBorrowedBooks;
	}

	public JTextField getTxtOverdueBooks() {
		return txtOverdueBooks;
	}

	public JTextField getTxtBookId() {
		return txtBookId;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JTextArea getTxtArea() {
		return txtArea;
	}

	public JComboBox getCb() {
		return cb;
	}

	public BorrowPanel getBorrow() {
		return borrow;
	}

	public void setBorrow(BorrowPanel borrow) {
		this.borrow = borrow;
	}
	
	public JTextField getTxtLimit() {
		return txtLimit;
	}

	//Borrower panel in main borrow panel
	public JPanel userPanel() {
		user = new JPanel();
		user.setBackground(Color.WHITE);
		user.setBounds(5, 50, 245, 500);
		user.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		lblUserId = new JLabel("Borrower: ");
		lblUserId.setBounds(10, 10, 100, 30);
		String[] comboList = {"Name", "ID"};
		cbBorrower = new JComboBox(comboList);
		cbBorrower.setBounds(110, 10, 130, 30);
		txtUserId = new JTextField();
		txtUserId.setBounds(10, 50, 230, 30);
		lblUserName = new JLabel("Name: ");
		lblUserName.setBounds(10, 290, 50, 30);
		txtUserName = new JTextField();
		txtUserName.setBounds(60, 290, 180, 30);
		txtUserName.setEditable(false);
		lblDisplayUserId = new JLabel("ID: ");
		lblDisplayUserId.setBounds(10, 330, 50, 30);
		txtDisplayUserId = new JTextField();
		txtDisplayUserId.setBounds(60, 330, 180, 30);
		txtDisplayUserId.setEditable(false);
		lblBorrowedBooks = new JLabel("Borrowed books: ");
		lblBorrowedBooks.setBounds(10, 370, 100, 30);
		txtBorrowedBooks = new JTextField();
		txtBorrowedBooks.setBounds(110, 370, 50, 30);
		txtBorrowedBooks.setEditable(false);
		lblOverdueBooks = new JLabel("Overdue books: ");
		lblOverdueBooks.setBounds(10, 410, 100, 30);
		txtOverdueBooks = new JTextField();
		txtOverdueBooks.setBounds(110, 410, 50, 30);
		txtOverdueBooks.setEditable(false);
		lblLimit = new JLabel("Limit:");
		lblLimit.setBounds(70, 450, 100, 30);
		txtLimit = new JTextField();
		txtLimit.setBounds(110, 450, 50, 30);
		txtLimit.setEditable(false);

		user.add(lblUserId);
		user.add(cbBorrower);
		user.add(txtUserId);
		user.add(borrowerTable());
		user.add(lblDisplayUserId);
		user.add(txtDisplayUserId);
		user.add(lblUserName);
		user.add(txtUserName);
		user.add(lblBorrowedBooks);
		user.add(txtBorrowedBooks);
		user.add(lblOverdueBooks);
		user.add(txtOverdueBooks);
		user.add(lblLimit);
		user.add(txtLimit);
		
		txtUserId.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				borrowModel.checkBorrowerInfo(borrow);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				borrowModel.checkBorrowerInfo(borrow);
				
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
					borrowModel.setSelectBorrowerBy("identification");
				}else if(choice == 0){
					borrowModel.setSelectBorrowerBy("name");
				}
			}
		});
		cbBorrower.setSelectedIndex(0);
		user.setLayout(null);
		return user;
	}

	//Borrower table
	public JScrollPane borrowerTable() {
		borrowerModel = new DefaultTableModel();
		borrowerTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(borrowerTable);
		scrollPane.setBounds(2, 90, 243, 200);
		scrollPane.getViewport().setBackground(Color.WHITE);
		borrowerModel.addColumn("Name");
		borrowerModel.addColumn("ID");
		borrowerTable.setModel(borrowerModel);
		borrowerTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				borrowModel.setFields(borrow);
				
			}
		});
		return scrollPane;
	}
	
	//Book table in main borrow panel
	public JScrollPane booksTable() {
		model = new DefaultTableModel();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(460, 50, 700, 500);
		scrollPane.getViewport().setBackground(Color.WHITE);
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Author");
		model.addColumn("Publisher");
		model.addColumn("Price");
		model.addColumn("Status");
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(90);
		table.getColumnModel().getColumn(3).setMinWidth(90);
		table.getColumnModel().getColumn(4).setMinWidth(70);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
//		ListSelectionModel listModel = table.getSelectionModel();
//		listModel.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				// TODO Auto-generated method stub
//				int rowIndex = table.getSelectedRow();
//				
//			}
//		});
		return scrollPane;
	}

	//Book panel in borrow main panel
	public JPanel bookPanel() {
		book = new JPanel();
		book.setBackground(Color.WHITE);
		book.setBounds(255, 50, 200, 500);
		book.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		lblBookId = new JLabel("Books: ");
		lblBookId.setBounds(10, 10, 50, 30);
		String[] comboList = {"ID", "Name", "Author", "Publisher"};
		cb = new JComboBox(comboList);
		cb.setBounds(60, 10, 130, 30);
		txtBookId = new JTextField();
		txtBookId.setBounds(10, 50, 180, 30);
		btnAdd = new JButton("Add");
		btnAdd.setBounds(35, 90, 135, 30);;
		btnAdd.setBackground(new Color(50, 166, 254));
		btnAdd.setForeground(Color.WHITE);
		txtArea = new JTextArea();
		txtArea.setBounds(10, 130, 180, 150);
		txtArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(35, 300, 135, 30);
		btnConfirm.setBackground(new Color(50, 166, 254));
		btnConfirm.setForeground(Color.WHITE);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(35, 350, 135, 30);
		btnCancel.setBackground(new Color(239, 3, 3));
		btnCancel.setForeground(Color.WHITE);

		book.add(lblBookId);
		book.add(cb);
		book.add(txtBookId);
		book.add(btnAdd);
		book.add(txtArea);
		book.add(btnConfirm);
		book.add(btnCancel);
		book.setLayout(null);
		
		btnAdd.setEnabled(false);
		txtArea.setEditable(false);
		txtBookId.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setEnabled(true);
				if(!txtBookId.getText().equals("")){
					borrowModel.checkBookInfo(borrow);
				} else {
					btnAdd.setEnabled(false);
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
				btnAdd.setEnabled(true);
				if(!txtBookId.getText().equals("")){
					borrowModel.checkBookInfo(borrow);
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
		
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = cb.getSelectedIndex();
				if(choice == 0){
					borrowModel.setSelectBy("books.id Like ");
				}else if(choice == 1){
					borrowModel.setSelectBy("books.name Like ");
				} else if(choice == 2){
					borrowModel.setSelectBy("authors.name Like ");
				} else if(choice == 3){
					borrowModel.setSelectBy("publishers.name Like ");
				}
			}
		});
		cb.setSelectedIndex(0);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowModel.addBooksToBorrow(borrow,table);
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowModel.confirmBooksToBorrow(borrow);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				borrowModel.cancelOrder(borrow);
			}
		});
		return book;
	}

	// Add user panel, book panel and book table into the main borrow panel
	public JPanel borrowPanel() {
		JPanel borrowBook = new JPanel();
		borrowBook.setBackground(Color.WHITE);
		borrowBook.add(borrow.booksTable());
		borrowBook.add(borrow.userPanel());
		borrowBook.add(borrow.bookPanel());
		borrowBook.add(bc.btnBack());
		borrowBook.add(bc.btnExit());
		borrowBook.setLayout(null);
		return borrowBook;
	}
}
