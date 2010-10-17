/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.FinanceTypeService;
import br.rmpestano.finantial.service.UserService;
import br.rmpestano.finantial.service.generic.CrudService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;

/**
 * this bean is responsible for the Configuration page
 * @author rmpestano
 */
@ManagedBean(name="configurationBean")
@ViewScoped
public class ConfigurationController implements Serializable{
    private User user;
    private UserService userService;
    private boolean userUpdated;
    private boolean configState;
    private List<OutcomeType> outcomeTypes;
    private List<IncomeType> incomeTypes;
    private OutcomeType outcomeType;
    private IncomeType incomeType;
    private FinanceTypeService financeTypeService;
    private int outcomeTypesSize;
    private int incomeTypesSize;
    private String repeatPass;


    public ConfigurationController() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userService = (UserService) BeanManagerController.getBeanByName("userService");
        financeTypeService = (FinanceTypeService) BeanManagerController.getBeanByName("financeTypeService");
        userUpdated = false;
        outcomeTypes = user.getUserOutcomeTypes();
        incomeTypes = user.getUserIncomeTypes();
        outcomeType = new OutcomeType();
        incomeType = new IncomeType();
    }

    public User getUser() {
        return user;
    }

    public int getOutcomeTypesSize() {
        return outcomeTypes.size();
    }

    public void setOutcomeTypesSize(int outcomeTypesSize) {
        this.outcomeTypesSize = outcomeTypesSize;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public void setRepeatPass(String repeatPass) {
        this.repeatPass = repeatPass;
    }


    public void setIncomeType(IncomeType IncomeType) {
        this.incomeType = IncomeType;
    }

    public int getIncomeTypesSize() {
        return incomeTypes.size();
    }

    public void setIncomeTypesSize(int incomeTypesSize) {
        this.incomeTypesSize = incomeTypesSize;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(boolean userUpdated) {
        this.userUpdated = userUpdated;
    }

    public boolean isConfigState() {
        return configState;
    }

    public void setConfigState(boolean configState) {
        this.configState = configState;
    }

    public List<IncomeType> getIncomeTypes() {
        return incomeTypes;
    }

    public OutcomeType getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
    }

    public void setIncomeTypes(List<IncomeType> incomeTypes) {
        this.incomeTypes = incomeTypes;
    }

    public List<OutcomeType> getOutcomeTypes() {
        return outcomeTypes;
    }

    public void setOutcomeTypes(List<OutcomeType> outcomeTypes) {
        this.outcomeTypes = outcomeTypes;
    }


     public void updateUser(){
        try {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userService.atualizar(user));
            MessagesController.addInfo("Perfil atualizado com sucesso");
            userUpdated = true;
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao editar usuário:"+ex.getMessage());
        }
     }

     public void addOutcomeType(){
         try {
             user.getUserOutcomeTypes().add(outcomeType);
             outcomeType.setUser(user);
             financeTypeService.updateOutcomeType(outcomeType);
//             userService.atualizar(user);
             MessagesController.addInfo("Tipo despesa incluido com sucesso");
             outcomeType = new OutcomeType();
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao incluir tipo despesa:"+ex.getMessage());
             ex.printStackTrace();
         }
     }
     public void addIncomeType(){
         try {
             financeTypeService.addOutcomeType(incomeType);
             MessagesController.addInfo("Tipo receita:"+incomeType.getDescription() +" incluido com sucesso");
             incomeType = new IncomeType();
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao incluir tipo receita:"+ex.getMessage());
             ex.printStackTrace();
         }
     }
     public void removeOutcomeType(){
         try {
             OutcomeType removed = user.getUserOutcomeTypes().remove(user.getUserOutcomeTypes().indexOf(outcomeType));
             userService.atualizar(user);
//             long removedId = outcomeType.getId();
//             financeTypeService.removeOutcomeType(removedId);
             MessagesController.addInfo("Tipo despesa removido com sucesso");
             outcomeType = new OutcomeType();
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao remover tipo despesa:"+ex.getMessage());
             ex.printStackTrace();
         }
     }
     public void removeIncomeType(){
         try {
             financeTypeService.removeIncomeType(incomeType);
             MessagesController.addInfo("Tipo receita removido com sucesso");
             incomeType = new IncomeType();
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao remover tipo receita:"+ex.getMessage());
             ex.printStackTrace();
         }
     }

      public void checkNewPass(){
             if(repeatPass == null || user.getPassword() == null || repeatPass.trim().equals("") || (!repeatPass.equals(user.getPassword()))) {
                MessagesController.addError("As senhas não conferem");
           }
     }


public void cancelRemove(){
         incomeType = new IncomeType();
         outcomeType = new OutcomeType();
    }


}
