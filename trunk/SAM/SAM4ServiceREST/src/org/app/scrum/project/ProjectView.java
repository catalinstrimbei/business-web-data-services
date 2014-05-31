package org.app.scrum.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;

@XmlRootElement(name="proiect")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProjectView {
	private static String BASE_URL = "http://localhost:8080/ScrumREST/proiecte/project/";
	
	private Integer IdProiect;
	private String Nume;
	private Integer activitateCount = 0;
	
	public Integer getIdProiect() {
		return IdProiect;
	}
	public void setIdProiect(Integer IdProiect) {
		this.IdProiect = IdProiect;
	}
	public String getNume() {
		return Nume;
	}
	public void setNume(String Nume) {
		this.Nume = Nume;
	}
	public Integer getactivitateCount() {
		return activitateCount;
	}
	public void setactivitateCount(Integer activitateCount) {
		this.activitateCount = activitateCount;
	}
	public ProjectView(Integer IdProiect, String Nume) {
		super();
		this.IdProiect = IdProiect;
		this.Nume = Nume;
	}
	public ProjectView() {
		super();
	}
	
	public ProjectView(Proiect proiect) {
		this.setIdProiect(proiect.getIdProiect());
		this.setNume(proiect.getNume());
		if (proiect.getActivitati() != null)
			this.setactivitateCount(proiect.getActivitati().size());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IdProiect == null) ? 0 : IdProiect.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectView other = (ProjectView) obj;
		if (IdProiect == null) {
			if (other.IdProiect != null)
				return false;
		} else if (!IdProiect.equals(other.IdProiect))
			return false;
		return true;
	}	
	
	/* Rest Resource URL*/
//	@XmlElement(name = "link", namespace = AtomLink.ATOM_NAMESPACE)
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdProiect();
        return new AtomLink(restUrl, "get-proiect");
    }
	
	public static ProjectView[] getProjectViewList(Collection<Proiect> proiecte){
		List<ProjectView> projectViewList = new ArrayList<>();
		for(Proiect p: proiecte){
			projectViewList.add(new ProjectView(p));
		}
		return projectViewList.toArray(new ProjectView[0]);
	}	
}