package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Cerere;



@Remote
public interface DataService extends EntityRepository<Cerere>{

	String sayRest();

}