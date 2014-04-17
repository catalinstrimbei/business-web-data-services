package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
//@MappedSuperclass
public class PersoanaFizica extends Client{
	private String nume;
	private String prenume;
	

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
