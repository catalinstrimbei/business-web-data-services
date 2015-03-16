package org.app.scrum.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.patterns.ProjectBuilder;
import org.app.patterns.ProjectView;
import org.app.scrum.Project;

public class TestScrumJPAQL {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ScrumJPA");
		EntityManager em = emf.createEntityManager();

		List<Project> pList01 = em.createQuery("SELECT p FROM Project p").getResultList();
		for(Project p: pList01)
			System.out.println("Project: " + p);		
		
		
		/* Bug Eclipse */
		List<Project> pList02 = em.createNativeQuery("SELECT p.PROJECTNO, p.NAME, p.STARTDATE FROM project p", Project.class)
				.getResultList();
		for(Project p: pList02)
			System.out.println("Project: " + p);
				
		/* OK */
		List<Object[]> pList03 = em.createNativeQuery("SELECT p.PROJECTNO, p.NAME FROM project p").getResultList();
		for(Object[] p: pList03)
			System.out.println("Project[]: " + p[0] + " - " + p[1]);		

		/* OK */
		BigDecimal nrProiecte = (BigDecimal) em.createNativeQuery("SELECT COUNT(*) FROM project p").getSingleResult();
		System.out.println("Nr proiecte: " + nrProiecte);
		
		List<ProjectView> pList04 = em.createQuery("SELECT NEW org.app.patterns.ProjectView(p.projectNo, p.name) FROM Project p").getResultList();
		for(ProjectView p: pList04)
			System.out.println("ProjectView: " + p);		
		
		System.out.println("End");
	}

}
