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
import javax.persistence.Table;

/**
 * representa uma aba do tipo ano
 * @author rmpestano
 */
@Entity
@Table(name="finantial_year")
public class FinantialYear extends BaseEntity {

    private String title;
    @OneToMany(mappedBy = "finantialYear",cascade=CascadeType.ALL)
    private List<FinantialMonth> finantialMonths;

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

    public void create(){
        EntityManager em = PersistenceManager.createEntityManager();
        em.getTransaction().begin();
        em.persist(this);
        em.getTransaction().commit();
    }
    public void update(){
        EntityManager em = PersistenceManager.createEntityManager();
        em.getTransaction().begin();
        em.merge(this);
        em.getTransaction().commit();
    }
    public List<FinantialYear> findAll(){
        EntityManager em = PersistenceManager.createEntityManager();
        return em.createNamedQuery("select f FROM FinantialYear f").getResultList();
    }

    
}