/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util;

import br.rmpestano.finantial.model.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author rmpestano
 */

public class UserLoggedInListener implements PhaseListener{


     public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public void afterPhase(PhaseEvent event) {
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().setRequestCharacterEncoding("UTF-8");
//            FacesContext.getCurrentInstance().getExternalContext().setResponseCharacterEncoding("UTF-8");
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(UserLoggedInListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

        FacesContext fc = event.getFacesContext();

//        try {
//            fc.getExternalContext().setRequestCharacterEncoding("UTF-8");
//            fc.getExternalContext().setResponseContentType("text/html; charset=UTF-8");
//            fc.getExternalContext().setResponseCharacterEncoding("UTF-8");
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        }
        // Check to see if they are on the login page.
        boolean loginPage = fc.getViewRoot().getViewId().lastIndexOf("login.xhtml") > -1 ? true : false;
        if ((!loginPage && !loggedIn())) {
            final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
            }
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    private boolean loggedIn() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
         if(user != null){
            return true;
        } else{
            return false;

        }
    }


}

