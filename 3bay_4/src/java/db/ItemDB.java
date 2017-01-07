package db;

import entities.Bids;
import entities.Category;
import entities.Item;
import entities.ItemPhoto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class ItemDB {

    public Item getItem(Integer ID) {
        Item item = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemId= :ID");
        q.setParameter("ID", ID);

        List items = q.getResultList();

        tx.commit();
        em.close();

        if (items != null && items.size() == 1) {
            item = (Item) items.get(0);
        }

        return item;
    }

    public List getItems(String username, Integer choice, String sub) {
        List items = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date curDate = new Date();
  
        if (choice == 1) {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username= :username AND i.isActive=false AND i.endTime>:curDate AND i.isSold=false");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 2) {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username= :username AND :curDate>i.endTime AND i.isSold=false");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 3) {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username= :username AND i.isSold=true");
            q.setParameter("username", username);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 4) {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username= :username AND i.isActive=true AND i.isSold=false AND i.endTime>:curDate");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 5) {
            Query q = em.createQuery("SELECT DISTINCT b.itemId FROM Bids b where b.bidder.username= :username");
            q.setParameter("username", username);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 11) {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username<> :username AND i.isActive=true AND i.isSold=false AND i.endTime>:curDate");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 12) {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username<> :username AND i.isActive=true AND i.isSold=false AND i.endTime>:curDate AND EXISTS(SELECT c from Category c WHERE c.itemId= i AND c.category= :sub)");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            q.setParameter("sub", sub);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 13) {
            Integer down = Integer.parseInt(sub);
            Integer up = down + 1;
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username<> :username AND i.isActive=true AND i.isSold=false AND i.endTime>:curDate AND i.seller.sellerRating>= :down AND i.seller.sellerRating< :up");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            q.setParameter("down", down);
            q.setParameter("up", up);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 14) {
            Integer dif = Integer.parseInt(sub);
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username<> :username AND i.isActive=true AND i.isSold=false AND i.endTime>= :curDate AND i.endTime< :newDate");
            q.setParameter("username", username);
            q.setParameter("curDate", curDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(curDate);
            cal.add(Calendar.DATE, dif); // add dif days
            curDate = cal.getTime();
            q.setParameter("newDate", curDate);
            items = q.getResultList();
            tx.commit();
        } else if (choice == 21) {
            items = search(username, sub);
        }

        em.close();
        return items;
    }

    public Integer findLast(String username) {
        Integer itemId = 0;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q = em.createQuery("SELECT i.itemId FROM Item i WHERE i.seller.username= :username and i.startTime = (SELECT MAX(i.startTime) from Item i where i.seller.username= :username)");
        q.setParameter("username", username);

        List items = q.getResultList();
        tx.commit();
        em.close();

        if (items != null && items.size() == 1) {
            itemId = (Integer) items.get(0);
        }
        return itemId;
    }

    public String insertItem(Item item) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(item);
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

    public String insertPhoto(ItemPhoto photo) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(photo);
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

    public String insertCategory(Category category) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(category);
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

    public String insertBid(Bids bid) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(bid);
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

    public ArrayList<String> getPhotos(Integer itemId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select ip.path from ItemPhoto ip where ip.itemId.itemId= :itemId");
        q.setParameter("itemId", itemId);
        List<String> iList = q.getResultList();
        tx.commit();
        em.close();

        ArrayList<String> photos = new ArrayList<String>(iList);
        return photos;
    }

    public String[] getCategories(Item item) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select c.category from Category c where c.itemId= :itemId");
        q.setParameter("itemId", item);
        List<String> categories = q.getResultList();
        tx.commit();
        em.close();

        String[] cat = categories.toArray(new String[0]);
        return cat;
    }

    public String activateItem(Integer ID) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Update Item i set i.isActive = true where i.itemId= :ID");
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

    public String updateItem(Integer ID, String name, String description, Integer buyNow, Integer firstBid, Date startTime, Date endTime, Integer currentBid, boolean isActive, boolean isSold) {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String temp = "Update Item i set i.name=:name,i.description=:description,i.buyNow=:buyNow,i.firstBid=:firstBid,i.startTime=:startTime,i.endTime=:endTime,i.currentBid=:currentBid,i.isActive=:isActive,i.isSold=:isSold where i.itemId= :ID";
        Query q = em.createQuery(temp);
        q.setParameter("name", name);
        q.setParameter("description", description);
        q.setParameter("buyNow", buyNow);
        q.setParameter("firstBid", firstBid);
        q.setParameter("startTime", startTime);
        q.setParameter("endTime", endTime);
        q.setParameter("currentBid", currentBid);
        q.setParameter("isActive", isActive);
        q.setParameter("isSold", isSold);
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

    public List<Bids> getBids(Integer itemId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select b from Bids b where b.itemId.itemId= :itemId");
        q.setParameter("itemId", itemId);
        List<Bids> bids = q.getResultList();
        tx.commit();
        em.close();

        return bids;
    }

    public List<Item> getUserBids(String username) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select DISTINCT b.itemId from Bids b where b.bidder.username= :username");
        q.setParameter("username", username);
        List<Item> bids = q.getResultList();
        tx.commit();
        em.close();

        return bids;
    }

    public List search(String username, String keys) {
        String[] myS = keys.split("\\s+");
        List ret;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Date curDate = new Date();
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.seller.username<> :username AND i.isActive=true AND i.isSold=false AND i.endTime>:curDate");
        q.setParameter("username", username);
        q.setParameter("curDate", curDate);
        ret = q.getResultList();
        tx.commit();

        for (String key : myS) {
            tx.begin();
            Query nq = em.createQuery("SELECT i from Item i WHERE i.seller.username<> :username AND i.isActive=true AND i.isSold=false AND i.endTime>:curDate AND i.description like :key ");
            nq.setParameter("username", username);
            nq.setParameter("curDate", curDate);
            nq.setParameter("key", '%' + key + '%');

            List res = nq.getResultList();
            tx.commit();

            ret.retainAll(res);
        }

        em.close();

        return ret;
    }

    public String status(Integer itemId, String username) {
        Bids bid = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3bay_4PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("SELECT b FROM Bids b WHERE b.itemId.itemId= :itemId ORDER BY b.date ASC");
        q.setParameter("itemId", itemId);
        List<Bids> bids = q.getResultList();
        tx.commit();
        em.close();

        if (bids.isEmpty()) {
            return "Unbidded";
        } else {
            Bids last = bids.get(bids.size() - 1);
            if (last.getBidder().getUsername().equals(username)) {
                Date curDate = new Date();
                if (last.getItemId().getIsSold() || last.getItemId().getEndTime().before(curDate)) {
                    return "Bought";
                } else {
                    return "Highest Bidder";
                }
            } else {
                for (Bids b : bids) {
                    if (b.getBidder().getUsername().equals(username)) {
                        return "Outbidded";
                    }
                }
                return "Unbidded";
            }
        }
    }
}
