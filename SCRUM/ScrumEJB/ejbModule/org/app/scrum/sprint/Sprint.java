package org.app.scrum.sprint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.Cerinta;

public class Sprint {
	private Integer idSprint;
	private String obiectiv;
	private List<Cerinta> cerinte = new ArrayList<>();
	private Date dataStart;
	private String review;
	
	public List<Cerinta> getCerinte() {
		return cerinte;
	}
	public void setCerinte(List<Cerinta> cerinte) {
		this.cerinte = cerinte;
	}
	/*******************************/
	
	// proprietatea dataStart
	public Date getDataStart() {
		return dataStart;
	}
	public void setDataStart(Date dataStart) {
		this.dataStart = dataStart;
	}
	
	// --------------------------------------------------------------------- //
	private Long getDurataEstimataCerinta(Cerinta cerinta){
		Long durataEstimataCerinta = 0l; // 0 long
		for(Task t: cerinta.getTaskuri()){
			durataEstimataCerinta += t.getTimpEstimat() * 60 * 60 * 1000;
		}
		return durataEstimataCerinta;
	}
	
	private Long getDurataEstimataSprint() {
		Long durataEstimataSprint = 0l; // 0 long
		for (Cerinta c: this.cerinte){
			durataEstimataSprint += getDurataEstimataCerinta(c);
		}
		
		return durataEstimataSprint;
	}	
	
	// prop dataFinalizare
	public Date getDataFinalizare() {
		Long t1 = this.dataStart.getTime() + this.getDurataEstimataSprint();
		
		Date dataFinalizare = new Date(t1);
		
		return dataFinalizare;
	}	
	// --------------------------------------------------------------------- //
	public Integer getIdSprint() {
		return idSprint;
	}
	public void setIdSprint(Integer idSprint) {
		this.idSprint = idSprint;
	}
	public String getObiectiv() {
		return obiectiv;
	}
	public void setObiectiv(String obiectiv) {
		this.obiectiv = obiectiv;
	}





	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public Sprint() {
		super();
	}
	public Sprint(Integer idSprint, String obiectiv, Date dataStart) {
		super();
		this.idSprint = idSprint;
		this.obiectiv = obiectiv;
		this.dataStart = dataStart;
	}
	
	
	
}
