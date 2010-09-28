/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.Outcome;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.report.Range;
import br.rmpestano.finantial.model.report.Report;
import br.rmpestano.finantial.model.report.TipoValorReport;
import br.rmpestano.finantial.model.report.ValorIntervaloReport;
import br.rmpestano.finantial.service.FinanceService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name="reportBean")
@SessionScoped
public class ReportController {
    private boolean isReportOneEnable;
    private boolean isReportTwoEnable;
    private List<Report> reports;
    private FinanceService financeService;
    private FinantialMonth currentMonth;
    private List<Outcome> despesas;
    private DualListModel<OutcomeType> outcomeTypesPickList;
    private DualListModel<Range> intervalosPickList;
    private Integer currentReportNumber;
    private Report currentMonthReport;
    private List<TipoValorReport> tipoValorReport;
    private List<ValorIntervaloReport> valorIntervaloReport;


    private final int REPORT_NUMBER_ONE = 1;
    private final int REPORT_NUMBER_TWO = 2;

    public ReportController() {
        financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
    }


    @PostConstruct
    public void initReports(){
        reports = new ArrayList<Report>();
        reports.add(new Report("Relatório de Despesas por Tipo", "Neste relatório é possivel visualizar os gastos mensais em função do tipo de gasto",1));
        reports.add(new Report("Relatório de Despesas por Intervalo", "Neste relatório é possivel visualizar os gastos mensais em função do seu valor",2));
        initPickLists();
    }

    private void initPickLists(){
         List<OutcomeType> sourceOutcomeType =  OutcomeType.findAll();
         List<OutcomeType> targetOutcomeType =  new ArrayList<OutcomeType>();
         outcomeTypesPickList = new DualListModel<OutcomeType>(sourceOutcomeType, targetOutcomeType);
          List<Range> sourceInterval = new ArrayList<Range>();
        List<Range> targetInterval = new ArrayList<Range>();
        sourceInterval.add(new Range(0, 10));
        sourceInterval.add(new Range(11, 20));
        sourceInterval.add(new Range(21, 30));
        sourceInterval.add(new Range(31, 40));
        sourceInterval.add(new Range(41, 50));
        sourceInterval.add(new Range(51, 75));
        sourceInterval.add(new Range(76, 100));
        sourceInterval.add(new Range(101, 150));
        sourceInterval.add(new Range(151, 250));
        sourceInterval.add(new Range(251, 500));
        sourceInterval.add(new Range(501, 1000));
        sourceInterval.add(new Range(1001, 2000));

        intervalosPickList = new DualListModel<Range>(sourceInterval, targetInterval);

    }
    public DualListModel<Range> getIntervalos() {
        return intervalosPickList;
    }

    public void setIntervalos(DualListModel<Range> intervalos) {
        this.intervalosPickList = intervalos;
    }


    public boolean isIsReportOneEnable() {
        return isReportOneEnable;
    }

    public void setIsReportOneEnable(boolean isReportOneEnable) {
        this.isReportOneEnable = isReportOneEnable;
    }

