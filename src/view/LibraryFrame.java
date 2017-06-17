package view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LibraryFrame extends JFrame {

	private PanelMenu panelMenu;
	private PanelBorrowers panelBorrowers;
	private PanelStatistic panelStatistic;
	public static JPanel mainPanel = new JPanel();
	public static CardLayout cl = new CardLayout();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibraryFrame() {
		this.setTitle("Phần mềm quản lý thư viện");
		this.setSize(1200, 725);

		this.panelMenu = new PanelMenu();
		this.panelBorrowers = new PanelBorrowers();
		this.panelStatistic = new PanelStatistic();
		
		mainPanel.setLayout(cl);
		mainPanel.setBounds(0, 0, 1200, 700);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(this.panelMenu, "Menu");
		mainPanel.add(this.panelBorrowers, "Borrowers");
		mainPanel.add(this.panelStatistic, "Statistic");

		cl.show(mainPanel, "Menu");

		this.add(mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	}

	public static void main(String[] args) {
		LibraryFrame mainFrm = new LibraryFrame();
		mainFrm.setVisible(true);
	}
}
