package com.mycompany.ejb.repository;

import com.mycompany.ejb.UserBean;
import com.mycompany.ejb.entity.User;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless(name = "UserBean")
@Remote(UserBean.class)
@RolesAllowed({"app-users"})
public class UserRepository implements UserBean {

    @PersistenceContext(unitName = "default")
    private EntityManager manager;

    public User findById(Long id) {
        return manager.find(User.class, id);
    }

    public User persist(User user) {
        return manager.merge(user);
    }

    public User findByUsername(String username) {

        TypedQuery<User> query = manager.createQuery("FROM " + User.class.getName() + " e WHERE e.username = :username", User.class);

        query.setParameter("username", username);

        User user = null;

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }

        return user;
    }
}
