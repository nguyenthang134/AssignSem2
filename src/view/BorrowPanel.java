package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import controller.DatabaseLibConnection;
import model.BorrowModel;

public class BorrowPanel{
	private JPanel user;
	private JLabel lblUserId;
	private JTextField txtUserId;
	private JButton btnTest;
	private JLabel lblUserName;
	private JTextField txtUserName;
	private JLabel lblBorrowedBooks;
	private JTextField txtBorrowedBooks;
	private JLabel lblOverdueBooks;
	private JTextField txtOverdueBooks;
	private DefaultTableModel model;
	private JTable table;
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
	
	//User panel in main borrow panel
	public JPanel userPanel() {
		user = new JPanel();
		user.setBackground(Color.WHITE);
		user.setBounds(20, 50, 200, 420);
		user.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		lblUserId = new JLabel("Borrower ID: ");
		lblUserId.setBounds(10, 10, 100, 30);
		txtUserId = new JTextField();
		txtUserId.setBounds(10, 50, 180, 30);
		btnTest = new JButton("Check infomation");
		btnTest.setBounds(35, 90, 135, 30);
		btnTest.setBackground(new Color(50, 166, 254));
		btnTest.setForeground(Color.WHITE);
		lblUserName = new JLabel("Borrower name: ");
		lblUserName.setBounds(10, 130, 100, 30);
		txtUserName = new JTextField();
		txtUserName.setBounds(10, 170, 180, 30);
		txtUserName.setEditable(false);
		lblBorrowedBooks = new JLabel("Borrowed books: ");
		lblBorrowedBooks.setBounds(10, 210, 100, 30);
		txtBorrowedBooks = new JTextField();
		txtBorrowedBooks.setBounds(10, 250, 50, 30);
		txtBorrowedBooks.setEditable(false);
		lblOverdueBooks = new JLabel("Overdue books: ");
		lblOverdueBooks.setBounds(10, 290, 100, 30);
		txtOverdueBooks = new JTextField();
		txtOverdueBooks.setBounds(10, 330, 50, 30);
		txtOverdueBooks.setEditable(false);

		user.add(lblUserId);
		user.add(txtUserId);
		user.add(btnTest);
		user.add(lblUserName);
		user.add(txtUserName);
		user.add(lblBorrowedBooks);
		user.add(txtBorrowedBooks);
		user.add(lblOverdueBooks);
		user.add(txtOverdueBooks);

		btnTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowModel.checkBorrowerInfo(borrow);
			}
		});

		borrowModel.checkOverdueBooks();
		user.setLayout(null);
		return user;
	}

	//Book table in main borrow panel
	public JScrollPane booksTable() {
		model = new DefaultTableModel();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(460, 50, 700, 420);
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
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(3).setMinWidth(120);
		table.getColumnModel().getColumn(4).setMaxWidth(50);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
		ListSelectionModel listModel = table.getSelectionModel();
		listModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int rowIndex = table.getSelectedRow();
				
			}
		});
		return scrollPane;
	}

	//Book panel in borrow main panel
	public JPanel bookPanel() {
		book = new JPanel();
		book.setBackground(Color.WHITE);
		book.setBounds(240, 50, 200, 420);
		book.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		lblBookId = new JLabel("Books: ");
		lblBookId.setBounds(10, 10, 50, 30);
		String[] comboList = {"--------Choose-------","Book ID","Book name"};
		cb = new JComboBox(comboList);
		cb.setBounds(60, 10, 130, 30);
		txtBookId = new JTextField();
		txtBookId.setBounds(10, 50, 180, 30);
		txtBookId.setEditable(false);
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
				btnAdd.setEnabled(false);
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
				if(choice == 1){
					txtBookId.setEditable(true);
					borrowModel.setSelectBy("books.id Like ");
				}else if(choice == 2){
					txtBookId.setEditable(true);
					borrowModel.setSelectBy("books.name Like ");
				} else if(choice == 0){
					txtBookId.setEditable(false);
				}
			}
		});
		
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
		borrowBook.setLayout(null);
		return borrowBook;
	}
}
