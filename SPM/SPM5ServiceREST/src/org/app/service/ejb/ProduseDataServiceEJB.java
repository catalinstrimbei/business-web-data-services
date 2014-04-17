package org.app.service.ejb;

import javax.ejb.Stateless;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Produse;

@Stateless
public class ProduseDataServiceEJB extends EntityRepositoryBase<Produse> implements ProduseDataService{
	
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ProduseService#save(org.app.service.entities.Produse)
	 */
	@Override
	public Produse save(Produse produs){
		produs = this.add(produs);
		return produs;
	}
	
	public String test(){
		return "Hello from ProduseDataService.";
	}
}
