package nytro.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class VideogiocoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7010010608149940666L;
	private int codice;
	private String ISIN;
	private String dataRilascio;
	private String dataRimozione;
	private String titolo;
	private float votoMedio;
	private int PEGI;
	//necessario inserire blob 
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}
	public String getDataRilascio() {
		return dataRilascio;
	}
	public void setDataRilascio(String dataRilascio) {
		this.dataRilascio = dataRilascio;
	}
	public String getDataRimozione() {
		return dataRimozione;
	}
	public void setDataRimozione(String dataRimozione) {
		this.dataRimozione = dataRimozione;
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
	public List<String> getGenere() throws SQLException {
		return VideogiocoDAO.doGetGenere(this);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISIN == null) ? 0 : ISIN.hashCode());
		result = prime * result + PEGI;
		result = prime * result + codice;
		result = prime * result + ((dataRilascio == null) ? 0 : dataRilascio.hashCode());
		result = prime * result + ((dataRimozione == null) ? 0 : dataRimozione.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(votoMedio);
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
		VideogiocoBean other = (VideogiocoBean) obj;
		if (ISIN == null) {
			if (other.ISIN != null)
				return false;
		} else if (!ISIN.equals(other.ISIN))
			return false;
		if (PEGI != other.PEGI)
			return false;
		if (codice != other.codice)
			return false;
		if (dataRilascio == null) {
			if (other.dataRilascio != null)
				return false;
		} else if (!dataRilascio.equals(other.dataRilascio))
			return false;
		if (dataRimozione == null) {
			if (other.dataRimozione != null)
				return false;
		} else if (!dataRimozione.equals(other.dataRimozione))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		if (Double.doubleToLongBits(votoMedio) != Double.doubleToLongBits(other.votoMedio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return getClass().getName() + "[codice=" + codice + ", ISIN=" + ISIN + ", dataRilascio=" + dataRilascio
				+ ", dataRimozione=" + dataRimozione + ", titolo=" + titolo + ", votoMedio=" + votoMedio + ", PEGI="
				+ PEGI + "]";
	}
	
	
	
	

}
