package nytro.model;

import java.sql.Date;

public class Videogioco {
	public Videogioco() {
		codice = -1;
		ISIN = "";
		PEGI = -1;
		rilascio = null;
		rimozione = null;
		titolo = "";
		votoMedio = -1;	
	}
	
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public Date getRilascio() {
		return rilascio;
	}
	public void setRilascio(Date rilascio) {
		this.rilascio = rilascio;
	}
	public Date getRimozione() {
		return rimozione;
	}
	public void setRimozione(Date rimozione) {
		this.rimozione = rimozione;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public float getVotoMedio() {
		return votoMedio;
	}
	public void setVotoMedio(float votoMedio) {
		this.votoMedio = votoMedio;
	}
	public int getPEGI() {
		return PEGI;
	}
	public void setPEGI(int pEGI) {
		PEGI = pEGI;
	}
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videogioco other = (Videogioco) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Videogioco [codice=" + codice + ", rilascio=" + rilascio + ", rimozione=" + rimozione + ", titolo="
				+ titolo + ", votoMedio=" + votoMedio + ", PEGI=" + PEGI + ", ISIN=" + ISIN + "]";
	}

	int codice;
	Date rilascio;
	Date rimozione;
	String titolo;
	float votoMedio;
	int PEGI;
	String ISIN;
}
