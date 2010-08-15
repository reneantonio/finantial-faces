/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * classe utilitária que fornece um entityManager para classes do modelo que não possuem uma service já que terão poucas consultas
 * @author rmpestano
 */
public final class PersistenceManager {
    private static EntityManagerFactory emf;

    private PersistenceManager() {
    }

    static {
        PersistenceManager.emf = Persistence.createEntityManagerFactory("FinantialFacesPU");
    }

    public static EntityManager createEntityManager() {
        return PersistenceManager.emf.createEntityManager();
    }

}
