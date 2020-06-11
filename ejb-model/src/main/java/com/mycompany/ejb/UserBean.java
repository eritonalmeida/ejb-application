package com.mycompany.ejb;

import com.mycompany.ejb.entity.User;

public interface UserBean {

    public User findByUsername(String username);

    public User findById(Long id);

    public User persist(User user);
}
