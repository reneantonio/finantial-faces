/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.Preference;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.FinanceTypeService;
import br.rmpestano.finantial.service.TabService;
import br.rmpestano.finantial.service.ThemeService;
import br.rmpestano.finantial.service.UserService;
import br.rmpestano.finantial.service.generic.CrudService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 * this bean is responsible for the Configuration page
 * @author rmpestano
 */
@ManagedBean(name="configurationBean")
@ViewScoped
public class ConfigurationController implements Serializable{
    private User user;
    private final String currentUserLogin;
    private UserService userService;
    private boolean configState;
    private List<OutcomeType> outcomeTypes;
    private List<IncomeType> incomeTypes;
    private OutcomeType outcomeType;
    private OutcomeType selectedOutcomeType;
    private IncomeType selectedIncomeType;
    private IncomeType incomeType;
    private FinanceTypeService financeTypeService;
    private int outcomeTypesSize;
    private int incomeTypesSize;
    private String repeatPass;
    private String userTheme;
    @ManagedProperty(value="#{tabBean}")
    private TabController tabController;
    @ManagedProperty(value="#{themeService}")
    private ThemeService themeservice;
    @EJB CrudService<Preference> preferenceService;
    private boolean editName;
    private boolean editFullName;
    private boolean editTheme;
    private boolean editPass;


    public ConfigurationController() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userService = (UserService) BeanManagerController.getBeanByName("userService");
        financeTypeService = (FinanceTypeService) BeanManagerController.getBeanByName("financeTypeService");
        currentUserLogin = user.getUsername();
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

     public TabController getTabController() {
        return tabController;
    }

    public ThemeService getThemeservice() {
        return themeservice;
    }

    public void setThemeservice(ThemeService themeservice) {
        this.themeservice = themeservice;
    }

    public boolean isEditFullName() {
        return editFullName;
    }

    public void setEditFullName(boolean editFullName) {
        this.editFullName = editFullName;
    }

    public boolean isEditPass() {
        return editPass;
    }

    public void setEditPass(boolean editPass) {
        this.editPass = editPass;
    }

    public boolean isEditTheme() {
        return editTheme;
    }

    public void setEditTheme(boolean editTheme) {
        this.editTheme = editTheme;
    }


    public boolean isEditName() {
        return editName;
    }

    public void setEditName(boolean editName) {
        this.editName = editName;
    }


