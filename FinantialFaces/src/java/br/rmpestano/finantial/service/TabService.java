/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.controller.TabController;
import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.generic.CrudService;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;

/**
 * this class manage the years and the months in TabPanel
 * @author rmpestano
 */
@Named(value="tabService")
@SessionScoped
public class TabService implements Serializable{
    @Inject CrudService<FinantialYear> yearCrudService;
    @Inject CrudService<FinantialMonth> monthCrudService;
    private Integer numberOfYearsToView = 1;
    private String yearToView = "2010";
    private List<String> listOfYears;
    private boolean specificYear = false;
    private User user;
    private String currentYearForm = "year_2010";
    private int currentYearIndex;
    private int currentMonthIndex;
    private String selectedMonthName;
    private int maxYearIndex;
    
     public void create(FinantialMonth fm){
        try {
            monthCrudService.create(fm);
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao inserir mês:"+fm.getDate(), ex.getMessage());
        }

   }

    public int getMaxYearIndex() {
        return maxYearIndex;
    }
     
     
     public String changeNumYearstoView(Integer years){
         this.numberOfYearsToView = years;
         this.specificYear = false;
         return "/pages/home.faces?faces-redirect=true";
     }

     public void specificYearMode() {
         this.specificYear = true;
     }

    public String getCurrentYearForm() {
        System.out.println("CurrentForm:"+currentYearForm);
        return currentYearForm;
    }

    public void setCurrentYearForm(String currentYearForm) {
        this.currentYearForm = currentYearForm;
    }

    public int getCurrentMonthIndex() {
        return currentMonthIndex;
    }

    public void setCurrentMonthIndex(int currentMonthIndex) {
        this.currentMonthIndex = currentMonthIndex;
    }


   public void update(FinantialMonth fm){
        try {
            monthCrudService.update(fm);
        }catch (Exception ex) {
            MessagesController.addError("Problemas ao atualizar mês:"+fm.getDate(), ex.getMessage());
            ex.printStackTrace();
            return;
        }

   }

    public int getCurrentYearIndex() {
        return currentYearIndex;
    }

    public void setCurrentYearIndex(int currentYearIndex) {
        this.currentYearIndex = currentYearIndex;
    }


    public boolean isEspecificYear() {
        return specificYear;
    }

    public void setEspecificYear(boolean especificYear) {
        this.specificYear = especificYear;
    }

    public String getSelectedMonthName() {
        switch(currentMonthIndex){
            case 0:{
                selectedMonthName = "Janeiro";
                break;
            }
            case 1:{
                selectedMonthName = "Fevereiro";
                break;
            }
            case 2:{
                selectedMonthName = "Março";
                break;
            }
            case 3:{
                selectedMonthName = "Abril";
                break;
            }
            case 4:{
                selectedMonthName = "Maio";
                break;
            }
            case 5:{
                selectedMonthName = "Junho";
                break;
            }
            case 6:{
                selectedMonthName = "Julho";
                break;
            }
            case 7:{
                selectedMonthName = "Agosto";
                break;
            }
            case 8:{
                selectedMonthName = "Setembro";
                break;
            }
            case 9:{
                selectedMonthName = "Outubro";
                break;
            }
            case 10:{
                selectedMonthName = "Novembro";
                break;
            }
            case 11:{
                selectedMonthName = "Dezembro";
                break;
            }

        }
        return selectedMonthName;
    }



    public List<FinantialYear> getAllFinantialYears(){
        return yearCrudService.findAll(FinantialYear.class);
    }

    public int findYearIndex(String title){
        int index = 0;
        for (FinantialYear finantialYear : yearCrudService.findAll(FinantialYear.class)) {
            if(finantialYear.getTitle().equals(title)){
                return index;
            }
             else{
                index++;
             }
        }
        return -1;//não encontrou
    }

    /** get the tab year title looking to the tab index, ex: tab index of tab 2010 is 0, so the title which is returned is 2010 (2010 + 0 =2010)
     *
     * @param index
     * @return
     */
    public String yearIndexToString(int index) {
        Integer baseYear = 2010 + index;
        return baseYear.toString();
    }

/**
 * 
 * @return ano corrente
 */
    public FinantialYear getInitialFinantialYear(){
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.get(Calendar.YEAR);
        return FinantialYear.findByYear(""+ c.get(Calendar.YEAR));
    }
    
