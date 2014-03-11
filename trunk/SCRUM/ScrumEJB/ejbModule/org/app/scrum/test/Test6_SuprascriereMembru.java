package org.app.scrum.test;

import org.app.scrum.team.ManagerProiect;
import org.app.scrum.team.Membru;
import org.app.scrum.team.Rol;

public class Test6_SuprascriereMembru {

	public static void main(String[] args) {
		Membru m1 = new Membru(1, "ION ION", Rol.DEVELOPER);
		m1.setCompetente("Java, JEE, SQL");
		
		ManagerProiect m2 = new ManagerProiect(1, "MARIN MARIN", 2, "agile, scrum, xp");
		m2.setCompetente("MSFT Project, Redmine, ScrumSoft");
		
		System.out.println("Competente m1: " + m1.getCompetente());
		System.out.println("Competente m2 (a): " + m2.getCompetente());
		System.out.println("Competente m2 (a-manageriale): " + m2.getCompetente(ManagerProiect.TipCompetenta.MANAGERIAL));
		System.out.println("Competente m2 (b-tehnice): " + m2.getCompetente(ManagerProiect.TipCompetenta.TEHNIC));
	}

}
