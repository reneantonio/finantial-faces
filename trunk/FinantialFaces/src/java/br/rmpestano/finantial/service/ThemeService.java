/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.Preference;
import br.rmpestano.finantial.util.ThemeExtraProperties;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author rmpestano
 */

@ManagedBean(name="themeService")
@SessionScoped
public class ThemeService implements Serializable {
    private String theme = "";
    private ThemeExtraProperties themeProperties;
    private static final String DARK_SKY = "dark-sky";
    private static final String UI_DARKNESS = "ui-darkness";
    private static final String ARISTO = "aristo";
    private static final String VADER = "vader";
    private static final String DOT_LUV = "dot-luv";
    private static final String MINT_CROC = "mint-choc";
    private static final String OVERCAST = "overcast";
    private static final String CASABLANCA = "casablanca";
    private static final String BLUESKY = "bluesky";
    private static final String CUPERTINO = "cupertino";
    private static final String PEPPER_GRINDER = "pepper-grinder";

    public ThemeService() {
//        darkSkyInit();
        uiDarknessInit();
    }



    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void darkSkyInit(){
        theme = "dark-sky";
        themeProperties = new ThemeExtraProperties();
//        themeProperties.setFINANCE_FOOTER_COLOR("#0D31BA");
        themeProperties.setLOADING_ICON("ajax-loader-blue.gif");
//        themeProperties.setBUTTON_BACKGROUND("#AAADB8");
//        themeProperties.setMOUSEOUT_EVENT("this.style.background='#AAADB8'");
//        themeProperties.setMOUSEOVER_EVENT("this.style.background='#333333'");
        themeProperties.setCOLOR("black");
    }
    public void uiDarknessInit(){
        theme = "ui-darkness";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setCOLOR("white");
        themeProperties.setLOADING_ICON("ajax-loader-orange.gif");
    }

     public void aristoInit(){
        theme = "aristo";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setCOLOR("gray");
        themeProperties.setLOADING_ICON("ajax-loader-red.gif");
        themeProperties.setBACKWARD_ARROW("backward_red.png");
        themeProperties.setFORWARD_ARROW("forward_red.png");
    }

    public void vaderInit(){
        theme = "vader";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setCOLOR("white");
        themeProperties.setLOADING_ICON("ajax-loader-red.gif");
    }
    public void dotLuvInit(){
        theme = "dot-luv";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setCOLOR("white");
    }
    public void mintChocInit(){
        theme = "mint-choc";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setCOLOR("white");
        themeProperties.setLOADING_ICON("ajax-loader-green.gif");
    }
    public void overcastInit(){
        theme = "overcast";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setLOADING_ICON("ajax-loader-red.gif");
        themeProperties.setBACKWARD_ARROW("backward_gray.png");
        themeProperties.setFORWARD_ARROW("forward_gray.png");

    }
    public void casablancaInit(){
        theme = "casablanca";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setLOADING_ICON("ajax-loader-black.gif");
        themeProperties.setBACKWARD_ARROW("backward_red.png");
        themeProperties.setFORWARD_ARROW("forward_red.png");
    }
    public void blueSkyInit(){
        theme = "bluesky";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setBACKWARD_ARROW("backward_blue.png");
        themeProperties.setFORWARD_ARROW("forward_blue.png");
    }
    public void cupertinoInit(){
        theme = "cupertino";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setBACKWARD_ARROW("backward_blue.png");
        themeProperties.setFORWARD_ARROW("forward_blue.png");
    }
    public void pepperInit(){
        theme = "pepper-grinder";
        themeProperties = new ThemeExtraProperties();
        themeProperties.setLOADING_ICON("ajax-loader-brown.gif");
    }

    public ThemeExtraProperties getThemeProperties() {
        return themeProperties;
    }

    public void setPreferedTheme(Preference p) {
        String preferedTheme = p.getValue();
        if (p.getValue() != null) {
            if (preferedTheme.equalsIgnoreCase(DARK_SKY)) {
                this.blueSkyInit();
            }
            if (preferedTheme.equalsIgnoreCase(UI_DARKNESS)) {
                this.uiDarknessInit();
            }
            if (preferedTheme.equalsIgnoreCase(ARISTO)) {
                this.aristoInit();
            }
            if (preferedTheme.equalsIgnoreCase(VADER)) {
                this.vaderInit();
            }
            if (preferedTheme.equalsIgnoreCase(DOT_LUV)) {
                this.dotLuvInit();
            }
            if (preferedTheme.equalsIgnoreCase(MINT_CROC)) {
                this.mintChocInit();
            }
            if (preferedTheme.equalsIgnoreCase(OVERCAST)) {
                this.overcastInit();
            }
            if (preferedTheme.equalsIgnoreCase(CASABLANCA)) {
                this.casablancaInit();
            }
            if (preferedTheme.equalsIgnoreCase(BLUESKY)) {
                this.blueSkyInit();
            }
            if (preferedTheme.equalsIgnoreCase(CUPERTINO)) {
                this.cupertinoInit();
            }
            if (preferedTheme.equalsIgnoreCase(PEPPER_GRINDER)) {
                this.pepperInit();
            }
        }
    }


}
