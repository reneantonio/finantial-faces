/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author rmpestano
 * FROM: http://primefaces.prime.com.tr/forum/viewtopic.php?f=3&t=2631&p=12527&hilit=session+timeout#p12527
 */
public class ViewExpiredExceptionExceptionHandlerFactory extends ExceptionHandlerFactory{
    private ExceptionHandlerFactory parent;
    public ViewExpiredExceptionExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result = parent.getExceptionHandler();
        result = new ViewExpiredExceptionExceptionHandler(result);
        return result;
    }

}
