package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ButtonController;
import model.StatisticModel;

public class PanelStatistic extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelStatistic ps;
	private JDateChooser byDay1;
	private JDateChooser byDay2;
	private JButton btnShow;
	private java.sql.Date day1;
	private java.sql.Date day2;
	private ButtonController bc;
	private StatisticModel model;
	private JTable statTable;
	public static DefaultTableModel statModel;

	public PanelStatistic() {
		this.setBounds(0, 0, 1200, 700);
		this.bc = new ButtonController();
		this.model = new StatisticModel();
		this.setBackground(Color.WHITE);

		JLabel lblTitlePanel = new JLabel("Statistic of borrower and book");
		lblTitlePanel.setBounds(50, 10, 600, 40);
		Font lblTitleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitlePanel.setFont(lblTitleFont);

		Font myFont = new Font("Serif", Font.BOLD, 20);

		JLabel lblByDay1 = new JLabel("From");
		lblByDay1.setFont(myFont);
		lblByDay1.setBounds(125, 80, 125, 30);

		JLabel lblByDay2 = new JLabel("To");
		lblByDay2.setFont(myFont);
		lblByDay2.setBounds(450, 80, 80, 30);

		byDay1 = new JDateChooser();
		byDay1.setBounds(205, 80, 200, 30);
		byDay2 = new JDateChooser();
		byDay2.setBounds(520, 80, 200, 30);

		JLabel lblBorrower = new JLabel();
		lblBorrower.setFont(myFont);
		lblBorrower.setBounds(900, 510, 200, 30);

		JLabel lblBook = new JLabel();
		lblBook.setFont(myFont);
		lblBook.setBounds(900, 550, 200, 30);

		btnShow = new JButton("Show");
		btnShow.setBounds(800, 80, 100, 30);
		btnShow.setBackground(new Color(50, 166, 254));
		btnShow.setForeground(Color.WHITE);
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setDay1(new Date(byDay1.getDate().getTime()));
					setDay2(new Date(byDay2.getDate().getTime()));
					if (getDay1().after(getDay2()) || getDay2().before(getDay1())) {
						JOptionPane.showMessageDialog(null, "Please re-choose your date");
					} else {
						Show();
						lblBorrower.setText("Total: " + model.countBorrower(ps) + " Borrowers");
						lblBook.setText("Total: " + model.countBook(ps) + " Books");
					}
				} catch (Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(null, "Please enter your choosed date");
				}
			}
		});

		this.add(lblTitlePanel);
		this.add(byDay1);
		this.add(byDay2);
		this.add(lblByDay1);
		this.add(lblByDay2);
		this.add(btnShow);
		this.add(bc.btnBack());
		this.add(bc.btnExit());
		this.add(displayTable());
		this.add(lblBorrower);
		this.add(lblBook);
		this.setLayout(null);
	}

	public JScrollPane displayTable() {
		statTable = new JTable();
		statModel = new DefaultTableModel();
		statTable.setModel(statModel);
		JScrollPane scrollTable = new JScrollPane(statTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(80, 150, 975, 350);

		return scrollTable;
	}

	public void Show() {
		statModel.setRowCount(0);
		statModel.setColumnCount(0);
		String borrowerColumn[] = { "Orders", "Borrowers_name", "Books_name", "Orders_Status" };
		for (String item : borrowerColumn) {
			statModel.addColumn(item);
		}
		model.order(ps);
	}

	public PanelStatistic getPs() {
		return ps;
	}

	public void setPs(PanelStatistic ps) {
		this.ps = ps;
	}

	public java.sql.Date getDay1() {
		return day1;
	}

	public void setDay1(java.sql.Date day1) {
		this.day1 = day1;
	}

	public java.sql.Date getDay2() {
		return day2;
	}

	public void setDay2(java.sql.Date day2) {
		this.day2 = day2;
	}
}
