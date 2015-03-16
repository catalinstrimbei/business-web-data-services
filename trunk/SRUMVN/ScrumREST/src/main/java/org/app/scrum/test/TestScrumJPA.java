package org.app.scrum.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.patterns.ProjectBuilder;
import org.app.scrum.Project;

public class TestScrumJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ScrumJPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Project proiect;
		for(int i=1; i <= 4; i++){
			proiect = new ProjectBuilder().buildProiect(i, "Proiect Test", i+2);
			em.persist(proiect);
		}
		
		System.out.println("Salvare proiecte!");
		
		em.getTransaction().commit();
		
		System.out.println("End");
	}

}

/*
 	<persistence-unit name="ScrumJPA" transaction-type="RESOURCE_LOCAL">
		<class>org.app.scrum.BusinessFeature</class>
		<class>org.app.scrum.Feature</class>
		<class>org.app.scrum.Project</class>
		<class>org.app.scrum.Release</class>
		<class>org.app.scrum.TechnicalFeature</class>
		<class>org.app.scrum.team.Member</class>
		<class>org.app.scrum.team.ProjectManager</class>
		<class>org.app.scrum.team.Team</class>
		<class>org.app.scrum.team.TeamLeader</class>
			
		<properties>
			<property name="javax.persistence.jdbc.url" value="jdbc:oracle:thin:@85.122.22.12:1521:ORCL"/>
			<property name="javax.persistence.jdbc.user" value="scrum"/>
			<property name="javax.persistence.jdbc.password" value="agile"/>
			<property name="javax.persistence.jdbc.driver" value="oracle.jdbc.driver.OracleDriver"/>
			<property name="hibernate.dialect" value="org.hibernate.dialect.Oracle10gDialect"/>
         	<property name="hibernate.hbm2ddl.auto" value="none"/>			
		</properties>		
	</persistence-unit>	

	<persistence-unit name="ScrumEJB">
		<jta-data-source>java:/ORCLFeaaScrum</jta-data-source>
		<class>org.app.scrum.BusinessFeature</class>
		<class>org.app.scrum.Feature</class>
		<class>org.app.scrum.Project</class>
		<class>org.app.scrum.Release</class>
		<class>org.app.scrum.TechnicalFeature</class>
		<class>org.app.scrum.team.Member</class>
		<class>org.app.scrum.team.ProjectManager</class>
		<class>org.app.scrum.team.Team</class>
		<class>org.app.scrum.team.TeamLeader</class>
		
	  <properties>
	     <property name="hibernate.dialect" value="org.hibernate.dialect.Oracle10gDialect"/>
	     <property name="hibernate.hbm2ddl.auto" value="update"/>
	  </properties>		
	  		
	</persistence-unit>

*/