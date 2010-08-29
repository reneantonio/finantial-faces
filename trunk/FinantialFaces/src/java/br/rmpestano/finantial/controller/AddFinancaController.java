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
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rmpestano
 */
@ViewScoped
@ManagedBean(name="addBean")
public class AddFinancaController implements Serializable{

    private TabService tabService;
    private final String INCOME = "income";
    private final String OUTCOME = "outcome";
    private List<String> tiposFinanca = new ArrayList<String>(){{add(INCOME);add(OUTCOME);}};
    private List<OutcomeType> subtiposOutcome;
    private List<IncomeType> subtiposIncome;
    private Outcome despesa;
    private Income  receita;
    private String tipoCorrete = OUTCOME;
    private IncomeType subtipoIncomeCorrete;
    private OutcomeType subtipoOutcomeCorrete;
    private Date date;
    private Double incomeValue;
    private String incomeDescription;
    private Double outcomeValue;
    private String outcomeDescription;

    public AddFinancaController() {
        subtiposIncome = IncomeType.findAll();
        subtiposOutcome = OutcomeType.findAll();
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");
        receita = new Income();
        despesa = new Outcome();
    }

    public List<String> getTiposFinanca() {
        return tiposFinanca;
    }

    public void editDespesa(Outcome despesa){
        this.receita = null;
        this.despesa = despesa;
    }
    public void editReceita(Income receita){
        this.despesa=null;
        this.receita = receita;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIncomeDescription() {
        return incomeDescription;
    }

    public void setIncomeDescription(String incomeDescription) {
        this.incomeDescription = incomeDescription;
    }

    public Double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(Double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public String getOutcomeDescription() {
        return outcomeDescription;
    }

    public void setOutcomeDescription(String outcomeDescription) {
        this.outcomeDescription = outcomeDescription;
    }

    public Double getOutcomeValue() {
        return outcomeValue;
    }

    public void setOutcomeValue(Double outcomeValue) {
        this.outcomeValue = outcomeValue;
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




    public String incluir() {
        try{
        Calendar c = new GregorianCalendar();

        c.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

        c.setTime(date);

        c.set(Calendar.DAY_OF_MONTH, 1);
        FinantialMonth fm = FinantialMonth.findById(c.getTime());
        if (tipoCorrete.equals(INCOME)) {
            receita = new Income();
            receita.setDate(date);
            receita.setDescription(incomeDescription);
            receita.setValue(incomeValue);
            receita.setType(subtipoIncomeCorrete);
            fm.getMonthIncomes().add(receita);
            receita.setFinantialMonth(fm);
            receita.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        }
        if (tipoCorrete.equals(OUTCOME)) {
            despesa = new Outcome();
            despesa.setDate(date);
            despesa.setValue(outcomeValue);
            despesa.setDescription(outcomeDescription);
            c.setTime(date);
            despesa.setType(subtipoOutcomeCorrete);
            fm.getMonthOutcomes().add(despesa);
            despesa.setFinantialMonth(fm);
            despesa.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        }
        tabService.update(fm);
        tabService.setYearTabIndex(tabService.findYearIndex(fm.getFinantialYear().getTitle()));
        if(c != null){
             tabService.setMonthTabIndex(c.get(Calendar.MONTH));
        }
        MessagesController.addInfo("Finança incluida com sucesso!");
        return "/pages/home.faces?faces-redirect=true";
        }catch(Exception ex){
            MessagesController.addError("Erro ao incluir finança",ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
