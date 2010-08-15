/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;

/**
 *
 * @author rmpestano
 */

@MappedSuperclass
public abstract class BaseEntity  implements Serializable {
     private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     protected Long id;

    public Long getId() {
        return id;
    }

}
