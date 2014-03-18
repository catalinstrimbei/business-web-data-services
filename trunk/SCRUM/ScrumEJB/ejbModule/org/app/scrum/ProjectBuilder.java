package org.app.scrum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProjectBuilder implements EntityFactory{
	public Project buildProiect(Integer idProiect, String denumire, Integer nrReleases){
		
		Project proiect = new Project(idProiect, denumire + "." + idProiect , new Date());
		List<Release> releasesProiect = new ArrayList<>();
		
		Date dataPublicare = new Date();
		Long interval =  30l /*zile*/ * 24 /*ore*/ * 60 /*min*/ * 60 /*sec*/ * 1000 /*milisec*/;
		
		for (int i=0; i<=nrReleases-1; i++){
			releasesProiect.add(new Release(null, "R: " + proiect.getNrProiect() + "." + i, 
					new Date(dataPublicare.getTime() + i * interval), proiect));
		}
		
		proiect.setReleases(releasesProiect);
		
		return proiect;
	}
}
