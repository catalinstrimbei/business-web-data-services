package org.app.scrum.test;

import org.app.scrum.team.ProjectManager;
import org.app.scrum.team.Member;
import org.app.scrum.team.Role;

public class Test6_SuprascriereMembru {

	public static void main(String[] args) {
		Member m1 = new Member(1, "ION ION", Role.DEVELOPER);
		m1.setCompetente("Java, JEE, SQL");
		
		ProjectManager m2 = new ProjectManager(1, "MARIN MARIN", 2, "agile, scrum, xp");
		m2.setCompetente("MSFT Project, Redmine, ScrumSoft");
		
		System.out.println("Competente m1: " + m1.getCompetente());
		System.out.println("Competente m2 (a): " + m2.getCompetente());
		System.out.println("Competente m2 (a-manageriale): " + m2.getCompetente(ProjectManager.TipCompetenta.MANAGERIAL));
		System.out.println("Competente m2 (b-tehnice): " + m2.getCompetente(ProjectManager.TipCompetenta.TEHNIC));
	}

}
