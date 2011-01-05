/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.UserService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rmpestano
 */
@ManagedBean(name = "adminBean")
@ViewScoped
public class AdminController {

    private UserService userService;
    private List<User> users;
    private User selectedUser;
    private User newUser;

    public AdminController() {
        userService = (UserService) BeanManagerController.getBeanByName("userService");
        users = userService.findAll();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }


    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void removeUser(){
        try{
         userService.removeUser(selectedUser);
         userService.incluir(newUser);
         MessagesController.addInfo("Usuário removido com sucesso!");
        }
        catch(Exception ex){
            MessagesController.addError("Problemas ao remover usuário:"+ex.getMessage());
            ex.printStackTrace();
        }

    }

     public void cancelRemove(){
         selectedUser = new User();
    }


      public void addUser(){
            RequestContext context = RequestContext.getCurrentInstance();
            User u = userService.findByLogin(newUser.getUsername());
            if(u != null){
                MessagesController.addError("Usuário já cadastrado");
                context.addCallbackParam("failled", true);
                return;
            }
            try {
                newUser.setUserIncomeTypes(new ArrayList<IncomeType>());
                newUser.setUserOutcomeTypes(new ArrayList<OutcomeType>());
                userService.incluir(newUser);
                users = userService.findAll();
                MessagesController.addInfo("Usuário incluido com sucesso!");
            } catch (Exception ex) {
                context.addCallbackParam("failled", true);
                MessagesController.addError("Problemas ao incluir usuário:"+ex.getMessage());
                ex.printStackTrace();
            }

    }

      public void prepareAddUser(){
          newUser = new User();
      }

}
