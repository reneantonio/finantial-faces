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
import br.rmpestano.finantial.service.FinanceService;
import br.rmpestano.finantial.service.I18nService;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.IdleEvent;
import org.primefaces.model.LazyDataModel;

import org.primefaces.event.TabChangeEvent;

/**
 * this bean is responsible for the months and their finances in View
 * no sql queries here, use tabService instead
 * @author rmpestano
 */
@ManagedBean(name = "tabBean")
@ViewScoped
public class TabController implements Serializable {

    TabService tabService;
    FinanceService financeService;
    List<FinantialYear> tabYears;
    private String lastYearDate; //its used in the addFinance calendar minDate and maxdate atributes
    private String firstYearDate;
    private Double totalOutcomeInThemonth;
    private FinantialYear currentYear;
    private FinantialMonth currentMonth;
    private boolean editYear;
    private int currentMonthIndex;
    private List<String> listOfYears;
    private String currentYearTitle;
    private LazyDataModel<Outcome> currentUserOutcomesInTheMonth;
    private LazyDataModel<Income> currentUserIncomesInTheMonth;
    private int currentYearIndex;
    private int financesActiveIndex = -1;
    private int financesLastTabIndex = -1;
    private SelectItem[] outcomeFilterOptions;
    private SelectItem[] incomeFilterOptions;
    private final int ACORDION_NOT_SELECTED_INDEX = -1;
    private final int ACORDION_OUTCOME_INDEX = 0;
    private final int ACORDION_INCOME_INDEX = 1;
    private final int ACORDION_REPORT_INDEX = 2;
    private int userOutcomesSize;
    private int userIncomesSize;
    private Double totalIncomeInTheMonth;
    private I18nService i18nService;

    @PostConstruct
    public void initMonthsAndYears() {
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");//colocar esse bean em enterprise.context.viewScoope e dar inject ao inves dessa gambia
        financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");//colocar esse bean em enterprise.context.viewScoope e dar inject ao inves dessa gambia
        i18nService = (I18nService) BeanManagerController.getBeanByName("i18nService");
        tabYears = tabService.getFinantialYears();
        Collections.sort(tabYears);
        setInitialTabs();
        lastYearDate = tabService.findLastYear();
        firstYearDate = tabService.findFirstYear();
        currentYear = tabYears.get(currentYearIndex);
        outcomeFilterOptions = createOutcomeFilterOptions();
        incomeFilterOptions = createIncomeFilterOptions();
        currentUserOutcomesInTheMonth = new LazyDataModel<Outcome>() {

            @Override
            public List<Outcome> load(int first, int pageSize, String string, boolean bln, Map<String, String> map) {
                List<Outcome> lazyOutcomes = new ArrayList<Outcome>();
                lazyOutcomes = populateLazyOutcome(first, pageSize, map);
                return lazyOutcomes;
            }

            private List<Outcome> populateLazyOutcome(int first, int pageSize, Map<String, String> map) {
                if (!map.isEmpty()) {
                    long typeId = Long.parseLong(map.get("type.description"));
                    this.setRowCount(financeService.findMonthOutcomesByUserAndType(currentMonth.getDate(), typeId).size());
                    return getLazyCurrentUserOutcomesInTheMonth(typeId, first, pageSize);
                }
                 else{
                    this.setRowCount(getUserOutcomesSize());
                    return getLazyCurrentUserOutcomesInTheMonth(first, pageSize);
                 }
            }
        };

        currentUserIncomesInTheMonth = new LazyDataModel<Income>() {

            @Override
            public List<Income> load(int first, int pageSize, String string, boolean bln, Map<String, String> filter) {
                List<Income> lazyIncomes = new ArrayList<Income>();
                lazyIncomes = populateLazyIncome(first, pageSize, filter);
               this.setRowCount(lazyIncomes.size());
                return lazyIncomes;
            }

            private List<Income> populateLazyIncome(int first, int pageSize, Map<String, String> filter) {
                if (!filter.isEmpty()) {
                    long typeId = Long.parseLong(filter.get("type.description"));
                    this.setRowCount(financeService.findMonthIncomesByUserAndType(currentMonth.getDate(), typeId).size());
                    return getLazyCurrentUserIncomesInTheMonth(typeId, first, pageSize);
                }

                this.setRowCount(getUserIncomesSize());
                return getLazyCurrentUserIncomesInTheMonth(first, pageSize);
            }
        };
//        currentUserOutcomesInTheMonth.setRowCount(this.getUserOutcomesSize());
//        currentUserIncomesInTheMonth.setRowCount(this.getUserIncomesSize());
    }

