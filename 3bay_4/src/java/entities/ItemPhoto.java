/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "item_photo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPhoto.findAll", query = "SELECT i FROM ItemPhoto i"),
    @NamedQuery(name = "ItemPhoto.findById", query = "SELECT i FROM ItemPhoto i WHERE i.id = :id"),
    @NamedQuery(name = "ItemPhoto.findByPath", query = "SELECT i FROM ItemPhoto i WHERE i.path = :path")})
public class ItemPhoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Path")
    private String path;
    @JoinColumn(name = "ItemId", referencedColumnName = "ItemId")
    @ManyToOne(optional = false)
    private Item itemId;

    public ItemPhoto() {
    }

    public ItemPhoto(Integer id) {
        this.id = id;
    }

    public ItemPhoto(Integer id, String path) {
        this.id = id;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPhoto)) {
            return false;
        }
        ItemPhoto other = (ItemPhoto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ItemPhoto[ id=" + id + " ]";
    }
    
}
