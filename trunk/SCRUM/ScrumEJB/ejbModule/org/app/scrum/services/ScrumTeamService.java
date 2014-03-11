package org.app.scrum.services;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

public class ScrumTeamService {
	public Integer getTotalOreEstimateCerinta(Cerinta cerinta, Membru membru){
		Integer totalOreEstimateCerinta = 0;
		for(Task t: cerinta.getTaskuri()){
			if (t.getResponsabil().equals(membru))
				totalOreEstimateCerinta += t.getTimpEstimat();
		}
		return totalOreEstimateCerinta;
	}
	
	public Integer getTotalOreRamaseCerinta(Cerinta cerinta, Membru membru){
		Integer totalOreRamaseCerinta = 0;
		for(Task t: cerinta.getTaskuri()){
			if (t.getResponsabil().equals(membru))
				totalOreRamaseCerinta += t.getTimpRamas();
		}
		return totalOreRamaseCerinta;
	}
	
	// model-subiect sesiune-no.1
	public Integer getTotalOreEstimateSprint(Sprint sprint, Membru membru){
		return null;
	}
	public Integer getTotalOreEstimateCerinta(Cerinta cerinta, Echipa echipa){
		return null;
	}
	public Integer getTotalOreEstimateCerinta(Cerinta cerinta){
		return null;
	}
	
	// model-subiect sesiune-no.2
	public Integer getTotalOreRamaseSprint(Sprint sprint, Membru membru){
		return null;
	}
	public Integer getTotalOreRamaseCerinta(Cerinta cerinta, Echipa echipa){
		return null;
	}
	public Integer getTotalOreRamaseCerinta(Cerinta cerinta){
		return null;
	}
}