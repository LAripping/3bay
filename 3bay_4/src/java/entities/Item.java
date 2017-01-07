/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemId", query = "SELECT i FROM Item i WHERE i.itemId = :itemId"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByCurrentBid", query = "SELECT i FROM Item i WHERE i.currentBid = :currentBid"),
    @NamedQuery(name = "Item.findByBuyNow", query = "SELECT i FROM Item i WHERE i.buyNow = :buyNow"),
    @NamedQuery(name = "Item.findByFirstBid", query = "SELECT i FROM Item i WHERE i.firstBid = :firstBid"),
    @NamedQuery(name = "Item.findByStartTime", query = "SELECT i FROM Item i WHERE i.startTime = :startTime"),
    @NamedQuery(name = "Item.findByEndTime", query = "SELECT i FROM Item i WHERE i.endTime = :endTime"),
    @NamedQuery(name = "Item.findByIsActive", query = "SELECT i FROM Item i WHERE i.isActive = :isActive"),
    @NamedQuery(name = "Item.findByIsSold", query = "SELECT i FROM Item i WHERE i.isSold = :isSold")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ItemId")
    private Integer itemId;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Description")
    private String description;
    @Column(name = "CurrentBid")
    private Integer currentBid;
    @Column(name = "BuyNow")
    private Integer buyNow;
    @Basic(optional = false)
    @Column(name = "FirstBid")
    private int firstBid;
    @Basic(optional = false)
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "isActive")
    private boolean isActive;
    @Basic(optional = false)
    @Column(name = "isSold")
    private boolean isSold;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<ItemPhoto> itemPhotoCollection;
    @JoinColumn(name = "Seller", referencedColumnName = "SocialSecurityNumber")
    @ManyToOne(optional = false)
    private User seller;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Bids> bidsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Category> categoryCollection;

    public Item() {
    }

    public Item(Integer itemId) {
        this.itemId = itemId;
    }

    public Item(Integer itemId, String name, int firstBid, Date startTime, Date endTime, boolean isActive, boolean isSold) {
        this.itemId = itemId;
        this.name = name;
        this.firstBid = firstBid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.isSold = isSold;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }

    @XmlTransient
    public Collection<ItemPhoto> getItemPhotoCollection() {
        return itemPhotoCollection;
    }

    public void setItemPhotoCollection(Collection<ItemPhoto> itemPhotoCollection) {
        this.itemPhotoCollection = itemPhotoCollection;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @XmlTransient
    public Collection<Bids> getBidsCollection() {
        return bidsCollection;
    }

    public void setBidsCollection(Collection<Bids> bidsCollection) {
        this.bidsCollection = bidsCollection;
    }

    @XmlTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Item[ itemId=" + itemId + " ]";
    }
    
}
