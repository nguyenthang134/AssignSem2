package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import view.User;

public class BorrowerController {
	String regexInt = "[\\d]+";
	String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	String regexName = "[\\w\\s]+";

	public boolean RegexInt() {
		Pattern p = Pattern.compile(regexInt);
		Matcher m = p.matcher(User.txtid.getText());
		return m.find();
	}

	public boolean RegexMail() {
		Pattern p = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(User.txtmail.getText());
		return m.find();
	}

	public boolean RegexName() {
		Pattern p = Pattern.compile(regexName);
		Matcher m = p.matcher(User.txtname.getText());
		return m.find();
	}

	public void Validate() throws IOException {

		if (User.txtid.getText().length() > 11 || RegexInt() != true || User.txtid.getText().length() == 0) {
			User.txtid.setBorder(BorderFactory.createLineBorder(Color.RED));
			User.txtid.setText("");
		}

		if (User.txtmail.getText().length() > 50 || RegexMail() != true || User.txtmail.getText().length() == 0) {
			User.txtmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			User.txtmail.setText("");
		}

		if (User.txtname.getText().length() > 20 || RegexName() != true || User.txtname.getText().length() == 0) {
			User.txtname.setBorder(BorderFactory.createLineBorder(Color.RED));
			User.txtname.setText("");
		}

		if (User.txtadd.getText().length() == 0) {
			User.txtadd.setBorder(BorderFactory.createLineBorder(Color.RED));
			User.txtadd.setText("");
		}

		if (User.txtphone.getText().length() > 11 || RegexInt() != true || User.txtphone.getText().length() == 0) {
			User.txtphone.setBorder(BorderFactory.createLineBorder(Color.RED));
			User.txtphone.setText("");
		}
	}
}
