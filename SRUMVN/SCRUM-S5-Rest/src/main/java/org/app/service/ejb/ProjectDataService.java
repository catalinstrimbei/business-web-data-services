package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Project;
import org.app.service.entities.Release;

@Remote
public interface ProjectDataService extends EntityRepository<Project>{
	// Classic CRUD methods
	// CREATE and UPDATE
	/* Project add(Project)					inherited from EntityRepository*/
	// READ
	/* Project getById(Project)				inherited from EntityRepository*/
	/* Collection<Project> toCollection()	inherited from EntityRepository*/
	// DELETE
	/* boolean remove(Project)				inherited from EntityRepository*/
	
	// create aggregate entity: project root with releases as components
	Project createNewProject(Integer id);
	// Query method on release components
	Release getReleaseById(Integer releaseid);
	// Other
	String getMessage();
}