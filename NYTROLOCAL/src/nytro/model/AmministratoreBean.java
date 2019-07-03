package nytro.model;

import java.io.Serializable;

public class AmministratoreBean extends AccountBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5937880810280541420L;
	private String nome;
	private String cognome;
	
	public AmministratoreBean() {
		;
	}

	public AmministratoreBean(AccountBean bean) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		AmministratoreBean other = (AmministratoreBean) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() +"[nome=" + nome + ", cognome=" + cognome + "]";
	}

}
