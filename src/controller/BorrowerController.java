package controller;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import view.PanelBorrowers;

public class BorrowerController {
	String regexInt = "[\\d]+";
	String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	String regexName = "[\\w\\s]+";
	
	public boolean RegexInt(PanelBorrowers pb) {
		Pattern p = Pattern.compile(regexInt);
		Matcher m = p.matcher(pb.txtId.getText());
		return m.find();
	}

	public boolean RegexMail(PanelBorrowers pb) {
		Pattern p = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pb.txtmail.getText());
		return m.find();
	}

	public boolean RegexName(PanelBorrowers pb) {
		Pattern p = Pattern.compile(regexName);
		Matcher m = p.matcher(pb.txtname.getText());
		return m.find();
	}

	public void Validate(PanelBorrowers pb){
//		try{
			if (RegexInt(pb) != true || pb.txtId.getText().length() <= 0) {
				pb.txtId.setBorder(BorderFactory.createLineBorder(Color.RED));
			}

			if (pb.txtmail.getText().length() > 50 || RegexMail(pb) != true
					|| pb.txtmail.getText().length() == 0) {
				pb.txtmail.setBorder(BorderFactory.createLineBorder(Color.RED));
				pb.txtmail.setText("");
			}

			if (pb.txtname.getText().length() > 20 || RegexName(pb) != true
					|| pb.txtname.getText().length() == 0) {
				pb.txtname.setBorder(BorderFactory.createLineBorder(Color.RED));
				pb.txtname.setText("");
			}

			if (pb.txtadd.getText().length() == 0) {
				pb.txtadd.setBorder(BorderFactory.createLineBorder(Color.RED));
				pb.txtadd.setText("");
			}

			if (pb.txtphone.getText().length() > 11 || RegexInt(pb) != true
					|| pb.txtphone.getText().length() == 0) {
				pb.txtphone.setBorder(BorderFactory.createLineBorder(Color.RED));
				pb.txtphone.setText("");
			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "Please input your form first","Error Message",JOptionPane.ERROR_MESSAGE);
//		}
	}
}
