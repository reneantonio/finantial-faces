/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * entidade que representa as despesas (saidas)
 * @author rmpestano
 */
@Entity
@Table(name = "outcome")
public class Outcome extends BaseEntity {
    @ManyToOne
    private User user;
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private FinantialMonth finantialMonth;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @OneToOne
    private OutcomeType type;
    private Double value;
    private String Description;




    public OutcomeType getType() {
        return type;
    }

    public void setType(OutcomeType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FinantialMonth getFinantialMonth() {
        return finantialMonth;
    }

    public void setFinantialMonth(FinantialMonth finantialMonth) {
        this.finantialMonth = finantialMonth;
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
        if (!(object instanceof Outcome)) {
            return false;
        }
        Outcome other = (Outcome) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.rmpestano.finantial.model.Outcome[id=" + id + "]";
    }
}
