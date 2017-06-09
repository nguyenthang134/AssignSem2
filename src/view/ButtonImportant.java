package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonImportant {
	
	private BufferedImage btnIconExit;
	private JButton btnExit;
	private BufferedImage btnIconBack;
	private JButton btnBack;
	
	public JButton btnExit() throws IOException{
		btnIconExit = ImageIO.read(new File("../AssignSem2/src/assets/exit-icon.png"));
		btnExit = new JButton("", new ImageIcon(btnIconExit));
		btnExit.setBounds(1105, 575, 80, 80);
		btnExit.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnExit.setContentAreaFilled(false);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return btnExit;
	}
	
	public JButton btnBack() throws IOException{
		btnIconBack = ImageIO.read(new File("../AssignSem2/src/assets/back-icon.png"));
		btnBack = new JButton(new ImageIcon(btnIconBack));
		btnBack.setBounds(0, 575, 80, 80);
		btnBack.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		
		return btnBack;
	}
}
