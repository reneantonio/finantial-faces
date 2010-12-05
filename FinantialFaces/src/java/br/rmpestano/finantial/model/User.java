/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author rmpestano
 */
@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(name="User.findAll",query="SELECT u FROM User u"),@NamedQuery(name="User.findByLogin",query="SELECT u FROM User u WHERE u.username = :username")})
public class User extends BaseEntity {

    @NotEmpty(message="Informe um login")
    @Length(min=3,message="Tamanho mínimo de login = 3")
    private String username;
    @NotEmpty(message="Informe uma senha")
    @Length(min=3,message="Tamanho mínimo de senha = 3")
    private String password;
    @NotEmpty(message="Informe um nome")
    @Length(min=3,message="Tamanho mínimo de nome = 3")
    private String fullname;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<IncomeType> userIncomeTypes;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<OutcomeType> userOutcomeTypes;
    @OneToMany
    private List<Preference> preferences;

    @Transient
    private String userTheme;

    //DESNECESSÁRIO, PEGA COM UM SIMPLES SQL
//    @OneToMany(mappedBy = "user")
//    private List<Income> receitas;
//    @OneToMany(mappedBy = "user")
//    private List<Outcome> despesas;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<IncomeType> getUserIncomeTypes() {
        return userIncomeTypes;
    }

    public void setUserIncomeTypes(List<IncomeType> userIncomeTypes) {
        this.userIncomeTypes = userIncomeTypes;
    }

    public List<OutcomeType> getUserOutcomeTypes() {
        return userOutcomeTypes;
    }

    public void setUserOutcomeTypes(List<OutcomeType> userOutcomeTypes) {
        this.userOutcomeTypes = userOutcomeTypes;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }

    public String getUserTheme(){
        Preference p = findPreferenceByKey("theme");
        if(p!=null){
            return p.value;
        }
        return "Ui-Darkness";
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final User other = (User) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 37 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return username;
    }

    public Preference findPreferenceByKey(String key){
        for (Preference preference : this.preferences) {
            if(preference.key_!= null && preference.key_.equalsIgnoreCase(key)){
                return preference;
            }
        }
        return null;
    }
}
