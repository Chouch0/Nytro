package nytro.model;

import java.io.Serializable;

public class RecensioneBean implements Serializable {
	
	private int numRecensione;
	private int codVideogioco;
	private String username;
	private String commento;
	private double voto;
	
	public int getNumRecensione() {
		return numRecensione;
	}
	public void setNumRecensione(int numRecensione) {
		this.numRecensione = numRecensione;
	}
	public int getCodVideogioco() {
		return codVideogioco;
	}
	public void setCodVideogioco(int codVideogioco) {
		this.codVideogioco = codVideogioco;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	public double getVoto() {
		return voto;
	}
	public void setVoto(double voto) {
		this.voto = voto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codVideogioco;
		result = prime * result + ((commento == null) ? 0 : commento.hashCode());
		result = prime * result + numRecensione;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		long temp;
		temp = Double.doubleToLongBits(voto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		RecensioneBean other = (RecensioneBean) obj;
		if (codVideogioco != other.codVideogioco)
			return false;
		if (commento == null) {
			if (other.commento != null)
				return false;
		} else if (!commento.equals(other.commento))
			return false;
		if (numRecensione != other.numRecensione)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (Double.doubleToLongBits(voto) != Double.doubleToLongBits(other.voto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return getClass().getName() +"[numRecensione=" + numRecensione + ", codVideogioco=" + codVideogioco + ", username="
				+ username + ", commento=" + commento + ", voto=" + voto + "]";
	}
	
	

}
