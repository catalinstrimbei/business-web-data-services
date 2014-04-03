package org.app.scrum.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ws.rs.PathParam;

import org.app.patterns.EntityRepository;
import org.app.scrum.project.Project;
import org.app.scrum.project.Release;

@Remote
public interface ScrumProjectDataService extends EntityRepository<Project>{

	String sayRest();
	
	String sayMessage(String string);

	public abstract List<Release> getReleases(Project p);

	public abstract Project createNewProject();
	
	public Project getByKey(Integer id);

}