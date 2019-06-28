package nytro.model;

public class VideogiocoPagamentoBean extends VideogiocoBean{
	private double prezzo;
	private int copieVendute;
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getCopieVendute() {
		return copieVendute;
	}

	public void setCopieVendute(int copieVendute) {
		this.copieVendute = copieVendute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + copieVendute;
		long temp;
		temp = Double.doubleToLongBits(prezzo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		VideogiocoPagamentoBean other = (VideogiocoPagamentoBean) obj;
		if (copieVendute != other.copieVendute)
			return false;
		if (Double.doubleToLongBits(prezzo) != Double.doubleToLongBits(other.prezzo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "[prezzo=" + prezzo + ", copieVendute=" + copieVendute + "]";
	}
	
	

}
