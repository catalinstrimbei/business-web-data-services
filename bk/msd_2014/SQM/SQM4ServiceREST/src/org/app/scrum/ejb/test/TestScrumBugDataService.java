package org.app.scrum.ejb.test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.app.scrum.services.BugSprintDataService;
import org.app.service.entities.Bug;
import org.app.service.entities.Comentariu;
import org.app.service.entities.PrioritateEnum;
import org.app.service.entities.StatusEnum;
import org.app.service.entities.TipBugEnum;

import static org.junit.Assert.*;

public class TestScrumBugDataService {

	private static Logger logger = Logger
			.getLogger(TestScrumBugDataService.class.getName());
	private static BugSprintDataService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = BugSprintDataServiceEJBFactory.getScrumBugRepositoryService();

	}

	/* CHECK EJB live test */

	@Test
	public void testGetMessage() {
		String message = service.getMessage();
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG testGetMessage: " + message);
	}

	/**
	 * CREATE Test 1 (create-read)
	 */
	@Test
	public void testCreateBug() {

		/*
		 * Bug(String idBug, String titlu, Enum<TipBugEnum> tipBug,
		 * Enum<StatusEnum> status, Enum<PrioritateEnum> prioritate, Date
		 * dataAdaugare, Date dataInchidere, Date dataScadenta, String
		 * descriere, List<Atasament> atasamente, Integer nrAtasamente,
		 * List<Comentariu> comentarii)
		 */
		Calendar dataScadenta = Calendar.getInstance();
		;
		Bug bug = new Bug("1", "Project Display Test 1",
				TipBugEnum.IMPLEMENTARE, StatusEnum.CREATED, PrioritateEnum.P3,
				new Date(), null, dataScadenta.getTime(),
				"Refacere Pagina Display", null, null, null);
		bug = service.add(bug);
		logger.info("DEBUG createBug: saved bug: " + bug);
		assertNotNull("Failed to create new bug in repository!", bug);
		bug = service.getById("1");
		assertNotNull("Failed to find new bug in repository!", bug);
		logger.info("DEBUG createBug: queried bug: " + bug);
	}

	/**
	 * CREATE Test 2 (create aggregate)
	 */
	@Test
	public void testCreateNewBug() {
		Bug bug = service.createNewBug("2");
		assertNotNull("Failed to create new bug in repository!", bug);

		// update bug
		bug.setDescriere(bug.getDescriere() + " - changed by test client");
		List<Comentariu> comentariu = bug.getComentarii();

		// update bug comments
		for (Comentariu com : comentariu)
			com.setTitlu(com.getTitlu() + " - changed by test client");
		bug = service.add(bug);
		assertNotNull("Failed to save new bug in repository!", bug);
		logger.info("DEBUG createBug: bug changed: " + bug);
		// check read
		bug = service.getById("1");
		assertNotNull("Failed to find changed bug in repository!", bug);
		logger.info("DEBUG createBug: queried bug: " + bug);

	}

	/**
	 * READ Test (read-collection)
	 */
	@Test
	public void testToCollection() {
		Collection<Bug> bugs = service.toCollection();
		assertTrue("Failed to read bugs from repository!", bugs != null);
		logger.info("DEBUG testCollection: " + bugs.size());
		assertTrue("Failed to read any bug from repository!", bugs.size() > 0);
	}

	/**
	 * UPDATE Test (read-instance&update)
	 */
	@Test
	public void testAdd() {
		Bug bug = service.getById("1");
		assertNotNull("Failed to get bug from repository!", bug);
		bug.setTitlu(bug.getTitlu() + " - updated by test client");
		bug = service.add(bug);
		assertNotNull("Failed to save updated bug in repository!", bug);
		// check read
		bug = service.getById("1");
		assertNotNull("Failed to find updated bug in repository!", bug);
		logger.info("DEBUG testAdd: bug updated " + bug);
	}

	/**
	 * REMOVE Test
	 */
	@Test
	public void testRemove() {
		Bug bug = service.getById("1");
		assertNotNull("Failed to get bug from repository!", bug);
		service.remove(bug);
		// check read
		bug = service.getById("1");
		logger.info("DEBUG testRemove: bug removed " + bug);
		assertNotNull("Failed to remove bug from repository!", bug);
	}
}
