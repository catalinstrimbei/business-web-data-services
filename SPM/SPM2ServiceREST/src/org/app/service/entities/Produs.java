package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produs implements Serializable{
	@Id
	@GeneratedValue
	private Integer idProdus;
	private String nume;
	@OneToMany(/*mappedBy="contract"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	private List<Licenta> licenta = new ArrayList<Licenta>();
	@ManyToOne
	private Garantie garantie;
	
	public static Produs toDTOAggregate(Produs produs){
		if(produs==null)
			return null;
		Produs produsDTO=produs.toDTO();
		List<Licenta> licentaDTO= Licenta.toDTOList(produs.getLicenta());
		return produsDTO;
	}
		
		
		private Produs toDTO() {
			// TODO Auto-generated method stub
			return null;
		}


		public Produs(String nume, List<Licenta> licenta, Garantie garantie) {
			
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

	public List<Licenta> getLicenta() {
		return licenta;
	}

	

	public Garantie getGarantie() {
		return garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}
	

	public void setLicenta(List<Licenta> licenta) {
		this.licenta = licenta;
	}
	

}
