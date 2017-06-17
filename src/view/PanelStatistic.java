package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;

import controller.ButtonController;
import org.jdesktop.swingx.JXDatePicker;

public class PanelStatistic extends JTabbedPane {

	private ButtonController bc;

	public PanelStatistic() {
		this.bc = new ButtonController();
		this.setBounds(0, 0, 1200, 725);
		this.addTab("Danh sách người mượn", panelListBorrower());
	}

	public JPanel panelListBorrower() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);

		JLabel lblTitlePanel1 = new JLabel("Danh sách người mượn");
		lblTitlePanel1.setBounds(50, 10, 400, 50);
		Font lblTitleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitlePanel1.setFont(lblTitleFont);

//		JXDatePicker picker = new JXDatePicker();
//		picker.setDate(Calendar.getInstance().getTime());
//		picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		Font btnRadioFont = new Font("Serif", Font.BOLD, 20);
		JRadioButton btnByDay = new JRadioButton("Theo ngày");
		btnByDay.setFont(btnRadioFont);
		btnByDay.setBounds(80, 80, 500, 50);
		JRadioButton btnByMonth = new JRadioButton("Theo tháng");
		btnByDay.setBounds(80, 80, 500, 50);
		JRadioButton btnByYear = new JRadioButton("Theo năm");
		btnByDay.setBounds(80, 80, 500, 50);

		panel1.add(lblTitlePanel1);
		panel1.add(btnByDay);
		panel1.add(bc.btnBack());
		panel1.add(bc.btnExit());
		panel1.setLayout(null);

		return panel1;
	}

	public static void main(String[] args) {
		// LibraryFrame frmLib = new LibraryFrame();
		JFrame frm = new JFrame("Test");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(1200, 725);
		PanelStatistic ps = new PanelStatistic();
		frm.add(ps);
		frm.setLayout(null);
		frm.setVisible(true);
	}
}
