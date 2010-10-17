/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.generic.CrudService;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author rmpestano
 */
@Named(value="financeTypeService")
@Dependent
public class FinanceTypeService implements Serializable{
     @EJB CrudService<OutcomeType> outcomeTypeCrudService;
     @EJB CrudService<IncomeType>  incomeTypeCrudService;

     public void addOutcomeType(OutcomeType outcomeType){
         outcomeTypeCrudService.create(outcomeType);
     }
     public void updateOutcomeType(OutcomeType outcomeType){
         outcomeTypeCrudService.update(outcomeType);
     }
     public void removeOutcomeType(Long id){
         outcomeTypeCrudService.delete(id,OutcomeType.class);
     }
     public void addOutcomeType(IncomeType incomeType){
         incomeTypeCrudService.create(incomeType);
     }
     public void updateOutcomeType(IncomeType incomeType){
         incomeTypeCrudService.update(incomeType);
     }
      public void removeIncomeType(IncomeType incomeType){
         incomeTypeCrudService.delete(incomeType.getId(),IncomeType.class);
     }

     private User getCurrentUser(){
         return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
     }
}
