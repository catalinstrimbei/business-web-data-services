package org.app.scrum.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.app.patterns.EntityRepositoryService;
import org.app.scrum.Project;
import org.app.scrum.Release;

@Remote
public interface ScrumProjectDataService extends EntityRepositoryService<Project>{

	String sayMessage(String string);

	public abstract List<Release> getAllReleases();

	public abstract List<Release> getReleases(Project p);

	public abstract Project createNewProject();

}