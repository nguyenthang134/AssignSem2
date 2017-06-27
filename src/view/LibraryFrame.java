package view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LibraryFrame extends JFrame {

	private PanelMenu panelMenu;
	private PanelBorrowers panelBorrowers;
	private PanelBook panelBook;
	private PanelStatistic panelStatistic;
	private SearchPanel panelSearch;
	private BorrowReturnMainPanel panelBorrowReturn;
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
		this.panelBook = new PanelBook();
		this.panelStatistic = new PanelStatistic();
		this.panelSearch = new SearchPanel();
		this.panelBorrowReturn = new BorrowReturnMainPanel();
		panelStatistic.setPs(panelStatistic);
		panelSearch.setSearchPanel(panelSearch);
		panelBorrowReturn.setBar(panelBorrowReturn);
		panelBook.setPbook(panelBook);
		
		mainPanel.setLayout(cl);
		mainPanel.setBounds(0, 0, 1200, 700);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(this.panelMenu, "Menu");
		mainPanel.add(this.panelBorrowers, "Borrowers");
		mainPanel.add(this.panelBook, "Books");
		mainPanel.add(this.panelStatistic, "Statistic");
		mainPanel.add(this.panelSearch, "Search");
		mainPanel.add(this.panelBorrowReturn, "Borrow/Return");

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
