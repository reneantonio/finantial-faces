/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.controller;

import br.rmpestano.finantial.model.Finance;
import br.rmpestano.finantial.model.Income;
import br.rmpestano.finantial.model.Outcome;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rmpestano
 */
@ViewScoped
@ManagedBean(name="addBean")
public class AddFinancaController {
    private final String INCOME = "income";
    private final String OUTCOME = "outcome";
    private List<String> tiposFinanca = new ArrayList<String>(){{add(INCOME);add(OUTCOME);}};

    private Finance finance;
    private Outcome despesa;
    private Income  receita;
    private String tipoCorrete = OUTCOME;
    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
        
    }

    public List<String> getTiposFinanca() {
        return tiposFinanca;
    }

    public void setTiposFinanca(List<String> tiposFinanca) {
        this.tiposFinanca = tiposFinanca;
    }

    public String getTipoCorrete() {
        return tipoCorrete;
    }

    public void setTipoCorrete(String tipoCorrete) {
        this.tipoCorrete = tipoCorrete;
    }

    public Outcome getDespesa() {
        return despesa;
    }

    public void setDespesa(Outcome despesa) {
        this.despesa = despesa;
    }

    public Income getReceita() {
        return receita;
    }

    public void setReceita(Income receita) {
        this.receita = receita;
    }


    public String getINCOME() {
        return INCOME;
    }

    public String getOUTCOME() {
        return OUTCOME;
    }


    
    
}
