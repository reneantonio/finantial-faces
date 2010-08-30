/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.service.FinanceService;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name="tabBean")
@ViewScoped
public class TabController implements Serializable{
    TabService tabService;
    List<FinantialYear> tabYears;
    @Inject
    FinanceService financeService;
    private String lastCalendarDate;
    private String firstCalendarDate;
    private Double totalOutcomeInThemonth;
    private List<Income> selectedIncomes = new ArrayList<Income>();
    private List<Outcome> selectedOutcomes = new ArrayList<Outcome>();

    public TabController() {
    }

    @PostConstruct
    public void initMonthsAndYears() {
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");//@ManagedProperty
        //financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
//        tabService.init();
        tabYears = tabService.getYearsToView();//dependera do numero de anos que o user quer ver
        lastCalendarDate = tabService.findlastYear();
        firstCalendarDate = tabService.findfirstYear();
    }

    public void removeOutcome(){
        for (Outcome outcome : selectedOutcomes) {
            try {
                financeService.removeOutcome(outcome);
            } catch (Exception ex) {
                ex.printStackTrace();
                MessagesController.addError("Problema ao deletar", ex.getMessage());
            }
        }
    }
    public List<FinantialYear> getAbas() {
        return tabYears;
    }

    public void setAbas(List<FinantialYear> abas) {
        this.tabYears = abas;
    }


    public List<FinantialYear> getTabYears() {
        return tabYears;
    }

    public void setTabYears(List<FinantialYear> tabYears) {
        this.tabYears = tabYears;
    }

    public String getFirstCalendarDate() {
        return firstCalendarDate;
    }

    public void setFirstCalendarDate(String firstCalendarDate) {
        this.firstCalendarDate = firstCalendarDate;
    }

    public String getLastCalendarDate() {
        return lastCalendarDate;
    }

    public void setLastCalendarDate(String lastCalendarDate) {
        this.lastCalendarDate = lastCalendarDate;
    }

    public Double getTotalOutcomeInThemonth() {
        return totalOutcomeInThemonth;
    }

    public void setTotalOutcomeInThemonth(Double totalOutcomeInThemonth) {
        this.totalOutcomeInThemonth = totalOutcomeInThemonth;
    }

    public List<Income> getSelectedIncomes() {
        return selectedIncomes;
    }

    public void setSelectedIncomes(List<Income> selectedIncomes) {
        this.selectedIncomes = selectedIncomes;
    }

    public List<Outcome> getSelectedOutcomes() {
        return selectedOutcomes;
    }

    public void setSelectedOutcomes(List<Outcome> selectedOutcomes) {
        this.selectedOutcomes = selectedOutcomes;
    }




   
}
