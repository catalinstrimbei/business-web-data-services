package org.app.patterns;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class EntityRepositoryBase<T extends Object> implements EntityRepository<T> {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceContext(unitName="ScrumEJB")
	protected EntityManager em;
	
	protected Class<T> repositoryType;
	protected String genericSQL;
	private Boolean isIDGeneratedValue = false;

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
		
		this.isIDGeneratedValue = isIDGeneratedValue();
		logger.info("isIDGeneratedValue: " + isIDGeneratedValue);
		
		logger.info("... END DEFAULT INIT: ENTITY REPOSITORY!");
	}
	
	public EntityRepositoryBase(EntityManager em, Class<T> t) {
		this.em = em;
		this.repositoryType = t;
		genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
				+ " o";
		logger.info("generic JPAQL: " + genericSQL);
		
		this.isIDGeneratedValue = isIDGeneratedValue();
		logger.info("isIDGeneratedValue: " + isIDGeneratedValue);		
	}

	public EntityRepositoryBase(Class<T> t) {
		this.repositoryType = t;
		genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
				+ " o";
		logger.info("generic JPAQL: " + genericSQL);
		
		this.isIDGeneratedValue = isIDGeneratedValue();
		logger.info("isIDGeneratedValue: " + isIDGeneratedValue);		
	}	
	
	@Override
	public T getById(Object id) {
		return (T) em.find(repositoryType, id);
	}
	
	// QBExample
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

	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> toCollection() {
		logger.info("JPAQL: " + genericSQL);

		return em.createQuery(genericSQL).getResultList();
	}

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
	@Override @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public T add(T entity) {
		try {
			Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
			
			logger.info("ADD entity: ID is " + id);
			logger.info("ADD entity: em.contains(entity) is " + em.contains(entity));
			logger.info("ADD entity: em.find(repositoryType, id) " + em.find(repositoryType, id));
			logger.info("ADD entity: isIDGeneratedValue " + this.isIDGeneratedValue);
			
			// if ID GeneratedValue and is not new instance
//			if (id != null && this.isIDGeneratedValue && em.find(repositoryType, id) == null) 
				
			// if ID is null entity could have GenerateValue for ID
//			if (id == null || em.find(repositoryType, id) == null )
			if (id == null && this.isIDGeneratedValue)
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

	@Override
	public Collection<T> addAll(Collection<T> entities) {

		try {
			for (T entity : entities) {
				add(entity);
			}
			return entities;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

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
	@Override
	public int size() {
		String sqlCount = "SELECT count(o) FROM "
				+ repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1) + " o";

		logger.info("JPAQL: " + sqlCount);

		Long size = (Long) em.createQuery(sqlCount).getSingleResult();
		return size.intValue();
	}

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
	
	private Boolean isIDGeneratedValue(){
		if (this.repositoryType == null)
			return false;
		
		Field[] entityFields = this.repositoryType.getDeclaredFields();
		Boolean isId = false;
		Boolean isGeneratedValue = false;
		for(Field field: entityFields){
			Annotation[] annotations = field.getDeclaredAnnotations();
			for(Annotation annotation : annotations){
				if(annotation instanceof Id)
					isId = true;
				if(annotation instanceof GeneratedValue)
					isGeneratedValue = true;
				
//				logger.info("isIDGeneratedValue -- isId_isGeneratedValue = " + isId + "..."  + isGeneratedValue);
			}
			if (isId && isGeneratedValue)
				return true;
		}
		return false;
	}
}