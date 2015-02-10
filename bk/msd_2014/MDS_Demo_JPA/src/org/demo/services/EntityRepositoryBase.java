package org.demo.services;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;

/**
 * 
 * @author catalin
 */
public class EntityRepositoryBase<T extends Object> implements EntityRepository<T>{
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
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
	
//	public EntityRepositoryBase(EntityManager em) {
//		this.em = em;
//		this.repositoryType = returnedClass();
//		logger.info("init repositoryType: " + repositoryType);
//		this.genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
//				+ " o";		
//		this.isIDGeneratedValue = isIDGeneratedValue();
//	}
	
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
	@Override
	public T add(T entity) {
		try {
			Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
			
//			logger.info("ADD entity: ID is " + id);
//			logger.info("ADD entity: em.contains(entity) is " + em.contains(entity));
//			logger.info("ADD entity: em.find(repositoryType, id) " + em.find(repositoryType, id));
//			logger.info("ADD entity: isIDGeneratedValue " + this.isIDGeneratedValue);
			
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
	    
	    logger.info("**** superType = " + superType);
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
	
	// Entity Type solution -----------------------------------------------------------------//
	/**
	 * Method returns class implementing EntityInterface which was used in class
	 * extending AbstractDAO
	 * 
	 * @return Class<T extends EntityInterface>
	 */
	public Class<T> returnedClass() {
		return (Class<T>) getTypeArguments(EntityRepositoryBase.class,
				getClass()).get(0);
	}

	/**
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	public static Class<?> getClass(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type)
					.getGenericComponentType();
			Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Get the actual type arguments a child class has used to extend a generic
	 * base class.
	 * 
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass,
			Class<? extends T> childClass) {
		Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class) {
				// there is no useful information for us in raw types, so just
				// keep going.
				type = ((Class) type).getGenericSuperclass();
			} else {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> rawType = (Class) parameterizedType.getRawType();

				Type[] actualTypeArguments = parameterizedType
						.getActualTypeArguments();
				TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++) {
					resolvedTypes
							.put(typeParameters[i], actualTypeArguments[i]);
				}

				if (!rawType.equals(baseClass)) {
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass,
		// determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class) {
			actualTypeArguments = ((Class) type).getTypeParameters();
		} else {
			actualTypeArguments = ((ParameterizedType) type)
					.getActualTypeArguments();
		}
		List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments) {
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}	
}
