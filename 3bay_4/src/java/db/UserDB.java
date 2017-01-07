package db;

import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class UserDB {

    public List getUsers() {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username!='Admin' and u.username!='Guest' ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }

    public List getMostUsers(String username) {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username!='Admin' and u.username!='Guest' and u.username!= :username");
        q.setParameter("username", username);
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }

    public User find(String username, String password) {
        User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username = :username and u.passwords = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List users = q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1) {
            user = (User) users.get(0);
        } else {
            user = null;
        }

        return user;
    }

    public String insertUser(User user) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(user);
            tx.commit();
            retMessage = "ok";
            return retMessage;
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            retMessage = e.getMessage();
            return retMessage;
        } finally {
            em.close();
        }
    }

    public User getUser(String username) {
        User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username = :username");
        q.setParameter("username", username);
        List users = q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1) {
            user = (User) users.get(0);
        }

        return user;
    }

    public String updateUser(String SSN) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Update User u set u.isConfirmed = true where u.socialSecurityNumber = :SSN");
        q.setParameter("SSN", SSN);
        q.executeUpdate();
        try {
            tx.commit();
            retMessage = "ok";
            return retMessage;
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            retMessage = e.getMessage();
            return retMessage;
        } finally {
            em.close();
        }
    }

    public String updateRating(String name, Integer rating, Boolean updateSeller) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q = null;

        if (updateSeller==true) {
            String temp= "Update User u set u.sellerRating = :r where u.username = :n";
            q = em.createQuery(temp);
        } else {
            q = em.createQuery("Update User u set u.buyerRating = :r where u.username = :n");    
        }
        q.setParameter("r", rating);
        q.setParameter("n", name);
        q.executeUpdate();
        try {
            tx.commit();
            retMessage = "ok";
            return retMessage;
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            retMessage = e.getMessage();
            return retMessage;
        } finally {
            em.close();
        }
    }

    public String updateUsers() {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Update User u set u.isConfirmed = true where u.isConfirmed=false");
        q.executeUpdate();
        try {
            tx.commit();
            retMessage = "ok";
            return retMessage;
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            retMessage = e.getMessage();
            return retMessage;
        } finally {
            em.close();
        }
    }
}
