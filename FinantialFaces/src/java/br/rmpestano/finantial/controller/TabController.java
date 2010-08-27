/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.PersistenceManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name="tabBean")
@ViewScoped
public class TabController {
    TabService tabService;
    List<FinantialYear> tabYears;
    private FinantialYear year2010;
    private FinantialYear year2011;



    @PostConstruct
    public void initMonthsAndYears() {
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");
        System.out.println("Injetou tab service:" + tabService);
//        tabService.init();
        tabYears = tabService.findAll();//dependera do numero de anos que o uswr quer ver
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

    public FinantialYear getYear2010() {
        return tabYears.get(0);
    }

    public void setYear2010(FinantialYear year2010) {
        this.year2010 = year2010;
    }

    public FinantialYear getYear2011() {
        return tabYears.get(1);
    }

    public void setYear2011(FinantialYear year2011) {
        this.year2011 = year2011;
    }


   
}
