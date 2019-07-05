package nytro.model;

import java.io.Serializable;

import com.mysql.cj.jdbc.Blob;

public class AccountBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9143873100465034388L;
	private String username;
	private String password;
	private String email;
	private String emailRecupero;
	private String cellulare;
	private String data;
	private String ora;
	private String ip;
	private int ruolo;
	private Blob imgProfilo;
	
//Necessario inserire le blob per: 	  Img_Profilo		longblob,
	
	
/* Per ora non prevediamo la possibilità di usare password cifrate nel database
 
	public void setPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(password.getBytes(StandardCharsets.UTF_8));
			this.passwordhash = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
*/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellulare == null) ? 0 : cellulare.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailRecupero == null) ? 0 : emailRecupero.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((ora == null) ? 0 : ora.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ruolo;
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
		AccountBean other = (AccountBean) obj;
		if (cellulare == null) {
			if (other.cellulare != null)
				return false;
		} else if (!cellulare.equals(other.cellulare))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailRecupero == null) {
			if (other.emailRecupero != null)
				return false;
		} else if (!emailRecupero.equals(other.emailRecupero))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (ora == null) {
			if (other.ora != null)
				return false;
		} else if (!ora.equals(other.ora))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ruolo != other.ruolo)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[username=" + username + ", password=" + password + ", email=" + email + ", emailRecupero="
				+ emailRecupero + ", cellulare=" + cellulare + ", data=" + data + ", ora=" + ora + ", ip=" + ip
				+ ", ruolo=" + ruolo + "]";
	}
	
	public AccountBean clone() {
		try {
			AccountBean tmp = (AccountBean) super.clone();
			return tmp;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
