package org.app.service.project;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;

@XmlRootElement(name="proiect")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AngajareResurse {
	private static String BASE_URL = "http://localhost:8080/SAM4ServiceREST/projects/";
	
	private Double CostAngajare;
	private Double Cantitate;
	private Date DataAngajare;
	private ProjectView project;
	
	public Double getCostAngajare() {
		return CostAngajare;
	}

	public void setCostAngajare(Double CostAngajare) {
		this.CostAngajare = CostAngajare;
	}

	public Double getCantitate() {
		return Cantitate;
	}

	public void setCantitate(Double Cantitate) {
		this.Cantitate = Cantitate;
	}


	public Date getDataAngajare() {
		return DataAngajare;
	}

	public void setDataAngajare(Date DataAngajare) {
		this.DataAngajare = DataAngajare;
	}

	public ProjectView getProject() {
		return project;
	}

	public void setProject(ProjectView project) {
		this.project = project;
	}

}
