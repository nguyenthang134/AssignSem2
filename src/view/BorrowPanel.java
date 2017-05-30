package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.api.mysqla.result.Resultset;

import controller.DatabaseLibConnection;

public class BorrowPanel {
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
				checkBorrowerInfo();

			}
		});

		user.setLayout(null);
		return user;
	}

	public void checkBorrowerInfo() {
		try {
			java.sql.Statement stm = DatabaseLibConnection.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("Select * from borrowers where identification = " + txtUserId.getText());
			while(rs.next()){
				//int id = rs.getInt(1);
				String name = rs.getString(2);
				int borrow = rs.getInt(6);
				int overdue = rs.getInt(7);
				
				//txtUserId.setText(String.valueOf(id));
				txtUserName.setText(name);
				txtBorrowedBooks.setText(String.valueOf(borrow));
				txtOverdueBooks.setText(String.valueOf(overdue));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public JScrollPane booksTable() {
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable();
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

		return scrollPane;
	}

	private JPanel book;
	private JLabel lblBookId;
	private JTextField txtBookId;
	private JButton btnCheckInfo;
	private JButton btnAdd;
	private JButton btnBorrow;
	private JTextArea txtArea;
	private JButton btnCancel;

	public JPanel bookPanel() {
		book = new JPanel();
		book.setBackground(Color.WHITE);
		book.setBounds(240, 50, 200, 420);
		book.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		lblBookId = new JLabel("Book ID: ");
		lblBookId.setBounds(10, 10, 100, 30);
		txtBookId = new JTextField();
		txtBookId.setBounds(10, 50, 180, 30);
		btnCheckInfo = new JButton("Check infomation");
		btnCheckInfo.setBounds(35, 90, 135, 30);
		btnCheckInfo.setBackground(new Color(50, 166, 254));
		btnCheckInfo.setForeground(Color.WHITE);
		btnAdd = new JButton("Add");
		btnAdd.setBounds(35, 130, 135, 30);
		btnAdd.setBackground(new Color(50, 166, 254));
		btnAdd.setForeground(Color.WHITE);
		txtArea = new JTextArea();
		txtArea.setBounds(10, 170, 180, 100);
		txtArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnBorrow = new JButton("Confirm");
		btnBorrow.setBounds(35, 280, 135, 30);
		btnBorrow.setBackground(new Color(50, 166, 254));
		btnBorrow.setForeground(Color.WHITE);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(35, 320, 135, 30);
		btnCancel.setBackground(new Color(239, 3, 3));
		btnCancel.setForeground(Color.WHITE);

		book.add(lblBookId);
		book.add(txtBookId);
		book.add(btnCheckInfo);
		book.add(btnAdd);
		book.add(txtArea);
		book.add(btnBorrow);
		book.add(btnCancel);

		book.setLayout(null);

		return book;
	}

	public JPanel borrowPanel() {
		BorrowPanel borrow = new BorrowPanel();
		JPanel borrowBook = new JPanel();
		borrowBook.setBackground(Color.WHITE);
		borrowBook.add(borrow.booksTable());
		borrowBook.add(borrow.userPanel());
		borrowBook.add(borrow.bookPanel());
		borrowBook.setLayout(null);
		return borrowBook;
	}

}
