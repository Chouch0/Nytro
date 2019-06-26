package nytro.model;

public class CasaEditrice {
	public CasaEditrice() {
		ISIN = "";
		CEO = "";
		sito = "";
	}
	
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}
	public String getCEO() {
		return CEO;
	}
	public void setCEO(String cEO) {
		CEO = cEO;
	}
	public String getSito() {
		return sito;
	}
	public void setSito(String sito) {
		this.sito = sito;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CasaEditrice other = (CasaEditrice) obj;
		if (ISIN == null) {
			if (other.ISIN != null)
				return false;
		} else if (!ISIN.equals(other.ISIN))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CasaEditrice [ISIN=" + ISIN + ", CEO=" + CEO + ", sito=" + sito + "]";
	}

	String ISIN;
	String CEO;
	String sito;
}
