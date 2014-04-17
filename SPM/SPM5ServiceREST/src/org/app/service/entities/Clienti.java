package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clienti {

	@Id
	@GeneratedValue Integer idClient;
	String NumeClient;
	String AdresaClient;
	String EmailClient;
	Integer Telefon;
	Integer idRaport;
	Integer idCerereTraining;
	Integer UserName;
	Integer idCerereSuport;
	
	@OneToMany(mappedBy = "clientiRap")
	private List<Rapoarte_incidente> rapoarteInc = new ArrayList<Rapoarte_incidente>();
	@OneToMany(mappedBy = "clientiTr")
	private List<Cerere_training> cerereTraining = new ArrayList <Cerere_training>();
	@OneToMany(mappedBy = "clientiSup")
	private List<Cereri_suport> cerereSuport = new ArrayList <Cereri_suport>();
	
	public Integer getIdClient() {
		return idClient;
	}
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	public String getNumeClient() {
		return NumeClient;
	}
	public void setNumeClient(String numeClient) {
		NumeClient = numeClient;
	}
	public String getAdresaClient() {
		return AdresaClient;
	}
	public void setAdresaClient(String adresaClient) {
		AdresaClient = adresaClient;
	}
	public String getEmailClient() {
		return EmailClient;
	}
	public void setEmailClient(String emailClient) {
		EmailClient = emailClient;
	}
	public Integer getTelefon() {
		return Telefon;
	}
	public void setTelefon(Integer telefon) {
		Telefon = telefon;
	}
	public Integer getIdRaport() {
		return idRaport;
	}
	public void setIdRaport(Integer idRaport) {
		this.idRaport = idRaport;
	}
	public Integer getIdCerereTraining() {
		return idCerereTraining;
	}
	public void setIdCerereTraining(Integer idCerereTraining) {
		this.idCerereTraining = idCerereTraining;
	}
	public Integer getUserName() {
		return UserName;
	}
	public void setUserName(Integer userName) {
		UserName = userName;
	}
	public Integer getIdCerereSuport() {
		return idCerereSuport;
	}
	public void setIdCerereSuport(Integer idCerereSuport) {
		this.idCerereSuport = idCerereSuport;
	}
	public Clienti(Integer idClient, String numeClient, String adresaClient,
			String emailClient, Integer telefon, Integer idRaport,
			Integer idCerereTraining, Integer userName, Integer idCerereSuport) {
		super();
		this.idClient = idClient;
		NumeClient = numeClient;
		AdresaClient = adresaClient;
		EmailClient = emailClient;
		Telefon = telefon;
		this.idRaport = idRaport;
		this.idCerereTraining = idCerereTraining;
		UserName = userName;
		this.idCerereSuport = idCerereSuport;
	}
	public Clienti() {
		super();
	}
	public List<Rapoarte_incidente> getRapoarteInc() {
		return rapoarteInc;
	}
	public void setRapoarteInc(List<Rapoarte_incidente> rapoarteInc) {
		this.rapoarteInc = rapoarteInc;
	}
	public List<Cerere_training> getCerereTraining() {
		return cerereTraining;
	}
	public void setCerereTraining(List<Cerere_training> cerereTraining) {
		this.cerereTraining = cerereTraining;
	}
	public List<Cereri_suport> getCerereSuport() {
		return cerereSuport;
	}
	public void setCerereSuport(List<Cereri_suport> cerereSuport) {
		this.cerereSuport = cerereSuport;
	}
	
}
