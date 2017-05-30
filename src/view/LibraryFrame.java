package view;

import java.io.IOException;

import javax.swing.JFrame;

public class LibraryFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibraryFrame() throws IOException {
		Menu menu = new Menu();
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(menu.panelMenu());
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public static void main(String[] agrs) throws IOException {
		LibraryFrame frmLib = new LibraryFrame();
	}
}
