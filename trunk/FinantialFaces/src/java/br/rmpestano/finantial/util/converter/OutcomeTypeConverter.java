/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util.converter;

import br.rmpestano.finantial.model.OutcomeType;
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
@FacesConverter(value="outcomeConveter")
public class OutcomeTypeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        List<OutcomeType> userOutcomeTypes = u.getUserOutcomeTypes();
         for (OutcomeType outcomeType : userOutcomeTypes) {
            if(outcomeType.getDescription().equals(value.toString())){ //se usuário tiver dois tipos com o mesmo nome dai fuuuu(teria que pegar pelo id mas tá dando pau no equalsdo OutcomeType)
                return outcomeType;
            }
        }
         return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}
