/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rmpestano
 */
@Named(value="browserService")
@SessionScoped
public class BrowserService implements Serializable{

    private boolean checkCompatibility = true;
     /**
     * se o browser for incompatível mostrar modal
     * @param ev
     */
     public void browserInfo(ActionEvent ev){
         RequestContext context = RequestContext.getCurrentInstance();
         context.addCallbackParam("compatible", browserCompatibility());
    }

/**
 *
 * @return -1 = no compatibility
 *          0 = not tested
 *          1 = compatinle
 *
 */
     private byte browserCompatibility() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        Map requestHeaders = facescontext.getExternalContext().getRequestHeaderMap();
        String agent = (String) requestHeaders.get("User-Agent");
         System.out.println("AGENT:"+agent);
        checkCompatibility = false;
        if(agent.toLowerCase().contains("chrome")){//chrome
           return 1;
        }
        if(agent.toLowerCase().contains("opera")){//opera
            return 1;
        }
        if(agent.toLowerCase().contains("applewebkit")){//safari
            return 1;
        }
        if(agent.toLowerCase().contains("firefox")){ //firefox 3.6 >
             String version = agent.toLowerCase().substring(agent.indexOf("Firefox/") + 8, agent.indexOf("Firefox/") + 14);
             String dgt1 = version.substring(0, 1);
                 if(Integer.parseInt(dgt1) >=4){
                    return 1;
                }
                else if(Integer.parseInt(dgt1) >= 3) {
                    String dgt2 = version.substring(2, 3);
                    if (Integer.parseInt(dgt2) >= 6) { //Firefox 3.6 ou maior suportado
                        return 1;
                    } else {
                        return 0;//firefox 3.0 -- 3.5, algumas incompatibilidades(deixa not tested)
                    }
                } else{
                  checkCompatibility = true;//pra continuar chamando
                 return -1;//firefox < 3(netscape & cia)  not supported
                }
        }
        if(agent.toLowerCase().contains("msie")){//IE
            checkCompatibility = true;//pra continuar chamando
            return -1;//not supported
        }


        return 0;//not tested

    }

    public boolean isCheckCompatibility() {
        return checkCompatibility;
    }

    public void setCheckCompatibility(boolean checkCompatibility) {
        this.checkCompatibility = checkCompatibility;
    }



}
