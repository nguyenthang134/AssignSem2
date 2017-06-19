package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ButtonController;
import entity.Borrowers;
import model.StatisticModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class PanelStatistic extends JTabbedPane {

	private ButtonGroup btnGroup;
	private JRadioButton btnByDay;
	private JRadioButton btnByMonth;
	private JDateChooser byDay1;
	private JDateChooser byDay2;
	private JMonthChooser byMonth;
	private JYearChooser byYear;
	private JButton btnShow;
	private JButton btnPrint;
	private DefaultTableModel statModel;
	private Date day1;
	private PanelStatistic ps;
	
	
	public PanelStatistic getPs() {
		return ps;
	}

	public void setPs(PanelStatistic ps) {
		this.ps = ps;
	}

	public Date getDay1() {
		return day1;
	}

	public void setDay1(Date day1) {
		this.day1 = day1;
	}

	public Date getDay2() {
		return day2;
	}

	public void setDay2(Date day2) {
		this.day2 = day2;
	}

	private Date day2;

	private ButtonController bc;
	private StatisticModel model;

	public PanelStatistic() {
		this.bc = new ButtonController();
		this.model = new StatisticModel();
		this.setBounds(0, 0, 1200, 725);
		this.addTab("Danh sách người mượn", panelListBorrower());
	}

	public JPanel panelListBorrower() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);

		JLabel lblTitlePanel1 = new JLabel("Danh sách người mượn");
		lblTitlePanel1.setBounds(50, 10, 300, 40);
		Font lblTitleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitlePanel1.setFont(lblTitleFont);

		Font myFont = new Font("Serif", Font.BOLD, 20);

		btnByDay = new JRadioButton("Theo ngày");
		btnByDay.setFont(myFont);
		btnByDay.setBounds(80, 80, 125, 30);
		// btnByDay.setBackground(Color.WHITE);

		btnByMonth = new JRadioButton("Theo tháng");
		btnByMonth.setFont(myFont);
		btnByMonth.setBounds(80, 130, 125, 30);
		// btnByMonth.setBackground(Color.WHITE);

		btnGroup = new ButtonGroup();
		btnGroup.add(btnByDay);
		btnGroup.add(btnByMonth);

		JLabel lblByDay = new JLabel("Đến ngày");
		lblByDay.setFont(myFont);
		lblByDay.setBounds(450, 80, 80, 30);

		byDay1 = new JDateChooser();
		byDay1.setBounds(205, 80, 200, 30);
		byDay2 = new JDateChooser();
		byDay2.setBounds(580, 80, 200, 30);
		

		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			day1 = sdf1.parse(byDay1.getDate().toString());
//			setDay1(sdf1.parse(byDay1.getDate().toString()));
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			setDay2(sdf2.parse(byDay2.getDate().toString()));
		} catch (ParseException | NullPointerException e) {
			e.printStackTrace();
//			System.err.println(e);
		}

		JLabel lblByMonth = new JLabel("Năm");
		lblByMonth.setFont(myFont);
		lblByMonth.setBounds(450, 130, 80, 30);

		byMonth = new JMonthChooser();
		byMonth.setBounds(205, 130, 200, 30);
		byYear = new JYearChooser();
		byYear.setBounds(580, 130, 200, 30);

		btnShow = new JButton("Xem");
		btnShow.setBounds(950, 80, 100, 50);
		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					System.out.println(byDay1.getDate().toString());
					System.out.println(day1.toString());
					if (ps.getSelectedIndex() == 0) {
						if (model.checkDate(ps)) {
							ArrayList<Borrowers> borrowerList = model.Borrower();
							showBorrower(borrowerList);
						}
					}
				}
				catch(Exception e1){
//					System.err.println(e1);
					e1.printStackTrace();
				}
			}
			
		});
		btnPrint = new JButton("In");
		btnPrint.setBounds(950, 150, 100, 50);

		panel1.add(lblTitlePanel1);
		panel1.add(btnByDay);
		panel1.add(byDay1);
		panel1.add(byDay2);
		panel1.add(lblByDay);
		panel1.add(btnByMonth);
		panel1.add(byMonth);
		panel1.add(byYear);
		panel1.add(lblByMonth);
		panel1.add(btnShow);
		panel1.add(btnPrint);
		panel1.add(bc.btnBack());
		panel1.add(bc.btnExit());
		panel1.add(displayTable());
		panel1.setLayout(null);

		return panel1;
	}

	public JScrollPane displayTable() {
		JTable statTable = new JTable();
		statModel = new DefaultTableModel();

		statTable.setModel(statModel);
		JScrollPane scrollTable = new JScrollPane(statTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(80, 225, 975, 350);

		return scrollTable;
	}

	public void date() {
		if (btnByDay.isSelected()) {

		} else if (btnByMonth.isSelected()) {

		}
	}

	public void showBorrower(ArrayList<Borrowers> borrowersList) {
		statModel.setRowCount(0);
		String borrowerColumn[] = { "Orders", "Identification", "Name", "Borrowed_Books" };
		for (String item : borrowerColumn) {
			statModel.addColumn(item);
		}
		int i = 1;
		for (Borrowers item : borrowersList) {
			Object[] row = { i++, item.getIdentification(), item.getBorrowers_name(), item.getBorrowed_books() };
			statModel.addRow(row);
		}
	}
	
	public static void main(String[] args) {
		// LibraryFrame frmLib = new LibraryFrame();
		// JFrame frm = new JFrame("Test");
		// frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frm.setSize(1200, 725);
		// PanelStatistic ps = new PanelStatistic();
		// frm.add(ps);
		// frm.setLayout(null);
		// frm.setVisible(true);
	}
}
