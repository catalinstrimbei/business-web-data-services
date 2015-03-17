package org.app.patterns;

import org.app.scrum.Project;

public interface IProjectService extends Service{

	public abstract Integer computeDaysSpentOnProject(Project project);

}