package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.ButtonController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PanelMenu extends JPanel {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	private JLabel lblTitleMenu;

	private BufferedImage btnIconUser;
	private BufferedImage btnIconBook;
	private BufferedImage btnIconBorrow;
	private BufferedImage btnIconSearch;
	private BufferedImage btnIconReport;

	private JButton btnBorrowers;
	private JButton btnBook;
	private JButton btnBorrow;
	private JButton btnSearch;
	private JButton btnReport;
	private ArrayList<JButton> btnList;

	private ButtonController bc;

	public PanelMenu() {
		this.bc = new ButtonController();
		this.setBounds(0, 0, 1200, 700);
		this.setBackground(Color.WHITE);
		this.add(lblTitleMenu());
		this.add(panelMenu());
		this.add(bc.btnBackLogin());
		this.add(bc.btnExit());
		this.setLayout(null);
	}

	public JLabel lblTitleMenu() {
		lblTitleMenu = new JLabel("Phần mềm quản lý thư viện");
		lblTitleMenu.setBounds(50, 10, 400, 50);
		Font titleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitleMenu.setFont(titleFont);
		return lblTitleMenu;
	}

	public JPanel panelMenu() {
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(150, 80, 900, 550);
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBorder(BorderFactory.createEmptyBorder());
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));

		try {
			btnIconUser = ImageIO.read(new File("../AssignSem2/src/assets/user-icon.png"));
			btnIconBook = ImageIO.read(new File("../AssignSem2/src/assets/Book-icon.png"));
			btnIconBorrow = ImageIO.read(new File("../AssignSem2/src/assets/Hand-icon.png"));
			btnIconSearch = ImageIO.read(new File("../AssignSem2/src/assets/search-icon.png"));
			btnIconReport = ImageIO.read(new File("../AssignSem2/src/assets/Statistic-icon.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		btnBorrowers = new JButton("Người dùng", new ImageIcon(btnIconUser));
		btnBook = new JButton("Sách", new ImageIcon(btnIconBook));
		btnBorrow = new JButton("Mượn/Trả", new ImageIcon(btnIconBorrow));
		btnSearch = new JButton("Tìm kiếm", new ImageIcon(btnIconSearch));
		btnReport = new JButton("Thống kê", new ImageIcon(btnIconReport));		

		btnBorrowers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryFrame.cl.show(LibraryFrame.mainPanel, "Borrowers");
			}
		});

		btnBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelBorrowers pb = new PanelBorrowers();
				pb.resetForm();
				LibraryFrame.cl.show(LibraryFrame.mainPanel, "Books");
			}
		});
		
		btnBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryFrame.cl.show(LibraryFrame.mainPanel, "Borrow/Return");
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryFrame.cl.show(LibraryFrame.mainPanel, "Search");
			}
		});

		btnReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryFrame.cl.show(LibraryFrame.mainPanel, "Statistic");
			}
		});

		btnList = new ArrayList<JButton>();
		btnList.add(btnBorrowers);
		btnList.add(btnBook);
		btnList.add(btnBorrow);
		btnList.add(btnSearch);
		btnList.add(btnReport);

		for (int i = 0; i < btnList.size(); i++) {
			btnList.get(i).setPreferredSize(new Dimension(200, 200));
			btnList.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
			btnList.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
			panelMenu.add(btnList.get(i));
		}
		return panelMenu;
	}
	
	public static void main(String[] agrs) throws IOException {
		// LibraryFrame frmLib = new LibraryFrame();
		// Menu panelMenu = new Menu();
		// frmLib.add(panelMenu);
		// frmLib.setLayout(null);
		// frmLib.setVisible(true);
	}
}
