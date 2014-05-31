package org.app.scrum.services;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.scrum.project.Activitate;
import org.app.scrum.project.Proiect;


@Remote
public interface ProjectSprintDataService 
//	extends EntityRepository<Project, Integer>{
	extends EntityRepository<Proiect>{
	public String getMessage();
	public Proiect createNewProject(Integer IdProiect);
	public Activitate getActivitateById(Integer releaseid);
}
