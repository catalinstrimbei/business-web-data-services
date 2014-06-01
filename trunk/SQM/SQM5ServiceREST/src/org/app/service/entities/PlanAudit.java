package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;


@XmlRootElement(name="planaudit")
@XmlAccessorType(XmlAccessType.NONE)
@Entity

public class PlanAudit implements Serializable{
	
@XmlElement
	public int getIdplan() {
		return idplan;
	}

	public void setIdplan(int idplan) {
		this.idplan = idplan;
	}

	public String getDenumireplan() {
		return denumireplan;
	}

	public void setDenumireplan(String denumireplan) {
		this.denumireplan = denumireplan;
	}

	public Date getDatastart() {
		return datastart;
	}

	public void setDatastart(Date datastart) {
		this.datastart = datastart;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@XmlElementWrapper(name="echipa")
	@XmlElement(ns="echipa1")
	public List<Echipa> getEchipa() {
		return echipa;
	}

	public void setEchipa(List<Echipa> echipa) {
		this.echipa = echipa;
	}

//private static final FetchType EAGER = null;
	//private static final CascadeType[] ALL = null;
@Id
@GeneratedValue
	private int idplan;
	private String denumireplan;
	@Temporal(TemporalType.DATE)
	private Date datastart=new Date();
	private Date deadline=new Date();
	
@OneToMany (mappedBy="planaudit", cascade= ALL, fetch= EAGER, orphanRemoval=false)
private List<Echipa> echipa=new ArrayList<>();


public PlanAudit toDTO()
{PlanAudit planauditDTO=new PlanAudit(idplan, denumireplan, datastart, datastart);
return planauditDTO;
	}
public static PlanAudit toDTOAggregate(PlanAudit planaudit) {
	// TODO Auto-generated method stub
	if (planaudit==null)
		return null;
	PlanAudit planauditDTO=planaudit.toDTO();
	List<Echipa>echipaDTO=Echipa.toDTOList(planaudit.getEchipa());
	planauditDTO.setEchipa(echipaDTO);
	return planauditDTO;
}

public static Collection<PlanAudit> toDTOs(Collection<PlanAudit> collection) {

	return null;
}

public String getName() {
	// TODO Auto-generated method stub
	return null;
}

public PlanAudit(int idplan, String denumireplan, Date datastart,
		Date deadline) {
	super();
	this.idplan = idplan;
	this.denumireplan = denumireplan;
	this.datastart = datastart;
	this.deadline = deadline;
	
	
}

public PlanAudit() {
	super();
	// TODO Auto-generated constructor stub
}

public void setName(String string) {
	// TODO Auto-generated method stub
	
}

public static String BASE_URL = "http://localhost:8080/ScrumREST/plan/";
@XmlElement (ns = "link")
public AtomLink getLink() throws Exception {
	
	String restUrl=BASE_URL + this.getIdplan();
	return new AtomLink (restUrl, "get-plan");
	
	
}

public String size() {
	// TODO Auto-generated method stub
	return null;
}






	

}
