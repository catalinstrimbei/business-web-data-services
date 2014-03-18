package org.app.scrum.services;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

public class ScrumTeamService {
	public Integer getTotalOreEstimateCerinta(Feature cerinta, Member membru){
		Integer totalOreEstimateCerinta = 0;
		for(Task t: cerinta.getTaskuri()){
			if (t.getResponsabil().equals(membru))
				totalOreEstimateCerinta += t.getTimpEstimat();
		}
		return totalOreEstimateCerinta;
	}
	
	public Integer getTotalOreRamaseCerinta(Feature cerinta, Member membru){
		Integer totalOreRamaseCerinta = 0;
		for(Task t: cerinta.getTaskuri()){
			if (t.getResponsabil().equals(membru))
				totalOreRamaseCerinta += t.getTimpRamas();
		}
		return totalOreRamaseCerinta;
	}
	
	// model-subiect sesiune-no.1
	public Integer getTotalOreEstimateSprint(Sprint sprint, Member membru){
		return null;
	}
	public Integer getTotalOreEstimateCerinta(Feature cerinta, Team echipa){
		return null;
	}
	public Integer getTotalOreEstimateCerinta(Feature cerinta){
		return null;
	}
	
	// model-subiect sesiune-no.2
	public Integer getTotalOreRamaseSprint(Sprint sprint, Member membru){
		return null;
	}
	public Integer getTotalOreRamaseCerinta(Feature cerinta, Team echipa){
		return null;
	}
	public Integer getTotalOreRamaseCerinta(Feature cerinta){
		return null;
	}
}