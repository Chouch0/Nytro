package nytro.model;

import java.io.Serializable;

public class VideogiocoFreeToPlayBean extends VideogiocoBean implements Serializable {


	private String modalitaDiGioco;
	
	public String getModalitaDiGioco() {
		return modalitaDiGioco;
	}

	public void setModalitaDiGioco(String modalitaDiGioco) {
		this.modalitaDiGioco = modalitaDiGioco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((modalitaDiGioco == null) ? 0 : modalitaDiGioco.hashCode());
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
		VideogiocoFreeToPlayBean other = (VideogiocoFreeToPlayBean) obj;
		if (modalitaDiGioco == null) {
			if (other.modalitaDiGioco != null)
				return false;
		} else if (!modalitaDiGioco.equals(other.modalitaDiGioco))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() +"[modalitaDiGioco=" + modalitaDiGioco + "]";
	}
	
	
}
