package org.app.scrum.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

// Test model-subiect sesiune-no.1
public class TestScrumTeamService_S1 {
	
	public static void main() throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		ScrumTeamService service = new ScrumTeamService();
		
		Sprint sprintS1 = new Sprint(1, "S1", dateFormat.parse("30/01/2014"));
		Echipa echipaS1 = new Echipa(1, Echipa.Specializare.BACKEND);
		List<Membru> membri = new ArrayList<>();
		membri.add(new Membru(1, "M1S1", "uml architect"));
		membri.add(new Membru(2, "M2S1", "java developer"));
		membri.add(new Membru(3, "M3S1", "java developer"));
		echipaS1.setMembri(membri);
		
		// cerinte
		List<Cerinta> cerinteS1 = new ArrayList<>();
		cerinteS1.add(new Cerinta(1, "Design arhitectura model business"));
		cerinteS1.add(new Cerinta(2, "Implementare model business in Java"));
		sprintS1.setCerinte(cerinteS1);
		
		// taskuri pentru prima cerinta
		Cerinta cerinta = sprintS1.getCerinte().get(0);
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(1, "Design model clase entitati", dateFormat.parse("30/01/2014"), 12, membri.get(0)));
		cerinta.setTaskuri(tasks);

		// taskuri pentru a doua cerinta		
		cerinta = sprintS1.getCerinte().get(1);
		tasks = new ArrayList<>();
		tasks.add(new Task(3, "Implementare clase entitati in Java", dateFormat.parse("30/01/2014"), 16, membri.get(1)));
		tasks.add(new Task(4, "Implementare servicii Java", dateFormat.parse("30/01/2014"), 12, membri.get(2)));
		cerinta.setTaskuri(tasks);
		
		// Raport final
		System.out.println("Sprint: " + sprintS1.getIdSprint() 
				+ " - obiectiv:" + sprintS1.getObiectiv());
		System.out.println(" - Cerinta -" + "- Timp estimat -");
		for(Cerinta c: sprintS1.getCerinte()){
			System.out.println(" -- " + c.getDenumire() 
					+ "--" + service.getTotalOreEstimateCerinta(cerinta));
			for(Task t: c.getTaskuri()){
				System.out.println("---- Task denumire: " + t.getDenumire()
						+ ", responsabil: " + t.getResponsabil().getNumePrenume()
						+ ", timp estimat: " + t.getTimpEstimat() 
						+ ", din total estimare sprint: "
							+ service.getTotalOreEstimateSprint(sprintS1, t.getResponsabil()));
			}
			System.out.println("----------------------------------------------------");
		}
			
	}
}
