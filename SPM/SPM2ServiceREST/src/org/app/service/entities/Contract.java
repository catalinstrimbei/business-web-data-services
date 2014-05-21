package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import static javax.persistence.CascadeType.ALL;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name="contract")
@XmlAccessorType(XmlAccessType.NONE)
@Inheritance(strategy=InheritanceType.JOINED)
@Entity
public abstract class Contract {
	@Id
	@GeneratedValue
	protected Integer nrContract;
	@ManyToOne
	protected Client client;
	@OneToMany(/*mappedBy="contract"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	protected List<Produs> produs = new ArrayList<Produs>();
	protected Date dataContract;
	protected Date dataInceput;
	protected Date dataIncheiere;
	protected String observatii;
	
	public Contract(Integer nrContract, Client client, List<Produs> produs,
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

	@XmlElement
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

	public List<Produs> getProdus() {
		return produs;
	}

	public void setProdus(List<Produs> produs) {
		this.produs = produs;
	}

	public Date getDataContract() {
		return dataContract;
	}

	public void setDataContract(Date date) {
		this.dataContract = date;
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


	public static List<Contract> toDTOList(Object contract) {
		// TODO Auto-generated method stub
		return null;
	}


	public Contract toDTO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
