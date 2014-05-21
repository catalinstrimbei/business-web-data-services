package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
//import com.sun.xml.internal.txw2.annotation.XmlElement;
@XmlRootElement(name="client")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
//@MappedSuperclass
public class PersoanaFizica extends Client implements Serializable{
	private String nume;
	private String prenume;
	
	public PersoanaFizica toDTO(){
		PersoanaFizica clientDTO= new PersoanaFizica (adresa, telefon, email,contBanca,nume, prenume);
		return clientDTO;
		
	}
	public static PersoanaFizica toDTOAggregate(PersoanaFizica client){
		if(client==null)
			return null;
		PersoanaFizica clientDTO=client.toDTO();
		List<Contract> contracteDTO= Contract.toDTOList(client.getContract());
		return clientDTO;
	}
	public static PersoanaFizica[] toDTOList(Collection<PersoanaFizica> clienti){
		List<PersoanaFizica> clientDTOList= new ArrayList<>();
		for(PersoanaFizica cl:clienti){
			clientDTOList.add(cl.toDTO());
		} 
		return clientDTOList.toArray(new PersoanaFizica[0]);
	}
	public static String BASE_URL="http://localhost:8080/ScrumREST/client/";
	@XmlElement(name="link")
	
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdClient();
		return new AtomLink(restUrl, "get-client");
	}

	/*public Object getContract() {
		// TODO Auto-generated method stub
		return null;
	}*/
	public PersoanaFizica(String adresa, String telefon, String email,
			String contBanca, String nume, String prenume) {
		super(adresa, telefon, email, contBanca);
		this.nume = nume;
		this.prenume = prenume;
	}
	
	
	public PersoanaFizica() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PersoanaFizica(String adresa, String telefon, String email,
			String contBanca) {
		super(adresa, telefon, email, contBanca);
		// TODO Auto-generated constructor stub
	}


	public PersoanaFizica(String nume2, String prenume2) {
		// TODO Auto-generated constructor stub
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
	public static Collection<Client> toDTOs(Collection<Client> collection) {
		// TODO Auto-generated method stub
		return null;
	}
	@XmlElement()
	public Integer getIdClient() {
		return idClient;
	}
	@XmlElementWrapper(name="contract") @XmlElement(name="contract")
	public List<Contract> getContract() {
		return contract;
	}

}
