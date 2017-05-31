package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends Panel{
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
	private JButton btnUser;
	private JButton btnBook;
	private JButton btnBorrow;
	private JButton btnSearch;
	private JButton btnReport;
	private ArrayList<JButton> btnList;
	private BufferedImage btnIconBack;
	private JButton btnBackMenu;
	
	public Menu() throws IOException {
		ButtonImportant btnImp = new ButtonImportant();
		this.setBounds(0, 0, 1200, 700);
		this.add(lblTitleMenu());
		this.add(panel());
		this.add(btnBackMenu());
		this.add(btnImp.btnExit());
		this.setLayout(null);
	}
	
	public JLabel lblTitleMenu(){
		lblTitleMenu = new JLabel("Phần mềm quản lý thư viện");
		lblTitleMenu.setBounds(50, 10, 400, 50);
		Font titleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitleMenu.setFont(titleFont);
		return lblTitleMenu;
	}
	
	public JPanel panel() throws IOException{
		JPanel panel = new JPanel();
		panel.setBounds(150, 80, 900, 550);
		panel.setBorder(BorderFactory.createEmptyBorder());
		panel.setLayout( new FlowLayout(FlowLayout.CENTER,100,50));
		
		btnIconUser = ImageIO.read(new File("../AssignSem2/src/assets/user-icon.png"));
		btnUser = new JButton("Người dùng", new ImageIcon(btnIconUser));
		
		btnIconBook = ImageIO.read(new File("../AssignSem2/src/assets/Book-icon.png"));
		btnBook = new JButton("Sách", new ImageIcon(btnIconBook));
		
		btnIconBorrow = ImageIO.read(new File("../AssignSem2/src/assets/Hand-icon.png"));
		btnBorrow = new JButton("Mượn/Trả", new ImageIcon(btnIconBorrow));
		
		btnIconSearch = ImageIO.read(new File("../AssignSem2/src/assets/search-icon.png"));
		btnSearch = new JButton("Tìm kiếm", new ImageIcon(btnIconSearch));
		
		btnIconReport = ImageIO.read(new File("../AssignSem2/src/assets/Statistic-icon.png"));
		btnReport = new JButton("Thống kê", new ImageIcon(btnIconReport));
		
		btnList = new ArrayList<JButton>();
		btnList.add(btnUser);
		btnList.add(btnBook);
		btnList.add(btnBorrow);
		btnList.add(btnSearch);
		btnList.add(btnReport);
		
		for(int i=0;i<btnList.size();i++){
			btnList.get(i).setPreferredSize(new Dimension(200, 200));
			btnList.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
			btnList.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
			panel.add(btnList.get(i));
		}	
		return panel;
	}
	
	public JButton btnBackMenu() throws IOException{
		btnIconBack = ImageIO.read(new File("../AssignSem2/src/assets/back-icon.png"));
		btnBackMenu = new JButton(new ImageIcon(btnIconBack));
		btnBackMenu.setBounds(0, 575, 80, 80);
		btnBackMenu.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnBackMenu.setContentAreaFilled(false);
		btnBackMenu.setFocusPainted(false);
		
		return btnBackMenu;
	}
	
	public static void main(String[] agrs) throws IOException {
		LibraryFrame frmLib = new LibraryFrame();
		Menu panelmenu = new Menu();
		frmLib.add(panelmenu);
		frmLib.setLayout(null);
		frmLib.setVisible(true);
	}
}
