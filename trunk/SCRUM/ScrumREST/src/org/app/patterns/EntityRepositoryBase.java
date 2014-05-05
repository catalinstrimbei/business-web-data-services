package org.app.patterns;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author catalin
 */
//public class EntityRepositoryBase<T extends Object, Z extends Object> implements EntityRepository<T, Z> {
public class EntityRepositoryBase<T extends Object> implements EntityRepository<T> {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceContext(unitName="ScrumEJB")
	protected EntityManager em;
	
	protected Class<T> repositoryType;
	protected String genericSQL;
//	protected Class<? extends Object> idType;

	@Override
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public EntityRepositoryBase() {

		logger.info("START DEFAULT INIT: ENTITY REPOSITORY ... ");
		
		this.repositoryType = getEntityParametrizedType();
		logger.info("init repositoryType: " + repositoryType.getSimpleName());
		
		this.genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
				+ " o";
		logger.info("init generic JPAQL: " + genericSQL);		
		
		logger.info("... END DEFAULT INIT: ENTITY REPOSITORY!");
	}
	
	public EntityRepositoryBase(EntityManager em, Class<T> t) {
		this.em = em;
		this.repositoryType = t;
		genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
				+ " o";
		logger.info("generic JPAQL: " + genericSQL);
	}

	public EntityRepositoryBase(Class<T> t) {
		this.repositoryType = t;
		genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
				+ " o";
		logger.info("generic JPAQL: " + genericSQL);
	}	
	
	// Repository query implementation
	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#getById(java.lang.Object)
	 */
//	@GET @Path("entity/{id}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
//	public T getById(@PathParam("id") Z id) {
//	public <E> T getById(@PathParam("id") E id) {
	public T getById(Object id) {
		return (T) em.find(repositoryType, id);
	}


	
	// QBExample
	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#get(T)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<T> get(T entitySample) {

		Map<String, Object> sqlCriterias = new HashMap<String, Object>();
		try {
			// get all properties and transform them into sqlCriterias
			PropertyDescriptor[] properties = Introspector.getBeanInfo(repositoryType).getPropertyDescriptors();
			Object propertyValue;
			Method readMethod;
			for (PropertyDescriptor property : properties) {
				readMethod = property.getReadMethod();
				if (readMethod != null) {
					logger.info("readMethod = " + readMethod);
					propertyValue = readMethod.invoke(entitySample);
					logger.info("propertyValue = " + propertyValue);
					if (propertyValue == null || property.getName().equals("class")) {
						continue;
					}
					if (propertyValue instanceof Collection && ((Collection) propertyValue).size() == 0) {
						continue;
					}
					sqlCriterias.put(property.getName(), propertyValue);
				}
			}
		} catch (IllegalAccessException ex) {
			Logger.getLogger(EntityRepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(EntityRepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(EntityRepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IntrospectionException ex) {
			Logger.getLogger(EntityRepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (sqlCriterias.isEmpty()) {
			return null;
		}

		String queryString = genericSQL + " WHERE ";
		for (String criteria : sqlCriterias.keySet()) {
			if (sqlCriterias.get(criteria) instanceof Collection) {
				queryString += "o." + criteria + " IN (:" + criteria + ") AND ";
			} else {
				queryString += "o." + criteria + " = :" + criteria + " AND ";
			}
		}
		queryString += " 1 = 1";

		logger.info("JPAQL: " + queryString);

		Query query = em.createQuery(queryString);
		for (String criteria : sqlCriterias.keySet()) {
			query = query.setParameter(criteria, sqlCriterias.get(criteria));
		}
		return query.getResultList();

	}

	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#toCollection()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> toCollection() {
		logger.info("JPAQL: " + genericSQL);

		return em.createQuery(genericSQL).getResultList();
	}

	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#toArray()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		logger.info("JPAQL: " + genericSQL);

		List<T> entities = em.createQuery(genericSQL).getResultList();
		if (entities == null) {
			return null;
		}

		return (T[]) entities.toArray();
	}

	// Repository transaction implementation
	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#add(T)
	 */
	@Override @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public T add(T entity) {
		try {
			Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
			if (id == null) // || em.find(repositoryType, id) == null) // !!! - case of Generated value to test
				em.persist(entity);
			else
				em.merge(entity);
			em.flush();
			
			return entity;
		} catch (Exception e) {
			logger.info("ERROR: " + " not able to ADD " + entity + "!");
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}

	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#addAll(java.util.Collection)
	 */
	@Override
	public Collection<T> addAll(Collection<T> entities) {

		try {
			for (T entity : entities) {
//				em.merge(entity);
				add(entity);
			}
			return entities;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#remove(T)
	 */
	@Override
	public boolean remove(T entity) {
		try {
			entity = em.merge(entity);
			em.remove(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {

		}
	}

	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<T> entities) {

		try {
			for (Object c : entities) {
				em.remove(c);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Others
	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#size()
	 */
	@GET @Path("/size") @Produces("application/json")
	@Override
	public int size() {
		String sqlCount = "SELECT count(o) FROM "
				+ repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1) + " o";

		logger.info("JPAQL: " + sqlCount);

		Long size = (Long) em.createQuery(sqlCount).getSingleResult();
		return size.intValue();
	}

	/* (non-Javadoc)
	 * @see org.app.patterns.EntityRepositoryService#refresh(T)
	 */
	@Override
	public T refresh(T entity) {
		entity = em.merge(entity);
		em.refresh(entity);
		return entity;
	}
	
	
	private Class<?> extractClassFromType(Type t) throws ClassCastException {
	    if (t instanceof Class<?>) {
	        return (Class<?>)t;
	    }
	    return (Class<?>)((ParameterizedType)t).getRawType();
	}

	public Class<T> getEntityParametrizedType() throws ClassCastException {
	    Class<?> superClass = getClass(); // initial value
	    Type superType;
	    do {
	        superType = superClass.getGenericSuperclass();
	        superClass = extractClassFromType(superType);
	    } while (! (superClass.equals(EntityRepositoryBase.class)));

	    Type actualArg = ((ParameterizedType)superType).getActualTypeArguments()[0];
	    return (Class<T>)extractClassFromType(actualArg);
	}		
	
//	private void getEntityID(T entity){
//		
//		Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
//		/* http://www.objectdb.com/java/jpa/persistence/metamodel */
//		Metamodel metamodel = em.getMetamodel();
//		ManagedType<T> managedType = metamodel.managedType(this.repositoryType);
//		EntityType<T> entityType = metamodel.entity(this.repositoryType);
//		SingularAttribute<?, Long> id1 = entityType.getId(Long.class);
//	}
}