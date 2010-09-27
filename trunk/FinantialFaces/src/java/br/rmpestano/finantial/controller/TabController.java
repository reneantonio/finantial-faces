/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;


import org.primefaces.event.TabChangeEvent;


/**
 * this bean is responsible for the months and their finances in View
 * no sql queries here, use tabService
 * @author rmpestano
 */
@ManagedBean(name="tabBean")
@ViewScoped
public class TabController implements Serializable{
    TabService tabService;
    List<FinantialYear> tabYears;
    private String lastYearDate; //its used in the addFinance calendar minDate and maxdate atributes
    private String firstYearDate;
    private Double totalOutcomeInThemonth;
    private FinantialYear currentYear;
    private FinantialMonth currentMonth;
    private boolean editYear ;
    private int currentMonthIndex;
    private List<String> listOfYears;
    private String currentYearTitle;
    private int currentYearIndex;
    private int financesActiveIndex = -1;
    private int financesLastTabIndex = -1;
    private SelectItem[] outcomeFilterOptions;
    private SelectItem[] incomeFilterOptions;
    private final int ACORDION_NOT_SELECTED_INDEX = -1;
    private final int ACORDION_OUTCOME_INDEX = 0;
    private final int ACORDION_INCOME_INDEX = 1;
    private final int ACORDION_REPORT_INDEX = 2;
    
    

    public TabController() {
    }

    @PostConstruct
    public void initMonthsAndYears() {
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");//colocar esse bean em enterprise.context.viewScoope e dar inject ao inves dessa gambia
        tabYears = tabService.getFinantialYears();
        Collections.sort(tabYears);
        setInitialTabs();
        lastYearDate = tabService.findLastYear();
        firstYearDate = tabService.findFirstYear();
        currentYear = tabYears.get(currentYearIndex);
        outcomeFilterOptions = createOutcomeFilterOptions();
        incomeFilterOptions = createIncomeFilterOptions();
    }

    public int getACORDION_NOT_SELECTED_INDEX() {
        return ACORDION_NOT_SELECTED_INDEX;
    }

    public int getACORDION_INCOME_INDEX() {
        return ACORDION_INCOME_INDEX;
    }

    public int getACORDION_OUTCOME_INDEX() {
        return ACORDION_OUTCOME_INDEX;
    }

    public int getACORDION_REPORT_INDEX() {
        return ACORDION_REPORT_INDEX;
    }


    private SelectItem[] createOutcomeFilterOptions(){
        List<OutcomeType> types = tabService.getOutcomeTypes();
        SelectItem[] retorno = new SelectItem[types.size()+1];
        retorno[0] = new SelectItem("", "Todos");
        for (int i = 0; i < types.size(); i++) {
             retorno[i+1] = new SelectItem(types.get(i).getDescription(), types.get(i).getDescription());
        }
        return retorno;
    }
    private SelectItem[] createIncomeFilterOptions(){
        List<IncomeType> types = tabService.getIncomeTypes();
        SelectItem[] retorno = new SelectItem[types.size()+1];
        retorno[0] = new SelectItem("", "Todos");
        for (int i = 0; i < types.size(); i++) {
             retorno[i+1] = new SelectItem(types.get(i).getDescription(), types.get(i).getDescription());
        }
        return retorno;
    }

    public int getFinancesLastTabIndex() {
        return financesLastTabIndex;
    }

    public void setFinancesLastTabIndex(int financesLastTabIndex) {
        this.financesLastTabIndex = financesLastTabIndex;
    }


    public SelectItem[] getOutcomeFilterOptions() {
        return outcomeFilterOptions;
    }

    public void setOutcomeFilterOptions(SelectItem[] outcomeFilterOptions) {
        this.outcomeFilterOptions = outcomeFilterOptions;
    }

    public SelectItem[] getIncomeFilterOptions() {
        return incomeFilterOptions;
    }

    public void setIncomeFilterOptions(SelectItem[] incomeFilterOptions) {
        this.incomeFilterOptions = incomeFilterOptions;
    }


    public boolean isEditYear() {
        return editYear;
    }

    public void setEditYear(boolean editYear) {
        this.editYear = editYear;
    }

    public int getCurrentYearIndex() {
        return currentYearIndex;
    }

    public int getFinancesActiveIndex() {
        return financesActiveIndex;
    }

    public void setFinancesActiveIndex(int financesActiveIndex) {
        this.financesActiveIndex = financesActiveIndex;
    }


    public void setCurrentYearIndex(int currentYearIndex) {
        currentYear = tabYears.get(currentYearIndex);
        this.currentYearIndex = currentYearIndex;
    }

    public String getCurrentYearTitle() {
        return currentYearTitle;
    }

    public void setCurrentYearTitle(String currentYearTitle) {
        this.currentYearTitle = currentYearTitle;
    }

    public FinantialMonth getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(FinantialMonth currentMonth) {
        this.currentMonth = currentMonth;
    }

    public String getFirstYearDate() {
        return firstYearDate;
    }

    public void setFirstYearDate(String firstYearDate) {
        this.firstYearDate = firstYearDate;
    }

