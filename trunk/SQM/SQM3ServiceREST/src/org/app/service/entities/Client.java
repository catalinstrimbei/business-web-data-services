package org.app.service.entities;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Client implements Serializable{
	
	  @Id @GeneratedValue 
	  private Integer clientId;
	  private String cnp;
	  private String firstName;
	  private String lastName;
	  
	  @Temporal(TemporalType.DATE)
	  private Date dateOfBirth;
	  private String adress;
	  private String city;
	  private String phoneNumber;
	  
	  @OneToMany(mappedBy="client")
	  private List<Comunication> comunication=new ArrayList<Comunication>();
	  
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Comunication> getComunication() {
			return comunication;
		}
	public void setComunication(List<Comunication> comunication) {
			this.comunication = comunication;
		}
	  	
}
