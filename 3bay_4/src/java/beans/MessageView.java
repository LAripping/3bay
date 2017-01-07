/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.MessageDB;
import entities.Message;
import entities.User;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leo
 */
@ManagedBean(name = "messageView")
@SessionScoped
public class MessageView {

    private Integer id;
    private String text;
    private Date timeSent;
    private User sender;
    private User recipient;
    private Boolean hiddenFromInbox;
    private Boolean hiddenFromOutbox;

    private Boolean recipientViews;

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

    public Boolean isHiddenFromInbox() {
        return hiddenFromInbox;
    }

    public void setHiddenFromInbox(Boolean hiddenFromInbox) {
        this.hiddenFromInbox = hiddenFromInbox;
    }

    public Boolean isHiddenFromOutbox() {
        return hiddenFromOutbox;
    }

    public void setHiddenFromOutbox(Boolean hiddenFromOutbox) {
        this.hiddenFromOutbox = hiddenFromOutbox;
    }

    public Boolean isRecipientViews() {
        return recipientViews;
    }

    public void setRecipientViews(Boolean recipientViews) {
        this.recipientViews = recipientViews;
    }

    public String makeView(Integer msgId, Boolean fromRecipient) {
        MessageDB messageDB = new MessageDB();
        Message m = messageDB.findById(msgId);

        id = msgId;
        text = m.getText();
        timeSent = m.getTimeSent();
        sender = m.getSender();
        recipient = m.getRecipient();
        hiddenFromInbox = m.getHiddenFromInbox();
        hiddenFromOutbox = m.getHiddenFromOutbox();
        recipientViews = fromRecipient;

        //Message marked read if-and-only-if its recipient views it! 
        if (fromRecipient) {
            String retMessage = messageDB.markMessageRead(msgId, true);
            if (!retMessage.equals("ok")) {
                return "";
            }
        }

        return "/restricted/view_message?faces-redirect=true?";
    }

    public String endTitle() {
        if (recipientViews) {
            return "Sender";
        } else {
            return "Recipient";
        }
    }

    public User endName() {
        if (recipientViews) {
            return sender;
        } else {
            return recipient;
        }
    }

    public String endLink() {
        if (recipientViews) {
            return "Sender's Profile";
        } else {
            return "Recipient's Profile";
        }
    }

    public String deleteValue() {
        if (recipientViews) {
            return "Delete from Inbox";
        } else {
            return "Delelte from Outbox";
        }
    }

    public Boolean isHidden() {
        if (recipientViews) {
            return isHiddenFromInbox();
        } else {
            return isHiddenFromOutbox();
        }
    }

}
