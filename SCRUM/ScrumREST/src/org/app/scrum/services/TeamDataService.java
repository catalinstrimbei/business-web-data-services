package org.app.scrum.services;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.scrum.project.Project;
import org.app.scrum.team.Team;


@Remote
public interface TeamDataService extends EntityRepository<Team>{

}
