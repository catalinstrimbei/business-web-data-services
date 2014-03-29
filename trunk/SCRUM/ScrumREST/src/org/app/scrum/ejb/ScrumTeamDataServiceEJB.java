package org.app.scrum.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.team.Team;

@Stateless
@LocalBean
public class ScrumTeamDataServiceEJB 
	extends EntityRepositoryBase<Team>
	implements ScrumTeamDataService{
	
	// skeleton

}
