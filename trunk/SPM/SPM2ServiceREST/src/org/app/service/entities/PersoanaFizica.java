package org.app.service.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

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

	public Object getContract() {
		// TODO Auto-generated method stub
		return null;
	}
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
	
	

}
