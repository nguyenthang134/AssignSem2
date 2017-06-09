package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;

import view.PanelBorrowers;

public class BorrowerController {
	String regexInt = "[\\d]+";
	String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	String regexName = "[\\w\\s]+";

	public boolean RegexInt() {
		Pattern p = Pattern.compile(regexInt);
		Matcher m = p.matcher(PanelBorrowers.cbId.getEditor().getItem().toString());
		return m.find();
	}

	public boolean RegexMail() {
		Pattern p = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(PanelBorrowers.txtmail.getText());
		return m.find();
	}

	public boolean RegexName() {
		Pattern p = Pattern.compile(regexName);
		Matcher m = p.matcher(PanelBorrowers.txtname.getText());
		return m.find();
	}

	public void Validate() throws IOException {

		if (PanelBorrowers.cbId.getEditor().getItem().toString().length() > 11 || RegexInt() != true || PanelBorrowers.cbId.getEditor().getItem().toString().length() == 0) {
			PanelBorrowers.cbId.setBorder(BorderFactory.createLineBorder(Color.RED));
		}

		if (PanelBorrowers.txtmail.getText().length() > 50 || RegexMail() != true
				|| PanelBorrowers.txtmail.getText().length() == 0) {
			PanelBorrowers.txtmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			PanelBorrowers.txtmail.setText("");
		}

		if (PanelBorrowers.txtname.getText().length() > 20 || RegexName() != true
				|| PanelBorrowers.txtname.getText().length() == 0) {
			PanelBorrowers.txtname.setBorder(BorderFactory.createLineBorder(Color.RED));
			PanelBorrowers.txtname.setText("");
		}

		if (PanelBorrowers.txtadd.getText().length() == 0) {
			PanelBorrowers.txtadd.setBorder(BorderFactory.createLineBorder(Color.RED));
			PanelBorrowers.txtadd.setText("");
		}

		if (PanelBorrowers.txtphone.getText().length() > 11 || RegexInt() != true
				|| PanelBorrowers.txtphone.getText().length() == 0) {
			PanelBorrowers.txtphone.setBorder(BorderFactory.createLineBorder(Color.RED));
			PanelBorrowers.txtphone.setText("");
		}
	}
}
