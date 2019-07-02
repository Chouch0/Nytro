package nytro.model;

import java.io.Serializable;

public class CasaEditriceBean extends AccountBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ISIN;
	private String nomeCasaEditrice;
	private String CEO;
	private String sitoWeb;
	
	public CasaEditriceBean() {
		;
	}
	
	public CasaEditriceBean(AccountBean bean) {
		this.setUsername(bean.getUsername());
		this.setPassword(bean.getPassword());
		this.setEmail(bean.getEmail());
		this.setEmailRecupero(bean.getEmailRecupero());
		this.setCellulare(bean.getCellulare());
		this.setData(bean.getData());
		this.setOra(bean.getOra());
		this.setIp(bean.getIp());
		this.setRuolo(bean.getRuolo());
	}
	
	public String getISIN() {
		return ISIN;
	}
	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}
	public String getNomeCasaEditrice() {
		return nomeCasaEditrice;
	}
	public void setNomeCasaEditrice(String nomeCasaEditrice) {
		this.nomeCasaEditrice = nomeCasaEditrice;
	}
	public String getCEO() {
		return CEO;
	}
	public void setCEO(String cEO) {
		CEO = cEO;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}
	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((CEO == null) ? 0 : CEO.hashCode());
		result = prime * result + ((ISIN == null) ? 0 : ISIN.hashCode());
		result = prime * result + ((nomeCasaEditrice == null) ? 0 : nomeCasaEditrice.hashCode());
		result = prime * result + ((sitoWeb == null) ? 0 : sitoWeb.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CasaEditriceBean other = (CasaEditriceBean) obj;
		if (CEO == null) {
			if (other.CEO != null)
				return false;
		} else if (!CEO.equals(other.CEO))
			return false;
		if (ISIN == null) {
			if (other.ISIN != null)
				return false;
		} else if (!ISIN.equals(other.ISIN))
			return false;
		if (nomeCasaEditrice == null) {
			if (other.nomeCasaEditrice != null)
				return false;
		} else if (!nomeCasaEditrice.equals(other.nomeCasaEditrice))
			return false;
		if (sitoWeb == null) {
			if (other.sitoWeb != null)
				return false;
		} else if (!sitoWeb.equals(other.sitoWeb))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "[ISIN=" + ISIN + ", nomeCasaEditrice=" + nomeCasaEditrice + ", CEO=" + CEO
				+ ", sitoWeb=" + sitoWeb + "]";
	}
	
	
	
}
