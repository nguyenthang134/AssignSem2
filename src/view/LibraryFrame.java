package view;

import java.io.IOException;

import javax.swing.JFrame;

public class LibraryFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibraryFrame() throws IOException {
		this.setTitle("Phần mềm quản lý thư viện");
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	}
}
