package view;

import javax.swing.JTabbedPane;

public class PanelStatistic extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelStatistic ps;
	private PanelStat1 panel1;
	private PanelStat2 panel2;

	public PanelStatistic() {
		this.panel1 = new PanelStat1();
		panel1.setP1(panel1);
		this.panel2 = new PanelStat2();
		panel2.setP2(panel2);
		this.setBounds(0, 0, 1200, 725);
		this.addTab("Danh sách người mượn", panel1);
		this.addTab("Danh sách sách mượn", panel2);
		this.revalidate();
		this.repaint();
	}

	public static void main(String[] args) {

	}

	public PanelStatistic getPs() {
		return ps;
	}

	public void setPs(PanelStatistic ps) {
		this.ps = ps;
	}
}
