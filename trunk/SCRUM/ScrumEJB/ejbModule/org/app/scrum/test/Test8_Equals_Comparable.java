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
		Team echipa = new Team(1, Team.Specializare.BACKEND, null);
		
		Member membru = new ProjectManager(1, "M1", 2, "agile, scrum, xp");
		membru.setCompetente("MSFT Project, Redmine, ScrumSoft"); // polimorfism operatie
		echipa.adaugaMembru(membru);
		
		membru = new TeamLeader(2, "M2", null); // variabila polimorfica
		membru.setCompetente("Java, JEE, SQL, Oracle, JavaScript, HTML5");  // polimorfism operatie
		((TeamLeader)membru).setCompetente("Redmine", TeamLeader.TipCompetente.MANAGERIALE); // spraincarcare
		
		echipa.adaugaMembru(membru);
		
		membru = new Member(3, "M3", Role.DEVELOPER);
		membru.setCompetente("Java, JEE");  // polimorfism operatie
		echipa.adaugaMembru(membru); // apel polimorfic
		
		membru = new Member(4, "M4", Role.TESTER);
		membru.setCompetente("JUnit");
		echipa.adaugaMembru(membru);
		
		// equals si Comparable
		Member m1, m2;
		m1 = echipa.getMembri().get(0);
		m2 = echipa.getMembri().get(1);
		System.out.println("m1.equals(m2): " + m1.equals(m2));
		System.out.println("m1.compareTo(m2): " + m1.compareTo(m2));
		
	}

}
