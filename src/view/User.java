package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

import controller.BorrowerController;
import model.BorrowerModel;

public class User extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAdd;
	private JButton btnMod;
	private JButton btnDel;
	private JButton btnReset;
	private JButton btnIdCheck;
	private JLabel lblid;
	private JLabel lblname;
	private JLabel lblmail;
	private JLabel lbladd;
	private JLabel lblphone;
	private JLabel lblstatus;
	public static JTextField txtid;
	public static JTextField txtname;
	public static JTextField txtmail;
	public static JTextField txtadd;
	public static JTextField txtphone;
	public static JComboBox<String> cbstatus;
	public BorrowerController validate = new BorrowerController();
	public BorrowerModel model = new BorrowerModel();

	public User() {
		try {
			ButtonImportant btnImp = new ButtonImportant();
			this.setBounds(0, 0, 1200, 700);
			this.setBackground(Color.WHITE);
			this.add(panelForm());
			this.add(panelModel());
			this.add(panelTable());
			this.add(btnImp.btnExit());
			this.add(btnImp.btnBack());
			this.setLayout(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel panelModel() throws IOException {
		JPanel panelModel = new JPanel();
		panelModel.setBounds(10, 10, 550, 60);
		panelModel.setBackground(Color.WHITE);
		panelModel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
		btnAdd = new JButton("Add");
		btnMod = new JButton("Modify");
		btnDel = new JButton("Delete");
		btnReset = new JButton("Reset");
		ArrayList<JButton> btnListModel = new ArrayList<JButton>();
		btnListModel.add(btnAdd);
		btnListModel.add(btnMod);
		btnListModel.add(btnDel);
		btnListModel.add(btnReset);
		Font btnModelFont = new Font("Serif", Font.PLAIN, 20);
		for (int i = 0; i < btnListModel.size(); i++) {
			btnListModel.get(i).setBackground(new Color(50, 166, 254));
			btnListModel.get(i).setFont(btnModelFont);
			btnListModel.get(i).setForeground(Color.WHITE);
			btnListModel.get(i).setPreferredSize(new Dimension(100, 40));
			panelModel.add(btnListModel.get(i));
		}
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validate.Validate();
					model.Insert();
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnMod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validate.Validate();
					model.Modify();
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validate.Validate();
					model.Delete();
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.Reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return panelModel;
	}

	public JPanel panelForm() {
		JPanel panelForm = new JPanel();
		panelForm.setBounds(10, 80, 275, 500);
		panelForm.setBackground(Color.WHITE);
		panelForm.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)),
				BorderFactory.createLineBorder(Color.BLACK)));

		lblid = new JLabel("Identification");
		lblid.setBounds(10, 0, 100, 30);
		txtid = new JTextField();
		txtid.setBounds(10, 30, 250, 30);

		btnIdCheck = new JButton("Verify");
		btnIdCheck.setBounds(80, 70, 100, 20);
		btnIdCheck.setBackground(new Color(1, 215, 58));
		btnIdCheck.setForeground(Color.WHITE);
		btnIdCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.CheckID();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		lblname = new JLabel("Borrower's name");
		lblname.setBounds(10, 100, 100, 30);
		txtname = new JTextField();
		txtname.setBounds(10, 130, 250, 30);

		lblmail = new JLabel("Email");
		lblmail.setBounds(10, 180, 100, 30);
		txtmail = new JTextField();
		txtmail.setBounds(10, 210, 250, 30);

		lbladd = new JLabel("Address");
		lbladd.setBounds(10, 260, 100, 30);
		txtadd = new JTextField();
		txtadd.setBounds(10, 290, 250, 30);

		lblphone = new JLabel("Phone");
		lblphone.setBounds(10, 340, 100, 30);
		txtphone = new JTextField();
		txtphone.setBounds(10, 370, 250, 30);

		lblstatus = new JLabel("Status");
		lblstatus.setBounds(10, 420, 100, 30);
		String status[] = { "Select status", "0", "1" };
		cbstatus = new JComboBox<String>(status);
		cbstatus.setBounds(10, 450, 250, 30);

		panelForm.add(lblname);
		panelForm.add(txtname);
		panelForm.add(lblid);
		panelForm.add(txtid);
		panelForm.add(lblmail);
		panelForm.add(txtmail);
		panelForm.add(lbladd);
		panelForm.add(txtadd);
		panelForm.add(lblphone);
		panelForm.add(txtphone);
		panelForm.add(lblstatus);
		panelForm.add(cbstatus);
		panelForm.add(btnIdCheck);
		panelForm.setLayout(null);
		return panelForm;
	}

	public JPanel panelTable() {
		JPanel panelTable = new JPanel();
		panelTable.setBounds(300, 80, 875, 500);
		panelTable.setBackground(Color.WHITE);
		panelTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)),
				BorderFactory.createLineBorder(Color.BLACK)));

		String data[][] = { { "101", "Amit", "670000", "", "", "", "", "", "" },
				{ "102", "Jai", "780000", "", "", "", "", "", "" },
				{ "101", "Sachin", "700000", "", "", "", "", "", "" } };
		String userColumn[] = { "Identification", "Name", "Email", "Address", "Phone", "Borrowed_books",
				"Overdue_books", "Overdue_limit", "Status" };
		JTable userTable = new JTable(data, userColumn);

		JScrollPane scrollTable = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(10, 10, 855, 480);

		panelTable.add(scrollTable);
		panelTable.setLayout(null);
		return panelTable;
	}

	public static void main(String[] args) throws IOException {
		LibraryFrame frmLib = new LibraryFrame();
		User panelUser = new User();
		frmLib.add(panelUser);
		frmLib.setLayout(null);
		frmLib.setVisible(true);
	}
}
