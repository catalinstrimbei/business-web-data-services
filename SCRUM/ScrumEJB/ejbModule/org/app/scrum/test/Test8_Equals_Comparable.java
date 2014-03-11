package org.app.scrum.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

public class Test8_Equals_Comparable {

	public static void main(String[] args) {
		// Polimorfism in "echipa"
		Echipa echipa = new Echipa(1, Echipa.Specializare.BACKEND, null);
		
		Membru membru = new ManagerProiect(1, "M1", 2, "agile, scrum, xp");
		membru.setCompetente("MSFT Project, Redmine, ScrumSoft"); // polimorfism operatie
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
		
		// equals si Comparable
		Membru m1, m2;
		m1 = echipa.getMembri().get(0);
		m2 = echipa.getMembri().get(1);
		System.out.println("m1.equals(m2): " + m1.equals(m2));
		System.out.println("m1.compareTo(m2): " + m1.compareTo(m2));
		
	}

}
