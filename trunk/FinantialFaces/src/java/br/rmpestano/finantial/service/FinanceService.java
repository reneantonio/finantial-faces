/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.service.generic.CrudService;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rmpestano
 */
@Named(value="financeService")
@RequestScoped
public class FinanceService {

    @Inject CrudService<Outcome> outcomeCrudService;
    @Inject CrudService<Income> incomeCrudService;

    public void removeOutcome(Outcome outcome) throws Exception{
        outcomeCrudService.delete(outcome.getId(), Outcome.class);
    }

}
