package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.ButtonController;
import model.SearchModel;

public class SearchPanel extends JPanel{
	private JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JLabel lblSearchBy;
	private JComboBox cb;
	private JTextField txtSearchby;
	private SearchModel searchModel = new SearchModel();
	private static SearchPanel searchPanel = new SearchPanel();
	
	public DefaultTableModel getModel() {
		return model;
	}

	public JComboBox getCb() {
		return cb;
	}

	public JTextField getTxtSearchby() {
		return txtSearchby;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public void setCb(JComboBox cb) {
		this.cb = cb;
	}

	public void setTxtSearchby(JTextField txtSearchby) {
		this.txtSearchby = txtSearchby;
	}

	public SearchPanel() {
		frame = new JFrame();
		this.setSize(1200, 700);
		this.setBackground(Color.WHITE);
				
		JPanel panel = new JPanel();
		panel.setBounds(20, 50, 200, 90);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		lblSearchBy = new JLabel("Search by: ");
		lblSearchBy.setBounds(10, 10, 80, 30);
		String[] comboListBorrower = { "------Choose-----", "Book ID", "Book name", "Book author", "Book publisher", "Book category"};
		cb = new JComboBox(comboListBorrower);
		cb.setBounds(80, 10, 115, 30);
		txtSearchby = new JTextField();
		txtSearchby.setBounds(10, 50, 180, 30);
		txtSearchby.setEditable(false);
		panel.add(lblSearchBy);
		panel.add(cb);
		panel.add(txtSearchby);
		panel.setLayout(null);
		
		model = new DefaultTableModel();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(240, 50, 920, 420);
		scrollPane.getViewport().setBackground(Color.WHITE);
		model.addColumn("Book_ID");
		model.addColumn("Book_name");
		model.addColumn("Category");
		model.addColumn("Author");
		model.addColumn("Publisher");
		model.addColumn("Price");
		model.addColumn("Status");
		table.setModel(model);
		
		txtSearchby.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (!txtSearchby.getText().equals("")) {
					searchModel.search(searchPanel);
				} else {
					int rowCount = model.getRowCount();
					// Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (!txtSearchby.getText().equals("")) {
					searchModel.search(searchPanel);
				} else {
					int rowCount = model.getRowCount();
					// Remove rows one by one from the end of the table
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
					txtSearchby.setEditable(false);
				}else if(choice == 1){
					searchModel.setSearchBy("books.id Like ");
					txtSearchby.setEditable(true);
				}else if(choice == 2){
					searchModel.setSearchBy("books.name Like ");
					txtSearchby.setEditable(true);
				}else if(choice == 3){
					searchModel.setSearchBy("authors.name Like ");
					txtSearchby.setEditable(true);
				}else if(choice == 4){
					searchModel.setSearchBy("publishers.name Like ");
					txtSearchby.setEditable(true);
				}else if(choice == 5){
					searchModel.setSearchBy("categories.name Like ");
					txtSearchby.setEditable(true);
				}
				
			}
		});
		
		ButtonController bc = new ButtonController();
		this.add(panel);
		this.add(scrollPane);
		this.add(bc.btnExit());
		this.add(bc.btnBackBorrowers());
		this.setLayout(null);
	}
}
