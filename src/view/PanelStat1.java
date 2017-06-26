package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import controller.ButtonController;
import entity.Borrowers;
import model.StatisticModel;

public class PanelStat1 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGroup btnGroup;
	private JRadioButton btnByDay;
	private JRadioButton btnByMonth;
	private JDateChooser byDay1;
	private JDateChooser byDay2;
	private JMonthChooser byMonth;
	private JYearChooser byYear;
	private JButton btnShowDay;
	private JButton btnShowMonth;
	private java.sql.Date day1;
	private java.sql.Date day2;
	private Long mc;
	private Long yc;
	private ButtonController bc;
	private StatisticModel model;
	private PanelStat1 p1;
	private JTable statTable;
	private DefaultTableModel statModel;

	public PanelStat1() {

		this.bc = new ButtonController();
		this.model = new StatisticModel();
		this.setBackground(Color.WHITE);

		JLabel lblTitlePanel1 = new JLabel("Danh sách người mượn");
		lblTitlePanel1.setBounds(50, 10, 300, 40);
		Font lblTitleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitlePanel1.setFont(lblTitleFont);

		Font myFont = new Font("Serif", Font.BOLD, 20);

		btnByDay = new JRadioButton("Theo ngày");
		btnByDay.setFont(myFont);
		btnByDay.setBounds(80, 80, 125, 30);
		btnByDay.setBackground(Color.WHITE);
		btnByDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnShowDay.setEnabled(true);
				btnShowMonth.setEnabled(false);
				btnShowDay.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							setDay1(new Date(byDay1.getDate().getTime()));
							setDay2(new Date(byDay2.getDate().getTime()));
							// System.out.println(getDay1().toString());
							// System.out.println(getDay2().toString());
							ArrayList<Borrowers> getDay = model.checkDate(p1);
							Show(getDay);
						} catch (Exception e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(null, "Please enter your choosed date");
						}
					}
				});
			}
		});

		btnByMonth = new JRadioButton("Theo tháng");
		btnByMonth.setFont(myFont);
		btnByMonth.setBounds(80, 130, 125, 30);
		btnByMonth.setBackground(Color.WHITE);
		btnByMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnShowMonth.setEnabled(true);
				btnShowDay.setEnabled(false);
				btnShowMonth.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							setMc((long) (byMonth.getMonth() + 1));
							setYc((long) byYear.getYear());
							ArrayList<Borrowers> getMonth = model.checkMonth(p1);
							Show(getMonth);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
		});
	
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

		JLabel lblByMonth = new JLabel("Năm");
		lblByMonth.setFont(myFont);
		lblByMonth.setBounds(450, 130, 80, 30);

		byMonth = new JMonthChooser();
		byMonth.setBounds(205, 130, 200, 30);
		byYear = new JYearChooser();
		byYear.setBounds(580, 130, 200, 30);

		btnShowDay = new JButton("Show");
		btnShowDay.setBounds(900, 80, 100, 30);
		btnShowDay.setBackground(new Color(50, 166, 254));
		btnShowDay.setForeground(Color.WHITE);
		btnShowDay.setEnabled(false);

		btnShowMonth = new JButton("Show");
		btnShowMonth.setBounds(900, 130, 100, 30);
		btnShowMonth.setBackground(new Color(50, 166, 254));
		btnShowMonth.setForeground(Color.WHITE);
		btnShowMonth.setEnabled(false);

		this.add(lblTitlePanel1);
		this.add(btnByDay);
		this.add(byDay1);
		this.add(byDay2);
		this.add(lblByDay);
		this.add(btnByMonth);
		this.add(byMonth);
		this.add(byYear);
		this.add(lblByMonth);
		this.add(btnShowDay);
		this.add(btnShowMonth);
		this.add(bc.btnBack());
		this.add(bc.btnExit());
		this.add(displayTable());
		this.setLayout(null);
	}

	public JScrollPane displayTable() {
		statTable = new JTable();
		statModel = new DefaultTableModel();
		statTable.setModel(statModel);
		JScrollPane scrollTable = new JScrollPane(statTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(80, 225, 975, 350);

		return scrollTable;
	}

	public void Show(ArrayList<Borrowers> borrowerList) {
		statModel.setRowCount(0);
		statModel.setColumnCount(0);
		String borrowerColumn[] = { "Orders", "Identification", "Name", "Borrowed_Books" };
		for (String item : borrowerColumn) {
			statModel.addColumn(item);
		}
		int i = 1;
		for (Borrowers item : borrowerList) {
			Object row[] = { i++, item.getIdentification(), item.getBorrowers_name(), item.getBorrowed_books() };
			statModel.addRow(row);
		}
	}

	public PanelStat1 getP1() {
		return p1;
	}

	public void setP1(PanelStat1 p1) {
		this.p1 = p1;
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

	public Long getMc() {
		return mc;
	}

	public void setMc(Long mc) {
		this.mc = mc;
	}

	public Long getYc() {
		return yc;
	}

	public void setYc(Long yc) {
		this.yc = yc;
	}
}
