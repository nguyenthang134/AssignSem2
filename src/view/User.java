package view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class User extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAdd;
	private JButton btnMod;
	private JButton btnDel;
	private JButton btnReset;
	
	public User() throws IOException {
		ButtonImportant btnImp = new ButtonImportant();
		this.setBounds(0, 0, 1200, 700);
		this.add(panelForm());
		this.add(panelModel());
		this.add(panelTable());
		this.add(btnImp.btnExit());
		this.add(btnImp.btnBack());
		this.setLayout(null);
	}
	
	public JPanel panelModel(){
		JPanel panelModel = new JPanel();
		panelModel.setBounds(10, 10, 550, 60);
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
		for(int i=0;i<btnListModel.size();i++){
			btnListModel.get(i).setBackground(new Color(89, 194, 255));
			btnListModel.get(i).setFont(btnModelFont);
			btnListModel.get(i).setForeground(Color.WHITE);
			btnListModel.get(i).setPreferredSize(new Dimension(100, 40));
			btnListModel.get(i).setBorder(BorderFactory.createBevelBorder(0));
			panelModel.add(btnListModel.get(i));
		}
		return panelModel;
	}
	
	public JPanel panelForm(){
		JPanel panelForm = new JPanel();
		panelForm.setBounds(10, 80, 275, 500);
		panelForm.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)), BorderFactory.createLineBorder(Color.BLACK)));
		
		JLabel lblname = new JLabel("Borrower's name");
		lblname.setBounds(10, 20, 100, 30);
		JTextField txtname = new JTextField();
		txtname.setBounds(10, 50, 250, 30);
		
		JLabel lblid = new JLabel("Identification");
		lblid.setBounds(10, 100, 100, 30);
		JTextField txtid = new JTextField();
		txtid.setBounds(10, 130, 250, 30);
		
		JLabel lblmail = new JLabel("Email");
		lblmail.setBounds(10, 180, 100, 30);
		JTextField txtmail = new JTextField();
		txtmail.setBounds(10, 210, 250, 30);
		
		JLabel lbladd = new JLabel("Address");
		lbladd.setBounds(10, 260, 100, 30);
		JTextField txtadd = new JTextField();
		txtadd.setBounds(10, 290, 250, 30);
		
		JLabel lblphone = new JLabel("Phone");
		lblphone.setBounds(10, 340, 100, 30);
		JTextField txtphone = new JTextField();
		txtphone.setBounds(10, 370, 250, 30);
		
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
		panelForm.setLayout(null);
		return panelForm;
	}
	
	public JPanel panelTable(){
		JPanel panelTable = new JPanel();
		panelTable.setBounds(300, 80, 875, 500);
		panelTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(89, 194, 255)), BorderFactory.createLineBorder(Color.BLACK)));
		
		String data[][]= {{"101","Amit","670000","","",""},
			              {"102","Jai","780000","","",""},  
		                  {"101","Sachin","700000","","",""}};
		String userColumn[] = {"Identification","Name","Email","Address","Phone","Status"};
		JTable userTable = new JTable(data, userColumn);
		
		JScrollPane scrollTable = new JScrollPane(userTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
