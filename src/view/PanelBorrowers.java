package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.BorrowerController;
import controller.ButtonController;
import entity.Borrowers;
import model.BorrowerModel;

public class PanelBorrowers extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblid;
	private JLabel lblname;
	private JLabel lblmail;
	private JLabel lbladd;
	private JLabel lblphone;

	private JButton btnSave;
	private JButton btnReset;
	private JButton btnDel;
	private JButton btnShow;

	public JTextField txtId;
	public JTextField txtname;
	public JTextField txtmail;
	public JTextField txtadd;
	public JTextField txtphone;

	private DefaultTableModel tableModel;

	private BorrowerController validate;
	private BorrowerModel model;
	private ButtonController bc;
	private int action = 1; // 1-> create, 2-> edit.

	public PanelBorrowers() {
		this.validate = new BorrowerController();
		this.bc = new ButtonController();
		this.model = new BorrowerModel();
		this.setBounds(0, 0, 1200, 700);
		this.setBackground(Color.WHITE);
		this.add(panelForm());
		this.add(panelTable());
		this.add(panelSearch());
		this.add(bc.btnBack());
		this.add(bc.btnExit());
		this.setLayout(null);
	}

	public JPanel panelForm() {
		JPanel panelForm = new JPanel();
		panelForm.setBounds(10, 40, 275, 530);
		panelForm.setBackground(Color.WHITE);
		panelForm.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)),
				BorderFactory.createLineBorder(Color.BLACK)));

		lblid = new JLabel("Identification");
		lblid.setBounds(10, 0, 100, 30);
		txtId = new JTextField();
		txtId.setBounds(10, 30, 250, 30);

		lblname = new JLabel("Borrower's name");
		lblname.setBounds(10, 80, 100, 30);
		txtname = new JTextField();
		txtname.setBounds(10, 110, 250, 30);

		lblmail = new JLabel("Email");
		lblmail.setBounds(10, 160, 100, 30);
		txtmail = new JTextField();
		txtmail.setBounds(10, 190, 250, 30);

		lbladd = new JLabel("Address");
		lbladd.setBounds(10, 240, 100, 30);
		txtadd = new JTextField();
		txtadd.setBounds(10, 270, 250, 30);

		lblphone = new JLabel("Phone");
		lblphone.setBounds(10, 320, 100, 30);
		txtphone = new JTextField();
		txtphone.setBounds(10, 350, 250, 30);

		btnSave = new JButton("Save");
		btnSave.setBounds(15, 410, 110, 30);

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					// Cần validate đối tượng trước khi lưu.
					Borrowers borrower = new Borrowers();
					borrower.setIdentification(Integer.parseInt(txtId.getText()));
					borrower.setBorrowers_name(txtname.getText());
					borrower.setBorrowers_mail(txtmail.getText());
					borrower.setBorrowers_address(txtadd.getText());
					borrower.setBorrowers_phone(Integer.parseInt(txtphone.getText()));
					
					validate.Validate();
					
					if (action == 1) {
						if (model.insert(borrower)) {
							JOptionPane.showMessageDialog(null, "Action success!");
						} else {
							JOptionPane.showMessageDialog(null, "Action fails!", "Error Message",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (action == 2) {
						if (model.modify(borrower)) {
							JOptionPane.showMessageDialog(null, "Action success!");
						} else {
							JOptionPane.showMessageDialog(null, "Action fails!", "Error Message",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						System.err.println("Invalid action.");
						JOptionPane.showMessageDialog(null, "Invalid action!", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					}
					ArrayList<Borrowers> borrowersList = model.getList();
					showBorrowers(borrowersList);
					resetForm();
				} catch (Exception e2) {
					e2.printStackTrace();
//					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnDel = new JButton("Delete");
		btnDel.setBounds(145, 410, 110, 30);
		btnDel.setEnabled(false);

		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Confirm lại người dùng khi delete.
				int conf = JOptionPane.showConfirmDialog(null, "Are you sure wanna delete this borrower?");
				if (JOptionPane.YES_OPTION == conf) {
					int id = Integer.parseInt(txtId.getText());
					Borrowers borrower = model.getById(id);
					if (borrower == null) {
						JOptionPane.showMessageDialog(null, "Not found or has been deleted!", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					} else if (borrower != null && model.delete(id)) {
						JOptionPane.showMessageDialog(null, "Action success!");
					} else {
						JOptionPane.showMessageDialog(null, "Action fails!", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				ArrayList<Borrowers> borrowersList = model.getList();
				showBorrowers(borrowersList);
				resetForm();
			}
		});

		btnShow = new JButton("Show");
		btnShow.setBounds(15, 460, 110, 30);

		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Borrowers> borrowersList = model.getList();
				showBorrowers(borrowersList);
			}
		});

		btnReset = new JButton("Reset");
		btnReset.setBounds(145, 460, 110, 30);

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetForm();
				btnDel.setEnabled(false);
			}
		});

		ArrayList<JButton> btnList = new ArrayList<JButton>();
		btnList.add(btnSave);
		btnList.add(btnDel);
		btnList.add(btnShow);
		btnList.add(btnReset);

		for (JButton item : btnList) {
			item.setBackground(new Color(50, 166, 254));
			item.setForeground(Color.WHITE);
		}

		panelForm.add(lblname);
		panelForm.add(txtname);
		panelForm.add(lblid);
		panelForm.add(txtId);
		panelForm.add(lblmail);
		panelForm.add(txtmail);
		panelForm.add(lbladd);
		panelForm.add(txtadd);
		panelForm.add(lblphone);
		panelForm.add(txtphone);

		panelForm.add(btnSave);
		panelForm.add(btnDel);
		panelForm.add(btnShow);
		panelForm.add(btnReset);

		panelForm.setLayout(null);
		return panelForm;
	}

	public JPanel panelTable() {
		JPanel panelTable = new JPanel();
		JTable borrowerTable = new JTable();
		tableModel = new DefaultTableModel();
		panelTable.setBounds(300, 40, 875, 530);
		panelTable.setBackground(Color.WHITE);
		panelTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)),
				BorderFactory.createLineBorder(Color.BLACK)));

		String[] userColumn = { "Orders", "Identification", "Name", "Email", "Address", "Phone", "Borrowed_books",
				"Overdue_books", "Overdue_limit" };

		for (int i = 0; i < userColumn.length; i++) {
			tableModel.addColumn(userColumn[i]);
		}
		borrowerTable.setModel(tableModel);
		JScrollPane scrollTable = new JScrollPane(borrowerTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(10, 10, 855, 510);
		panelTable.add(scrollTable);
		panelTable.setLayout(null);

		borrowerTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				try {
					action = 2;
					int id = Integer.parseInt(String.valueOf(tableModel.getValueAt(borrowerTable.getSelectedRow(), 1)));
					Borrowers borrower = model.getById(id);
					if (borrower == null) {
						JOptionPane.showMessageDialog(null, "Not found or has been deleted!", "Error Message",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					txtId.setText(String.valueOf(borrower.getIdentification()));
					txtname.setText(borrower.getBorrowers_name());
					txtmail.setText(borrower.getBorrowers_mail());
					txtadd.setText(borrower.getBorrowers_address());
					txtphone.setText(String.valueOf(borrower.getBorrowers_phone()));
					btnDel.setEnabled(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		return panelTable;
	}

	public JPanel panelSearch() {
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(0, 0, 600, 40);
		panelSearch.setBackground(Color.WHITE);

		BufferedImage iconSearch = null;
		try {
			iconSearch = ImageIO.read(new File("../AssignSem2/src/assets/Search-icon1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton btnIconSearch = new JButton(new ImageIcon(iconSearch));
		btnIconSearch.setBounds(10, 3, 30, 30);
		btnIconSearch.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnIconSearch.setContentAreaFilled(false);
		btnIconSearch.setFocusPainted(false);

		JTextField txtSearch = new JTextField();
		txtSearch.setBounds(186, 5, 275, 30);
		txtSearch.setEditable(false);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				process();
			}

			public void removeUpdate(DocumentEvent e) {
				process();
			}

			public void insertUpdate(DocumentEvent e) {
				process();
			}

			public void process() {
				try {
					String parsedStr = txtSearch.getText();
					if (parsedStr.length() > 0) {
						ArrayList<Borrowers> listResult = model.searchBy(parsedStr);
						showBorrowers(listResult);
					}
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}
			}
		});

		JComboBox<String> cbSearch = new JComboBox<String>();
		cbSearch.setBounds(40, 5, 145, 29);
		cbSearch.addItem("----------Select----------");
		cbSearch.addItem("Search by ID");
		cbSearch.addItem("Search by Name");
		cbSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int slt = cbSearch.getSelectedIndex();
				switch (slt) {
				case 1:
					txtSearch.setEditable(true);
					model.setSearchBy("identification");
					break;
				case 2:
					txtSearch.setEditable(true);
					model.setSearchBy("name");
					break;
				default:
					txtSearch.setEditable(false);
					break;
				}
			}
		});

		panelSearch.add(btnIconSearch);
		panelSearch.add(txtSearch);
		panelSearch.add(cbSearch);
		panelSearch.setLayout(null);
		return panelSearch;
	}

	public void resetForm() {
		ArrayList<JTextField> resetList = new ArrayList<JTextField>();
		resetList.add(txtId);
		resetList.add(txtname);
		resetList.add(txtmail);
		resetList.add(txtadd);
		resetList.add(txtphone);
		for (JTextField item : resetList) {
			item.setText("");
			item.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
	}

	public void showBorrowers(ArrayList<Borrowers> borrowersList) {
		tableModel.setRowCount(0);
		int i = 1;
		for (Borrowers item : borrowersList) {
			Object[] row = { i++, item.getIdentification(), item.getBorrowers_name(), item.getBorrowers_mail(),
					item.getBorrowers_address(), item.getBorrowers_phone(), item.getBorrowed_books(),
					item.getOverdue_books(), item.getOverdue_limit() };
			tableModel.addRow(row);
		}
	}

	public static void main(String[] args) {
		// LibraryFrame frmLib = new LibraryFrame();
		JFrame frm = new JFrame("Test");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(1200, 700);
		PanelBorrowers pb = new PanelBorrowers();
		frm.add(pb);
		frm.setLayout(null);
		frm.setVisible(true);
	}
}
