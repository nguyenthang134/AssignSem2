package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BorrowReturnMainPanel extends JFrame{

	public static void main(String[] args) {
		BorrowPanel borrowPanel = new BorrowPanel();
		ReturnPanel returnPanel = new ReturnPanel();
		BorrowReturnMainPanel bar = new BorrowReturnMainPanel();
		JFrame frame = new JFrame();
		frame.setSize(1200, 700);
		frame.getContentPane().setBackground(Color.WHITE);
		
		JTabbedPane mainPanel = new JTabbedPane();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 0,1185, 660);
		mainPanel.add("Borrow",borrowPanel.borrowPanel());
		mainPanel.add("Return",returnPanel.returnPanel());
		
		frame.add(mainPanel);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
