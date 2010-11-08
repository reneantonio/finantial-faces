/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.generic.CrudService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Service Facade
 * @author rmpestano
 */
@Named(value="financeService")
@Dependent
public class FinanceService implements Serializable{

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

     public List<Income> findMonthIncomesByUserAndType(Date month, Long type_id) {
        String sql = "select * from income i where i.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "' and TYPE_ID = '"+type_id+"'";
        return incomeCrudService.findByNativeQuery(sql, Income.class);
    }
       public List<Income> findMonthIncomesByUserAndType(Date month, Long type_id, int first,int pageSize) {
        String sql = "select * from (select @row := @row + 1 as row,o.* as result from income o, (SELECT @row := 0) r where o.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month)  + "' and TYPE_ID = '"+type_id+ "') As derived1 where row between "+ (first+1) + " and "+(first + pageSize);
        return incomeCrudService.findByNativeQuery(sql, Income.class);
    }
     public List<Outcome> findMonthOutcomesByUserAndType(Date month, Long type_id) {
        String sql = "select * from outcome i where i.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "' and TYPE_ID = '"+type_id+"'";
        return outcomeCrudService.findByNativeQuery(sql, Outcome.class);
    }
     public List<Outcome> findMonthOutcomesByUserAndType(Date month, Long type_id, int first,int pageSize) {
        String sql = "select * from (select @row := @row + 1 as row,o.* as result from outcome o, (SELECT @row := 0) r where o.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month)  + "' and TYPE_ID = '"+type_id+ "') As derived1 where row between "+ (first+1) + " and "+(first + pageSize);
        return outcomeCrudService.findByNativeQuery(sql, Outcome.class);
    }

    public List<Outcome> findMonthOutcomesByUser(Date month) {
        String sql = "select * from outcome o where o.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "'";
        return outcomeCrudService.findByNativeQuery(sql, Outcome.class);
    }
    //used for lazy loading
    public List<Income> findMonthIncomesByUser(Date month, int first,int pageSize) {
        String sql = "select * from (select @row := @row + 1 as row,o.* as result from income o, (SELECT @row := 0) r where o.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "') As derived1 where row between "+ (first+1) + " and "+(first+ + pageSize);
        return incomeCrudService.findByNativeQuery(sql, Income.class);
    }

    //used for lazy loading
    public List<Outcome> findMonthOutcomesByUser(Date month, int first,int pageSize) {
        String sql = "select * from (select @row := @row + 1 as row,o.* as result from outcome o, (SELECT @row := 0) r where o.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "') As derived1 where row between "+ (first+1) + " and "+(first + pageSize);
        return outcomeCrudService.findByNativeQuery(sql, Outcome.class);
    }
    public List<Income> findMonthIncomesByUser(Date month) {
        String sql = "select * from income o where o.USER_ID = '" + getCurrentUser().getId() + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "'";
        return incomeCrudService.findByNativeQuery(sql, Income.class);
    }

     public Double getTotalOutcomeInTheMonth(FinantialMonth fm) {
        Double total =new Double(0);
        for (Outcome outcome : this.findMonthOutcomesByUser(fm.getDate())) {
                if(outcome.getValue() != null){
                total+=outcome.getValue();
            }
        }
        return total;
    }
     public Double getTotalIncomeInTheMonth(FinantialMonth fm) {
        Double total =new Double(0);
        for (Income income : this.findMonthIncomesByUser(fm.getDate())) {
                if(income.getValue() != null){
                total+=income.getValue();
            }
        }
        return total;
    }

    private User getCurrentUser(){
        User currentUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return currentUser;
    }

    public List<IncomeType> findUserIncomeTypes(){
        return getCurrentUser().getUserIncomeTypes();
    }
    public List<OutcomeType> findUserOutcomeTypes(){
        return getCurrentUser().getUserOutcomeTypes();
    }
}
