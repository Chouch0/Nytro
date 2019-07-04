package nytro.model;

import java.io.Serializable;

public class VideogiocoDemoBean extends VideogiocoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8170460594028385269L;
	private int codiceVideogiocoPrincipale;
	private int durata;
	
	public int getCodiceVideogiocoPrincipale() {
		return codiceVideogiocoPrincipale;
	}

	public void setCodiceVideogiocoPrincipale(int codiceVideogiocoPrincipale) {
		this.codiceVideogiocoPrincipale = codiceVideogiocoPrincipale;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + codiceVideogiocoPrincipale;
		result = prime * result + durata;
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
		VideogiocoDemoBean other = (VideogiocoDemoBean) obj;
		if (codiceVideogiocoPrincipale != other.codiceVideogiocoPrincipale)
			return false;
		if (durata != other.durata)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() +"[codiceVideogiocoPrincipale=" + codiceVideogiocoPrincipale + ", durata=" + durata
				+ "]";
	}
	
	

}
