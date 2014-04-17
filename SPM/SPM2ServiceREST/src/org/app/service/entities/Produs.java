package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produs {
	@Id
	@GeneratedValue
	private Integer idProdus;
	private String nume;
	@ManyToOne
	private Licenta licenta;
	@ManyToOne
	private Garantie garantie;
	
	public Produs(String nume, Licenta licenta, Garantie garantie) {
		super();
		this.nume = nume;
		this.licenta = licenta;
		this.garantie = garantie;
	}

	
	public Produs() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idProdus == null) ? 0 : idProdus.hashCode());
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
		Produs other = (Produs) obj;
		if (idProdus == null) {
			if (other.idProdus != null)
				return false;
		} else if (!idProdus.equals(other.idProdus))
			return false;
		return true;
	}


	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public Licenta getLicenta() {
		return licenta;
	}

	public void setLicenta(Licenta licenta) {
		this.licenta = licenta;
	}

	public Garantie getGarantie() {
		return garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}
	
	
	

}
