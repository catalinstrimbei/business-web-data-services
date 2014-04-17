package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Conturi_clienti {

	@Id
	@GeneratedValue Integer UserName;
	String Parola;
	
	@OneToOne
	private Clienti clientiCt;
	
	public Integer getUserName() {
		return UserName;
	}
	public void setUserName(Integer userName) {
		UserName = userName;
	}
	public String getParola() {
		return Parola;
	}
	public void setParola(String parola) {
		Parola = parola;
	}
	public Conturi_clienti(Integer userName, String parola) {
		super();
		UserName = userName;
		Parola = parola;
	}
	public Conturi_clienti() {
		super();
	}
	public Clienti getClientiCt() {
		return clientiCt;
	}
	public void setClientiCt(Clienti clientiCt) {
		this.clientiCt = clientiCt;
	}
	
	
}
