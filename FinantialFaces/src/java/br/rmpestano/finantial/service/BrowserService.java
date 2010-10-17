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
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rmpestano
 */
@Named(value="browserService")
@SessionScoped
public class BrowserService implements Serializable{

    private String currentBrowser="";
    private boolean checkCompatibility = true;
    private final String FIREFOX = "firefox";
    private final String CHROME = "chrome";
    private final String SAFARI = "safari";

    public BrowserService() {
        browserCompatibility();
    }

     /**
     * se o browser for incompatível mostrar modal
     * @param ev
     */



     public void browserInfo(){
         RequestContext context = RequestContext.getCurrentInstance();
         context.addCallbackParam("compatible", browserCompatibility());
    }

    public String getCurrentBrowser() {
        return currentBrowser;
    }

    public void setCurrentBrowser(String currentBrowser) {
        this.currentBrowser = currentBrowser;
    }

    public String getFIREFOX() {
        return FIREFOX;
    }

    public String getCHROME() {
        return CHROME;
    }

    public String getSAFARI() {
        return SAFARI;
    }




/**
 *
 * @return -1 = not supported
 *          0 = not tested
 *          1 = compatible
 *
 */
     private byte browserCompatibility() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        Map requestHeaders = facescontext.getExternalContext().getRequestHeaderMap();
        String agent = (String) requestHeaders.get("User-Agent");
         System.out.println("AGENT:"+agent);
        checkCompatibility = false;
        if(agent.toLowerCase().contains("chrome")){//chrome
            currentBrowser = CHROME;
           return 1;
        }
        if(agent.toLowerCase().contains("opera")){//opera
            return 1;
        }
        if(agent.toLowerCase().contains("applewebkit")){//safari
            return 1;
        }
        if(agent.toLowerCase().contains("firefox")){ //firefox 3.6 >
            currentBrowser = FIREFOX;
             String version = agent.toLowerCase().substring(agent.indexOf("Firefox/") + 8);
             String dgt1 = version.substring(0, 1);
                 if(Integer.parseInt(dgt1) >=4){
                    return 1;
                }
                else if(Integer.parseInt(dgt1) >= 3) {
                    String dgt2 = version.substring(2, 3);
                    if (Integer.parseInt(dgt2) >= 5) { //Firefox 3.5 ou maior suportado
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


    /**
     * This function makes it possible that web browser don't cache the page and collaborates to avoid the problematic Back button to mess up the flow of the site.
     */
    public void noCache() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Cache-Control", "no-cache,no-store, must-revalidate, max-age=0");
        response.addDateHeader("Expires", -1);
        response.addHeader("Expires", "Mon, 24 May 1984 10:00:00 GMT");
    }

}
