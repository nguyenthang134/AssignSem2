package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.LibraryFrame;
import view.LoginAdminView;
import view.PanelMenu;

public class ButtonController {

	private BufferedImage btnIconExit;
	private JButton btnExit;
	private BufferedImage btnIconBack;
	private JButton btnBack;
	private JButton btnBackLogin;
	private LoginAdminView log;
	private PanelMenu pm;
	
	public JButton btnExit() {
		try {
			btnIconExit = ImageIO.read(new File("../AssignSem2/src/assets/exit-icon.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnExit = new JButton(new ImageIcon(btnIconExit));
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

	public JButton btnBack() {
		try {
			btnIconBack = ImageIO.read(new File("../AssignSem2/src/assets/back-icon.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		btnBack = new JButton(new ImageIcon(btnIconBack));
		btnBack.setBounds(0, 575, 80, 80);
		btnBack.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryFrame.cl.show(LibraryFrame.mainPanel, "Menu");
			}
		});

		return btnBack;
	}

	public JButton btnBackLogin() {
		try {
			btnIconBack = ImageIO.read(new File("../AssignSem2/src/assets/back-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnBackLogin = new JButton(new ImageIcon(btnIconBack));
		btnBackLogin.setBounds(0, 575, 80, 80);
		btnBackLogin.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnBackLogin.setContentAreaFilled(false);
		btnBackLogin.setFocusPainted(false);

		btnBackLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
		        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		        frame.setVisible(false);
		        frame.dispose();
				log = new LoginAdminView();
				log.setVisible(true);
			}
		});

		return btnBackLogin;
	}
}
