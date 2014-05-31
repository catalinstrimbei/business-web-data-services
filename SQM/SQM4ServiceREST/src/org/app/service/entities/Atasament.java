package org.app.service.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.02
 */
@Entity 
public class Atasament implements Serializable, IManagementAtasament {

	@Id @GeneratedValue
	private String id;
	private String titlu;
	private String descriere;
	private String numeFisierAtasat;
	private Object fisier;
	
	@ManyToOne
	private Bug bug;
//	private Utilitati utilitati;

	public Atasament toDTO(){
			return new Atasament(id, titlu, descriere, numeFisierAtasat, bug.toDTO());
	}
	
	public static List<Atasament> toDTOList(List<Atasament> atasamente){
		List<Atasament> atasamenteDTOList = new ArrayList<>();
		for (Atasament a: atasamente){
			atasamenteDTOList.add(a.toDTO());
		}
		return atasamenteDTOList;
	}
	
	public Atasament(){

	}

	public Atasament(String id, String titlu, String descriere,
			String numeFisierAtasat, Bug dto) {
		// TODO Auto-generated constructor stub
	}
	

	public void finalize() throws Throwable {

	}

	public Atasament create(){
		return null;
	}

	/**
	 * 
	 * @param atach
	 */
	public void delete(Atasament atach){

	}

	/**
	 * 
	 * @param atach
	 */
	public void select(Atasament atach){

	}

	/**
	 * 
	 * @param atach
	 */
	public void update(Atasament atach){

	}


	// get & set 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public String getNumeFisierAtasat() {
		return numeFisierAtasat;
	}

	public void setNumeFisierAtasat(String numeFisierAtasat) {
		this.numeFisierAtasat = numeFisierAtasat;
	}

	public Object getFisier() {
		return fisier;
	}

	public void setFisier(Object fisier) {
		this.fisier = fisier;
	}


}