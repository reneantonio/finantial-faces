/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service.generic;

import java.lang.reflect.ParameterizedType;
import javax.ejb.EJB;

/**
 *
 * @author rmpestano
 */
public abstract class GenericService<T> {
    @EJB
    protected CrudService<T> crudService;


            








}
