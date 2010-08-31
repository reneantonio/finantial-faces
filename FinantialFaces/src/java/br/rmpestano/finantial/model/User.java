/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rmpestano.finantial.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rmpestano
 */
@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(name="User.findAll",query="SELECT u FROM User u"),@NamedQuery(name="User.findByLogin",query="SELECT u FROM User u WHERE u.username = :username")})
public class User extends BaseEntity {

    private String username;
    private String password;
    private String fullname;

    @OneToMany(mappedBy = "user")
    private List<Income> receitas;
    @OneToMany(mappedBy = "user")
    private List<Outcome> despesas;


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


    @Override
    public String toString() {
        return fullname;
    }
}
