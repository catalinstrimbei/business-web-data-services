package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.xml.internal.txw2.annotation.XmlElement;
@Entity
public abstract class Client {
	public static String BASE_URL;
	@Id
	@GeneratedValue
	protected Integer idClient;
	protected String adresa;
	protected String telefon;
	protected String email;
	protected String contBanca;
	@OneToMany(/*mappedBy="client"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	protected List<Contract> contract = new ArrayList<Contract>();
	
	
	
	public Client(List<Contract> contract) {
		super();
		this.contract = contract;
	}


	public Client(String adresa, String telefon, String email, String contBanca) {
		super();
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
		this.contBanca = contBanca;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idClient == null) ? 0 : idClient.hashCode());
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
		Client other = (Client) obj;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		return true;
	}


	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContBanca() {
		return contBanca;
	}
	public void setContBanca(String contBanca) {
		this.contBanca = contBanca;
	}

	@XmlElement()
	public Integer getIdClient() {
		return idClient;
	}


	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}



 void setContract(List<Contract> contracteClient) {
		// TODO Auto-generated method stub
		
	}


public static Client toDTOAggregate(Client client) {
	// TODO Auto-generated method stub
	return null;
}

	
}
