/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * entidade que representa as despesas (saidas)
 * @author rmpestano
 */
@Entity
@Table(name = "outcome")
public class Outcome extends Finance {
    @ManyToOne
    private User user;
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private FinantialMonth finantialMonth;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @OneToOne
    private OutcomeType type;
    @NotNull(message="Forneça um valor para a despesa.")
    @Min(value=0, message="Valores negativos não são aceitos.")
    private Double value;
    @Size(min=0,max=40,message="Tamanho máximo da descrição:40 caracteres.")
    private String description;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public OutcomeType getType() {
        return type;
    }

    public void setType(OutcomeType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FinantialMonth getFinantialMonth() {
        if(finantialMonth == null && date !=null){
            this.setFinantialMonth(FinantialMonth.findByDate(date));
        }
        return finantialMonth;
    }

    public void setFinantialMonth(FinantialMonth finantialMonth) {
        this.finantialMonth = finantialMonth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "br.rmpestano.finantial.model.Outcome[id=" + id + "]";
    }

   
}
