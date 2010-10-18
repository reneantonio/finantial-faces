/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util.converter;

import br.rmpestano.finantial.model.IncomeType;
import br.rmpestano.finantial.model.User;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rmpestano
 */
@FacesConverter(value="incomeConveter")
public class IncomeTypeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        List<IncomeType> userOutcomeTypes = u.getUserIncomeTypes();
        for (IncomeType incomeType : userOutcomeTypes) {
            if (incomeType.getDescription().equals(value.toString())) { //se usuário tiver dois tipos com o mesmo nome dai fuuuu(teria que pegar pelo id mas tá dando pau no equalsdo OutcomeType)
                return incomeType;
            }
        }
        return null;
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}
