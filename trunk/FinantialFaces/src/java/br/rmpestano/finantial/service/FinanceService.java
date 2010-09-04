/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.FinantialMonth;
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
    @Inject CrudService<FinantialMonth> monthCrudService;

    public FinanceService() {
    }


    public void removeOutcome(Outcome outcome){
        outcomeCrudService.delete(outcome.getId(), Outcome.class);
    }
    public void updateOutcome(Outcome outcome){
        outcomeCrudService.update(outcome);
    }

    public void updateMonth(FinantialMonth fm){
        monthCrudService.update(fm);
    }




}
