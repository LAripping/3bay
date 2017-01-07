/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Leo
 */
public class MessageDB {

    public String insertMessage(Message message) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(message);
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

    public String hideFromInbox(Integer ID, Boolean choice) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("UPDATE Message m SET m.hiddenFromInbox = :C WHERE m.id= :ID");
        q.setParameter("C", choice);
        q.setParameter("ID", ID);
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
    
    
        public String hideFromOutbox(Integer ID, Boolean choice) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("UPDATE Message m SET m.hiddenFromOutbox = :C WHERE m.id= :ID");
        q.setParameter("C", choice);
        q.setParameter("ID", ID);
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

    public String markMessageRead(Integer ID, Boolean choice) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Update Message m set m.isRead = :C where m.id= :ID");
        q.setParameter("C", choice);
        q.setParameter("ID", ID);
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

    public List getMessages(String username, Integer choice) {
        List messages = null;
        Query q = null;
                
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        if (choice == 2) {
            q = em.createQuery("SELECT m FROM Message m WHERE m.recipient.username= :rU");
            q.setParameter("rU", username);
        } else if(choice == 1){
            q = em.createQuery("SELECT m FROM Message m WHERE m.sender.username= :sU");
            q.setParameter("sU", username);
        }
        
        messages = q.getResultList();
        tx.commit();
        em.close();
        return messages;
    }

    public Message findById(Integer id) {
        Message ret = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("SELECT m FROM Message m WHERE m.id= :id");
        q.setParameter("id", id);

        ret = (Message) q.getSingleResult();
        tx.commit();
        em.close();
        return ret;
    }

}
