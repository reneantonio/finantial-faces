/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service.generic;

import java.lang.Class;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author rmpestano
 */
@Named(value="cudService")
@Dependent
@Stateless
public class CrudService <T> {
    @PersistenceContext
    EntityManager em;

    public CrudService() {

    }



     public T create(T t) throws Exception {


            em.persist(t);
            em.flush();
            return t;

    }

     public void delete(Long  id,Class c) {
        T t =  this.findById(id,c);
        if(t != null){
            em.remove(t);
         }
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T update(T t) {

        t = this.em.merge(t);
        em.setFlushMode(FlushModeType.COMMIT);
//        this.em.refresh(t);
        return t;
    }



    public List<T> findWithNamedQuery(String namedQueryName) {
        return em.createNamedQuery(namedQueryName).getResultList();
    }

     public List<T> findWithNamedQuery(String namedQueryName, Map parameters) {

         List<T> list = findWithNamedQuery(namedQueryName, parameters, 0);
         if(list.size() > 0){
             return  list;
         }
         else{
             return null;
         }
    }

    public List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    public List<T> findWithNamedQuery(String queryName,int firstResult, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setFirstResult(firstResult).
                setMaxResults(resultLimit).
                getResultList();
    }


    public List<T> findByNativeQuery(String sql, Class c) {
        return this.em.createNativeQuery(sql, c).getResultList();
    }

    public List<T> findWithTypedQuery(String sql, Class c){
        TypedQuery<T> query = em.createQuery(sql, c);
        return query.getResultList();
    }


    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Entry> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry entry : rawParameters) {
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }
        return (List<T>)query.getResultList();
    }
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, TemporalType temporalType) {
        Set<Entry> rawParameters = parameters.entrySet();
        Query query = em.createNamedQuery(namedQueryName);

        for (Entry entry : rawParameters) {
            query.setParameter(entry.getKey().toString(), (Date)entry.getValue(),temporalType);
        }
        return (List<T>)query.getResultList();
    }
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, Class T) {
        Set<Entry> rawParameters = parameters.entrySet();
        Query query = em.createNamedQuery(namedQueryName,T);

        for (Entry entry : rawParameters) {
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }
        return (List<T>)query.getResultList();
    }

    public List<T> findWithNamedQuery(String namedQueryName, Map parameters,
            int firstResult, int resultLimit) {
        Set<Entry> rawParameters = parameters.entrySet();
        javax.persistence.Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        if (firstResult > 0)
            query.setFirstResult(firstResult);

        for (Entry entry : rawParameters) {
            query.setParameter(entry.getKey().toString(), entry.getValue());
        }
        return query.getResultList();
    }
    
    public T findById(Long id, Class c){
        return (T) em.find(c,id);
    }

    public List<T> findAll(Class c){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(c);
        cq.from(c);
        TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }

}
