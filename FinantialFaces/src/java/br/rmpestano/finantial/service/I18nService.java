/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author rmpestano
 */
@Named(value="i18nService")
@SessionScoped
public class I18nService implements Serializable{
    private String PT_PROPERTIES = "pt";
    private String EN_PROPERTIES = "en";
    private String currentResourceBundle = PT_PROPERTIES;
    private String currentLanguage = "brasil";
    private  String LANGUAGE_BRAZILIAN = "brasil";
    private  String LANGUAGE_AMERICAN = "usa";

    public String getCurrentResourceBundle() {
        return currentResourceBundle;
    }

    public void setCurrentResourceBundle(String currentResourceBundle) {
        this.currentResourceBundle = currentResourceBundle;
    }

    public String getEN_PROPERTIES() {
        return EN_PROPERTIES;
    }

    public String getPT_PROPERTIES() {
        return PT_PROPERTIES;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public String getLANGUAGE_AMERICAN() {
        return LANGUAGE_AMERICAN;
    }

    public String getLANGUAGE_BRAZILIAN() {
        return LANGUAGE_BRAZILIAN;
    }


    public String changeLanguage(){
        if(currentResourceBundle.equals(this.EN_PROPERTIES)){
            currentResourceBundle = this.PT_PROPERTIES;
            currentLanguage = LANGUAGE_BRAZILIAN;
        }
        else{
            currentResourceBundle = this.EN_PROPERTIES;
            currentLanguage = LANGUAGE_AMERICAN;
        }


        if (FacesContext.getCurrentInstance().getViewRoot().getViewId().trim().endsWith("login.xhtml")) {
            return "login.faces";
        } else {
            return "/pages/home.faces";
        }

        }

}
