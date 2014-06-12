package org.demo.services;

import java.util.Collection;

import javax.persistence.EntityManager;

public interface EntityRepository<T extends Object> {
	public void setEm(EntityManager em);

	// Repository query implementation
	public T getById(Object id);
	
	// QBExample
	public Collection<T> get(T entitySample);

	public Collection<T> toCollection();

	public T[] toArray();

	// Repository transaction implementation
	
	public T add(T entity);

	public Collection<T> addAll(Collection<T> entities);

	public boolean remove(T entity);

	public boolean removeAll(Collection<T> entities);

	// Others
	public int size();

	public T refresh(T entity);

}