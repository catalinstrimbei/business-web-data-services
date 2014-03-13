package org.app.scrum.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepositoryService;
import org.app.scrum.Project;

@Remote
public interface ScrumProjectRepositoryService extends EntityRepositoryService<Project>{

	String sayMessage(String string);

}