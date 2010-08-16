/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author rmpestano
 */
@SessionScoped
@Named(value="themeService")
public class ThemeService implements Serializable {
    private String theme = "ui-darkness";


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }




}
