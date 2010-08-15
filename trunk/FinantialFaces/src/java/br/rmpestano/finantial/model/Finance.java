/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author rmpestano
 */
@Entity
@Table(name = "finance")
public class Finance extends BaseEntity {
    @ManyToOne
    private User user;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @OneToMany(mappedBy = "finance",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Income> income;
    @OneToMany(mappedBy = "finance",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Outcome> outcome;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(List<Income> income) {
        this.income = income;
    }

    public List<Outcome> getOutcome() {
        return outcome;
    }

    public void setOutcome(List<Outcome> outcome) {
        this.outcome = outcome;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finance)) {
            return false;
        }
        Finance other = (Finance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.rmpestano.finantial.model.Finance[id=" + id + "]";
    }
}
