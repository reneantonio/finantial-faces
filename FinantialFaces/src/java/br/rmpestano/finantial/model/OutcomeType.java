/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 * entidade que representa os tipo de despesas
 * @author rmpestano
 */
@Entity
@Table(name = "outcome_type")
public class OutcomeType extends BaseEntity     {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof OutcomeType)) {
            return false;
        }
        OutcomeType other = (OutcomeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }

     public static List<OutcomeType> findAll(){

        return PersistenceManager.createEntityManager().createQuery("select t from OutcomeType t").getResultList();
    }
     public static OutcomeType findByDes(String des){
        Query q = PersistenceManager.createEntityManager().createQuery("select t from OutcomeType t where t.description = :des");
        q.setParameter("des", des);
        return (OutcomeType) q.getResultList().get(0);
    }
}
