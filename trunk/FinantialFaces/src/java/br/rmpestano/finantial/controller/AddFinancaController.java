/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.Finance;
import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.MonthService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author rmpestano
 */
@ViewScoped
@ManagedBean(name="addBean")
public class AddFinancaController {
    MonthService monthService;
    private final String INCOME = "income";
    private final String OUTCOME = "outcome";
    private List<String> tiposFinanca = new ArrayList<String>(){{add(INCOME);add(OUTCOME);}};
    private List<OutcomeType> subtiposOutcome;
    private List<IncomeType> subtiposIncome;
    private Finance finance = new Finance();
    private Outcome despesa = new Outcome();
    private Income  receita = new Income();
    private String tipoCorrete = OUTCOME;
    private IncomeType subtipoIncomeCorrete;
    private OutcomeType subtipoOutcomeCorrete;

    public AddFinancaController() {
        subtiposIncome = IncomeType.findAll();
        subtiposOutcome = OutcomeType.findAll();
        monthService = (MonthService) BeanManagerController.getBeanByName("monthService");
    }


    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
        
    }

    public List<String> getTiposFinanca() {
        return tiposFinanca;
    }

    public void setTiposFinanca(List<String> tiposFinanca) {
        this.tiposFinanca = tiposFinanca;
    }

    public String getTipoCorrete() {
        return tipoCorrete;
    }

    public void setTipoCorrete(String tipoCorrete) {
        this.tipoCorrete = tipoCorrete;
    }

    public Outcome getDespesa() {
        return despesa;
    }

    public void setDespesa(Outcome despesa) {
        this.despesa = despesa;
    }

    public Income getReceita() {
        return receita;
    }

    public void setReceita(Income receita) {
        this.receita = receita;
    }

    public List<IncomeType> getSubtiposIncome() {
        return subtiposIncome;
    }

    public void setSubtiposIncome(List<IncomeType> subtiposIncome) {
        this.subtiposIncome = subtiposIncome;
    }

    public List<OutcomeType> getSubtiposOutcome() {
        return subtiposOutcome;
    }

    public void setSubtiposOutcome(List<OutcomeType> subtiposOutcome) {
        this.subtiposOutcome = subtiposOutcome;
    }


    public String getINCOME() {
        return INCOME;
    }

    public String getOUTCOME() {
        return OUTCOME;
    }

    public IncomeType getSubtipoIncomeCorrete() {
        return subtipoIncomeCorrete;
    }

    public void setSubtipoIncomeCorrete(IncomeType subtipoIncomeCorrete) {
        this.subtipoIncomeCorrete = subtipoIncomeCorrete;
    }

    public OutcomeType getSubtipoOutcomeCorrete() {
        return subtipoOutcomeCorrete;
    }

    public void setSubtipoOutcomeCorrete(OutcomeType subtipoOutcomeCorrete) {
        this.subtipoOutcomeCorrete = subtipoOutcomeCorrete;
    }




    public void incluir() {

        Date d = finance.getDate();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if (receita != null) {
            receita.setType(subtipoIncomeCorrete);
            if (finance.getIncome() == null) {
                finance.setIncome(new ArrayList<Income>() {{add(receita);}});
            } else {
                finance.getIncome().add(receita);
            }
        }
        if (despesa != null) {
            despesa.setType(subtipoOutcomeCorrete);
            if (finance.getOutcome() == null) {
                finance.setOutcome(new ArrayList<Outcome>() {{add(despesa);}});
            } else {
                finance.getOutcome().add(despesa);
            }
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        FinantialMonth fm = FinantialMonth.findById(c.getTime());
        finance.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        List<Finance> monthFinances = fm.getMonthFinances();
        if(monthFinances == null){
            fm.setMonthFinances(new ArrayList<Finance>() {{add(finance);}});
        }
        else{
              fm.getMonthFinances().add(finance);
         }
        monthService.update(fm);

    }

}
