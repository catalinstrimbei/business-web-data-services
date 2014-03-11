package org.app.scrum;

import java.util.Date;

public class ServiciuProiecte implements IServiciuProiecte {
	/* (non-Javadoc)
	 * @see org.app.scrum.IServiciuProiecte#calculZileEfortProiect(org.app.scrum.Proiect)
	 */
	@Override
	public Integer calculZileEfortProiect(Proiect proiect){
		Integer nrZile;
		Long intervalZi = 24l * 60 * 60 * 1000;
		
		Long releasesInterval = 0l;
		Date ultimaData = proiect.getDataStart();
		for (Release r: proiect.getReleases()){
			releasesInterval += r.getDataPublicare().getTime() - ultimaData.getTime();
			ultimaData = r.getDataPublicare();
		}
		
		Long interval =releasesInterval/intervalZi; 
		return interval.intValue();
	}
}
