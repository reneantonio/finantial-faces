/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model;

import br.rmpestano.finantial.util.PersistenceManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  entidade que representa as receitas (entradas)
 * @author rmpestano
 */
@Entity
@Table(name="income")
public class Income extends BaseEntity implements Finance {
    @ManyToOne
    private User user;
    @ManyToOne
    private FinantialMonth finantialMonth;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @OneToOne
    private IncomeType type;
    @NotNull(message="Forneça um valor para a receita.")
    @Min(value=0, message="Valores negativos não são aceitos.")
    private Double value;
    @Size(min=0,max=40,message="Tamanho máximo da descrição:40 caracteres.")
    private String description;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public IncomeType getType() {
        return type;
    }

    public void setType(IncomeType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FinantialMonth getFinantialMonth() {
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

     public static List<Income> findMonthIncomesByUser(Long userId, Date month) {
        EntityManager em = PersistenceManager.createEntityManager();
        String sql = "select * from income o where o.USER_ID = '" + userId + "' and FINANTIALMONTH_DATE = '" + sdf.format(month) + "'";
        return em.createNativeQuery(sql, Income.class).getResultList();
    }

    @Override
    public String toString() {
        return "br.rmpestano.finantial.model.Income[id=" + id + "]";
    }

}
