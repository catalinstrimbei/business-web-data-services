package org.app.scrum.project;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;



@XmlRootElement(name="activitate")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Activitate implements Serializable{
	/* internal member fields*/
	@Id @GeneratedValue
	private Integer IdActivitate;
	private String Denumire; // NEW born
	private Double CostEstimat;
	private Double CostFinal;
	private Date DataIncepere;
	private Date DataSfarsit;// vers 1.1
	private String Descriere;
	private String Stare;
	
	@Temporal(TemporalType.DATE)
	private Date publishDate; // dataEstimarePublicare
	
    @ManyToOne
	private Proiect proiect;

	@OneToMany(cascade = ALL, fetch=FetchType.EAGER)
	private List<Resursa> resurse = new ArrayList<>();
	
	/* properties for xmls and json dtos*/
	@XmlElement
	public Integer getIdActivitate() {
		return IdActivitate;
	}
	public void setIdActivitate(Integer IdActivitate) {
		this.IdActivitate = IdActivitate;
	}
	
	@XmlElement
	public String getDenumire() {
		return Denumire;
	}
	public void setDenumire(String Denumire) {
		this.Denumire = Denumire;
	}
	
	@XmlElement
	public String getDescriere() {
		return Descriere;
	}
	public void setDescriere(String Descriere) {
		this.Descriere = Descriere;
	}
	
	@XmlElement
	public String getStare() {
		return Stare;
	}
	public void setStare(String Stare) {
		this.Stare = Stare;
	}
	
	@XmlElement
	public Date getDataIncepere() {
		return DataIncepere;
	}
	public void setDataIncepere(Date DataIncepere) {
		this.DataIncepere = DataIncepere;
	}
	@XmlElement
	public Date getDataSfarsit() {
		return DataSfarsit;
	}
	public void setDataSfarsit(Date DataSfarsit) {
		this.DataSfarsit = DataSfarsit;
	}
	
	@XmlElement
	public Double getCostEstimat() {
		return CostEstimat;
	}
	public void setCostEstimat(Double CostEstimat) {
		this.CostEstimat = CostEstimat;
	}
	
	@XmlElement
	public Double getCostFinal() {
		return CostFinal;
	}
	public void setCostFinal(Double CostFinal) {
		this.CostFinal = CostFinal;
	}
	@XmlElement
	public Proiect getProiect() {
		return proiect;
	}
	public void setProiect(Proiect Proiect) {
		this.proiect = Proiect;
	}
	
	@XmlElementWrapper(name = "resurse")
    @XmlElement(name = "resursa")
	public List<Resursa> getResurse() {
		return resurse;
	}
	public void setResurse(List<Resursa> resurse) {
		this.resurse = resurse;
	}
	
	/* constructors*/
	public Activitate() {
		super();
	}
	
	public Activitate(Integer IdActivitate, String Denumire, Double CostEstimat, Double CostFinal, Date DataIncepere, Date DataSfarsit, String Descriere, String Stare, Proiect Proiect) {
		super();
		this.IdActivitate = IdActivitate;
		this.Denumire = Denumire;
		this.CostEstimat = CostEstimat;
		this.CostFinal = CostFinal;
		this.DataIncepere = DataIncepere;
		this.DataSfarsit = DataSfarsit;
		this.Descriere = Descriere;
		this.Stare = Stare;
		this.proiect = Proiect;
	}

	public Activitate(Integer IdActivitate, Double CostEstimat, Double CostFinal, Date DataIncepere, Date DataSfarsit,  String Stare, Proiect Proiect) {
		super();
		this.IdActivitate = IdActivitate;
		this.CostEstimat = CostEstimat;
		this.CostFinal = CostFinal;
		this.DataIncepere = DataIncepere;
		this.DataSfarsit = DataSfarsit;
		this.Stare = Stare;
		this.proiect = Proiect;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IdActivitate == null) ? 0 : IdActivitate.hashCode());
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
		Activitate other = (Activitate) obj;
		if (IdActivitate == null) {
			if (other.IdActivitate != null)
				return false;
		} else if (!IdActivitate.equals(other.IdActivitate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Activitate [IdActivitate=" + IdActivitate + ", Denumire=" + Denumire
				+ ", Descriere=" + Descriere + "]";
	}

	/* Rest Resource URL*/
//	public static String BASE_URL = "http://localhost:8080/ScrumREST/projects/";
	
	public static String BASE_URL = Proiect.BASE_URL;
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ this.getProiect().getIdProiect() 
				+ "/activitati/" 
				+ this.getIdActivitate();
        return new AtomLink(restUrl, "get-activitate");
    }	
	public void setLink(AtomLink link){}

	/* DTO Logic*/
	public Activitate toDTO(){
		return new Activitate(IdActivitate,Denumire,CostEstimat,CostFinal,DataIncepere,DataSfarsit,Descriere, Stare, proiect.toDTO());
	}
	
	public static List<Activitate> toDTOList(List<Activitate> activitati){
		List<Activitate> ActivitateDTOList = new ArrayList<>();
		for(Activitate r: activitati){
			ActivitateDTOList.add(r.toDTO());
		}
		return ActivitateDTOList;
	}	
}