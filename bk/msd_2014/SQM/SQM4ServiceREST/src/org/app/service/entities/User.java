package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */

@Entity
public class User implements IManagementUser {

	@Id @GeneratedValue
	private int idUser;
	private String numeUtilizator;
	private String parola;
	private String numePrenumeReal;
	private String adresaEmail;
	
	//public Utilitati m_Utilitati;

	//1 user are 1 sg rol--> oneToOne
	// private Rol rol;

	
	//nu cred ca mai trebuie fiindca avem utilitati
	private List<Bug> listaBugs;
	public User(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param user
	 */
	public void getIdRol(User user){

	}

	public User create(){
		return null;
	}

	/**
	 * 
	 * @param user
	 */
	public void delete(User user){

	}

	/**
	 * 
	 * @param user
	 */
	public void select(User user){

	}

	/**
	 * 
	 * @param user
	 */
	public void update(User user){

	}


	//get &set
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNumeUtilizator() {
		return numeUtilizator;
	}

	public void setNumeUtilizator(String numeUtilizator) {
		this.numeUtilizator = numeUtilizator;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getNumePrenumeReal() {
		return numePrenumeReal;
	}

	public void setNumePrenumeReal(String numePrenumeReal) {
		this.numePrenumeReal = numePrenumeReal;
	}

	public String getAdresaEmail() {
		return adresaEmail;
	}

	public void setAdresaEmail(String adresaEmail) {
		this.adresaEmail = adresaEmail;
	}

	public List<Bug> getListaBugs() {
		return listaBugs;
	}

	public void setListaBugs(List<Bug> listaBugs) {
		this.listaBugs = listaBugs;
	}	
	

}