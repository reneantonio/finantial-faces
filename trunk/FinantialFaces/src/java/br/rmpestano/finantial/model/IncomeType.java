/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *  entidade que representa os tipo de receitas
 * @author rmpestano
 */
@Entity
@Table(name = "income_type")
public class IncomeType extends BaseEntity {

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
    public String toString() {
        return description;
    }

    public static List<IncomeType> findAll(){

        return PersistenceManager.createEntityManager().createQuery("select t from IncomeType t").getResultList();
    }

    public static IncomeType findByDes(String des){
        Query q = PersistenceManager.createEntityManager().createQuery("select t from IncomeType t where t.description = :des");
        q.setParameter("des", des);
        return (IncomeType) q.getResultList().get(0);
    }
}
