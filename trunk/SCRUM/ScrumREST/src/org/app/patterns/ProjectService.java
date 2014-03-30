package org.app.patterns;

import java.util.Date;

import org.app.scrum.project.Project;
import org.app.scrum.project.Release;

public class ProjectService implements IProjectService {
	/* (non-Javadoc)
	 * @see org.app.scrum.IServiciuProiecte#calculZileEfortProiect(org.app.scrum.Proiect)
	 */
	@Override
	public Integer computeDaysSpentOnProject(Project project){
		Integer countDays;
		Long dayInterval = 24l * 60 * 60 * 1000;
		
		Long releasesInterval = 0l;
		Date lastDate = project.getStartDate();
		for (Release r: project.getReleases()){
			releasesInterval += r.getPublishDate().getTime() - lastDate.getTime();
			lastDate = r.getPublishDate();
		}
		
		Long interval =releasesInterval/dayInterval; 
		return interval.intValue();
	}
}