    public int getREPORT_NUMBER_TWO() {
        return REPORT_NUMBER_TWO;
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


    public List<ValorIntervaloReport> getValorIntervaloReport() {
        return valorIntervaloReport;
    }

    public void setValorIntervaloReport(List<ValorIntervaloReport> valorIntervaloReport) {
        this.valorIntervaloReport = valorIntervaloReport;
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

    public boolean isIsReportTwoEnable() {
        return isReportTwoEnable;
    }

    public void setIsReportTwoEnable(boolean isReportTwoEnable) {
        this.isReportTwoEnable = isReportTwoEnable;
    }


    public void setCurrentMonthReport(Report currentMonthReport) {
        this.currentMonthReport = currentMonthReport;
    }

    public DualListModel<Range> getIntervalosPickList() {
        return intervalosPickList;
    }

    public void setIntervalosPickList(DualListModel<Range> intervalosPickList) {
        this.intervalosPickList = intervalosPickList;
    }

    public DualListModel<OutcomeType> getOutcomeTypesPickList() {
        return outcomeTypesPickList;
    }

    public void setOutcomeTypesPickList(DualListModel<OutcomeType> outcomeTypesPickList) {
        this.outcomeTypesPickList = outcomeTypesPickList;
    }


    public void prepareMonthReport(FinantialMonth fm){
        currentMonthReport = reports.get(currentReportNumber-1);
        currentMonth = fm;
        initPickLists();
        switch(currentReportNumber){
            case 1:{
                generateReport1();
                break;
            }
            case 2:{
                generateReport2();
                break;
            }
        }

       
    }

        public void clearReport(CloseEvent event){
            currentReportNumber = -1;
            isReportOneEnable = false;
            isReportTwoEnable = false;
        }

    private void generateReport1() {
         isReportOneEnable = true;
         isReportTwoEnable = false;
         tipoValorReport = new ArrayList<TipoValorReport>();
         despesas = currentMonth.getCurrentUserOutcomesInTheMonth();
         int currentIndex = 0;
         List<OutcomeType> reportOutcomeTypes = outcomeTypesPickList.getSource();//na primeira vez usa todos do source, depois vai atualizando via ajax
         for (OutcomeType outcomeType : reportOutcomeTypes) {
             tipoValorReport.add(new TipoValorReport(outcomeType.getDescription(), 0.0));
             Double sum = 0.0;
             for (Outcome outcome : financeService.findUserOutcomeByDateAndType(outcomeType.getId(), currentMonth.getDate())) {
                sum += outcome.getValue();
            }
            tipoValorReport.get(currentIndex).setValor(sum);
            currentIndex++;
        }
    }
    private void generateReport2() {
         isReportTwoEnable = true;
         isReportOneEnable = false;
         valorIntervaloReport = new ArrayList<ValorIntervaloReport>();
         intervalosPickList.setTarget(intervalosPickList.getSource());
         intervalosPickList.setSource(new ArrayList<Range>());
         List<Range> reportRanges = intervalosPickList.getTarget();
         for (Range range : reportRanges) {
            valorIntervaloReport.add(new ValorIntervaloReport(range));
        }
         Range lastRange = new Range(valorIntervaloReport.get(valorIntervaloReport.size()-1).getIntervalo().getSecondValue(), Integer.MAX_VALUE);
         lastRange.setLastRange(true);
         valorIntervaloReport.add(new ValorIntervaloReport(lastRange));
         despesas = currentMonth.getCurrentUserOutcomesInTheMonth();
         for (Outcome outcome : despesas) {
             int index = 0;
             boolean rangeMatched = false;
             for (Range range : reportRanges) {
                  if((outcome.getValue() >= range.getFirstValue()) && (outcome.getValue() <= range.getSecondValue()) ){
                   int num = valorIntervaloReport.get(index).getNumFinancas();
                    valorIntervaloReport.get(index).setNumFinancas(++num);
                    rangeMatched = true;
                  }

                  index++;
             }
             if(! rangeMatched){
                 int lastIndex = valorIntervaloReport.size()-1;
                 valorIntervaloReport.get(lastIndex).setNumFinancas(valorIntervaloReport.get(lastIndex).getNumFinancas()+1);
             }
        }
    }

    public void reportTwoReloaded(){
         valorIntervaloReport = new ArrayList<ValorIntervaloReport>();
         List<Range> reportRanges = intervalosPickList.getTarget();
         for (Range range : reportRanges) {
            valorIntervaloReport.add(new ValorIntervaloReport(range));
        }
         if(reportRanges.size()>0){
         Range lastRange = new Range(valorIntervaloReport.get(valorIntervaloReport.size()-1).getIntervalo().getSecondValue(), Integer.MAX_VALUE);
         lastRange.setLastRange(true);
         valorIntervaloReport.add(new ValorIntervaloReport(lastRange));
         despesas = currentMonth.getCurrentUserOutcomesInTheMonth();
         for (Outcome outcome : despesas) {
             boolean rangeMatched = false;
             int index = 0;
             for (Range range : reportRanges) {
                  if((outcome.getValue() >= range.getFirstValue()) && (outcome.getValue() <= range.getSecondValue()) ){
                   int num = valorIntervaloReport.get(index).getNumFinancas();
                    valorIntervaloReport.get(index).setNumFinancas(++num);
                    rangeMatched = true;
                  }

                  index++;
             }
                 if(! rangeMatched){
                     int lastIndex = valorIntervaloReport.size()-1;
                     valorIntervaloReport.get(lastIndex).setNumFinancas(valorIntervaloReport.get(lastIndex).getNumFinancas()+1);
                 }
             }
        }
    }

    public List<TipoValorReport> getTipoValorReport() {
        return tipoValorReport;
    }

    public void setTipoValorReport(List<TipoValorReport> tipoValorReport) {
        this.tipoValorReport = tipoValorReport;
    }



}
