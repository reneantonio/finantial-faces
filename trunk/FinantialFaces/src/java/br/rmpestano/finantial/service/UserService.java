/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.service;

import br.rmpestano.finantial.model.User;
import br.rmpestano.finantial.service.generic.CrudService;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author rmpestano
 */
@Named("userService")
@RequestScoped
public class UserService {
        private static final String FIND_BY_LOGIN = "User.findByLogin";
        private static final String FIND_ALL = "User.findAll";
        @Inject CrudService<User> crudService;


        public User findByLogin(String username){
            HashMap hash = new HashMap();
            hash.put("username", username);
            List<User> resultList = crudService.findWithNamedQuery(FIND_BY_LOGIN, hash);
            if(resultList != null){
                User u = resultList.get(0);
                return u;
            }
            else {
                return null;
            }
        }
        public User findById(String id){
            User u = crudService.findById(Long.parseLong(id),User.class);
            if(u != null){
                return u;
            }
            else {
                return null;
            }
        }
        public List<User> findAll(){
           return  crudService.findWithNamedQuery(FIND_ALL);
        }

        public void incluir(User usuario) throws Exception{

            crudService.create(usuario);

        }
        public void atualizar(User usuario) throws Exception{

            crudService.update(usuario);

        }
}
