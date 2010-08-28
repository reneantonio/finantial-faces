/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.UserService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author rmpestano
 */

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginController implements Serializable{
    UserService userService;
    User user = new User();

    @PostConstruct
    public void getUserService(){
        userService = (UserService) BeanManagerController.getBeanByName("userService");
        System.out.println("Injetou:"+userService);

    }
    public String doLogin(){


        User user = null;
        try {
            user = userService.findByLogin(this.user.getUsername());

        } catch (Exception e) {
            MessagesController.addError( "problemas ao identificar usuário, por favor tente mais tarde",e.getMessage());
            e.printStackTrace();
            return null;
        }
        if(user == null){

            MessagesController.addError("Usuario não encontrado", "Usuário:<b>"+this.user.getUsername()+"</b> não encontrado em nossa base de dados");
            return null;
        }
        else {
            if(user.getPassword().equalsIgnoreCase(this.user.getPassword())){//login feito com sucesso, coloca usuário na sessão
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

                System.out.println("Login:"+user.getUsername());
                return "/pages/home.faces?faces-redirect=true";
//                return "/pages/home2.faces?faces-redirect=true";
            }
            else{
                MessagesController.addError("senha incorreta ", "senha incorreta, verifique o CAPSLOCK");
                return null;
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
