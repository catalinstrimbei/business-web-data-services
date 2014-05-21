package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Operator implements Serializable{
	@Id
	@GeneratedValue
	private Integer idOperator;
	private String nume;
	private String prenume;
	private String adresa;
	private String email;
	
	@OneToMany
	(/*mappedBy="client"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	protected List<Activitate> activitate = new ArrayList<Activitate>();
	public static Operator toDTOAggregate(Operator operator){
		if(operator==null)
			return null;
		Operator operatorDTO=operator.toDTO();
		List<Activitate> activitateDTO= Activitate.toDTOList(operator.getActivitate());
		return operatorDTO;
	}
	private Object getActivitate() {
		// TODO Auto-generated method stub
		return null;
	}

	private Operator toDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Operator(String nume, String prenume, String adresa, String email) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
		this.email = email;
	}
	@OneToMany
	(/*mappedBy="client"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	protected List<Activitate> activitati = new ArrayList<Activitate>();
	
	public Operator() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOperator == null) ? 0 : idOperator.hashCode());
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
		Operator other = (Operator) obj;
		if (idOperator == null) {
			if (other.idOperator != null)
				return false;
		} else if (!idOperator.equals(other.idOperator))
			return false;
		return true;
	}


	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
