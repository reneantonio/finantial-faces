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
    private String description;




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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String toString() {
        return "br.rmpestano.finantial.model.Outcome[id=" + id + "]";
    }
}
