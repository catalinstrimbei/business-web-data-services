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



}
