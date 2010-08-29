/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * representa uma aba do tipo mes
 * @author rmpestano
 */
@Entity
@Table(name = "finantial_month")
public class FinantialMonth implements Serializable {

    private String title;
    @Temporal(TemporalType.DATE)
    @Id
    private Date date;
    @OneToMany(mappedBy = "finantialMonth",cascade=CascadeType.ALL)
    private List<Income> monthIncomes;
    @OneToMany(mappedBy = "finantialMonth",cascade=CascadeType.ALL)
    private List<Outcome> monthOutcomes;
    @ManyToOne(cascade=CascadeType.ALL)
    private FinantialYear finantialYear;

    @Transient
    private List<Income> currentUserIncomesInTheMonth;
    @Transient
    private List<Outcome> currentUserOutcomesInTheMonth;
    @Transient
    private Double totalOutcomeInTheMonth;
    @Transient
    private Double totalIncomeInTheMonth;

    public List<Income> getCurrentUserIncomesInTheMonth() {
         List<Income> userIncomes = new ArrayList<Income>();
            User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
             for (Income income : monthIncomes) {
                if(income.getUser().getId().equals(u.getId())){
                    userIncomes.add(income);
                }
            }

        return userIncomes;
    }
    public List<Outcome> getCurrentUserOutcomesInTheMonth() {
         List<Outcome> userOutcomes = new ArrayList<Outcome>();
            User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
             for (Outcome outcome : monthOutcomes) {
                if(outcome.getUser().getId().equals(u.getId())){
                    userOutcomes.add(outcome);
                }
            }

        return userOutcomes;
    }

    



    public FinantialYear getFinantialYear() {
        return finantialYear;
    }

    public void setFinantialYear(FinantialYear finantialYear) {
        this.finantialYear = finantialYear;
    }



    public Date getDate() {
        return date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Income> getMonthIncomes() {
        return monthIncomes;
    }

    public void setMonthIncomes(List<Income> monthIncomes) {
        this.monthIncomes = monthIncomes;
    }

    public List<Outcome> getMonthOutcomes() {
        return monthOutcomes;
    }

    public void setMonthOutcomes(List<Outcome> monthOutcomes) {
        this.monthOutcomes = monthOutcomes;
    }

    public Double getTotalOutcomeInTheMonth() {
        Double total =new Double(0);
        for (Outcome outcome : monthOutcomes) {
                if(outcome.getValue() != null){
                total+=outcome.getValue();
            }
        }
        return total;
    }
    public Double getTotalIncomeInTheMonth() {
        Double total =new Double(0);
        for (Income income : monthIncomes) {
            if(income.getValue() != null)
               total+=income.getValue();
        }
        return total;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FinantialMonth other = (FinantialMonth) obj;
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.title != null ? this.title.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return title;
    }


     public static void create(FinantialMonth fm){
        EntityManager em = PersistenceManager.createEntityManager();
        em.getTransaction().begin();
        em.persist(fm);
        em.getTransaction().commit();
    }
    public static void update(FinantialMonth fm){
        EntityManager em = PersistenceManager.createEntityManager();
        em.getTransaction().begin();
        em.merge(fm);
        em.getTransaction().commit();
    }
    public List<FinantialMonth> findAll(){
        EntityManager em = PersistenceManager.createEntityManager();
        return em.createQuery("select f FROM FinantialMonth f").getResultList();
    }

    public static FinantialMonth findByTitle(String title){
        EntityManager em = PersistenceManager.createEntityManager();
        Query q = em.createQuery("select f FROM FinantialMonth f WHERE f.title =:title");
        q.setParameter("title", title);
        return (FinantialMonth) q.getResultList().get(0);
    }
    public static FinantialMonth findById(Date id){
        EntityManager em = PersistenceManager.createEntityManager();
        Query q = em.createQuery("select f FROM FinantialMonth f WHERE f.date =:id");
        q.setParameter("id", id);
        return (FinantialMonth) q.getResultList().get(0);
    }

}
