package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.LibraryFrame;

public class ButtonController {

	private BufferedImage btnIconExit;
	private JButton btnExit;
	private BufferedImage btnIconBack;
	private JButton btnBack;
	private JButton btnBackMenu;

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

	public JButton btnBackMenu() {
		try {
			btnIconBack = ImageIO.read(new File("../AssignSem2/src/assets/back-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnBackMenu = new JButton(new ImageIcon(btnIconBack));
		btnBackMenu.setBounds(0, 575, 80, 80);
		btnBackMenu.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		btnBackMenu.setContentAreaFilled(false);
		btnBackMenu.setFocusPainted(false);

		btnBackMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// trở lại giao diện login
			}
		});

		return btnBackMenu;
	}
}
