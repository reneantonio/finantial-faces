/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model;

/**
 *
 * @author rmpestano
 */
public class TipoValorReport {
    String type;
    Double valor;


    public TipoValorReport(String type, Double valor) {
        this.type = type;
        this.valor = valor;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }



}
