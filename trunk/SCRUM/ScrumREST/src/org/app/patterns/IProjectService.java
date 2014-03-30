package org.app.patterns;

import org.app.scrum.project.Project;

public interface IProjectService extends Service{

	public abstract Integer computeDaysSpentOnProject(Project project);

}