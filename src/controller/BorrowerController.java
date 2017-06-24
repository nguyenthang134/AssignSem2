package controller;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;

import entity.Borrowers;
import view.PanelBorrowers;

public class BorrowerController {
	String regexInt = "[\\d]+";
	String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	String regexName = "[\\w\\s]+";
	private Borrowers val = new Borrowers();
	private static PanelBorrowers pb;
	
	public boolean RegexInt() {
		Pattern p = Pattern.compile(regexInt);
		Matcher m = p.matcher(String.valueOf(val.getIdentification()));
		return m.find();
	}

	public boolean RegexMail() {
		Pattern p = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(val.getBorrowers_mail());
		return m.find();
	}

	public boolean RegexName() {
		Pattern p = Pattern.compile(regexName);
		Matcher m = p.matcher(val.getBorrowers_name());
		return m.find();
	}

	public void Validate() {
		// try{
		System.out.println(0);
		if (String.valueOf(val.getIdentification()).length() > 11 || String.valueOf(val.getIdentification()).length() == 0 || RegexInt() != true) {
			pb.txtId.setBorder(BorderFactory.createLineBorder(Color.RED));
		}

		if (val.getBorrowers_mail().length() > 50 || RegexMail() != true || val.getBorrowers_mail().length() == 0) {
			pb.txtmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			pb.txtmail.setText("");
		}

		if (val.getBorrowers_name().length() > 20 || RegexName() != true || val.getBorrowers_name().length() == 0) {
			pb.txtname.setBorder(BorderFactory.createLineBorder(Color.RED));
			pb.txtname.setText("");
		}

		if (val.getBorrowers_address().length() == 0) {
			pb.txtadd.setBorder(BorderFactory.createLineBorder(Color.RED));
			pb.txtadd.setText("");
		}

		if (String.valueOf(val.getBorrowers_phone()).length() > 11 || RegexInt() != true || String.valueOf(val.getBorrowers_phone()).length() == 0) {
			pb.txtphone.setBorder(BorderFactory.createLineBorder(Color.RED));
			pb.txtphone.setText("");
		}
		// }
		// catch (Exception e) {
		// e.printStackTrace();
		// JOptionPane.showMessageDialog(null, "Please input your form
		// first","Error Message",JOptionPane.ERROR_MESSAGE);
		// }
	}
}
