package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;



@Singleton
public class ProdusFactory {
	
	
	 public Produs buildProdus(Integer idProdus, String denProdus, String categorieProdus){
		 Produs produs = new Produs (idProdus, denProdus + "." + idProdus, categorieProdus );
		 List <Versiune> versiuni = new ArrayList<>();
		 
		 

		 
		 
		 
}
	 
	 
}
