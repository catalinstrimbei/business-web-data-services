package org.app.scrum;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;


public class RegistruProiecte implements Repository{

    private EntityManager entityManager;
    private String sqlContDefaultText = "SELECT o FROM Proiect o";

    public RegistruProiecte(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //-------------------------------------------------------------//    
    /* Operatii de cautare conventionale */
    public Set<Proiect> getAll() {
        List<Proiect> result = this.entityManager
                .createQuery(this.sqlContDefaultText)
                .getResultList();

        TreeSet<Proiect> conturiOrdonate = new TreeSet<Proiect>();
        conturiOrdonate.addAll(result);

        return conturiOrdonate;
    }    

    public Proiect getProiectDupaNr(Integer nr){
    	Proiect c = this.entityManager.find(Proiect.class, nr);
    	this.entityManager.refresh(c);
    	return c;
    }
    
    //-------------------------------------------------------------//    
    /* Operatii CRUD */
    public void add(Proiect proiect){
    	try{
    		entityManager.getTransaction().begin();
            if (this.entityManager.contains(proiect))
                this.entityManager.merge(proiect);
            else
                this.entityManager.persist(proiect);
            entityManager.getTransaction().commit();
    	}catch(Exception ex){
    		if (entityManager.getTransaction().isActive())
    			entityManager.getTransaction().rollback();
    		throw new RuntimeException(ex.getMessage());
    	}        
    }

    public void remove(Proiect proiect){
    	try{
    		entityManager.getTransaction().begin();
            if (this.entityManager.contains(proiect))
                this.entityManager.remove(proiect);
            entityManager.getTransaction().commit();
    	}catch(Exception ex){
    		if (entityManager.getTransaction().isActive())
    			entityManager.getTransaction().rollback();
    		throw new RuntimeException(ex.getMessage());
    	} 
    }


    public void refresh(Proiect proiect){
    	this.entityManager.refresh(proiect);
    }
    
    //-------------------------------------------------------------//
    /* Operatii de cautare specifice */
    public Proiect getContDupaDenumire(String numeProiect){
        return (Proiect) this.entityManager
                .createQuery(sqlContDefaultText + " WHERE o.numeProiect = :numeProiect")
                .setParameter("numeProiect", numeProiect)
                .getSingleResult();
    }

	public Long getCountProiect() {
        return (Long) this.entityManager
        .createQuery("SELECT COUNT(c) FROM Proiect c")
        .getSingleResult();
	}
	//-------------------------------------------------------------------------------------
}
