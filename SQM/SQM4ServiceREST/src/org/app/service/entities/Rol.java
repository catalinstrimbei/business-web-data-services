package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;


/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */

@Entity
public class Rol implements IManagementRol {

	@Id @GeneratedValue
	private String idRol;
	private String numeRol;
	private String observatii;
	
	private List<Permisie> listaPermisii = new ArrayList<>();

	public Rol(){

	}

	public void finalize() throws Throwable {

	}

	public Rol create(){
		return null;
	}

	/**
	 * 
	 * @param r
	 */
	public void delete(Rol r){

	}

	/**
	 * 
	 * @param r
	 */
	public void select(Rol r){

	}

	/**
	 * 
	 * @param r
	 */
	public void update(Rol r){

	}

	
	// get @ set 

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getNumeRol() {
		return numeRol;
	}

	public void setNumeRol(String numeRol) {
		this.numeRol = numeRol;
	}

	public String getObservatii() {
		return observatii;
	}

	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}

	public List<Permisie> getListaPermisii() {
		return listaPermisii;
	}

	public void setListaPermisii(List<Permisie> listaPermisii) {
		this.listaPermisii = listaPermisii;
	}

}