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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Service Facade
 * @author rmpestano
 */
@Named(value="financeService")
@Dependent
public class FinanceService {

    @EJB CrudService<Outcome> outcomeCrudService;
    @EJB CrudService<Income> incomeCrudService;
    @EJB CrudService<FinantialMonth> monthCrudService;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
        String sql = "select * from outcome o where o.USER_ID = '" + currentUser.getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(d) + "' and TYPE_ID = '"+type_id+"'";
        try {
            return outcomeCrudService.findByNativeQuery(sql,Outcome.class);
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
