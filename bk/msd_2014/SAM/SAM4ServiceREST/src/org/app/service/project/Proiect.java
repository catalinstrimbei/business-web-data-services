
package org.app.service.project;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;


@XmlRootElement(name="proiect") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Proiect implements Serializable{
	
	/* internal stucture: member fields*/
	@Id // @GeneratedValue // !!!
	private Integer IdProiect;
	private String Nume;
	private Double CostFinal;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Transient
	
	@OneToMany(mappedBy="project", cascade = ALL, fetch = EAGER, orphanRemoval = false)
	private List<Activitate> activitati = new ArrayList<>();
	@OneToOne(cascade = ALL)
	private Activitate activitateCurenta;
	
	/* getters for xmls*/
	@XmlElement
	public Integer getIdProiect() {
		return IdProiect;
	}
	public void setIdProiect(Integer IdProiect) {
		this.IdProiect = IdProiect;
	}
	@XmlElement
	public String getNume() {
		return Nume;
	}
	public void setNume(String Nume) {
		this.Nume = Nume;
	}
	
//	@XmlElements({ @XmlElement(name = "release_s", type = Release.class) })
	
	@XmlElementWrapper(name = "activitati") @XmlElement(name = "activitati")
	public List<Activitate> getActivitati() {
		return activitati;
	}
	public void setActivitati(List<Activitate> activitati) {
		this.activitati = activitati;
	}
	public Activitate getCurrentActivitate() {
		return activitateCurenta;
	}
	public void setCurrentActivitate(Activitate currentActivitate) {
		this.activitateCurenta = activitateCurenta;
	}	
	
	/* Rest Resource URL*/
	public static String BASE_URL = "http://localhost:8080/SAM4ServiceREST/service/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdProiect();
        return new AtomLink(restUrl, "get-Proiect");
    }	
	
	public void setLink(AtomLink link){}
	
	@Override
	public String toString() {
		return "Proiect [IdProiect=" + IdProiect + ", Nume=" + Nume + "]";
	}

	/* Constructors */
	public Proiect(Integer IdProiect, String Nume, Double CostFinal) {
		super();
		this.IdProiect = IdProiect;
		this.Nume = Nume;
		
	}

	public Proiect() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IdProiect == null) ? 0 : IdProiect.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proiect other = (Proiect) obj;
		if (IdProiect == null) {
			if (other.IdProiect != null)
				return false;
		} else if (!IdProiect.equals(other.IdProiect))
			return false;
		return true;
	}
	public Proiect(Integer IdProiect, String Nume) {
		super();
		this.IdProiect = IdProiect;
		this.Nume = Nume;
	}

	/* DTO Logic*/
	public Proiect toDTO(){
		Proiect proiectDTO =new Proiect(IdProiect, Nume, CostFinal); 
//		ProiectDTO.setReleases(null);
//		ProiectDTO.setProiectManager(null);
//		ProiectDTO.setCurrentRelease(null);
		return proiectDTO;
	}
	
	public static Proiect toDTOAggregate(Proiect proiect){
		if (proiect == null)
			return null;
		Proiect ProiectDTO = proiect.toDTO();
		List<Activitate> activitatiDTO = Activitate.toDTOList(proiect.getActivitati());
		ProiectDTO.setActivitati(activitatiDTO);
		
		return ProiectDTO;
	}
	
	public static Proiect[] toDTOList(Collection<Proiect> Proiecte){
		List<Proiect> ProiectDTOList = new ArrayList<>();
		for(Proiect p: Proiecte){
			ProiectDTOList.add(p.toDTO());
		}
		return ProiectDTOList.toArray(new Proiect[0]);
	}	
	
	public static Collection<Proiect> toDTOs(Collection<Proiect> Proiecte){
		List<Proiect> ProiectDTOList = new ArrayList<>();
		for(Proiect p: Proiecte){
			ProiectDTOList.add(p.toDTO());
		}
		//return ProiectDTOList.toArray(new Proiect[0]);
		return ProiectDTOList;
	}	
	
}