    public void setTabController(TabController tabController) {
        this.tabController = tabController;
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public OutcomeType getSelectedOutcomeType() {
        return selectedOutcomeType;
    }

    public void setSelectedOutcomeType(OutcomeType selectedOutcomeType) {
        this.selectedOutcomeType = selectedOutcomeType;
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

    public IncomeType getSelectedIncomeType() {
        return selectedIncomeType;
    }

    public void setSelectedIncomeType(IncomeType selectedIncomeType) {
        this.selectedIncomeType = selectedIncomeType;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public boolean isConfigState() {
        return configState;
    }

    public void setConfigState(boolean configState) {
        this.configState = configState;
    }

    public List<IncomeType> getIncomeTypes() {
        return user.getUserIncomeTypes();
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
        return user.getUserOutcomeTypes();
    }

    public String getUserTheme() {
//        if(userTheme != null && userTheme.equals("")){
//            userTheme = "ui-darkness";
//        }
        return userTheme;
    }

    public void setUserTheme(String userTheme) {
        this.userTheme = userTheme;
    }


    public void setOutcomeTypes(List<OutcomeType> outcomeTypes) {
        this.outcomeTypes = outcomeTypes;
    }

    public void updateUsername(){
         if (user.getId() == 3 && currentUserLogin.equalsIgnoreCase("demo")) {
            MessagesController.addError("O usuário de demonstração não pode ser modificado");
            return;
        }
          User alreadyExistUser = userService.findByLogin(user.getUsername());
            if(alreadyExistUser != null && (!alreadyExistUser.getUsername().equals(currentUserLogin))){
                 MessagesController.addError("O login '"+user.getUsername()+ "' já existe, escolha outro");
                 user.setUsername(currentUserLogin);
                 return;
            }
        try {
            userService.atualizar(user);
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao editar nome do usuário:"+ex.getMessage());
        }
        MessagesController.addInfo("Login atualizado com sucesso");
        editName = false;
    }
    public void updatePass() {
        if (user.getId() == 3 && currentUserLogin.equalsIgnoreCase("demo")) {
            MessagesController.addError("O usuário de demonstração não pode ser modificado");
            return;
        }
        try {
            if (checkNewPass()) {
                userService.atualizar(user);
                MessagesController.addInfo("Senha atualizada com sucesso");
            } else {
                MessagesController.addError("As senhas não conferem");
                return;
            }
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao editar nome do usuário:" + ex.getMessage());
        }
        editPass = false;
    }
    public void updateFullname(){
        try {
            userService.atualizar(user);
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao editar nome do usuário:"+ex.getMessage());
        }
        MessagesController.addInfo("Nome atualizado com sucesso");
        editFullName = false;
    }

    public void cancelEdit(ActionEvent event){
       String caller = event.getComponent().getId();
        if (caller.contains("theme")) {
            editTheme = false;
            return;
        }
       if(caller.contains("user")){
            editName = false;
            return;
       }
       if(caller.contains("full")){
            editFullName = false;
            return;
       }
       if(caller.contains("pass")){
            editPass = false;
            return;
       }
    }

     public void updateUser(){
        try {
            //remover
            if(user.getId() == 3 && currentUserLogin.equalsIgnoreCase("demo")){
                MessagesController.addError("O usuário de demonstração não pode ser modificado");
                return;
            }
            User alreadyExistUser = userService.findByLogin(user.getUsername());
            if(alreadyExistUser != null && (!alreadyExistUser.getUsername().equals(currentUserLogin))){
                 MessagesController.addError("O login '"+user.getUsername()+ "' já existe, escolha outro");
                 user.setUsername(currentUserLogin);
                 return;
            }
            if(checkNewPass()){
                userService.atualizar(user);
                MessagesController.addInfo("Perfil atualizado com sucesso");

            }
         else{
            MessagesController.addError("As senhas não conferem");
         }
        } catch (Exception ex) {
            MessagesController.addError("Problemas ao editar usuário:"+ex.getMessage());
        }
     }

     public void addOutcomeType(){
         try {
            RequestContext context = RequestContext.getCurrentInstance();
             if(outcomeType.getDescription().trim().equals("")){
                 MessagesController.addError("Informe uma descrição");
                 context.addCallbackParam("failled", true);
                 return;
             }
             List<OutcomeType> userTypes = user.getUserOutcomeTypes();
             if (!isDuplicateOutcome(outcomeType)) {
                 userTypes.add(outcomeType);
                 financeTypeService.createOutcomeType(outcomeType);
                 userService.atualizar(user);
                 MessagesController.addInfo("Tipo despesa incluido com sucesso");
                 context.addCallbackParam("failled", false);
                 tabController.setOutcomeFilterOptions(tabController.createOutcomeFilterOptions());
             }
             else{
                context.addCallbackParam("failled", true);
                MessagesController.addError("Tipo: "+outcomeType +" já existe em sua coleção de tipos");
             }
             outcomeType = new OutcomeType();
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao incluir tipo despesa:"+ex.getMessage());
             ex.printStackTrace();
         }
     }
     public void addIncomeType(){
         try {
            RequestContext context = RequestContext.getCurrentInstance();
             List<IncomeType> userTypes = user.getUserIncomeTypes();
             if(incomeType.getDescription().trim().equals("")){
                 MessagesController.addError("Informe uma descrição");
                 context.addCallbackParam("failled", true);
                 return;
             }
             if (!isDuplicateIncome(incomeType)) {
                 userTypes.add(incomeType);
                 financeTypeService.createIncomeType(incomeType);
                 userService.atualizar(user);
                 MessagesController.addInfo("Tipo receita incluido com sucesso");
                 context.addCallbackParam("failled", false);
                 tabController.setIncomeFilterOptions(tabController.createIncomeFilterOptions());
             }
             else{
                context.addCallbackParam("failled", true);
                MessagesController.addError("Tipo: "+incomeType +" já existe em sua coleção de tipos");
             }
             incomeType = new IncomeType();
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao incluir tipo despesa:"+ex.getMessage());
             ex.printStackTrace();
         }
     }
     public void removeOutcomeType(){
         try {

             user.getUserOutcomeTypes().remove(user.getUserOutcomeTypes().indexOf(outcomeType));
             userService.atualizar(user);
//             financeTypeService.removeIncomeType(incomeType);não posso remover do banco pois o tipo pode estar relacionado a uma despesa
             MessagesController.addInfo("Tipo despesa removido com sucesso");
             outcomeType = new OutcomeType();
             tabController.setOutcomeFilterOptions(tabController.createOutcomeFilterOptions());
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao remover tipo despesa:"+ex.getMessage());
             ex.printStackTrace();
         }
     }
     public void removeIncomeType(){
          try {
             user.getUserIncomeTypes().remove(user.getUserIncomeTypes().indexOf(incomeType));
             userService.atualizar(user);
             MessagesController.addInfo("Tipo despesa removido com sucesso");
             incomeType = new IncomeType();
             tabController.setIncomeFilterOptions(tabController.createIncomeFilterOptions());
         } catch (Exception ex) {
             MessagesController.addError("Problemas ao remover tipo receita:"+ex.getMessage());
             ex.printStackTrace();
         }
     }

     public void prepareEditOutcome(OutcomeType outcomeType){
         selectedOutcomeType = new OutcomeType();
         selectedOutcomeType.setDescription(outcomeType.getDescription());
         this.outcomeType = outcomeType;
     }
     public void prepareEditIncome(IncomeType incomeType){
         selectedIncomeType = new IncomeType();
         selectedIncomeType.setDescription(incomeType.getDescription());
         this.incomeType = incomeType;
     }

     public void updateOutcomeType(){
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if(!isDuplicateOutcome(selectedOutcomeType)){
                outcomeType.setDescription(selectedOutcomeType.getDescription());
                financeTypeService.updateOutcomeType(outcomeType);
                MessagesController.addInfo("Tipo despesa modificado com sucesso");
                outcomeType = new OutcomeType();
                context.addCallbackParam("error", false);
            }else{
                MessagesController.addError("Tipo: "+selectedOutcomeType +" já existe em sua coleção de tipos");
                context.addCallbackParam("error", true);
            }
        } catch (Exception ex) {
           context.addCallbackParam("error", true);
           MessagesController.addError("Problemas ao editar tipo de despesa:"+ex.getMessage());
           ex.printStackTrace();

        }
     }
     public void updateIncomeType(){
         RequestContext context = RequestContext.getCurrentInstance();
        try {
            if(!isDuplicateIncome(selectedIncomeType)){
                incomeType.setDescription(selectedIncomeType.getDescription());
                financeTypeService.updateIncomeType(incomeType);
                MessagesController.addInfo("Tipo receita modificado com sucesso");
                incomeType = new IncomeType();
                context.addCallbackParam("error", false);
            }else{
                MessagesController.addError("Tipo: "+selectedIncomeType +" já existe em sua coleção de tipos");
                context.addCallbackParam("error", true);
            }
        } catch (Exception ex) {
           context.addCallbackParam("error", true);
           MessagesController.addError("Problemas ao editar tipo de receita:"+ex.getMessage());
           ex.printStackTrace();
        }
     }

      public boolean checkNewPass(){
             if(repeatPass == null || user.getPassword() == null || repeatPass.trim().equals("") || (!repeatPass.equals(user.getPassword()))) {
                return false;
           }
             return true;
     }

    public void cancelRemove(){
             incomeType = new IncomeType();
             outcomeType = new OutcomeType();
        }


    private boolean isDuplicateIncome(IncomeType incomeType){
          Collections.sort(incomeTypes);
          return !(Collections.binarySearch(incomeTypes, incomeType) < 0);
    }
    private boolean isDuplicateOutcome(OutcomeType outcomeType){
          Collections.sort(outcomeTypes);
          return !(Collections.binarySearch(outcomeTypes, outcomeType) < 0);
    }

    public String changeUserTheme(){
        Preference p = user.findPreferenceByKey("theme");
        if(p == null){
            try {
                p = new Preference();
                p.setValue(userTheme);
                p.setKey_("theme");
                user.getPreferences().add(p);
                userService.atualizar(user);
                themeservice.setPreferedTheme(p);
                return "/pages/configuration/configuration.faces?faces-redirect=true";
            } catch (Exception ex) {
                 MessagesController.addError("Problema ao modificar tema",ex.getMessage());
                 ex.printStackTrace();
            }
        }
     else{
            try {
                p.setValue(userTheme);
                preferenceService.update(p);
//                userService.atualizar(user);
                themeservice.setPreferedTheme(p);
                return "/pages/configuration/configuration.faces?faces-redirect=true";
            } catch (Exception ex) {
                MessagesController.addError("Problema ao modificar tema",ex.getMessage());
                ex.printStackTrace();
            }
     }

        return null;
    }

    public void editnameListener(){
        editName = true;
    }
    public void editPassListener(){
        editPass = true;
    }
    public void editFullnameListener(){
        editFullName = true;
    }

     public void editThemeListener(){
        editTheme = true;
    }


}
