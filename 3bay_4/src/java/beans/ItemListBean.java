package beans;

import db.ItemDB;
import db.UserDB;
import entities.Item;
import entities.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "items")
@SessionScoped
public class ItemListBean {

    private ItemDB itemDB;
    private Integer choice;
    private String sub;
    private String keys;

    public Integer getChoice() {
        return choice;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public ItemListBean() {

    }

    public DataModel<ItemBean> makeList(String username) {
        DataModel<ItemBean> itemList = null;
        itemDB = new ItemDB();
        if (itemList == null) {
            List list;
            if (choice == 21) {
                list = itemDB.getItems(username, choice, keys);
                keys = "";
            } else {
                list = itemDB.getItems(username, choice, sub);
            }
            if (list != null) {
                ArrayList<ItemBean> iList = new ArrayList<ItemBean>();

                for (Object o : list) {

                    ItemBean ib = new ItemBean();
                    ib.setItemId(((Item) o).getItemId());
                    ib.setName(((Item) o).getName());
                    ib.setBuyNow(((Item) o).getBuyNow());
                    ib.setFirstBid(((Item) o).getFirstBid());
                    ib.setCurrentBid(((Item) o).getCurrentBid());
                    ib.setEndTime(((Item) o).getEndTime());
                    ib.setPhotos(itemDB.getPhotos(((Item) o).getItemId()));
                    ib.setCategories(itemDB.getCategories((Item) o));
                    iList.add(ib);

                }
                itemList = new ListDataModel<ItemBean>(iList);
            }
        }
        return itemList;
    }

    public String keepChoice(Integer ch) {
        choice = ch;
        return "/restricted/my_auctions?faces-redirect=true";
    }

    public String keepChoice(Integer ch, String s) {
        choice = ch;
        sub = s;
        return "/restricted/browse_auctions?faces-redirect=true";
    }

    public String name() {
        if (choice == 1) {
            return "Inactive";
        } else if (choice == 2) {
            return "Expired";
        } else if (choice == 3) {
            return "Sold";
        } else if (choice == 4) {
            return "Active";
        } else if (choice == 5) {
            return "Bids";
        } else if (choice == 21) {
            return "Results";
        } else {
            return "Auctions";
        }
    }

    public Integer factor() {
        if (choice < 10) {
            return 1;
        } else {
            return 2;
        }
    }

    public DataModel<ItemBean> suggestions(String username) {
        String ret = "";
        UserDB userdb = new UserDB();
        itemDB = new ItemDB();

        // Get the names of all users except me, Admin and Guest
        List<User> users = userdb.getMostUsers(username);
        ArrayList<List<Item>> pref = new ArrayList<List<Item>>();

        // Get the bids of every user
        for (User u : users) {
            List<Item> temp = null;
            temp = itemDB.getUserBids(u.getUsername());
            if (!temp.isEmpty()) {
                pref.add(temp);
            }
        }

        // Get my bids
        List<Item> mine = itemDB.getUserBids(username);
        Integer[][] index = new Integer[pref.size()][2];

        // Get the common Bids between me and other users
        Integer c = 0;
        for (List<Item> it : pref) {
            ArrayList<Item> it2 = new ArrayList<Item>(mine);
            it2.retainAll(it);
            index[c][0] = c;
            index[c][1] = it2.size();
            c++;
        }

        // Sort by these common bids
        Arrays.sort(index, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] int1, Integer[] int2) {
                Integer numOfKeys1 = int1[1];
                Integer numOfKeys2 = int2[1];
                return -numOfKeys1.compareTo(numOfKeys2);
            }
        });

        // Sort user bids by these common bids
        ArrayList<List<Item>> sortpref = new ArrayList<List<Item>>();
        for (c = 0; c < pref.size(); c++) {
            sortpref.add(pref.get(index[c][0]));
        }

        // Throw my auctions and these already bought and at the same time, Flatten the list
        Date curDate = new Date();
        ArrayList<Item> newpref = new ArrayList<Item>();
        for (List<Item> it : sortpref) {
            it.removeAll(mine);
            for (Item i : it) {
                if (!newpref.contains(i) && i.getIsSold() == false && i.getEndTime().after(curDate) && !(i.getSeller().getUsername().equals(username))) {
                    newpref.add(i);
                }
            }
        }

        DataModel<ItemBean> itemList = null;
        ArrayList<ItemBean> iList = new ArrayList<ItemBean>();

        for (Item i : newpref) {

            ItemBean ib = new ItemBean();
            ib.setItemId(i.getItemId());
            ib.setName(i.getName());
            ib.setBuyNow(i.getBuyNow());
            ib.setFirstBid(i.getFirstBid());
            ib.setCurrentBid(i.getCurrentBid());
            ib.setEndTime(i.getEndTime());
            ib.setPhotos(itemDB.getPhotos(i.getItemId()));
            ib.setCategories(itemDB.getCategories(i));
            iList.add(ib);

        }
        itemList = new ListDataModel<ItemBean>(iList);

        return itemList;

    }

}
