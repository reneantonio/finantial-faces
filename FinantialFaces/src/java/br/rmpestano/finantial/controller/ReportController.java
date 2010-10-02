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
import br.rmpestano.finantial.model.report.Range;
import br.rmpestano.finantial.model.report.ReceitaPorDespesaReport;
import br.rmpestano.finantial.model.report.Report;
import br.rmpestano.finantial.model.report.TipoValorReport;
import br.rmpestano.finantial.model.report.ValorIntervaloReport;
import br.rmpestano.finantial.service.FinanceService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.util.ArrayList;
import java.util.Collections;
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
@ManagedBean(name = "reportBean")
@SessionScoped
public class ReportController {

    private boolean isReportOneEnable;
    private boolean isReportTwoEnable;
    private List<Report> reports;
    private FinanceService financeService;
    private FinantialMonth currentMonth;
    private List<Outcome> despesas;
    private List<Income> receitas;
    private DualListModel<OutcomeType> outcomeTypesPickList;
    private DualListModel<IncomeType> incomeTypesPickList;
    private DualListModel<Range> intervalosPickList;
    private Integer currentReportNumber;
    private Report currentMonthReport;
    private List<TipoValorReport> tipoValorReport;
    private List<ValorIntervaloReport> valorIntervaloReport;
    private List<ReceitaPorDespesaReport> receitaXDespesaReport;
    private final String INCOME = "income";
    private final String OUTCOME = "outcome";
    private String tipoCorrente = OUTCOME;
    private List<String> tiposFinanca = new ArrayList<String>() {

        {
            add(INCOME);
            add(OUTCOME);
        }
    };
    private final int REPORT_NUMBER_ONE = 1;
    private final int REPORT_NUMBER_TWO = 2;
    private final int REPORT_NUMBER_THREE = 3;

    public ReportController() {
        financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
    }


    @PostConstruct
    public void initReports() {
        reports = new ArrayList<Report>();
        reports.add(new Report("Relatório de Finanças por Tipo", "Neste relatório é possivel visualizar finanças em função do tipo de gasto ao longo do <span style='text-decoration: underline;font-weight:bold'>MÊS</span>", 1));
        reports.add(new Report("Relatório de Finanças por Intervalo", "Neste relatório é possivel visualizar finanças em função do seu valor ao longo do <span style='text-decoration: underline;font-weight:bold'>MÊS</span>", 2));
        reports.add(new Report("Relatório de saldos", "Neste relatório é possivel visualizar o comportamento das receitas e despesas ao longo do <span style='text-decoration: underline;font-weight:bold'>ANO</span>", 3));
        initPickLists();
    }

    public String getTipoCorrente() {
        return tipoCorrente;
    }

    public void setTipoCorrente(String tipoCorrent) {
        this.tipoCorrente = tipoCorrent;
    }

    public List<String> getTiposFinanca() {
        return tiposFinanca;
    }

    public void setTiposFinanca(List<String> tiposFinanca) {
        this.tiposFinanca = tiposFinanca;
    }

    public int getREPORT_NUMBER_THREE() {
        return REPORT_NUMBER_THREE;
    }

    public List<ReceitaPorDespesaReport> getReceitaXDespesaReport() {
        return receitaXDespesaReport;
    }

    public void setReceitaXDespesaReport(List<ReceitaPorDespesaReport> receitaXDespesaReport) {
        this.receitaXDespesaReport = receitaXDespesaReport;
    }


