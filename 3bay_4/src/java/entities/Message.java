/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByTimeSent", query = "SELECT m FROM Message m WHERE m.timeSent = :timeSent"),
    @NamedQuery(name = "Message.findByIsRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead"),
    @NamedQuery(name = "Message.findByHiddenFromInbox", query = "SELECT m FROM Message m WHERE m.hiddenFromInbox = :hiddenFromInbox"),
    @NamedQuery(name = "Message.findByHiddenFromOutbox", query = "SELECT m FROM Message m WHERE m.hiddenFromOutbox = :hiddenFromOutbox")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "Text")
    private String text;
    @Basic(optional = false)
    @Column(name = "TimeSent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSent;
    @Basic(optional = false)
    @Column(name = "isRead")
    private boolean isRead;
    @Basic(optional = false)
    @Column(name = "hiddenFromInbox")
    private boolean hiddenFromInbox;
    @Basic(optional = false)
    @Column(name = "hiddenFromOutbox")
    private boolean hiddenFromOutbox;
    @JoinColumn(name = "Sender", referencedColumnName = "SocialSecurityNumber")
    @ManyToOne(optional = false)
    private User sender;
    @JoinColumn(name = "Recipient", referencedColumnName = "SocialSecurityNumber")
    @ManyToOne(optional = false)
    private User recipient;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, String text, Date timeSent, boolean isRead, boolean hiddenFromInbox, boolean hiddenFromOutbox) {
        this.id = id;
        this.text = text;
        this.timeSent = timeSent;
        this.isRead = isRead;
        this.hiddenFromInbox = hiddenFromInbox;
        this.hiddenFromOutbox = hiddenFromOutbox;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean getHiddenFromInbox() {
        return hiddenFromInbox;
    }

    public void setHiddenFromInbox(boolean hiddenFromInbox) {
        this.hiddenFromInbox = hiddenFromInbox;
    }

    public boolean getHiddenFromOutbox() {
        return hiddenFromOutbox;
    }

    public void setHiddenFromOutbox(boolean hiddenFromOutbox) {
        this.hiddenFromOutbox = hiddenFromOutbox;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Message[ id=" + id + " ]";
    }
    
}
