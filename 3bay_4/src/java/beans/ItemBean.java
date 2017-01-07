/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.ItemDB;
import db.UserDB;
import entities.Category;
import entities.Item;
import entities.ItemPhoto;
import entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "item")
@SessionScoped
public class ItemBean {

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
    private ArrayList<String> photos;

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

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
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

    public ItemBean() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        startTime = new Date();
        itemId = 0;
        photos = new ArrayList<String>();
    }

    public String insertItem(String username) {
        ItemDB itemDB = new ItemDB();
        UserDB userDB = new UserDB();
        Item item = new Item();

        item.setItemId(itemId);
        item.setName(name);
        item.setDescription(description);
        item.setCurrentBid(0);
        item.setBuyNow(buyNow);
        item.setFirstBid(firstBid);
        item.setStartTime(startTime);
        item.setEndTime(endTime);
        seller = userDB.getUser(username);
        item.setSeller(seller);
        item.setIsActive(false);
        item.setIsSold(false);

        String retMessage = itemDB.insertItem(item);
        if (retMessage.equals("ok")) {

            for (String photo : photos) {
                ItemPhoto iphoto = new ItemPhoto();
                iphoto.setItemId(item);
                iphoto.setPath(photo);
                retMessage = itemDB.insertPhoto(iphoto);
            }

            if (retMessage.equals("ok")) {
                for (String cat : categories) {
                    Category category = new Category();
                    category.setItemId(item);
                    category.setCategory(cat);
                    retMessage = itemDB.insertCategory(category);
                }
                if (retMessage.equals("ok")) {
                    return "/restricted/user_home?faces-redirect=true?";
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile uphoto;

        uphoto = event.getFile();
        InputStream input = uphoto.getInputstream();
        Path folder = Paths.get(System.getProperty("catalina.base") + "/webapps/uploads/photos");

        String filename = FilenameUtils.getBaseName(uphoto.getFileName());
        String extension = FilenameUtils.getExtension(uphoto.getFileName());
        Path file = Files.createTempFile(folder, filename, "." + extension);
        try {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            String photo = file.toString();
            photo = photo.substring((System.getProperty("catalina.base") + "/webapps").length(), photo.length());
            photo = photo.replace("\\", "/");
            photos.add(photo);
        } catch (IOException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String showPhotos() {
        String ret = "";
        Integer i = 0;
        for (String str : photos) {
            ret += str;
            i++;
            if (i != photos.size()) {
                ret += ", ";
            }
        }
        return ret;
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

    public String time() {
        return endTime.toString().substring(0, 20);
    }

    public Integer bid() {
        if (currentBid == null) {
            return firstBid;
        } else {
            return currentBid;
        }
    }

    public String photo() {
        if (photos.isEmpty()) {
            return "";
        } else {
            return photos.get(0);
        }
    }

    public String status(Integer choice, String username) {
        ItemDB itemdb = new ItemDB();
        if (choice == 1) {
            return "Inactive";
        } else if (choice == 2) {
            return "Expired";
        } else if (choice == 3) {
            return "Sold";
        } else if (choice == 4 || username.equals("Admin") || username.equals("Guest")) {
            return "Active";
        } else {
            String ret = itemdb.status(itemId, username);
            return ret;
        }
    }

    public String colour(Integer choice, String username) {
        String status = status(choice, username);
        if (status.equals("Inactive") || status.equals("Expired") || status.equals("Outbidded")) {
            return "color: red";
        } else if (status.equals("Sold") || status.equals("Bought")) {
            return "color: gold";
        } else if (status.equals("Active") || status.equals("Unbidded")) {
            return "color: green";
        } else {
            return "color: blue";
        }
    }

}
