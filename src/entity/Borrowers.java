package entity;

public class Borrowers {

	int identification;
	String borrowers_name;
	String borrowers_mail;
	String borrowers_address;
	int borrowers_phone;
	int borrowers_status;
	int borrowed_books;
	int overdue_books;
	double overdue_limit;
	
	public int getBorrowers_status() {
		return borrowers_status;
	}

	public void setBorrowers_status(int borrowers_status) {
		this.borrowers_status = borrowers_status;
	}

	public int getBorrowed_books() {
		return borrowed_books;
	}

	public void setBorrowed_books(int borrowed_books) {
		this.borrowed_books = borrowed_books;
	}

	public int getOverdue_books() {
		return overdue_books;
	}

	public void setOverdue_books(int overdue_books) {
		this.overdue_books = overdue_books;
	}

	public double getOverdue_limit() {
		return overdue_limit;
	}

	public void setOverdue_limit(double overdue_limit) {
		this.overdue_limit = overdue_limit;
	}
	
	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public String getBorrowers_name() {
		return borrowers_name;
	}

	public void setBorrowers_name(String borrowers_name) {
		this.borrowers_name = borrowers_name;
	}

	public String getBorrowers_mail() {
		return borrowers_mail;
	}

	public void setBorrowers_mail(String borrowers_mail) {
		this.borrowers_mail = borrowers_mail;
	}

	public String getBorrowers_address() {
		return borrowers_address;
	}

	public void setBorrowers_address(String borrowers_address) {
		this.borrowers_address = borrowers_address;
	}

	public int getBorrowers_phone() {
		return borrowers_phone;
	}

	public void setBorrowers_phone(int borrowers_phone) {
		this.borrowers_phone = borrowers_phone;
	}

	@Override
	public String toString() {
		return "Borrowers [identification=" + identification + ", borrowers_name=" + borrowers_name
				+ ", borrowers_mail=" + borrowers_mail + ", borrowers_address=" + borrowers_address
				+ ", borrowers_phone=" + borrowers_phone + ", borrowers_status=" + borrowers_status
				+ ", borrowed_books=" + borrowed_books + ", overdue_books=" + overdue_books + ", overdue_limit="
				+ overdue_limit + "]";
	}
	
	
}
