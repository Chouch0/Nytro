package nytro.model;

import java.sql.Date;
import java.sql.Time;

public class Account {
	Account() {
		cellulare = "";
		data = null;
		email = "";
		emailRecupero = "";
		IP = "";
		ora = null;
		password = "";
		username = "";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailRecupero() {
		return emailRecupero;
	}
	public void setEmailRecupero(String emailRecupero) {
		this.emailRecupero = emailRecupero;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOra() {
		return ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", email=" + email + ", emailRecupero="
				+ emailRecupero + ", cellulare=" + cellulare + ", data=" + data + ", ora=" + ora + ", IP=" + IP + "]";
	}

	String username;
	String password;
	String email;
	String emailRecupero;
	String cellulare;
	Date data; //ultimo accesso
	Time ora; //ultimo accesso
	String IP;
}
