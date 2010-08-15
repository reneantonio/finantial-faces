/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * entidade que representa as despesas (saidas)
 * @author rmpestano
 */
@Entity
@Table(name = "outcome")
public class Outcome extends BaseEntity {

    @ManyToOne
    private Finance finance;
    private static final long serialVersionUID = 1L;
    @OneToOne
    private OutcomeType type;

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public OutcomeType getType() {
        return type;
    }

    public void setType(OutcomeType type) {
        this.type = type;
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
