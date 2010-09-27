/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.Report;
import br.rmpestano.finantial.service.FinanceService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name="reportBean")
@SessionScoped
public class ReportController {
    private boolean isReportOneEnable;
    private List<Report> reports;
    private FinanceService financeService;
    private List<Outcome> despesas;
    private Integer currentReportNumber;
    private Report currentMonthReport;

    private final int REPORT_NUMBER_ONE = 1;

    public ReportController() {
        financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
    }


    @PostConstruct
    public void initReports(){
        reports = new ArrayList<Report>();
        reports.add(new Report("Relatório de Despesas", "Neste relatório é possivel visualizar os gastos mensais em função do tipo de gasto",1));
    }

    public boolean isIsReportOneEnable() {
        return isReportOneEnable;
    }

    public void setIsReportOneEnable(boolean isReportOneEnable) {
        this.isReportOneEnable = isReportOneEnable;
    }

    public List<Outcome> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Outcome> despesas) {
        this.despesas = despesas;
    }

    public int getREPORT_NUMBER_ONE() {
        return REPORT_NUMBER_ONE;
    }


    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Integer getCurrentReportNumber() {
        return currentReportNumber;
    }

    public void setCurrentReportNumber(Integer currentReportNumber) {
        this.currentReportNumber = currentReportNumber;
    }

    public Report getCurrentMonthReport() {
        return currentMonthReport;
    }

    public void setCurrentMonthReport(Report currentMonthReport) {
        this.currentMonthReport = currentMonthReport;
    }



    public void prepareMonthReport(FinantialMonth fm){
        currentMonthReport = reports.get(currentReportNumber-1);
        despesas = fm.getCurrentUserOutcomesInTheMonth();
        isReportOneEnable = true;
    }

        public void clearReport(CloseEvent event){
            currentReportNumber = -1;
            isReportOneEnable = false;
        }

}
