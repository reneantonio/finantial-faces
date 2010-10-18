/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.FinantialMonth;
import br.rmpestano.finantial.model.FinantialYear;
import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.generic.CrudService;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * this class provides services over years and months
 * it may not be referenced in the view, to do so you must use the TabController
 * @author rmpestano
 */
@Named(value="tabService")
@SessionScoped
public class TabService implements Serializable{
    @EJB CrudService<FinantialYear> yearCrudService;
    @EJB CrudService<FinantialMonth> monthCrudService;
   
    private User user;
    
     public void create(FinantialMonth fm){
        try {
            monthCrudService.create(fm);
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao inserir mês:"+fm.getDate(), ex.getMessage());
        }

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

    public int getMaxYearIndex() {
        List<FinantialYear> years = yearCrudService.findAll(FinantialYear.class);
        return years.size();
    }


    public String getSelectedMonthName(int monthIndex) {
        switch(monthIndex){
            case 0:{
                return "Janeiro";
            }
            case 1:{
                return "Fevereiro";
            }
            case 2:{
                return "Março";
            }
            case 3:{
                return "Abril";
            }
            case 4:{
                return "Maio";
            }
            case 5:{
                return "Junho";
            }
            case 6:{
                return "Julho";
            }
            case 7:{
                return "Agosto";
            }
            case 8:{
                return "Setembro";
            }
            case 9:{
                return "Outubro";
            }
            case 10:{
                return "Novembro";
            }
            case 11:{
                return "Dezembro";
            }
            default:return null;
        }
    }


    public List<OutcomeType> getOutcomeTypes(){
        return getUser().getUserOutcomeTypes();
    }
    public List<IncomeType> getIncomeTypes(){
        return getUser().getUserIncomeTypes();
    }

    public List<FinantialYear> getFinantialYears(){
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

    public FinantialYear getCurrentFinantialYear(){
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.get(Calendar.YEAR);
        return FinantialYear.findByYear(""+ c.get(Calendar.YEAR));
    }
    public FinantialMonth getCurrentDateFinantialMonth(){
        Date d = new Date();
        return FinantialMonth.findByDate(d);
    }
    
    
    public String findLastYear() {
        StringBuilder lastDate = new StringBuilder();
        List<FinantialYear> availableYears = this.getFinantialYears();
        int yearsSize = availableYears.size();
        FinantialYear lastYear = availableYears.get(yearsSize-1);
        lastDate.append("31/12/").append(lastYear.getTitle());
        return lastDate.toString();
    }
    public String findFirstYear() {
          StringBuilder firstDate = new StringBuilder();
          FinantialYear fy = getFinantialYears().get(0);
          firstDate.append("01/01/").append(fy.getTitle());
        return firstDate.toString();
        }

    @PostConstruct
    public void init(){
        

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

         user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




}
