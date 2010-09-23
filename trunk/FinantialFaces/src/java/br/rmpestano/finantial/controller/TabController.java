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
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name="tabBean")
@ViewScoped
public class TabController implements Serializable{
    TabService tabService;
    List<FinantialYear> tabYears;
    private String lastCalendarDate;
    private String firstCalendarDate;
    private Double totalOutcomeInThemonth;
    private DataTable outcomeTable;
    private FinantialYear currentYear;
    
    

    public TabController() {
    }

    @PostConstruct
    public void initMonthsAndYears() {
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");//@ManagedProperty
        //financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
//        tabService.init();
//        tabYears = tabService.getYearsToView();//dependera do numero de anos que o user quer ver
        tabYears = tabService.getAllFinantialYears();
        Collections.sort(tabYears);
        lastCalendarDate = tabService.findlastYear();
        firstCalendarDate = tabService.findfirstYear();
        currentYear = tabYears.get(tabService.getCurrentYearIndex());
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

    public DataTable getOutcomeTable() {
        return outcomeTable;
    }

    public FinantialYear getCurrentYear() {
        return tabYears.get(tabService.getCurrentYearIndex());
    }

    public void setCurrentYear(FinantialYear currentYear) {
        this.currentYear = currentYear;
    }
    
    

    /**
     * clear datatable selection after editing
     */
//    public void clearSelection(){
//        outcomeTable.getSelectedRowIndexes().clear();
//    }
    public void setOutcomeTable(DataTable outcomeTable) {
        this.outcomeTable = outcomeTable;

    }







   
}
