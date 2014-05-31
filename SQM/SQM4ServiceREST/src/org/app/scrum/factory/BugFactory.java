package org.app.scrum.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Atasament;
import org.app.service.entities.Bug;
import org.app.service.entities.PrioritateEnum;
import org.app.service.entities.StatusEnum;
import org.app.service.entities.TipBugEnum;

@Singleton
public class BugFactory {
		
		/* Build a new Bug
		 */
				
		public Bug buildBug(String idBug, String titlu, TipBugEnum tipBug,
				StatusEnum status, PrioritateEnum prioritate,
				Date dataAdaugare, Date dataScadenta, String descriere, Integer nrAtasamente) {
			Bug bug = new Bug(idBug, titlu, tipBug, status, prioritate, dataAdaugare,dataScadenta, descriere, nrAtasamente);
			List<Atasament> atasamenteBug = new ArrayList<>();
			
			nrAtasamente= atasamenteBug.size();
			
			bug.setNrAtasamente(nrAtasamente);
			return bug;
		}
	}