    private void initPickLists() {
        List<OutcomeType> sourceOutcomeType = new ArrayList<OutcomeType>();
        List<OutcomeType> targetOutcomeType = OutcomeType.findAll();
        outcomeTypesPickList = new DualListModel<OutcomeType>(sourceOutcomeType, targetOutcomeType);
        List<IncomeType> sourceIncomeType = new ArrayList<IncomeType>();
        List<IncomeType> targetIncomeType = IncomeType.findAll();
        incomeTypesPickList = new DualListModel<IncomeType>(sourceIncomeType, targetIncomeType);
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

    public String getINCOME() {
        return INCOME;
    }

    public String getOUTCOME() {
        return OUTCOME;
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

    public DualListModel<IncomeType> getIncomeTypesPickList() {
        return incomeTypesPickList;
    }

    public void setIncomeTypesPickList(DualListModel<IncomeType> incomeTypesPickList) {
        this.incomeTypesPickList = incomeTypesPickList;
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

    public void prepareMonthReport(FinantialMonth fm) {
        currentMonthReport = reports.get(currentReportNumber - 1);
        currentMonth = fm;
        initPickLists();
        despesas = currentMonth.getCurrentUserOutcomesInTheMonth();
        receitas = currentMonth.getCurrentUserIncomesInTheMonth();
        switch (currentReportNumber) {
            case 1: {
                generateReport1();
                break;
            }
            case 2: {
                generateReport2();
                break;
            }
            case 3: {
                generateReport3();
                break;
            }
        }


    }

    public void clearReport(CloseEvent event) {
        currentReportNumber = -1;
        isReportOneEnable = false;
        isReportTwoEnable = false;
    }

    public void generateReport1() {
        isReportOneEnable = true;
        isReportTwoEnable = false;
        tipoValorReport = new ArrayList<TipoValorReport>();
        int currentIndex = 0;
        if (tipoCorrente.equals(OUTCOME)) {
            List<OutcomeType> reportOutcomeTypes = outcomeTypesPickList.getTarget();//na primeira vez usa todos do source, depois vai atualizando via ajax
            for (OutcomeType outcomeType : reportOutcomeTypes) {
                tipoValorReport.add(new TipoValorReport(outcomeType.getDescription(), 0.0));
                Double sum = 0.0;
                for (Outcome outcome : financeService.findUserOutcomeByDateAndType(outcomeType.getId(), currentMonth.getDate())) {
                    sum += outcome.getValue();
                }
                tipoValorReport.get(currentIndex).setValor(sum);
                currentIndex++;
            }
        } else {
            List<IncomeType> reportIncomeTypes = incomeTypesPickList.getTarget();//na primeira vez usa todos do source, depois vai atualizando via ajax
            for (IncomeType incomeType : reportIncomeTypes) {
                tipoValorReport.add(new TipoValorReport(incomeType.getDescription(), 0.0));
                Double sum = 0.0;
                for (Income income : financeService.findUserIncomeByDateAndType(incomeType.getId(), currentMonth.getDate())) {
                    sum += income.getValue();
                }
                tipoValorReport.get(currentIndex).setValor(sum);
                currentIndex++;
            }
        }


    }

    public void generateReport2() {
        isReportTwoEnable = true;
        isReportOneEnable = false;
        valorIntervaloReport = new ArrayList<ValorIntervaloReport>();
        intervalosPickList.setTarget(intervalosPickList.getSource());
        intervalosPickList.setSource(new ArrayList<Range>());
        List<Range> reportRanges = intervalosPickList.getTarget();
        for (Range range : reportRanges) {
            valorIntervaloReport.add(new ValorIntervaloReport(range));
        }
        Range lastRange = new Range(valorIntervaloReport.get(valorIntervaloReport.size() - 1).getIntervalo().getSecondValue(), Integer.MAX_VALUE);
        lastRange.setLastRange(true);
        valorIntervaloReport.add(new ValorIntervaloReport(lastRange));
        if (tipoCorrente.equals(OUTCOME)) {//acabar com esses testes e fazer Income e Outcome extender de Finança ou implementar uma interface em comum
            despesas = currentMonth.getCurrentUserOutcomesInTheMonth();
            for (Outcome outcome : despesas) {
                int index = 0;
                boolean rangeMatched = false;
                for (Range range : reportRanges) {
                    if ((outcome.getValue() >= range.getFirstValue()) && (outcome.getValue() <= range.getSecondValue())) {
                        int num = valorIntervaloReport.get(index).getNumFinancas();
                        valorIntervaloReport.get(index).setNumFinancas(++num);
                        rangeMatched = true;
                    }

                    index++;
                }
                if (!rangeMatched) {
                    int lastIndex = valorIntervaloReport.size() - 1;
                    valorIntervaloReport.get(lastIndex).setNumFinancas(valorIntervaloReport.get(lastIndex).getNumFinancas() + 1);
                }
            }
        } else {
            receitas = currentMonth.getCurrentUserIncomesInTheMonth();
            for (Income income : receitas) {
                int index = 0;
                boolean rangeMatched = false;
                for (Range range : reportRanges) {
                    if ((income.getValue() >= range.getFirstValue()) && (income.getValue() <= range.getSecondValue())) {
                        int num = valorIntervaloReport.get(index).getNumFinancas();
                        valorIntervaloReport.get(index).setNumFinancas(++num);
                        rangeMatched = true;
                    }

                    index++;
                }
                if (!rangeMatched) {
                    int lastIndex = valorIntervaloReport.size() - 1;
                    valorIntervaloReport.get(lastIndex).setNumFinancas(valorIntervaloReport.get(lastIndex).getNumFinancas() + 1);
                }
            }
        }

    }

    public void reportTwoReloaded() {
        valorIntervaloReport = new ArrayList<ValorIntervaloReport>();
        Collections.sort(intervalosPickList.getTarget());
        List<Range> reportRanges = intervalosPickList.getTarget();
        for (Range range : reportRanges) {
            valorIntervaloReport.add(new ValorIntervaloReport(range));
        }
        if (reportRanges.size() > 0) {
            Range firstRange = null;
            if (valorIntervaloReport.get(0).getIntervalo().getFirstValue() != 0) {
                firstRange = new Range(0, valorIntervaloReport.get(0).getIntervalo().getFirstValue() - 1);
                valorIntervaloReport.add(new ValorIntervaloReport(firstRange));
            }
            Collections.sort(valorIntervaloReport);
            Range lastRange = new Range(valorIntervaloReport.get(valorIntervaloReport.size() - 1).getIntervalo().getSecondValue(), Integer.MAX_VALUE);
            lastRange.setLastRange(true);
            valorIntervaloReport.add(new ValorIntervaloReport(lastRange));
            if (tipoCorrente.equals(OUTCOME)) {//acabar com esses testes e fazer Income e Outcome extender de Finança ou implementar uma interface em comum
                despesas = currentMonth.getCurrentUserOutcomesInTheMonth();
                for (Outcome outcome : despesas) {
                    boolean rangeMatched = false;
                    int index = firstRange == null ? 0 : 1;
                    for (Range range : reportRanges) {
                        if ((outcome.getValue() >= range.getFirstValue()) && (outcome.getValue() <= range.getSecondValue())) {
                            int num = valorIntervaloReport.get(index).getNumFinancas();
                            valorIntervaloReport.get(index).setNumFinancas(++num);
                            rangeMatched = true;
                        }

                        index++;
                    }
                    if (!rangeMatched) {

                        if (firstRange != null && (outcome.getValue() <= firstRange.getSecondValue())) {
                            valorIntervaloReport.get(0).setNumFinancas(valorIntervaloReport.get(0).getNumFinancas() + 1);
                        } else if (outcome.getValue() >= lastRange.getFirstValue() && outcome.getValue() <= lastRange.getSecondValue()){
                            int lastIndex = valorIntervaloReport.size() - 1;
                            valorIntervaloReport.get(lastIndex).setNumFinancas(valorIntervaloReport.get(lastIndex).getNumFinancas() + 1);

                        }
                    }
                }
            } else {
                receitas = currentMonth.getCurrentUserIncomesInTheMonth();
                for (Income income : receitas) {
                    boolean rangeMatched = false;
                    int index = firstRange == null ? 0 : 1;
                    for (Range range : reportRanges) {
                        if ((income.getValue() >= range.getFirstValue()) && (income.getValue() <= range.getSecondValue())) {
                            int num = valorIntervaloReport.get(index).getNumFinancas();
                            valorIntervaloReport.get(index).setNumFinancas(++num);
                            rangeMatched = true;
                        }

                        index++;
                    }
                    if (!rangeMatched) {

                        if (firstRange != null && (income.getValue() <= firstRange.getSecondValue())) {
                            valorIntervaloReport.get(0).setNumFinancas(valorIntervaloReport.get(0).getNumFinancas() + 1);
                        } else if (income.getValue() >= lastRange.getFirstValue() && income.getValue() <= lastRange.getSecondValue()){
                            int lastIndex = valorIntervaloReport.size() - 1;
                            valorIntervaloReport.get(lastIndex).setNumFinancas(valorIntervaloReport.get(lastIndex).getNumFinancas() + 1);
                        }
                    }
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

    public void generateReport3() {
        FinantialYear fy = currentMonth.getFinantialYear();
        receitaXDespesaReport = new ArrayList<ReceitaPorDespesaReport>();
        for (FinantialMonth finantialMonth : fy.getFinantialMonths()) {
            receitaXDespesaReport.add(new ReceitaPorDespesaReport(finantialMonth.getCurrentUserIncomesInTheMonth(), finantialMonth.getCurrentUserOutcomesInTheMonth(), finantialMonth.getMonthAbreviation()));
        }
    }

    public FinantialMonth getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(FinantialMonth currentMonth) {
        this.currentMonth = currentMonth;
    }


}
