package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */

@Entity
public class Permisie implements IManagementPermisie {

	@Id @GeneratedValue
	private String idPermisie;
	private String nume;
	private String descriere;

	public Permisie(){

	}

	public void finalize() throws Throwable {

	}

	public Permisie create(){
		return null;
	}

	/**
	 * 
	 * @param p
	 */
	public void delete(Permisie p){

	}

	/**
	 * 
	 * @param p
	 */
	public void select(Permisie p){

	}

	/**
	 * 
	 * @param p
	 */
	public void update(Permisie p){

	}
	//get & set 

	public String getIdPermisie() {
		return idPermisie;
	}

	public void setIdPermisie(String idPermisie) {
		this.idPermisie = idPermisie;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	

}