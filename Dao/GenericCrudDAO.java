/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author yanisi
 */

public abstract class GenericCrudDAO<T> {
    
     private final static String UNIT_NAME = "CrudPU";
    
     @PersistenceContext(unitName = UNIT_NAME)
       protected EntityManager em;
 
    private Class<T> entityClass;
 
    public GenericCrudDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    public void save(T entity) {
        em.persist(entity);
    }
 
    protected void delete(Object id, Class<T> classe) {
        T entityToBeRemoved = em.getReference(classe, id);
 
        em.remove(entityToBeRemoved);
    }
 
    public T update(T entity) {
        return em.merge(entity);
    }
 
    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }
 
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return result;
    }
 
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