    public String getLastYearDate() {
        return lastYearDate;
    }

    public void setLastYearDate(String lastYearDate) {
        this.lastYearDate = lastYearDate;
    }



    public List<FinantialYear> getTabYears() {
        if(tabYears == null){
            tabYears = tabService.getFinantialYears();
        }

        return tabYears;
    }

    public void setTabYears(List<FinantialYear> tabYears) {
        this.tabYears = tabYears;
    }


    public Double getTotalOutcomeInThemonth() {
        return totalOutcomeInThemonth;
    }

    public void setTotalOutcomeInThemonth(Double totalOutcomeInThemonth) {
        this.totalOutcomeInThemonth = totalOutcomeInThemonth;
    }

    public int getCurrentMonthIndex() {
        return currentMonthIndex;
    }

    public void setCurrentMonthIndex(int currentMonthIndex) {
        this.currentMonthIndex = currentMonthIndex;
    }

     private void setInitialTabs() {
            try {
                FinantialMonth fm = tabService.getCurrentFinantialMonth();
                FinantialYear fy = fm.getFinantialYear();
                currentYearTitle = fy.getTitle();
                currentYearIndex = tabService.findYearIndex(fy.getTitle());
                currentMonthIndex = fm.getMonthIndex();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    public FinantialYear getCurrentYear() {
        return tabYears.get(currentYearIndex);
    }

    public void setCurrentYear(FinantialYear currentYear) {
        this.currentYear = currentYear;
    }
    
    public void showOutcome(FinantialMonth fm){
         if(fm.isShowMonthOutcomes()){
            fm.setShowMonthOutcomes(false);
        }
         else{
            fm.setShowMonthOutcomes(true);
         }
    }

    public List<String> getListOfYears() {
        return listOfYears;
    }

    public void setListOfYears(List<String> listOfYears) {
        this.listOfYears = listOfYears;
    }

        public String getYearToView() {
        return currentYearTitle;
    }

    public void setYearToView(String yearToView) {
        this.currentYearTitle = yearToView;
    }

    public void showIncome(FinantialMonth fm){
        if(fm.isShowMonthIncomes()){
            fm.setShowMonthIncomes(false);
        }
         else{
            fm.setShowMonthIncomes(true);
         }
    }
    public void showIncomeReports(FinantialMonth fm){
         fm.setShowMonthIncomeReports(true);
    }
    public void showOutcomeReports(FinantialMonth fm){
         fm.setShowMonthOutcomeReports(true);
    }

    public void prepareEditYear(){
        editYear = true;
    }

    /**
     * clear datatable selection after editing
     */
//    public void clearSelection(){
//        outcomeTable.getSelectedRowIndexes().clear();
//    }


     public void tabChange(TabChangeEvent event){
        String tabTitle = event.getTab().getTitle();
        if(tabTitle.equalsIgnoreCase("despesas")){
            if (financesLastTabIndex == this.ACORDION_OUTCOME_INDEX) {
                financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
                financesLastTabIndex = this.ACORDION_NOT_SELECTED_INDEX;
            } else {
                FinantialMonth fm = currentYear.getFinantialMonths().get(currentMonthIndex);
                fm.setShowMonthOutcomes(true);
                fm.setShowMonthIncomes(false);
                financesLastTabIndex = this.ACORDION_OUTCOME_INDEX;
                return;
            }
        }
        if(tabTitle.equalsIgnoreCase("receitas")){
            if (financesLastTabIndex == this.ACORDION_INCOME_INDEX) {
                financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
                financesLastTabIndex = this.ACORDION_NOT_SELECTED_INDEX;
            } else {
                FinantialMonth fm = currentYear.getFinantialMonths().get(currentMonthIndex);
                fm.setShowMonthOutcomes(false);
                fm.setShowMonthIncomes(true);
                financesLastTabIndex = this.ACORDION_INCOME_INDEX;
                return;
            }
        }
        if(tabTitle.contains("Relat")){
            if(financesLastTabIndex == ACORDION_REPORT_INDEX){
             financesActiveIndex = ACORDION_NOT_SELECTED_INDEX;
             financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
            }
         else{
            financesLastTabIndex = ACORDION_REPORT_INDEX;
         }
         }
    }

       public void onMonthChange(TabChangeEvent event){
        String tabId = event.getTab().getId();
        this.currentMonthIndex = Integer.parseInt(tabId.substring(tabId.indexOf("b")+1));
        financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
        financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
    }

    public void nextYear(){
        if(currentYearIndex < tabService.getMaxYearIndex()-1){
            currentYearIndex ++;
            currentYear = tabYears.get(currentYearIndex);
            financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
            financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
        }
    }
    public void previousYear(){
        if(currentYearIndex > 0){
            currentYearIndex --;
            currentYear = tabYears.get(currentYearIndex);
            financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
            financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
        }
    }

     public void changeYear(int index){
        setCurrentYearIndex(index);
        currentYear = tabYears.get(index);
        financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
        financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
    }






   
}
