package org.app.service.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Singleton
public class ProjectFactory{
	public static Proiect buildProiect(Integer IdProiect, String Nume, Integer activitateCount){
		
		Proiect proiect = new Proiect(IdProiect, Nume + "." + IdProiect);
		List<Activitate> activitatiProiect = new ArrayList<>();
		Long interval =  30l /*zile*/ * 24 /*ore*/ * 60 /*min*/ * 60 /*sec*/ * 1000 /*milisec*/;
		Date dataIncepere = new Date();
		Date dataSfarsit = new Date();

		for (int i=0; i<=activitateCount-1; i++){
			activitatiProiect.add(new Activitate(proiect.getIdProiect() + i,
						0.0,0.0,new Date(dataIncepere.getTime()+i*interval), new Date(dataSfarsit.getTime()+i*interval),"",proiect)); 			 
		}
		proiect.setActivitati(activitatiProiect);
		
		return proiect;
	}
}
