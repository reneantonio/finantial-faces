/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 * this class represents an user preference
 * @author rafael.pestano
 */
@Entity
@Table(name="preference")
public class Preference extends BaseEntity{

    String key_;
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey_() {
        return key_;
    }

    public void setKey_(String key_) {
        this.key_ = key_;
    }




    public static Preference findById(Long id){
        EntityManager em = PersistenceManager.createEntityManager();
        Query q = em.createQuery("select p FROM Preference p WHERE p.id =:id");
        q.setParameter("id", id);
        return (Preference) q.getResultList().get(0);
    }




}
