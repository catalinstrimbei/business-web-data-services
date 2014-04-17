package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
public abstract class Contract {
	@Id
	@GeneratedValue
	private Integer nrContract;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Produs produs;
	private Date dataContract;
	private Date dataInceput;
	private Date dataIncheiere;
	private String observatii;
	
	public Contract(Integer nrContract, Client client, Produs produs,
			Date dataContract, Date dataInceput, Date dataIncheiere,
			String observatii) {
		super();
		this.nrContract = nrContract;
		this.client = client;
		this.produs = produs;
		this.dataContract = dataContract;
		this.dataInceput = dataInceput;
		this.dataIncheiere = dataIncheiere;
		this.observatii = observatii;
	}

	
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getNrContract() {
		return nrContract;
	}

	public void setNrContract(Integer nrContract) {
		this.nrContract = nrContract;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Produs getProdus() {
		return produs;
	}

	public void setProdus(Produs produs) {
		this.produs = produs;
	}

	public Date getDataContract() {
		return dataContract;
	}

	public void setDataContract(Date dataContract) {
		this.dataContract = dataContract;
	}

	public Date getDataInceput() {
		return dataInceput;
	}

	public void setDataInceput(Date dataInceput) {
		this.dataInceput = dataInceput;
	}

	public Date getDataIncheiere() {
		return dataIncheiere;
	}

	public void setDataIncheiere(Date dataIncheiere) {
		this.dataIncheiere = dataIncheiere;
	}

	public String getObservatii() {
		return observatii;
	}

	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}
	
	
	
	
}
