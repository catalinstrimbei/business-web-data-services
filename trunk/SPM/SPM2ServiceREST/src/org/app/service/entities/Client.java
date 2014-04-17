package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public abstract class Client implements Serializable{
	@Id
	@GeneratedValue
	private Integer idClient;
	private String adresa;
	private String telefon;
	private String email;
	private String contBanca;
	
	
	
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


	public Integer getIdClient() {
		return idClient;
	}


	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	
}
