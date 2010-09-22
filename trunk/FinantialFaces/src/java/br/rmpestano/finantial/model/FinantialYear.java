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
    private FinantialMonth mar;
    @Transient
    private FinantialMonth abr;
    @Transient
    private FinantialMonth mai;
    @Transient
    private FinantialMonth jun;
    @Transient
    private FinantialMonth jul;
    @Transient
    private FinantialMonth ago;
    @Transient
    private FinantialMonth set;
    @Transient
    private FinantialMonth out;
    @Transient
    private FinantialMonth nov;
    @Transient
    private FinantialMonth dez;
     
          
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

    public FinantialMonth getAbr() {
        return finantialMonths.get(3);
    }

    public FinantialMonth getAgo() {
        return finantialMonths.get(7);
    }

    public FinantialMonth getDez() {
        return finantialMonths.get(11);
    }

    public FinantialMonth getJul() {
        return finantialMonths.get(6);
    }

    public FinantialMonth getJun() {
        return finantialMonths.get(5);
    }

    public FinantialMonth getMai() {
        return finantialMonths.get(4);
    }

    public FinantialMonth getMar() {
        return finantialMonths.get(2);
    }

    public FinantialMonth getNov() {
        return finantialMonths.get(10);
    }

    public FinantialMonth getOut() {
        return finantialMonths.get(9);
    }

    public FinantialMonth getSet() {
        return finantialMonths.get(8);
    }
    

    @Override
    public String toString(){
        return this.title;
    }




    
}