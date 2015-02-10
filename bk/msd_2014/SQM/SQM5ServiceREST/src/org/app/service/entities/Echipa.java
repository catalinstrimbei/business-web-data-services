package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="echipa")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Echipa implements Serializable{
@Id@GeneratedValue

int idechipa;
String atributii;
String specializare;

@ManyToOne
private PlanAudit planaudit;

@OneToMany (cascade=ALL,fetch=FetchType.EAGER)
private List<Membru> membru=new ArrayList<>();

@XmlElement
public int getIdechipa() {
	return idechipa;
}
public void setIdechipa(int idechipa) {
	this.idechipa = idechipa;
}

@XmlElement
public String getAtributii() {
	return atributii;
}
public void setAtributii(String atributii) {
	this.atributii = atributii;
}

@XmlElement
public String getSpecializare() {
	return specializare;
}
public void setSpecializare(String specializare) {
	this.specializare = specializare;
}
public Echipa toDTO(){
	return new Echipa();
}
public static List <Echipa> toDTOList(List<Echipa> echipa){
	List<Echipa> echipaDTOList=new ArrayList<>();
	for (Echipa e:echipa){
		echipaDTOList.add(e.toDTO());
	} 
return echipaDTOList;

}
public String getIndicative() {
	// TODO Auto-generated method stub
	return null;
}
public void setIndicative(String string) {
	// TODO Auto-generated method stub
	
}

public static String BASE_URL=PlanAudit.BASE_URL;
@XmlElement(name="link")
public AtomLink getLink() throws Exception {
	
	String restUrl=BASE_URL + this.getPlanAudit().getIdplan() + "echipa" + this.getIdechipa();
	return new AtomLink(restUrl, "getEchipa");
}




private Echipa getPlanAudit() {
	// TODO Auto-generated method stub
	return null;
}
private String getIdplan() {
	// TODO Auto-generated method stub
	return null;
}

}
