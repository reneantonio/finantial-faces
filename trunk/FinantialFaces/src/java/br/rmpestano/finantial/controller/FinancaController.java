/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.FinantialMonth;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

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
    private String lastDayOfMonth;
    private String firstDayOfMonth;
    private Double financeValue;
    private String financeDescription;
    private Outcome selectedOutcome;
    private Income selectedIncome;
    private FinanceService financeService;
    private List<Date> months;

    


    public FinancaController() {
        subtiposIncome = IncomeType.findAll();
        subtiposOutcome = OutcomeType.findAll();
        tabService = (TabService) BeanManagerController.getBeanByName("tabService");
        financeService = (FinanceService) BeanManagerController.getBeanByName("financeService");
        receita = new Income();
        despesa = new Outcome();
        months = new ArrayList<Date>();
    }

    public List<String> getTiposFinanca() {
        return tiposFinanca;
    }


    public void setTiposFinanca(List<String> tiposFinanca) {
        this.tiposFinanca = tiposFinanca;
    }

    public String getTipoCorrete() {
        return tipoCorrete;
    }


    public void setTipoCorrete(String tipoCorrete) {
        this.tipoCorrete = tipoCorrete;
    }

    public String getFirstDayOfMonth() {
        return firstDayOfMonth;
    }

    public List<Date> getMonths() {
        return months;
    }

    public void setMonths(List<Date> months) {
        this.months = months;
    }


    public void setFirstDayOfMonth(String firstDayOfMonth) {
        this.firstDayOfMonth = firstDayOfMonth;
    }

    public Outcome getSelectedOutcome() {
        return selectedOutcome;
    }

    public void setSelectedOutcome(Outcome selectedOutcome) {
        this.selectedOutcome = selectedOutcome;
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

    public String getLastDayOfMonth() {
        return lastDayOfMonth;
    }

    public void setLastDayOfMonth(String lastDayOfMonth) {
        this.lastDayOfMonth = lastDayOfMonth;
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
                selectedIncome = receita;
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
                selectedOutcome = despesa;
            }
            tabService.update(fm);
            tabService.setYearTabIndex(tabService.findYearIndex(fm.getFinantialYear().getTitle()));
            MessagesController.addInfo(tipoCorrete.equals(INCOME) ? "Receita incluida com sucesso!": "Despesa incluida com sucesso");
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
        try{
         financeService.removeOutcome(selectedOutcome);
         MessagesController.addInfo("Despesa removida com sucesso!");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public void removeIncome(){
        try{
         financeService.removeIncome(selectedIncome);
         MessagesController.addInfo("Receita removida com sucesso!");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public Income getSelectedIncome() {
        return selectedIncome;
    }

    public void setSelectedIncome(Income selectedIncome) {
        this.selectedIncome = selectedIncome;
    }

    public void addMonthOutcome(){
        FinantialMonth fm = FinantialMonth.findByDate(despesa.getDate());
        fm.getMonthOutcomes().add(despesa);
        despesa.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        despesa.setFinantialMonth(fm);
        tabService.update(fm);
        setCurrentTab(despesa.getDate());
        MessagesController.addInfo("Despesa incluida com sucesso");
    }
    public void addMonthIncome(){
        FinantialMonth fm = FinantialMonth.findByDate(receita.getDate());
        fm.getMonthIncomes().add(receita);
        receita.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        receita.setFinantialMonth(fm);
        tabService.update(fm);
        setCurrentTab(receita.getDate());
        MessagesController.addInfo("Receita incluida com sucesso");
    }


    public void updateOutcome(ActionEvent ev){
        Map<String,Object> map =  FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        TabController tabController = (TabController) map.get("tabBean");
        Calendar c = new GregorianCalendar();
        c.setTime(selectedOutcome.getDate());
        c.set(Calendar.DAY_OF_MONTH, 1);

        if(! c.getTime().equals(selectedOutcome.getFinantialMonth().getDate())){//se o mes for diferente
            FinantialMonth fm = FinantialMonth.findByDate(selectedOutcome.getDate());
            fm.getMonthOutcomes().add(selectedOutcome);
            selectedOutcome.setFinantialMonth(fm);
            financeService.updateMonth(fm);
            tabController.setTabYears(tabService.getYearsToView());
        }
         else{
          financeService.updateOutcome(selectedOutcome);
         }
         MessagesController.addInfo("Despesa modificada com sucesso");
         setCurrentTab(selectedOutcome.getDate());
    }
    public void updateIncome(ActionEvent ev){
        Map<String,Object> map =  FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        TabController tabController = (TabController) map.get("tabBean");
        Calendar c = new GregorianCalendar();
        c.setTime(selectedIncome.getDate());
        c.set(Calendar.DAY_OF_MONTH, 1);
        if(! c.getTime().equals(selectedIncome.getFinantialMonth().getDate())){
            FinantialMonth fm = FinantialMonth.findByDate(selectedIncome.getDate());
            fm.getMonthIncomes().add(selectedIncome);
            selectedIncome.setFinantialMonth(fm);
            financeService.updateMonth(fm);
            tabController.setTabYears(tabService.getYearsToView());
        }
         else{
          financeService.updateIncome(selectedIncome);
         }
         MessagesController.addInfo("Receita modificada com sucesso");
         setCurrentTab(selectedIncome.getDate());
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

    public void prepareAddMonthOutcome(FinantialMonth fm){
        despesa = new Outcome();
        tipoCorrete = OUTCOME;
        Calendar c = new GregorianCalendar();
        c.setTime(fm.getDate());
        Integer month = c.get(Calendar.MONTH) +1;
        String year = fm.getFinantialYear().getTitle();
        StringBuilder sb = new StringBuilder();
        sb.append(""+c.getActualMaximum(Calendar.DAY_OF_MONTH)).append("/").append(month).append("/").append(year);
        lastDayOfMonth = sb.toString();
        firstDayOfMonth = "01/"+month +"/"+year;
        tabService.setCurrentYearForm("year_"+year);
        tabService.setCurrentMonthIndex(fm.getMonthIndex());
    }
    public void prepareAddMonthIncome(FinantialMonth fm){
        receita = new Income();
        tipoCorrete = INCOME;
        Calendar c = new GregorianCalendar();
        c.setTime(fm.getDate());
        Integer month = c.get(Calendar.MONTH) +1;
        String year = fm.getFinantialYear().getTitle();
        StringBuilder sb = new StringBuilder();
        sb.append(""+c.getActualMaximum(Calendar.DAY_OF_MONTH)).append("/").append(month).append("/").append(year);
        lastDayOfMonth = sb.toString();
        firstDayOfMonth = "01/"+month +"/"+year;
        tabService.setCurrentYearForm("year_"+year);
        tabService.setCurrentMonthIndex(fm.getMonthIndex());
    }

    public void prepareEditOutcome(Outcome despesa){
        selectedOutcome = despesa;
        tipoCorrete = OUTCOME;
        String year = despesa.getFinantialMonth().getFinantialYear().getTitle();
        tabService.setCurrentMonthIndex(despesa.getFinantialMonth().getMonthIndex());
        Calendar c = new GregorianCalendar();
        c.setTime(selectedOutcome.getFinantialMonth().getDate());
        Integer month = c.get(Calendar.MONTH) +1;
        StringBuilder sb = new StringBuilder();
        sb.append(""+c.getActualMaximum(Calendar.DAY_OF_MONTH)).append("/").append(month).append("/").append(year);
        lastDayOfMonth = sb.toString();
        firstDayOfMonth = "01/"+month +"/"+year;
        tabService.setCurrentYearForm("year_"+year);
    }
    public void prepareEditIncome(Income receita){
        selectedIncome = receita;
        tipoCorrete = INCOME;
        String year = receita.getFinantialMonth().getFinantialYear().getTitle();
        tabService.setCurrentMonthIndex(receita.getFinantialMonth().getMonthIndex());
        Calendar c = new GregorianCalendar();
        c.setTime(selectedIncome.getFinantialMonth().getDate());
        Integer month = c.get(Calendar.MONTH) +1;
        StringBuilder sb = new StringBuilder();
        sb.append(""+c.getActualMaximum(Calendar.DAY_OF_MONTH)).append("/").append(month).append("/").append(year);
        lastDayOfMonth = sb.toString();
        firstDayOfMonth = "01/"+month +"/"+year;
        tabService.setCurrentYearForm("year_"+year);
    }
    public void prepareRemoveOutcome(Outcome despesa){
        selectedOutcome = despesa;
        tipoCorrete = OUTCOME;
        String year = despesa.getFinantialMonth().getFinantialYear().getTitle();
        tabService.setCurrentMonthIndex(despesa.getFinantialMonth().getMonthIndex());
        Calendar c = new GregorianCalendar();
        c.setTime(selectedOutcome.getFinantialMonth().getDate());
        Integer month = c.get(Calendar.MONTH) +1;
        StringBuilder sb = new StringBuilder();
        sb.append(""+c.getActualMaximum(Calendar.DAY_OF_MONTH)).append("/").append(month).append("/").append(year);
        lastDayOfMonth = sb.toString();
        firstDayOfMonth = "01/"+month +"/"+year;
        tabService.setCurrentYearForm("year_"+year);
    }
    public void prepareRemoveIncome(Income receita){
        selectedIncome = receita;
        tipoCorrete = INCOME;
        String year = receita.getFinantialMonth().getFinantialYear().getTitle();
        tabService.setCurrentMonthIndex(receita.getFinantialMonth().getMonthIndex());
        Calendar c = new GregorianCalendar();
        c.setTime(selectedIncome.getFinantialMonth().getDate());
        Integer month = c.get(Calendar.MONTH) +1;
        StringBuilder sb = new StringBuilder();
        sb.append(""+c.getActualMaximum(Calendar.DAY_OF_MONTH)).append("/").append(month).append("/").append(year);
        lastDayOfMonth = sb.toString();
        firstDayOfMonth = "01/"+month +"/"+year;
        tabService.setCurrentYearForm("year_"+year);
    }

//    public void onEditPanelClose(CloseEvent event) {
//          selectedOutcome = new Outcome();
//          selectedIncome = new Income();
//    }

    public void cancelRemove(){
        if (tipoCorrete.equals(OUTCOME)) {
            selectedOutcome = new Outcome();
        } else {
            selectedIncome = new Income();
        }
    }
    public void rowSelectOutcome(SelectEvent event){
        prepareEditOutcome(selectedOutcome);
            
        
    }
    public void rowSelectIncome(SelectEvent event){
        tipoCorrete = INCOME;
        selectedOutcome = new Outcome();
    }

}
