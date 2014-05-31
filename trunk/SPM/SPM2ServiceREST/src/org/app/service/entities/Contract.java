package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Contract implements Serializable{
	@Id
	@GeneratedValue
	private Integer nrContract;
	@ManyToOne
	private Client client;
	@OneToMany(cascade=ALL, fetch= FetchType.EAGER)
	private List<Produs> produse = new ArrayList<>();
	@Temporal (TemporalType.DATE)
	private Date dataContract;
	private String observatii;
	@ManyToOne
	private Activitate activitate;
	
	public Contract toDTO(){
		return new Contract (nrContract, observatii, dataContract,  client.toDTO());
	}
	public static List<Contract> toDTOList(List<Contract> contracte){
		List<Contract> contractDTOList= new ArrayList<>();
		for(Contract c:contracte){
			contractDTOList.add(c.toDTO());
		}
		return contractDTOList;
	}

	@XmlElement
	public Integer getNrContract() {
		return nrContract;
	}
	
	
	public static String BASE_URL= Client.BASE_URL;
	@XmlElement(name="link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ this.getClient().getIdClient()
				+"/contract/"
				+this.getNrContract();
		return new AtomLink(restUrl, "get-contract");
	}
		
	public Contract(Object object, String string, Date date, Client client) {
	super();
		// TODO Auto-generated constructor stub
	}

	public String getObservatii() {
		return observatii;
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
		return produse;
	}

	public void setProdus(List<Produs> produs) {
		this.produse = produs;
	}

	public Date getDataContract() {
		return dataContract;
	}

	public void setDataContract(Date date) {
		this.dataContract = date;
	}

	
	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}
	public Activitate getActivitate(){
		return activitate;
	}
	public void setActivitate(Activitate activitate){
		this.activitate= activitate;
	}
	public static List<Contract> toDTOList(Object contract) {
		// TODO Auto-generated method stub
		return null;
	}
	public static Contract toDTOAggregate(Contract contract) {
		// TODO Auto-generated method stub
		return null;
	}
	public void setActivitate(String string) {
		// TODO Auto-generated method stub
		
	}
	public List<Produs> getProduse() {
		return produse;
	}
	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}
	public Contract(Integer nrContract, Client client, List<Produs> produse,
			Date dataContract, String observatii, Activitate activitate) {
		super();
		this.nrContract = nrContract;
		this.client = client;
		this.produse = produse;
		this.dataContract = dataContract;
		this.observatii = observatii;
		this.activitate = activitate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activitate == null) ? 0 : activitate.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result
				+ ((dataContract == null) ? 0 : dataContract.hashCode());
		result = prime * result
				+ ((nrContract == null) ? 0 : nrContract.hashCode());
		result = prime * result
				+ ((observatii == null) ? 0 : observatii.hashCode());
		result = prime * result + ((produse == null) ? 0 : produse.hashCode());
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
		Contract other = (Contract) obj;
		if (activitate == null) {
			if (other.activitate != null)
				return false;
		} else if (!activitate.equals(other.activitate))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (dataContract == null) {
			if (other.dataContract != null)
				return false;
		} else if (!dataContract.equals(other.dataContract))
			return false;
		
	
		if (nrContract == null) {
			if (other.nrContract != null)
				return false;
		} else if (!nrContract.equals(other.nrContract))
			return false;
		if (observatii == null) {
			if (other.observatii != null)
				return false;
		} else if (!observatii.equals(other.observatii))
			return false;
		if (produse == null) {
			if (other.produse != null)
				return false;
		} else if (!produse.equals(other.produse))
			return false;
		return true;
	}
	public Contract() {
		super();
	}


	/*public Contract toDTO() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
