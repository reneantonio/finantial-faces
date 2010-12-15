/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util;

import java.io.Serializable;

/**
 * This class will manage the properties which i want to customize to each Theme.
 * for example: imagine you want to display a different loading icon for each theme so instead testing the name of the theme in the view
 * just use the LOADING_ICON theme property. (see the ajax status in the main_template
 * @author rpestano
 */

public class ThemeExtraProperties implements Serializable{

    private String BUTTON_BACKGROUND = "";
    private String MOUSEOVER_EVENT= "";
    private String MOUSEOUT_EVENT = "";
    private String COLOR = "";
    private String FINANCE_FOOTER_COLOR = "";
    private String LOADING_ICON = "";
    private String FORWARD_ARROW = "";
    private String BACKWARD_ARROW = "";
    private String EVEN_ROW = "";// datatable conditional coloring

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

    public String getBACKWARD_ARROW() {
        return BACKWARD_ARROW;
    }

    public void setBACKWARD_ARROW(String BACKWARD_ARROW) {
        this.BACKWARD_ARROW = BACKWARD_ARROW;
    }

    public String getFORWARD_ARROW() {
        return FORWARD_ARROW;
    }

    public void setFORWARD_ARROW(String FORWARD_ARROW) {
        this.FORWARD_ARROW = FORWARD_ARROW;
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

    public String getEVEN_ROW() {
        return EVEN_ROW;
    }

    public void setEVEN_ROW(String EVEN_ROW) {
        this.EVEN_ROW = EVEN_ROW;
    }





    private void clearProperties(){
        this.BUTTON_BACKGROUND = "";
        this.MOUSEOUT_EVENT = "";
        this.MOUSEOVER_EVENT = "";
        this.COLOR = "";
        this.FINANCE_FOOTER_COLOR = "";
        this.LOADING_ICON = "";
        this.EVEN_ROW = "";
    }

}
