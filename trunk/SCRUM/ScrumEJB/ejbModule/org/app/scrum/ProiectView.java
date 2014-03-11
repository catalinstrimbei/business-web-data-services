package org.app.scrum;

public class ProiectView {
	private Integer nrProiect;
	
	private String numeProiect;

	public Integer getNrProiect() {
		return nrProiect;
	}

	public void setNrProiect(Integer nrProiect) {
		this.nrProiect = nrProiect;
	}

	public String getNumeProiect() {
		return numeProiect;
	}

	public void setNumeProiect(String numeProiect) {
		this.numeProiect = numeProiect;
	}

	public ProiectView(Integer nrProiect, String numeProiect) {
		super();
		this.nrProiect = nrProiect;
		this.numeProiect = numeProiect;
	}

	public ProiectView() {
		super();
	}

	@Override
	public String toString() {
		return "ProiectView [nrProiect=" + nrProiect + ", numeProiect="
				+ numeProiect + "]";
	}
	
	
}
