package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import controller.BorrowerController;
import controller.ButtonController;
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
	private JLabel lblstatus;

	public JButton btnAdd;
	public JButton btnMod;
	public JButton btnDel;
	public JButton btnReset;
	public JButton btnIdCheck;

	public static JComboBox<String> cbId;

//	public static JTextField txtId;
	public static JTextField txtname;
	public static JTextField txtmail;
	public static JTextField txtadd;
	public static JTextField txtphone;
	public static JComboBox<String> cbstatus;
	public static DefaultTableModel tableModel;
	
	public BorrowerController validate;
	public BorrowerModel model;
	public ButtonController bc;

	public PanelBorrowers() {
		this.validate = new BorrowerController();
		this.model = new BorrowerModel();
		this.bc = new ButtonController();
		
		this.setBounds(0, 0, 1200, 700);
		this.setBackground(Color.WHITE);
		this.add(panelForm());
		this.add(panelModel());
		this.add(panelTable());
		this.add(bc.btnBackBorrowers());
		this.add(bc.btnExit());
		this.setLayout(null);
	}

	public JPanel panelModel() {
		JPanel panelModel = new JPanel();
		panelModel.setBounds(10, 10, 550, 60);
		panelModel.setBackground(Color.WHITE);
		panelModel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
		
		btnAdd = new JButton("Add");
		btnMod = new JButton("Modify");
		btnDel = new JButton("Delete");
		btnReset = new JButton("Reset");

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
//		txtId = new JTextField();
//		txtId.setBounds(10, 30, 250, 30);
		
		cbId = new JComboBox<String>();
		cbId.setBounds(10, 30, 250, 30);
		cbId.setEditable(true);
		
		cbId.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt){
				String s = cbId.getEditor().getItem().toString();
				if(evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || evt.getKeyCode() == 8){
					cbId.setModel(model.getList(s));
					if(cbId.getItemCount() > 0){
						cbId.showPopup();
						if(evt.getKeyCode() != 8){
							((JTextComponent) cbId.getEditor().getEditorComponent()).select(s.length(), cbId.getEditor().getItem().toString().length());
						}else{
							cbId.getEditor().setItem(s);
						}
					}else{
						cbId.addItem(s);
					}
				}
			}
		});
		

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
//		panelForm.add(txtId);
		panelForm.add(cbId);
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

	public JPanel panelTable(){
//		ArrayList<String> list = model.Show();
		JPanel panelTable = new JPanel();
		panelTable.setBounds(300, 80, 875, 500);
		panelTable.setBackground(Color.WHITE);
		panelTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)),
				BorderFactory.createLineBorder(Color.BLACK)));

		String[] userColumn = { "Identification", "Name", "Email", "Address", "Phone", "Borrowed_books",
				"Overdue_books", "Overdue_limit", "Status" };

		tableModel = new DefaultTableModel();
		tableModel.fireTableDataChanged();
		for (int i = 0; i < userColumn.length; i++) {
			tableModel.addColumn(userColumn[i]);
		}
		
//		for(int k = 0;k < list.size();k++ ){
//			Object[] row = {list.get(k)};	
//			tableModel.addRow(row);
//		}
		
		model.Show();
		JTable userTable = new JTable();
		userTable.setModel(tableModel);
		JScrollPane scrollTable = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(10, 10, 855, 480);
		
		panelTable.add(scrollTable);
		panelTable.setLayout(null);
		return panelTable;
	}
	
	public static void main(String[] args) {
////		 LibraryFrame frmLib = new LibraryFrame();
//		 JFrame frm = new JFrame("Test");
//		 frm.setSize(1200, 700);
//		 PanelBorrowers pb = new PanelBorrowers();
//		 frm.add(pb);
//		 frm.setLayout(null);
//		 frm.setVisible(true);
	}
}