    /**
     * ou a data é de um ano especifico ou é de 2010 até yearToView
     * @return
     */
    
public String findfirstYear() {
        StringBuilder firstDate = new StringBuilder();
        if (specificYear == true) {
            FinantialYear fy = FinantialYear.findByYear(yearToView);
            firstDate.append("01/01/").append(fy.getTitle());
    } else {
        List<FinantialYear> firstYear = yearCrudService.findAll(FinantialYear.class);
        Collections.sort(firstYear);
        FinantialYear fy = firstYear.get(0);
        firstDate.append("01/01/").append(fy.getTitle());
    }

       return firstDate.toString();
    }
public String findlastYear() {
      StringBuilder lastDate = new StringBuilder();
    if (specificYear == true) {
        FinantialYear fy = FinantialYear.findByYear(yearToView);
        lastDate.append("31/12/").append(fy.getTitle());
    } else {

        List<FinantialYear> lastYear = yearCrudService.findAll(FinantialYear.class);
        Collections.sort(lastYear);

        FinantialYear fy = lastYear.get(numberOfYearsToView - 1);
        lastDate.append("31/12/").append(fy.getTitle());
    }
    return lastDate.toString();
    }

public List<FinantialYear> getYearsToView(){
      List<FinantialYear> retorno = new ArrayList<FinantialYear>();
      List<FinantialYear> years =  yearCrudService.findAll(FinantialYear.class);
      Collections.sort(years);
      if (this.specificYear == false) {
        for (int i = 0; i < numberOfYearsToView; i++) {
            retorno.add(years.get(i));
        }
    } else {
        retorno.add(FinantialYear.findByYear(yearToView));
    }

      return retorno;

}

    public List<String> getListOfYears() {
        return listOfYears;
    }

    public void setListOfYears(List<String> listOfYears) {
        this.listOfYears = listOfYears;
    }

    public String getYearToView() {
        return yearToView;
    }

    public void setYearToView(String yearToView) {
        this.yearToView = yearToView;
    }



    
    
    @PostConstruct
    public void init(){
        List<FinantialYear> years = yearCrudService.findAll(FinantialYear.class);
        maxYearIndex = years.size();
        listOfYears = new ArrayList<String>();
        for(int i=2010;i<=2010+maxYearIndex;i++){
            listOfYears.add(new String(""+i));
        }
        setInitialTabs();

//         if(!PersistenceManager.createEntityManager().createQuery("select f from FinantialYear f").getResultList().isEmpty()){
//            return;
//        }
//        try{
//        for (int i = 2010; i <= 2030; i++) {
//          FinantialYear fy = new FinantialYear();
//          fy.setTitle(""+i);
//            List<FinantialMonth> months = new ArrayList<FinantialMonth>();
//            for (int j = 0; j < 12; j++) {
//                FinantialMonth fm = new FinantialMonth();
//                switch(j){
//                    case 0:{
//                        fm.setTitle("tabyear.label.jan");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 1:{
//                        fm.setTitle("tabyear.label.fev");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 2:{
//                        fm.setTitle("tabyear.label.mar");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 3:{
//                        fm.setTitle("tabyear.label.abr");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 4:{
//                        fm.setTitle("tabyear.label.mai");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 5:{
//                        fm.setTitle("tabyear.label.jun");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 6:{
//                        fm.setTitle("tabyear.label.jul");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 7:{
//                        fm.setTitle("tabyear.label.ago");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 8:{
//                        fm.setTitle("tabyear.label.set");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 9:{
//                        fm.setTitle("tabyear.label.out");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 10:{
//                        fm.setTitle("tabyear.label.nov");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//                    case 11:{
//                        fm.setTitle("tabyear.label.dez");
//                        Calendar c = new GregorianCalendar(i, j, 1);
//                        fm.setDate(c.getTime());
//                        fm.setFinantialYear(fy);
//                        break;
//                    }
//
//                }
//                months.add(fm);
//            }
//          fy.setFinantialMonths(months);
//
//
//          crudService.create(fy);
//        }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }

    }

    public User getUser() {
        if(user == null){
         user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    private void setInitialTabs() {
        try {
            Date d = new Date();
            Calendar c = new GregorianCalendar();
            c.setTime(d);
            FinantialMonth fm = FinantialMonth.findByDate(d);
            FinantialYear fy = fm.getFinantialYear();
            currentYearIndex = this.findYearIndex(fy.getTitle());
            currentMonthIndex = c.get(Calendar.MONTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void nextYear(){
        if(currentYearIndex < maxYearIndex-1){
            currentYearIndex ++;
        }
    }
    public void previousYear(){
        if(currentYearIndex > 0){
            currentYearIndex --;
        }
    }
    
     public void changeYear(int index){
        setCurrentYearIndex(index);
    }


    public Integer getNumberOfYearsToView() {
        return numberOfYearsToView;
    }

    public void setNumberOfYearsToView(Integer numberOfYearsToView) {
        this.numberOfYearsToView = numberOfYearsToView;
    }

   public void onTabChange(TabChangeEvent event) {
       if (event.getTab() != null) {
           this.currentYearForm = "year_"+event.getTab().getClientId().substring(5, 9);
           this.currentMonthIndex = Integer.parseInt(event.getTab().getId().substring(event.getTab().getId().indexOf("b")+1));
       }

    }



}