    public LazyDataModel<Outcome> getCurrentUserOutcomesInTheMonth() {
        if (currentUserOutcomesInTheMonth.getPageSize() == 0) {//bug
            currentUserOutcomesInTheMonth.setPageSize(5);
        }
        return currentUserOutcomesInTheMonth;
    }

    public List<Outcome> getLazyCurrentUserOutcomesInTheMonth(int first, int pageSize) {
        return financeService.findMonthOutcomesByUser(currentMonth.getDate(), first, pageSize);
    }

    public List<Outcome> getLazyCurrentUserOutcomesInTheMonth(long typeId, int first, int pageSize) {
        return financeService.findMonthOutcomesByUserAndType(currentMonth.getDate(), typeId, first, pageSize);
    }

    public List<Income> getLazyCurrentUserIncomesInTheMonth(long typeId, int first, int pageSize) {
        return financeService.findMonthIncomesByUserAndType(currentMonth.getDate(), typeId, first, pageSize);
    }

    public List<Income> getLazyCurrentUserIncomesInTheMonth(int first, int pageSize) {
        return financeService.findMonthIncomesByUser(currentMonth.getDate(), first, pageSize);
    }

    public void setUserOutcomesSize(int userOutcomesSize) {
        this.userOutcomesSize = userOutcomesSize;
    }

    public LazyDataModel<Income> getCurrentUserIncomesInTheMonth() {
        if (currentUserIncomesInTheMonth.getPageSize() == 0) {//bug
            currentUserIncomesInTheMonth.setPageSize(5);
        }
        return currentUserIncomesInTheMonth;
    }

    public void setCurrentUserIncomesInTheMonth(LazyDataModel<Income> currentUserIncomesInTheMonth) {
        this.currentUserIncomesInTheMonth = currentUserIncomesInTheMonth;
    }

    public void setCurrentUserOutcomesInTheMonth(LazyDataModel<Outcome> currentUserOutcomesInTheMonth) {
        this.currentUserOutcomesInTheMonth = currentUserOutcomesInTheMonth;
    }

    public Double getSaldoMensal() {
        List<Income> receitas = financeService.findMonthIncomesByUser(currentMonth.getDate());
//        List<Outcome> despesas = this.getCurrentUserOutcomesInTheMonth();
        List<Outcome> despesas = financeService.findMonthOutcomesByUser(currentMonth.getDate());
        Double incomeSum = 0.0;
        for (Income income : receitas) {
            if (income.getValue() != null) {
                incomeSum += income.getValue();
            }
        }
        Double outcomeSum = 0.0;
        for (Outcome outcome : despesas) {
            if (outcome.getValue() != null) {
                outcomeSum += outcome.getValue();
            }
        }
        return incomeSum - outcomeSum;
    }

    public int getUserOutcomesSize() {
        return financeService.findMonthOutcomesByUser(currentMonth.getDate()).size();
    }

    public int getUserIncomesSize() {
        return financeService.findMonthIncomesByUser(currentMonth.getDate()).size();
    }

    public void setUserIncomesSize(int userIncomesSize) {
        this.userIncomesSize = userIncomesSize;
    }

