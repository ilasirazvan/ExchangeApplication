package com.exchange.dao;

import com.exchange.entities.User;
import com.exchange.entities.UserSecurity;
import com.exchange.exception.UserExistingException;
import com.exchange.exception.UserNotFoundException;
import com.exchange.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.util.List;

public class UserDAO {

    private String id;
    private String email;
    private String firstname;
    private String lastname;

    private Connection connection = null;

    @PersistenceContext
    private EntityManager entityManager;

    final static Logger logger = Logger.getLogger(UserDAO.class);

    public boolean createUser(UserSecurity userSecurity) throws UserExistingException {
        logger.debug("createUser: " + userSecurity.getEmail());
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(userSecurity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return true;
    }

    public String getUserIdByEmail(String email) {
        logger.debug("getUserIdByEmail: " + email);
        List<String> id = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT u.id FROM UserSecurity u WHERE u.email=:email");
            query.setParameter("email", email);
            id = query.list();
            if (id.isEmpty()) {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id.get(0);
    }

    public User getUser(String id) throws UserNotFoundException {
        logger.debug("getUser: " + id);
        List<UserSecurity> user = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT new User (u.id, u.email, u.firstname, u.lastname) FROM com.exchange.entities.User u WHERE u.id=:id");
            query.setParameter("id", id);
            user = query.list();
            if (user.isEmpty()) {
                throw new UserNotFoundException(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.get(0);
    }

    public UserSecurity getUserAuthentication(String id) throws UserNotFoundException {
        logger.debug("getUserAuthentication: " + id);

        List<UserSecurity> userSecurity = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT u FROM com.exchange.entities.UserSecurity u WHERE u.id=:id");
            query.setParameter("id", id);
            userSecurity = query.list();
            if (userSecurity.isEmpty()) {
                throw new UserNotFoundException(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userSecurity.get(0);
    }

    public boolean setUserAuthentication(UserSecurity user) throws UserNotFoundException {
        logger.debug("setUserAuthentication: " + user.getId());
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE UserSecurity u SET u.token=:token WHERE u.id=:id");
            query.setParameter("id",  user.getId());
            query.setParameter("token", user.getToken());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}