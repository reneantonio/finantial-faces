/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model;

/**
 *
 * @author rmpestano
 */
public class Report {
    private String Title;
    private String Description;
    private Integer number;


    public Report(String Title, String Description, Integer number) {
        this.Title = Title;
        this.Description = Description;
        this.number = number;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }



}
