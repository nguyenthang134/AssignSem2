package view;

import javax.swing.*;

public class LogInAdmin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        JFrame frmLogIn = new JFrame();
        frmLogIn.setSize(600, 300);
//        frmLogIn.getContentPane().setBackground(Color.BLUE);
        
        JLabel lblAcc = new JLabel("TÀI KHOẢN ");
        JLabel lblPass = new JLabel("MẬT KHẨU ");
        JTextField txtAcc = new JTextField();
        JPasswordField txtPass = new JPasswordField();
        JButton btnLogin = new JButton("ĐĂNG NHẬP");
        JButton btnClose = new JButton("THOÁT");
        
        lblAcc.setBounds(120, 50, 100, 40);
        txtAcc.setBounds(230, 55, 200, 30);
        lblPass.setBounds(120, 100, 100, 40);
        txtPass.setBounds(230, 105, 200, 30);
        btnLogin.setBounds(120, 170, 120, 40);
        btnClose.setBounds(310, 170, 120, 40);

        frmLogIn.add(lblAcc);
        frmLogIn.add(txtAcc);
        frmLogIn.add(lblPass);
        frmLogIn.add(txtPass);
        frmLogIn.add(btnLogin);
        frmLogIn.add(btnClose);
        
        frmLogIn.setLayout(null);
        frmLogIn.setLocationRelativeTo(null);
        frmLogIn.setVisible(true);
    }
}