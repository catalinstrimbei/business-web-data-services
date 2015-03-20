package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.scrum.project.Project;
import org.app.scrum.project.Release;
import org.app.scrum.services.ProjectSprintDataService;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.team.Team;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * JUnit test for EJB: ProjectSprintDataServiceEJB
 */
@RunWith(Arquillian.class)
public class TestScrumProjectDataServiceArquilian {
	private static Logger logger = Logger.getLogger(TestScrumProjectDataServiceArquilian.class.getName());
	//private static ProjectSprintDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//service = ProjectSprintDataServiceEJBFactory.getScrumProjectRepositoryService();
		
	}
	
	// Arquilian infrastructure
	@EJB
	private static ProjectSprintDataService service;
	@Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "scrum-test-ejb.war")
                .addPackage(EntityRepository.class.getPackage())
                .addPackage(Project.class.getPackage())
                .addPackage(Sprint.class.getPackage())
                .addPackage(Team.class.getPackage())
                .addPackage(ProjectSprintDataService.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }	
	
	
	/* CHECK EJB live test*/
	@Test
	public void testGetMessage(){
		System.out.println("########## TEST ARQUILLIAN ######################");
		String message = service.getMessage();
		assertNotNull("Message not returned from service!", message);
		logger.info("########## DEBUG testGetMessage: " + message);
	}

	/* CREATE Test 1 (create-read)*/
//	@Test
	public void testCreateProject(){
		Project project = new Project(7001, "Project Test 7001", new Date()); // !!!
		project = service.add(project);
		logger.info("DEBUG createProject: saved project: " + project);
		assertNotNull("Fail to create new project in repository!", project);
		project = service.getById(7001); // !!!
		assertNotNull("Fail to find new project in repository!", project);
		logger.info("DEBUG createProject: queried project" + project);
	}
	
	/* CREATE Test 2: create aggregate*/
//	@Test
	public void testCreateNewProject(){
		Project project = service.createNewProject(7002);
		assertNotNull("Fail to create new project in repository!", project);
		// update project
		project.setName(project.getName() + " - changed by test client");		
		List<Release> releases = project.getReleases();
		// update project components
		for(Release r: releases)
			r.setIndicative(r.getIndicative() + " - changed by test client");
		project = service.add(project);
		assertNotNull("Fail to save new project in repository!", project);
		logger.info("DEBUG createNewProject: project changed: " + project);
		// check read
		project = service.getById(7002);  // !!!
		assertNotNull("Fail to find changed project in repository!", project);
		logger.info("DEBUG createNewProject: queried project" + project);
	}	
	
	/* READ Test (read-collection) */
	@Test
	public void testToCollection(){
		Collection<Project> projects = service.toCollection();
		assertTrue("Fail to read projects from repository!", projects != null);
		logger.info("########## DEBUG testToCollection:" + projects.size());
		assertTrue("Fail to read any project from repository!", projects.size() > 0);
	}
	
	/* UPDATE Test (read-instance&update)*/
//	@Test
	public void testAdd(){
		Project project = service.getById(7001);
		assertNotNull("Fail to get project from repository!", project);
		project.setName(project.getName() + " - updated by test client");
		project = service.add(project);
		assertNotNull("Fail to save updated project in repository!", project);
		// check read
		project = service.getById(7001);
		assertNotNull("Fail to find updated project in repository!", project);
		logger.info("DEBUG testAdd: project updated: " + project);		
	}
	
	/* REMOVE Test */
//	@Test
	public void testRemove(){
		Integer projectNo = 7001;
		Project project = service.getById(projectNo);
		assertNotNull("Fail to get project from repository!", project);
		service.remove(project);
		// check read
		project = service.getById(projectNo);
		logger.info("DEBUG testAdd: project removed: " + project);
		assertNull("Fail to remove project in repository!", project);
	}

	
	/* READ Test (read-collection-aggregate-components) */
//	@Test
	public void testGetReleaseById(){
		Release release = service.getReleaseById(305);
		assertTrue("Fail to read releases from repository!", release != null);
		logger.info("DEBUG testGetReleaseById:" + release);
	}
}




