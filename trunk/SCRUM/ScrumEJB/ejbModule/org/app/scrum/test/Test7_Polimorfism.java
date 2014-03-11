package org.app.scrum.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

public class Test7_Polimorfism {

	public static void main(String[] args) {
		// Baza: ierarhiile

		// Polimorfism in "echipa"
		Echipa echipa = new Echipa(1, Echipa.Specializare.BACKEND, null);
		
		Membru membru = new ManagerProiect(1, "M1", 2, "agile, scrum, xp");
		membru.setCompetente("MSFT Project, Redmine, ScrumSoft"); // polimorfism operatie
		
//		ManagerProiect manager = (ManagerProiect) membru;
//		manager.setExperientaManageriala(5);
		((ManagerProiect) membru).setExperientaManageriala(5);
		
		echipa.adaugaMembru(membru);
		
		membru = new LiderEchipa(2, "M2", null); // variabila polimorfica
		membru.setCompetente("Java, JEE, SQL, Oracle, JavaScript, HTML5");  // polimorfism operatie
		((LiderEchipa)membru).setCompetente("Redmine", LiderEchipa.TipCompetente.MANAGERIALE); // spraincarcare
		
		echipa.adaugaMembru(membru);
		
		membru = new Membru(3, "M3", Rol.DEVELOPER);
		membru.setCompetente("Java, JEE");  // polimorfism operatie
		echipa.adaugaMembru(membru); // apel polimorfic
		
		membru = new Membru(4, "M4", Rol.TESTER);
		membru.setCompetente("JUnit");
		echipa.adaugaMembru(membru);
		
		// Polimorfism in delegare responsabilitati
		Cerinta cerinta = new CerintaFunctionala();
		List<Task> taskuri = new ArrayList<>(); // colectie polimorfica
		Membru responsabil = null; // variabila polimorfica

		Task task = new Task(); 
		task.setDataStart(new Date());
		task.setTimpEstimat(10);
		
		responsabil = echipa.getLiderEchipa();
		task.setResponsabil(responsabil); // parametru polimorfic
		
		taskuri.add(task);
		
		task = new Task();
		task.setDataStart(new Date());
		task.setTimpEstimat(12);
		
		responsabil = echipa.getMembri().get(1);
		task.setResponsabil(responsabil);  // parametru polimorfic		
		
		taskuri.add(task);
		
		cerinta.setTaskuri(taskuri);
		
	}

}
