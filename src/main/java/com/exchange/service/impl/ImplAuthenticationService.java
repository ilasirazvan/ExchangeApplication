package com.exchange.service.impl;

import com.exchange.entities.User;
import com.exchange.service.interfaces.IAuthenticationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ImplAuthenticationService implements IAuthenticationService {

    public void authenticate(String login, String password) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-pu-name");
        EntityManager em = entityManagerFactory.createEntityManager();

        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.password = :password AND u.login = :login", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .setMaxResults(1)
                .getResultList();
        if (users.isEmpty())
            throw new SecurityException("Invalid user/password");
    }
}
