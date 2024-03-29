/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.Preference;
import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.ThemeService;
import br.rmpestano.finantial.service.UserService;
import br.rmpestano.finantial.util.BeanManagerController;
import br.rmpestano.finantial.util.MessagesController;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rmpestano
 */

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginController implements Serializable{
    private UserService userService;
    private User user;
    boolean admin = false;
    @ManagedProperty(value="#{themeService}")
    private ThemeService themeService;

    public LoginController() {
        user = new User();
    }

    @PostConstruct
    public void getUserService(){
        userService = (UserService) BeanManagerController.getBeanByName("userService");
    }

    public void loginBlur(){
        User user = userService.findByLogin(this.user.getUsername());
        if(user == null){
            MessagesController.addError("Login "+this.user.getUsername() + " não existe em nossa base de dados");
        }
    }

    public String doLogin(){
        User user = null;
        try {
            user = userService.findByLogin(this.user.getUsername());

        } catch (Exception e) {
            MessagesController.addError("problemas ao identificar usuário, por favor tente mais tarde",e.getMessage());
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

                Preference p = user.findPreferenceByKey("theme");
                if(p!=null){
                    themeService.setPreferedTheme(p);
                }
                return "/pages/home.faces?faces-redirect=true";
            }
            else{
                MessagesController.addError("senha incorreta ", "senha incorreta, verifique o CAPSLOCK");
                return null;
            }
        }
    }

    public boolean isAdmin() {
        User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if( u!= null && u.getId() == 1){
            return true;
        }
        return false;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ThemeService getThemeService() {
        return themeService;
    }

    public void setThemeService(ThemeService themeService) {
        this.themeService = themeService;
    }
    
     public void doLogout(ActionEvent event) throws IOException{
	     final HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	     request.getSession(false).invalidate();
             FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getContextName());
	}

     public void fakeListener(){
     }

    
}