    public TabController() {
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

    public SelectItem[] createOutcomeFilterOptions() {
        List<OutcomeType> types = tabService.getUser().getUserOutcomeTypes();
        SelectItem[] retorno = new SelectItem[types.size() + 1];
        retorno[0] = new SelectItem("", "Todos");
        for (int i = 0; i < types.size(); i++) {
            retorno[i + 1] = new SelectItem(types.get(i).getId(), types.get(i).getDescription());
        }
        return retorno;
    }

    public SelectItem[] createIncomeFilterOptions() {
        List<IncomeType> types = tabService.getUser().getUserIncomeTypes();
        SelectItem[] retorno = new SelectItem[types.size() + 1];
        retorno[0] = new SelectItem("", "Todos");
        for (int i = 0; i < types.size(); i++) {
            retorno[i + 1] = new SelectItem(types.get(i).getId(), types.get(i).getDescription());
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
//      currentMonth = currentYear.getFinantialMonths().get(currentMonthIndex);
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
        if (tabYears == null) {
            tabYears = tabService.getFinantialYears();
        }

        return tabYears;
    }

    public void setTabYears(List<FinantialYear> tabYears) {
        this.tabYears = tabYears;
    }

    public Double getTotalOutcomeInThemonth() {
        return financeService.getTotalOutcomeInTheMonth(currentMonth);
    }

    public void setTotalOutcomeInThemonth(Double totalOutcomeInThemonth) {
        this.totalOutcomeInThemonth = totalOutcomeInThemonth;
    }

    public Double getTotalIncomeInTheMonth() {
        return financeService.getTotalIncomeInTheMonth(currentMonth);//o certo era n'ao ter query nos gets mas como soa poucos registros que a query retorna...
    }

    public void setTotalIncomeInTheMonth(Double totalIncomeInThemonth) {
        this.totalIncomeInTheMonth = totalIncomeInThemonth;
    }

    public int getCurrentMonthIndex() {
        return currentMonthIndex;
    }

    public void setCurrentMonthIndex(int currentMonthIndex) {
        this.currentMonthIndex = currentMonthIndex;
    }

    private void setInitialTabs() {
        try {
            FinantialMonth fm = tabService.getCurrentDateFinantialMonth();
            FinantialYear fy = fm.getFinantialYear();
            currentYearTitle = fy.getTitle();
            currentYearIndex = tabService.findYearIndex(fy.getTitle());
            currentMonthIndex = fm.getMonthIndex();
            currentMonth = fm;
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

    public void idleListener(IdleEvent event) {
        MessagesController.addError("Sessão Expirada!");
        //invalidate session
    }

    public void showOutcome(FinantialMonth fm) {
        if (fm.isShowMonthOutcomes()) {
            fm.setShowMonthOutcomes(false);
        } else {
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

    public void showIncome(FinantialMonth fm) {
        if (fm.isShowMonthIncomes()) {
            fm.setShowMonthIncomes(false);
        } else {
            fm.setShowMonthIncomes(true);
        }
    }

    public void prepareEditYear() {
        editYear = true;
    }

    /**
     * clear datatable selection after editing
     */
//    public void clearSelection(){
//        outcomeTable.getSelectedRowIndexes().clear();
//    }
    public void tabChange(TabChangeEvent event) {
        String tabTitle = event.getTab().getTitle();
        if (tabTitle.equalsIgnoreCase(i18nService.getBundle().getString("finance.label.tipo.outcome").concat("s"))) {
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
        if (tabTitle.equalsIgnoreCase(i18nService.getBundle().getString("finance.label.tipo.income").concat("s"))) {
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
        if (tabTitle.equalsIgnoreCase(i18nService.getBundle().getString("tab.reports"))) {
            if (financesLastTabIndex == ACORDION_REPORT_INDEX) {
                financesActiveIndex = ACORDION_NOT_SELECTED_INDEX;
                financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
            } else {
                financesLastTabIndex = ACORDION_REPORT_INDEX;
            }
        }
    }

    public void onMonthChange(TabChangeEvent event) {
        String tabId = event.getTab().getId();
        this.currentMonthIndex = Integer.parseInt(tabId.substring(tabId.indexOf("b") + 1));
        financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
        financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
        currentMonth = currentYear.getFinantialMonths().get(currentMonthIndex);
        resetIncomeLazyDataModel();
        resetOutcomeLazyDataModel();
    }

    public void nextYear() {
        if (currentYearIndex < tabService.getMaxYearIndex() - 1) {
            currentYearIndex++;
            currentYear = tabYears.get(currentYearIndex);
            currentMonth = currentYear.getFinantialMonths().get(currentMonthIndex);
            financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
            financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
            resetIncomeLazyDataModel();
            resetOutcomeLazyDataModel();
        }
    }

    public void previousYear() {
        if (currentYearIndex > 0) {
            currentYearIndex--;
            currentYear = tabYears.get(currentYearIndex);
            currentMonth = currentYear.getFinantialMonths().get(currentMonthIndex);
            financesActiveIndex = this.ACORDION_NOT_SELECTED_INDEX;
            financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
            resetIncomeLazyDataModel();
            resetOutcomeLazyDataModel();
        }
    }

    public void changeYear(int index) {
        setCurrentYearIndex(index);
        currentYear = tabYears.get(index);
        currentMonth = currentYear.getFinantialMonths().get(currentMonthIndex);
        financesActiveIndex = ACORDION_NOT_SELECTED_INDEX;
        financesLastTabIndex = ACORDION_NOT_SELECTED_INDEX;
        resetIncomeLazyDataModel();
        resetOutcomeLazyDataModel();
    }

    //atualiza lazyOutcomes a manda pra ultima página da tabela
    public void reloadOutcomeLazyDataModel() {
        LazyDataModel<Outcome> lazyModel = getCurrentUserOutcomesInTheMonth();
        lazyModel.setRowCount(this.getUserOutcomesSize());
        DataTable lazyTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("months_form:fm_" + getCurrentMonthIndex() + ":outcome_table" + getCurrentMonthIndex());
        int first = 0;
        if (lazyModel.getRowCount() > lazyModel.getPageSize()) {//se tem mais de uma página manda carregar a ultima para usuário ver o dado que foi incluido
            int lastPage = ((lazyModel.getRowCount() - 1) / lazyModel.getPageSize());
            first = (lastPage * lazyModel.getPageSize());
            lazyTable.setFirst(first);
            lazyTable.setPage(lastPage + 1);
        }
        lazyTable.setFilters(null);
        lazyTable.loadLazyData();
    }

    public void resetOutcomeLazyDataModel() {
        LazyDataModel<Outcome> lazyModel = getCurrentUserOutcomesInTheMonth();
        lazyModel.setRowCount(this.getUserOutcomesSize());
        DataTable lazyTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("months_form:fm_" + getCurrentMonthIndex() + ":outcome_table" + getCurrentMonthIndex());
        lazyTable.setFirst(0);
        lazyTable.setPage(1);
        lazyTable.setFilters(null);
        lazyTable.loadLazyData();
    }
    //atualiza lazyIncomes a manda pra ultima página da tabela

    public void reloadIncomeLazyDataModel() {
        LazyDataModel<Income> lazyModel = getCurrentUserIncomesInTheMonth();
        lazyModel.setRowCount(this.getUserIncomesSize());
        DataTable lazyTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("months_form:fm_" + getCurrentMonthIndex() + ":income_table" + getCurrentMonthIndex());
        int first = 0;
        if (lazyModel.getRowCount() > lazyModel.getPageSize()) {//se tem mais de uma página manda carregar a ultima para usuário ver o dado que foi incluido
            int lastPage = ((lazyModel.getRowCount() - 1) / lazyModel.getPageSize());
            first = (lastPage * lazyModel.getPageSize());
            lazyTable.setFirst(first);
            lazyTable.setPage(lastPage + 1);
        }
        lazyTable.setFilters(null);
        lazyTable.loadLazyData();
    }

    public void resetIncomeLazyDataModel() {
        LazyDataModel<Income> lazyModel = getCurrentUserIncomesInTheMonth();
        lazyModel.setRowCount(this.getUserIncomesSize());
        DataTable lazyTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("months_form:fm_" + getCurrentMonthIndex() + ":income_table" + getCurrentMonthIndex());
        lazyTable.setFirst(0);
        lazyTable.setPage(1);
        lazyTable.setFilters(null);
        lazyTable.loadLazyData();
    }
}
