package org.app.service.entities;
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
public class Comentariu implements IManagementComentariu {

	@Id @GeneratedValue
	private String id;
	private String titlu;
	private String descriere;
	
	@ManyToOne
	private Bug bug;
	
	
	/* Create a simply Comnetariu dto from Comentariu Entity
	 * by nullifying all relationships (if exists)  
	 */
	
	public Comentariu toDTO(){
		return new Comentariu(id, titlu, descriere, bug.toDTO());
	}
	
	/* Create a list of simple Comentariu -dto instances
	 * from a Collection of persistent - entities
	 */
	public static List <Comentariu> toDTOList (List<Comentariu> comentariu){
		List<Comentariu> comentariuDTOList = new ArrayList<>();
		for (Comentariu com : comentariu){
			comentariuDTOList.add(com.toDTO());
		}
		return comentariuDTOList;
	}

	public Comentariu(){

	}
	public Comentariu(String id, String titlu, String descriere, Bug bug) {
		super();
		this.id = id;
		this.titlu = titlu;
		this.descriere = descriere;
		this.bug = bug;
	}

	public void finalize() throws Throwable {

	}

	public Comentariu create(){
		return null;
	}

	/**
	 * 
	 * @param c
	 * @param b
	 */
	public void delete(Comentariu c, Bug b){

	}

	/**
	 * 
	 * @param c
	 * @param b
	 */
	public Comentariu select(Comentariu c, Bug b){
		return null;
	}

	/**
	 * 
	 * @param c
	 * @param b
	 */
	public void update(Comentariu c, Bug b){

	}
	//get & set 

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
	

}