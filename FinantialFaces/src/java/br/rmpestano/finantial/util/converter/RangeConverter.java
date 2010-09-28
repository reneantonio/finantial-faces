/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util.converter;

import br.rmpestano.finantial.model.OutcomeType;
import br.rmpestano.finantial.model.report.Range;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rmpestano
 */
@FacesConverter(value="rangeConveter")
public class RangeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String rangeStr = value.trim();
        Range r;
        if(rangeStr.indexOf(">")== 0){
           r = new Range(Integer.parseInt(rangeStr.substring(2)), 0);
           r.setLastRange(true);
        }
     else{
        r = new Range(Integer.parseInt(rangeStr.substring(0, rangeStr.indexOf("--")-1)),Integer.parseInt(rangeStr.substring(rangeStr.indexOf("--")+3)));
     }
         return r;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}
