package org.app.scrum.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.scrum.Project;
import org.app.scrum.Release;

@Remote
public interface ScrumProjectDataService extends EntityRepository<Project>{

	String sayMessage();
	
	String sayMessage(String string);

	public abstract List<Release> getReleases(Project p);

	public abstract Project createNewProject();

}