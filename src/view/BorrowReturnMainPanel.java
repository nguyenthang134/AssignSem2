package view;

import java.awt.Color;

import javax.swing.JTabbedPane;

public class BorrowReturnMainPanel extends JTabbedPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BorrowPanel borrowPanel;
	private ReturnPanel returnPanel;
	private BorrowReturnMainPanel bar;
	
	public BorrowReturnMainPanel getBar() {
		return bar;
	}

	public void setBar(BorrowReturnMainPanel bar) {
		this.bar = bar;
	}

	public BorrowReturnMainPanel(){
		this.borrowPanel = new BorrowPanel();
		borrowPanel.setBorrow(borrowPanel);
		this.returnPanel = new ReturnPanel();
		returnPanel.setReturnPanel(returnPanel);
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0,1185, 660);
		this.add("Borrow",borrowPanel.borrowPanel());
		this.add("Return",returnPanel.returnPanel());
	}
}
