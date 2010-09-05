/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.FinanceService;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author rmpestano
 */
@ViewScoped
@ManagedBean(name="financeBean")
public class FinancaController implements Serializable{

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
    private Double financeValue;
    private String financeDescription;
    private FinanceService financeService;


    public FinancaController() {
        subtiposIncome = IncomeType.findAll();
        subtiposOutcome = OutcomeType.findAll();
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");
        financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
        receita = new Income();
        despesa = new Outcome();
    }

    public List<String> getTiposFinanca() {
        return tiposFinanca;
    }

    public void editDespesa(Outcome despesa){
        this.tipoCorrete = OUTCOME;
        this.receita = null;
        this.despesa = despesa;
    }
    public void editReceita(Income receita){
        this.tipoCorrete = INCOME;
        this.despesa=null;
        this.receita = receita;
    }
    public void setTiposFinanca(List<String> tiposFinanca) {
        this.tiposFinanca = tiposFinanca;
    }

    public String getTipoCorrete() {
        return tipoCorrete;
    }

     public void onSelectOutcome(SelectEvent event) {
            tipoCorrete = OUTCOME;
            this.receita = null;
     }
     public void onSelectIncome(SelectEvent event) {
            tipoCorrete = INCOME;
            this.despesa = null;
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

    public String getFinanceDescription() {
        return financeDescription;
    }

    public void setFinanceDescription(String financeDescription) {
        this.financeDescription = financeDescription;
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

    public Double getFinanceValue() {
        return financeValue;
    }

    public void setFinanceValue(Double financeValue) {
        this.financeValue = financeValue;
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


    public void incluir(ActionEvent ev) {
        try {

            FinantialMonth fm = FinantialMonth.findByDate(date);
            if (tipoCorrete.equals(INCOME)) {
                receita = new Income();
                receita.setDate(date);
                receita.setDescription(financeDescription);
                receita.setValue(financeValue);
                receita.setType(subtipoIncomeCorrete);
                fm.getMonthIncomes().add(receita);
                receita.setFinantialMonth(fm);
                receita.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
            }
            if (tipoCorrete.equals(OUTCOME)) {
                despesa = new Outcome();
                despesa.setDate(date);
                despesa.setValue(financeValue);
                despesa.setDescription(financeDescription);
                despesa.setType(subtipoOutcomeCorrete);
                fm.getMonthOutcomes().add(despesa);
                despesa.setFinantialMonth(fm);
                despesa.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
            }
            tabService.update(fm);
            tabService.setYearTabIndex(tabService.findYearIndex(fm.getFinantialYear().getTitle()));
            MessagesController.addInfo("Finança incluida com sucesso!");
            this.setCurrentTab(date);
            Map<String,Object> map =  FacesContext.getCurrentInstance().getViewRoot().getViewMap();
            TabController tabController = (TabController) map.get("tabBean");
            tabController.setTabYears(tabService.getYearsToView());
        } catch (Exception ex) {
            MessagesController.addError("Erro ao incluir finança", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void removeOutcome(){
         financeService.removeOutcome(despesa);

    }
    public void updateOutcome(ActionEvent ev){
        Map<String,Object> map =  FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        TabController tabController = (TabController) map.get("tabBean");
        Calendar c = new GregorianCalendar();
        c.setTime(despesa.getDate());
        c.set(Calendar.DAY_OF_MONTH, 1);
        if(! c.getTime().equals(despesa.getFinantialMonth().getDate())){
            FinantialMonth fm = FinantialMonth.findByDate(despesa.getDate());
            fm.getMonthOutcomes().add(despesa);
            despesa.setFinantialMonth(fm);
            financeService.updateMonth(fm);
            tabController.setTabYears(tabService.getYearsToView());
        }
         else{
          financeService.updateOutcome(despesa);
         }
         MessagesController.addInfo("Despesa modificada com sucesso");
         setCurrentTab(despesa.getDate());
    }

    private void setCurrentTab(Date date) {
        if(date == null){
            date = new Date();
        }
        FinantialMonth fm = FinantialMonth.findByDate(date);
        if(fm != null){
            Calendar c = new GregorianCalendar();
            c.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
            c.setTime(date);
            c.set(Calendar.DAY_OF_MONTH, 1);
            tabService.setMonthTabIndex(c.get(Calendar.MONTH));
            tabService.setYearTabIndex(tabService.findYearIndex(fm.getFinantialYear().getTitle()));
        }
    }
}
