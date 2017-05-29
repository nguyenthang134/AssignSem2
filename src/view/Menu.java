package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends JFrame{
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	private static JLabel lblTitleMenu;
	private static BufferedImage btnIconUser;
	private static BufferedImage btnIconBook;
	private static BufferedImage btnIconBorrow;
	private static BufferedImage btnIconSearch;
	private static BufferedImage btnIconReport;
	private static JButton btnUser;
	private static JButton btnBook;
	private static JButton btnBorrow;
	private static JButton btnSearch;
	private static JButton btnReport;
	private static ArrayList<JButton> btnList;
	private static BufferedImage btnIconBack;
	private static BufferedImage btnIconExit;
	private static JButton btnBack;
	private static JButton btnExit;
	
	public static JLabel lblTitleMenu(){
		lblTitleMenu = new JLabel("Phần mềm quản lý thư viện");
		lblTitleMenu.setBounds(50, 10, 400, 50);
		Font titleFont = new Font("Serif", Font.ITALIC, 30);
		lblTitleMenu.setFont(titleFont);
		return lblTitleMenu;
	}
	
	public static JPanel panel() throws IOException{
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
	
	public static JPanel panelMenu() throws IOException{
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 1200, 700);
		panelMenu.setLayout(null);
		panelMenu.add(lblTitleMenu());
		panelMenu.add(panel());
		panelMenu.add(btnBack());
		panelMenu.add(btnExit());
		
		return panelMenu;
	}
	
	public static JButton btnBack() throws IOException{
		btnIconBack = ImageIO.read(new File("../AssignSem2/src/assets/back-icon.png"));
		btnBack = new JButton(new ImageIcon(btnIconBack));
		btnBack.setBounds(0, 550, 80, 80);
		
		return btnBack;
	}
	
	public static JButton btnExit() throws IOException{
		btnIconExit = ImageIO.read(new File("../AssignSem2/src/assets/exit-icon.png"));
		btnExit = new JButton("", new ImageIcon(btnIconExit));
		btnExit.setBounds(1105, 550, 80, 80);
		
		return btnExit;
	}
	
	public static void main(String[] args) throws IOException {
		JFrame frmMenu = new JFrame();
		frmMenu.setSize(1200, 700);
		frmMenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frmMenu.add(panelMenu());
		frmMenu.setLocationRelativeTo(null);
		frmMenu.setLayout(null);
		frmMenu.setVisible(true);
	}
}
