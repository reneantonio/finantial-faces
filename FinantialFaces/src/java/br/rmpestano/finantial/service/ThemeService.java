/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

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
public class ThemeService {
    private String theme = "bluesky";
    private List<String> themes;

     public ThemeService() {
         themes = new ArrayList<String>();
         themes.add("bluesky");
         themes.add("casablanca");
         themes.add("hot-sneaks");
         themes.add("overcast");
         themes.add("ui-darkness");
         themes.add("vader");
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }



    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }




}
