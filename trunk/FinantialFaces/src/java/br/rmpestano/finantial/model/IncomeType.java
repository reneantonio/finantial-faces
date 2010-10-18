/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *  entidade que representa os tipo de receitas
 * @author rmpestano
 */
@Entity
@Table(name = "income_type")
public class IncomeType extends BaseEntity implements Comparable<IncomeType>{
    @NotEmpty(message="Informe a descrição do tipo")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return description;
    }

    /**
     * @deprecated use findUserIncomeTypes instead in class FinanceService
     * @return
     */
    public static List<IncomeType> findAll(){

        return PersistenceManager.createEntityManager().createQuery("select t from IncomeType t").getResultList();
    }

    public static IncomeType findByDes(String des){
        Query q = PersistenceManager.createEntityManager().createQuery("select t from IncomeType t where t.description = :des");
        q.setParameter("des", des);
        return (IncomeType) q.getResultList().get(0);
    }

     public static IncomeType findById(long id) {
        Query q = PersistenceManager.createEntityManager().createQuery("select t from IncomeType t where t.id = :id");
        q.setParameter("id", id);
        return (IncomeType) q.getSingleResult();
    }
    @Override
    public int compareTo(IncomeType o) {
        return this.description.toLowerCase().compareTo(o.description.toLowerCase());
    }
}
