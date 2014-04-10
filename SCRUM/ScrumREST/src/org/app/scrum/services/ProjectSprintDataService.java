package org.app.scrum.services;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.scrum.project.Project;

@Remote
public interface ProjectSprintDataService extends EntityRepository<Project>{

}
