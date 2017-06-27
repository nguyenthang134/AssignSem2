package controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BorrowerController {
	String regexInt = "[0-9]";
	String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	String regexName = "^[\\p{L} .'-]+$";

	public boolean regexInt(String number) {
		Pattern p = Pattern.compile(regexInt);
		Matcher m = p.matcher(number);
		return m.find();
	}

	public boolean regexMail(String mail) {
		Pattern p = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(mail);
		return m.find();
	}

	public boolean regexName(String name) {
		Pattern p = Pattern.compile(regexName);
		Matcher m = p.matcher(name);
		return m.find();
	}

	public HashMap<String, String> validate(String txtId, String txtname, String txtmail, String txtadd,
			String txtphone) {
		HashMap<String, String> mapError = new HashMap<>();
		if (txtId == null || txtId.isEmpty()) {
			mapError.put("txtId", "ID cannot be empty");
		}
//		if (regexInt(txtId)) {
//			mapError.put("txtId1", "ID MUST be a number");
//		}
		if (txtname == null || txtname.isEmpty()) {
			mapError.put("txtname", "Name cannot be empty");
		}
//		if (regexName(txtname)) {
//			mapError.put("txtname1", "Name MUST be an alphabet");
//		}
		if (txtmail == null || txtmail.isEmpty()) {
			mapError.put("txtmail", "Email cannot be empty");
		}
//		if (regexMail(txtmail)) {
//			mapError.put("txtmail1", "Email is invalid");
//		}
		if (txtadd == null || txtadd.isEmpty()) {
			mapError.put("txtadd", "Address cannot be empty");
		}
		if (txtphone == null || txtphone.isEmpty()) {
			mapError.put("txtphone", "Phone cannot be empty");
		}
//		if (regexInt(txtphone)) {
//			mapError.put("txtphone1", "Phone MUST be a number");
//		}
		return mapError;
	}
}
