/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.ItemDB;
import db.UserDB;
import entities.Bids;
import entities.Item;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ManagedBean(name = "auction")
@SessionScoped
public class AuctionBean {

    private Integer itemId;
    private String name;
    private String description;
    private Integer currentBid;
    private Integer buyNow;
    private int firstBid;
    private Date startTime;
    private Date endTime;
    private User seller;
    private boolean isActive;
    private String[] categories;
    private boolean isSold;
    private List<String> photos;
    private Integer tempBid;

    private String winner;
    private Integer givenRating;
    private String bidResult;

    public String getBidResult() {
        return bidResult;
    }

    public void setBidResult(String bidResult) {
        this.bidResult = bidResult;
    }

    public Integer getTempBid() {
        return tempBid;
    }

    public void setTempBid(Integer tempBid) {
        this.tempBid = tempBid;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public boolean isIsSold() {
        return isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Integer currentBid) {
        this.currentBid = currentBid;
    }

    public Integer getBuyNow() {
        return buyNow;
    }

    public void setBuyNow(Integer buyNow) {
        this.buyNow = buyNow;
    }

    public int getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(int firstBid) {
        this.firstBid = firstBid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Integer getGivenRating() {
        return givenRating;
    }

    public void setGivenRating(Integer givenRating) {
        this.givenRating = givenRating;
    }

    public AuctionBean() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        startTime = new Date();
        itemId = 0;
        winner = null;
        bidResult = null;
    }

    public String showCategories() {
        String ret = "";
        Integer i = 0;
        for (String str : categories) {
            ret += str;
            i++;
            if (i != categories.length) {
                ret += ", ";
            }
        }
        return ret;
    }

    public String makeAuction(Integer ID, Integer choice) {

        ItemDB itemdb = new ItemDB();
        Item a = itemdb.getItem(ID);
        itemId = ID;
        name = a.getName();
        description = a.getDescription();
        currentBid = a.getCurrentBid();
        buyNow = a.getBuyNow();
        firstBid = a.getFirstBid();
        startTime = a.getStartTime();
        endTime = a.getEndTime();
        seller = a.getSeller();
        isActive = a.getIsActive();
        isSold = a.getIsSold();
        categories = itemdb.getCategories(a);
        photos = itemdb.getPhotos(ID);
        winner = null;
        bidResult = null;

        if (choice == 1) {
            return "/restricted/item_profile?faces-redirect=true";
        } else {
            return "/restricted/auction_profile?faces-redirect=true";
        }
    }

    public String wordActive() {
        if (isActive) {
            return "Active";
        } else {
            return "Activate";
        }
    }

    public String iconActive() {
        if (isActive) {
            return "ui-icon-check";
        } else {
            return "";
        }
    }

    public String activate() {
        ItemDB itemdb = new ItemDB();
        String retMessage = itemdb.activateItem(itemId);
        isActive = true;
        return "/restricted/item_profile?faces-redirect=true";
    }

    public boolean checkActive() {
        Date date = new Date();
        if (endTime.after(date)) {
            return isActive;
        } else {
            return true;
        }
    }

    public boolean checkEdit() {
        Date date = new Date();
        if (!isSold) {
            if (endTime.after(date)) {
                return isActive;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public String getAddress() {
        return seller.getStreet() + " " + seller.getStreetNumber() + ", " + seller.getCity();
    }

    public String goBack() {
        itemId = 0;
        return "/restricted/my_auctions?faces-redirect=true";
    }

    public String profile() {
        return "/3bay_4/faces/restricted/my_auctions.xhtml";
    }

    public String updateItem(String username) {
        ItemDB itemdb = new ItemDB();
        startTime = new Date();
        String retMessage = itemdb.updateItem(itemId, name, description, buyNow, firstBid, startTime, endTime, currentBid, false, false);
        if (retMessage.equals("ok")) {
            return "/restricted/my_auctions?faces-redirect=true";
        } else {
            return "";
        }
    }

    public boolean isBuyable(String username) {
        if (username.equals("Guest") || username.equals("Admin")) {
            return true;
        } else {
            return buyNow == 0;
        }
    }

    public boolean isBidable(String username) {
        if (username.equals("Guest") || username.equals("Admin")) {
            return true;
        } else {
            return false;
        }
    }

    public String bid(String username) {
        Integer cmp;
        if (currentBid != 0) {
            cmp = currentBid;
        } else {
            cmp = firstBid;
        }

        if (tempBid > cmp) {
            ItemDB itemdb = new ItemDB();
            UserDB userdb = new UserDB();

            currentBid = tempBid;
            String retMessage = itemdb.updateItem(itemId, name, description, buyNow, firstBid, startTime, endTime, currentBid, true, false);
            if (retMessage.equals("ok")) {
                User me = userdb.getUser(username);
                Date time = new Date();
                Item itmid = itemdb.getItem(itemId);
                Bids bid = new Bids();
                bid.setBid(currentBid);
                bid.setBidder(me);
                bid.setDate(time);
                bid.setItemId(itmid);
                itemdb.insertBid(bid);
                bidResult = "Your bid has been submitted!";
            } else {
                bidResult = "Oops, something went wrong! Please try again later!";
            }

        } else {
            bidResult = "Placed bid should be higher than current.";
        }
        return "/restricted/auction_profile?faces-redirect=true";
    }

    public String buy(String username) {
        ItemDB itemdb = new ItemDB();
        UserDB userdb = new UserDB();

        currentBid = buyNow;
        String retMessage = itemdb.updateItem(itemId, name, description, buyNow, firstBid, startTime, endTime, currentBid, true, true);
        if (retMessage.equals("ok")) {
            User me = userdb.getUser(username);
            Date time = new Date();
            Item itmid = itemdb.getItem(itemId);
            Bids bid = new Bids();
            bid.setBid(currentBid);
            bid.setBidder(me);
            bid.setDate(time);
            bid.setItemId(itmid);
            itemdb.insertBid(bid);
        }

        return "/restricted/auction_profile?faces-redirect=true";
    }

    public DataModel<BidBean> bids() {
        DataModel<BidBean> bidList = null;
        ItemDB itemDB = new ItemDB();
        if (bidList == null) {
            List list = itemDB.getBids(itemId);

            if (list != null) {
                ArrayList<BidBean> iList = new ArrayList<BidBean>();

                for (Object b : list) {

                    BidBean bb = new BidBean();
                    bb.setBid(((Bids) b).getBid());
                    bb.setBidder(((Bids) b).getBidder());
                    bb.setDate(((Bids) b).getDate());

                    iList.add(bb);

                }
                bidList = new ListDataModel<BidBean>(iList);
            }
        }
        return bidList;
    }

    public String epiloge(String invoker) {
        ItemDB itemDB = new ItemDB();
        Date curDate = new Date();
        Bids temp;
        if (endTime.before(curDate) || isSold || !isActive) {
            List list = itemDB.getBids(itemId);
            if (list.isEmpty()) {
                return "Auction is currently inactive";
            } else {
                temp = (Bids) list.get(list.size() - 1);
                winner = temp.getBidder().getUsername();
                return (winner + " has bought item #" + itemId);
            }
        } else {
            return "Auction is still active";
        }
    }

    public Boolean canRate(String invoker) {

        if (winner != null) {
            if (invoker.equals(winner) || invoker.equals(seller.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public String winnerORsellerTitle(String invoker) {
        if (invoker.equals(winner)) {
            return seller.getUsername();
        } else if (invoker.equals(seller.getUsername())) {
            return winner;
        }
        return "";
    }

    public String winnerORseller(String invoker) {
        if (invoker.equals(winner)) {
            return "seller-";
        } else if (invoker.equals(seller.getUsername())) {
            return "buyer-";
        }
        return "";
    }

    public Integer winnerORsellerRating(String invoker) {
        if (invoker.equals(winner)) {
            return seller.getSellerRating();
        } else if (invoker.equals(seller.getUsername())) {
            UserDB winnerDB = new UserDB();
            return winnerDB.getUser(winner).getBuyerRating();
        }
        return 0;
    }

    public String onrate(String invoker) {
        Integer current;
        Double newrating;
        UserDB userDB = new UserDB();

        if (invoker.equals(winner)) {
            current = seller.getSellerRating();
            newrating = (current.doubleValue() + givenRating.doubleValue()) / 2;
            userDB.updateRating(seller.getUsername(), newrating.intValue(), true);
            seller.setSellerRating(newrating.intValue());
        } else if (invoker.equals(seller.getUsername())) {
            UserDB winnerDB = new UserDB();
            current = winnerDB.getUser(winner).getBuyerRating();
            newrating = (current.doubleValue() + givenRating.doubleValue()) / 2;
            User winnerEntity = userDB.getUser(winner);
            userDB.updateRating(winnerEntity.getUsername(), newrating.intValue(), false);

        }

        return "/restricted/bids?faces-redirect=true";
    }

    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rating cleared", "Rating cleared DETAIL - IGNORED");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String export() throws IOException {
        ItemDB itemdb = new ItemDB();
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            // Item elements
            Element Item = doc.createElement("Item");
            doc.appendChild(Item);
            Item.setAttribute("ItemId", itemId.toString());

            Element Name = doc.createElement("Name");
            Name.appendChild(doc.createTextNode(name));
            Item.appendChild(Name);

            for (String cat : categories) {
                Element Category = doc.createElement("Category");
                Category.appendChild(doc.createTextNode(cat));
                Item.appendChild(Category);
            }

            Element Currently = doc.createElement("Currently");
            Currently.appendChild(doc.createTextNode(currentBid.toString()));
            Item.appendChild(Currently);

            Element FirstBid = doc.createElement("First_Bid");
            Integer fbtemp = firstBid;
            FirstBid.appendChild(doc.createTextNode(fbtemp.toString()));
            Item.appendChild(FirstBid);

            List<Bids> Lbids = itemdb.getBids(itemId);

            Element NumberOfBids = doc.createElement("Number_of_Bids");
            Integer stemp = Lbids.size();
            NumberOfBids.appendChild(doc.createTextNode(stemp.toString()));
            Item.appendChild(NumberOfBids);

            Element bids = doc.createElement("Bids");
            Item.appendChild(bids);

            for (Bids bid : Lbids) {
                Element Bid = doc.createElement("Bid");
                bids.appendChild(Bid);

                Element Bidder = doc.createElement("Bidder");
                Bid.appendChild(Bidder);
                Bidder.setAttribute("Rating", bid.getBidder().getSellerRating().toString());
                Bidder.setAttribute("UserID", bid.getBidder().getUsername());

                Element Location = doc.createElement("Location");
                Location.appendChild(doc.createTextNode(bid.getBidder().getCity()));
                Bidder.appendChild(Location);

                Element Country = doc.createElement("Country");
                Country.appendChild(doc.createTextNode(bid.getBidder().getCountry()));
                Bidder.appendChild(Country);

                Element Time = doc.createElement("Time");
                Time.appendChild(doc.createTextNode(bid.getDate().toString()));
                Bid.appendChild(Time);

                Element Amount = doc.createElement("Amount");
                Integer btemp = bid.getBid();
                Amount.appendChild(doc.createTextNode(btemp.toString()));
                Bid.appendChild(Amount);
            }

            Element Location = doc.createElement("Location");
            Location.appendChild(doc.createTextNode(seller.getCity()));
            Item.appendChild(Location);

            Element Country = doc.createElement("Coutnry");
            Country.appendChild(doc.createTextNode(seller.getCountry()));
            Item.appendChild(Country);

            Element Started = doc.createElement("Started");
            Started.appendChild(doc.createTextNode(startTime.toString()));
            Item.appendChild(Started);

            Element Ends = doc.createElement("Ends");
            Ends.appendChild(doc.createTextNode(endTime.toString()));
            Item.appendChild(Ends);

            Element Seller = doc.createElement("Seller");
            Item.appendChild(Seller);
            Seller.setAttribute("Rating", seller.getSellerRating().toString());
            Seller.setAttribute("UserID", seller.getUsername());

            Element Description = doc.createElement("Description");
            Description.appendChild(doc.createTextNode(description));
            Item.appendChild(Description);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("catalina.base") + "/webapps/uploads/xml/" + itemId + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        return "/restricted/admin_home?faces-redirect=true";
    }
}
