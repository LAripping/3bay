/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.MessageDB;
import db.UserDB;
import entities.Message;
import entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leo
 */
@ManagedBean(name = "message")
@SessionScoped
public class MessageBean {

    private Integer id;
    private String text;
    private Date timeSent;
    private boolean isRead;
    private User sender;
    private User recipient;
    private Boolean hiddenFromInbox;
    private Boolean hiddenFromOutbox;

    private String recipientUsername;
    private String senderUsername;

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

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
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

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipient) {
        this.recipientUsername = recipient;
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

    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        timeSent = new Date();
        id = 0;
        isRead = false;
    }

    public String isReadStatus() {
        if (isRead) {
            return "Read";
        } else {
            return "Unread";
        }
    }

    public String sendMessage(String senderUsername) {
        MessageDB messageDB = new MessageDB();
        UserDB userDB = new UserDB();
        Message message = new Message();

        recipient = userDB.getUser(recipientUsername);
        sender = userDB.getUser(senderUsername);

        message.setId(id);
        message.setIsRead(isRead);
        message.setRecipient(recipient);
        message.setSender(sender);
        message.setText(text);
        message.setTimeSent(timeSent);

        String retMessage = messageDB.insertMessage(message);
        if (retMessage.equals("ok")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Message sent!", "Message sent!"));
            return "/restricted/sent_messages?faces-redirect=true";
        } else {
            System.out.println("SEND MESSAGE ERROR " + retMessage);
            return "";
        }
    }

    public String markUnread(Integer id) {
        MessageDB mDB = new MessageDB();
        String retMessage = mDB.markMessageRead(id, false);
        if (retMessage.equals("ok")) {
            return "/restricted/received_messages?faces-redirect=true?";
        } else {
            return "";
        }
    }

    public String hide(Integer id, Boolean recipientViews) {
        MessageDB mDB = new MessageDB();
        String retMessage = null;
        if (recipientViews) {
            retMessage = mDB.hideFromInbox(id, true);
        } else {
            retMessage = mDB.hideFromOutbox(id, true);
        }
        if (retMessage.equals("ok")) {
            if (recipientViews) {
                return "/restricted/received_messages?faces-redirect=true?";
            } else {
                return "/restricted/sent_messages?faces-redirect=true?";
            }
        } else {
            return "";
        }
    }
}
