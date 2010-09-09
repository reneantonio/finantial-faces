/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * representa uma aba do tipo ano
 * @author rmpestano
 */
@Entity
@Table(name="finantial_year")
public class FinantialYear extends BaseEntity implements Comparable<FinantialYear>{

    private String title;
    @OneToMany(mappedBy = "finantialYear",cascade=CascadeType.ALL)
    private List<FinantialMonth> finantialMonths;

    @Transient
    private FinantialMonth jan;
    @Transient
    private FinantialMonth fev;

    @Transient
    private String firstDateOfYear;
    @Transient
    private String lastDateOfYear;


    public FinantialYear() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FinantialMonth> getFinantialMonths() {
        return finantialMonths;
    }

    public void setFinantialMonths(List<FinantialMonth> finantialMonths) {
        this.finantialMonths = finantialMonths;
    }

    public String getFirstDateOfYear() {
        return "01/01/"+this.getTitle();
    }

    public String getLastDateOfYear() {
         return "31/12/"+this.getTitle();
    }


    public static void create(FinantialYear fy){
        EntityManager em = PersistenceManager.createEntityManager();
        em.getTransaction().begin();
        em.persist(fy);
        em.getTransaction().commit();
    }
    public static void update(FinantialYear fy){
        EntityManager em = PersistenceManager.createEntityManager();
        em.getTransaction().begin();
        em.merge(fy);
        em.getTransaction().commit();
    }
    public List<FinantialYear> findAll(){
        EntityManager em = PersistenceManager.createEntityManager();
        return em.createQuery("select f FROM FinantialYear f").getResultList();
    }
    public static FinantialYear findByYear(String title){
        EntityManager em = PersistenceManager.createEntityManager();
        Query q = em.createQuery("select f FROM FinantialYear f WHERE f.title =:title");
        q.setParameter("title", title);
        return (FinantialYear) q.getResultList().get(0);
    }

    @Override
    public int compareTo(FinantialYear o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    public FinantialMonth getFev() {
        return finantialMonths.get(1);
    }



    public FinantialMonth getJan() {
         return finantialMonths.get(0);
    }

    




    
}