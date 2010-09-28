/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model.report;

import br.rmpestano.finantial.model.Finance;
import java.util.List;

/**
 *
 * @author rmpestano
 */
public class ValorIntervaloReport {
    int numFinancas;//numero de finanças naquele intervalo
    Range intervalo;

    public ValorIntervaloReport(Range intervalo) {
        this.intervalo = intervalo;
    }


    public Range getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Range intervalo) {
        this.intervalo = intervalo;
    }

    public int getNumFinancas() {
        return numFinancas;
    }

    public void setNumFinancas(int numFinancas) {
        this.numFinancas = numFinancas;
    }



}
