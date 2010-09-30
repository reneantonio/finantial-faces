/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.generic.CrudService;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
    public void removeIncome(Income income){
        incomeCrudService.delete(income.getId(), Income.class);
    }
    public void updateOutcome(Outcome outcome){
        outcomeCrudService.update(outcome);
    }
    public void updateIncome(Income income){
        incomeCrudService.update(income);
    }

    public void updateMonth(FinantialMonth fm){
        monthCrudService.update(fm);
    }

    public List<Outcome> findUserOutcomeByDateAndType(Long type_id, Date d){
        User currentUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        try {
            return Outcome.findMonthOutcomesByUserAndType(currentUser.getId(), d, type_id);
        } catch (Exception ex) {
            return null;
        }
    }
    public List<Income> findUserIncomeByDateAndType(Long type_id, Date d){
        User currentUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        try {
            return Income.findMonthIncomesByUserAndType(currentUser.getId(), d, type_id);
        } catch (Exception ex) {
            return null;
        }
    }




}
