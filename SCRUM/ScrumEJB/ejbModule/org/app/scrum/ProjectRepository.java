package org.app.scrum;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;


public class ProjectRepository implements Repository{

    private EntityManager entityManager;
    private String sqlContDefaultText = "SELECT o FROM Proiect o";

    public ProjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //-------------------------------------------------------------//    
    /* Operatii de cautare conventionale */
    public Set<Project> getAll() {
        List<Project> result = this.entityManager
                .createQuery(this.sqlContDefaultText)
                .getResultList();

        TreeSet<Project> conturiOrdonate = new TreeSet<Project>();
        conturiOrdonate.addAll(result);

        return conturiOrdonate;
    }    

    public Project getProiectDupaNr(Integer nr){
    	Project c = this.entityManager.find(Project.class, nr);
    	this.entityManager.refresh(c);
    	return c;
    }
    
    //-------------------------------------------------------------//    
    /* Operatii CRUD */
    public void add(Project proiect){
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

    public void remove(Project proiect){
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


    public void refresh(Project proiect){
    	this.entityManager.refresh(proiect);
    }
    
    //-------------------------------------------------------------//
    /* Operatii de cautare specifice */
    public Project getContDupaDenumire(String numeProiect){
        return (Project) this.entityManager
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
