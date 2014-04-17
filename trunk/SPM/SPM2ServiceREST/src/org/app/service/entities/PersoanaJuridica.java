package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class PersoanaJuridica extends Client{
	private String denumire;
	private Integer CUI;
	private String nrRegCom;
	
	public PersoanaJuridica(String adresa, String telefon, String email,
			String contBanca, String denumire, Integer cUI, String nrRegCom) {
		super(adresa, telefon, email, contBanca);
		this.denumire = denumire;
		CUI = cUI;
		this.nrRegCom = nrRegCom;
	}

	
	public PersoanaJuridica() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PersoanaJuridica(String adresa, String telefon, String email,
			String contBanca) {
		super(adresa, telefon, email, contBanca);
		// TODO Auto-generated constructor stub
	}


	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public Integer getCUI() {
		return CUI;
	}

	public void setCUI(Integer cUI) {
		CUI = cUI;
	}

	public String getNrRegCom() {
		return nrRegCom;
	}

	public void setNrRegCom(String nrRegCom) {
		this.nrRegCom = nrRegCom;
	}
	
	
	
	
}
