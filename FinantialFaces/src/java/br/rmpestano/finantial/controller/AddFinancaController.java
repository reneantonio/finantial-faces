/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rmpestano
 */
@ViewScoped
@ManagedBean(name="addBean")
public class AddFinancaController {

    private TabService tabService;
    private final String INCOME = "income";
    private final String OUTCOME = "outcome";
    private List<String> tiposFinanca = new ArrayList<String>(){{add(INCOME);add(OUTCOME);}};
    private List<OutcomeType> subtiposOutcome;
    private List<IncomeType> subtiposIncome;
    private Outcome despesa = new Outcome();
    private Income  receita = new Income();
    private String tipoCorrete = OUTCOME;
    private IncomeType subtipoIncomeCorrete;
    private OutcomeType subtipoOutcomeCorrete;

    public AddFinancaController() {
        subtiposIncome = IncomeType.findAll();
        subtiposOutcome = OutcomeType.findAll();
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");
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

        Calendar c = new GregorianCalendar();
        if (receita != null) {
            Date d = receita.getDate();
            c.setTime(d);
            receita.setType(subtipoIncomeCorrete);
            receita.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        }
        if (despesa != null) {
            Date d = despesa.getDate();
            c.setTime(d);
            despesa.setType(subtipoOutcomeCorrete);
            despesa.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        FinantialMonth fm = FinantialMonth.findById(c.getTime());
        List<Income> monthIncomes = fm.getMonthIncomes();
        if(monthIncomes == null){
            fm.setMonthIncomes(new ArrayList<Income>() {{add(receita);}});
        }
        else{
          fm.getMonthIncomes().add(receita);
         }
        List<Outcome> monthOutcomes = fm.getMonthOutcomes();
        if(monthOutcomes == null){
            fm.setMonthOutcomes(new ArrayList<Outcome>() {{add(despesa);}});
        }
        else{
          fm.getMonthOutcomes().add(despesa);
         }
        tabService.update(fm);
    }

}
