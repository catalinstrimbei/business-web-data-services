package org.app.scrum.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.scrum.team.Team;

@Remote
public interface ScrumTeamDataService extends EntityRepository<Team>{

}
