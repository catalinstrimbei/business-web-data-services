package org.app.scrum;

public class ProjectView {
	private Integer projectNo;
	
	private String name;

	public Integer getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectView(Integer projectNo, String name) {
		super();
		this.projectNo = projectNo;
		this.name = name;
	}

	public ProjectView() {
		super();
	}

	@Override
	public String toString() {
		return "ProjectView [projectNo=" + projectNo + ", name=" + name + "]";
	}
}
