package nytro.model;

public class GiocatoreBean extends AccountBean{
	
	private String dataNascita;
	private String dataIscrizione;
	private String genere;
	
	public GiocatoreBean(AccountBean bean) {
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
	
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(String dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataIscrizione == null) ? 0 : dataIscrizione.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
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
		GiocatoreBean other = (GiocatoreBean) obj;
		if (dataIscrizione == null) {
			if (other.dataIscrizione != null)
				return false;
		} else if (!dataIscrizione.equals(other.dataIscrizione))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "[dataNascita=" + dataNascita + ", dataIscrizione=" + dataIscrizione + ", genere=" + genere + "]";
	}
	
	
	
	

}
