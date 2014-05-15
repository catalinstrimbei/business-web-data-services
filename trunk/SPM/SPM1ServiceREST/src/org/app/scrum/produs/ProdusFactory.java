package org.app.scrum.produs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Editie;
import org.app.service.entities.Produs;
import org.app.service.entities.Versiune;



@Singleton
public class ProdusFactory {
	
	
	 public static Produs buildProdus(Integer idProdus, String denProdus,String categorieProdus,Integer versiuniCount, Integer editiiCount){
		 Produs produs = new Produs(idProdus, denProdus + "." +  idProdus, categorieProdus);
		 List <Versiune> versiuniProdus = new ArrayList<>();
		 List <Versiune> versiuneCurentaProdus = new ArrayList<>();
		 List <Editie> editiiProdus = new ArrayList<>();
		 
		 Date dataLansare=new Date();
		 Long interval= (long) (301 /*zile*/* 24 /*ore*/* 60 /*min*/* 60/*s*/* 1000/*milis*/);
		 
		 for ( int i=0; i<= versiuniCount-1; i++){
			 versiuniProdus.add(new Versiune( null, "V: " + produs.getIdProdus() + "." + i, new Date(dataLansare.getTime()+i*interval), produs));
		 }
		 produs.setVersiuni(versiuniProdus);
		 
		 for ( int i=0; i<= editiiCount-1; i++){
			 editiiProdus.add(new Editie( null, "E: " + produs.getIdProdus() + "." + i, produs));	
		 }
		 produs.setEditii(editiiProdus);
		 
		 return produs;
		 
		 
		 
		 
		 
		 

		 
		 
		 
}
	 
	 
}
