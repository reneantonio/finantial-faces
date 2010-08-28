/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util;

/**
 * This class will manage the properties which i want to customize to each Theme.
 * for example: imagine you want to display a different loading icon in each theme so instead testing the name of the theme in the view
 * just use the LOADING_ICON theme property. (see the ajax status in the main_template
 * @author rpestano
 */

public class ThemeExtraProperties {

    private String BUTTON_BACKGROUND = "";
    private String MOUSEOVER_EVENT= "";
    private String MOUSEOUT_EVENT = "";
    private String COLOR = "";
    private String FINANCE_FOOTER_COLOR = "";
    private String LOADING_ICON = "";

    public ThemeExtraProperties() {
        this.clearProperties();
    }


    public String getBUTTON_BACKGROUND() {
        return BUTTON_BACKGROUND;
    }

    public String getMOUSEOUT_EVENT() {
        return MOUSEOUT_EVENT;
    }

    public String getMOUSEOVER_EVENT() {
        return MOUSEOVER_EVENT;
    }

    public void setBUTTON_BACKGROUND(String BUTTON_BACKGROUND) {
        this.BUTTON_BACKGROUND = BUTTON_BACKGROUND;
    }

    public void setMOUSEOUT_EVENT(String MOUSEOUT_EVENT) {
        this.MOUSEOUT_EVENT = MOUSEOUT_EVENT;
    }

    public void setMOUSEOVER_EVENT(String MOUSEOVER_EVENT) {
        this.MOUSEOVER_EVENT = MOUSEOVER_EVENT;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getFINANCE_FOOTER_COLOR() {
        return FINANCE_FOOTER_COLOR;
    }

    public void setFINANCE_FOOTER_COLOR(String FINANCE_FOOTER_COLOR) {
        this.FINANCE_FOOTER_COLOR = FINANCE_FOOTER_COLOR;
    }

    public String getLOADING_ICON() {
        return LOADING_ICON;
    }

    public void setLOADING_ICON(String LOADING_ICON) {
        this.LOADING_ICON = LOADING_ICON;
    }



    private void clearProperties(){
        this.BUTTON_BACKGROUND = "";
        this.MOUSEOUT_EVENT = "";
        this.MOUSEOVER_EVENT = "";
        this.COLOR = "";
        this.FINANCE_FOOTER_COLOR = "";
        this.LOADING_ICON = "";
    }

}
