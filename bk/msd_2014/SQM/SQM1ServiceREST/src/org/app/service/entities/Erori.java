package org.app.service.entities;

import java.io.Serializable;






import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Erori implements Serializable {

	
	@Id @GeneratedValue
	private Integer idEroare;
	private String denumireEoare;
	private String descriereEroare;
	
	
	public Erori() {
		super();
	}


	public Erori(Integer idEroare, String denumireEoare, String descriereEroare) {
		super();
		this.idEroare = idEroare;
		this.denumireEoare = denumireEoare;
		this.descriereEroare = descriereEroare;
	}
 

	public Integer getIdEroare() {
		return idEroare;
	}


	public void setIdEroare(Integer idEroare) {
		this.idEroare = idEroare;
	}


	public String getDenumireEoare() {
		return denumireEoare;
	}


	public void setDenumireEoare(String denumireEoare) {
		this.denumireEoare = denumireEoare;
	}


	public String getDescriereEroare() {
		return descriereEroare;
	}


	public void setDescriereEroare(String descriereEroare) {
		this.descriereEroare = descriereEroare;
	}


	

	public void finalize() throws Throwable {

	}

	/*public Erori toDTO(){
		return new Erori (idEroare, denumireEoare, descriereEroare, test.toDTO());
		
	}
		
		public static List<Erori> toDTOList(List<Erori> erori) {
			List<Erori> eroriDTOList= new ArrayList<>();
			for(Erori e: erori) {
				eroriDTOList.add(e.toDTO());
			}
			return eroriDTOList;
			}
	*/
}
