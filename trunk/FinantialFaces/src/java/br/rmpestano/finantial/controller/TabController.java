/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name="tabBean")
@ViewScoped
public class TabController {
    TabService tabService;
    List<FinantialYear> tabYears;
    private String lastCalendarDate;
    private String firstCalendarDate;
    private Double totalOutcomeInThemonth;



    @PostConstruct
    public void initMonthsAndYears() {
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");//@ManagedProperty
        System.out.println("Injetou tab service:" + tabService);
//        tabService.init();
        tabYears = tabService.getYearsToView();//dependera do numero de anos que o user quer ver
        lastCalendarDate = tabService.findlastYear();
        firstCalendarDate = tabService.findfirstYear();
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




   
}
