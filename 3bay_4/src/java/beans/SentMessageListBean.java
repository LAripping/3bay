/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.MessageDB;
import db.UserDB;
import entities.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Leo
 */
@ManagedBean(name = "messagesOut")
@SessionScoped
public class SentMessageListBean implements Serializable {

    private DataModel<MessageBean> messageList;
    private MessageDB messageDB;
    private Integer totalUnread;
    private Boolean showHidden;

    public Integer getTotalUnread() {
        return totalUnread;
    }

    public void setTotalUnread(Integer totalUnread) {
        this.totalUnread = totalUnread;
    }

    public DataModel<MessageBean> getMessageList() {
        return messageList;
    }

    public void setMessageList(DataModel<MessageBean> messageList) {
        this.messageList = messageList;
    }

    public Boolean isShowHidden() {
        return showHidden;
    }

    public void setShowHidden(Boolean showHidden) {
        this.showHidden = showHidden;
    }

    public SentMessageListBean() {
        this.messageList = null;
        this.messageDB = new MessageDB();
        this.totalUnread = 0;
        this.showHidden = false;
    }

    public String showHiddenStatus() {
        if (showHidden == false) {
            return "Show All";
        } else {
            return "Now they do!";
        }
    }

    public String showHiddenTitle() {
        if (showHidden == false) {
            return "Inbox";
        } else {
            return "Inbox (all messages received)";
        }
    }

    public String showHiddenIcon() {
        if (showHidden == false) {
            return "ui-icon-lightbulb";
        } else {
            return "";
        }
    }

    public String makeReceivedList_refresh(String recipientUsername) {
        showHidden = true;
        messageList = makeSentList(recipientUsername);
        return "/restricted/received_messages?faces-redirect=true?";
    }

    public DataModel<MessageBean> makeSentList(String recipientUsername) {
        UserDB recipientDB = new UserDB();
        DataModel<MessageBean> ret = null;

        List resultList = messageDB.getMessages(recipientUsername, 1);
        ArrayList<MessageBean> mList = new ArrayList<MessageBean>();
        if (resultList != null) {
            for (Object o : resultList) {
                MessageBean mb = new MessageBean();
                mb.setId(((Message) o).getId());
                mb.setText(((Message) o).getText());
                mb.setTimeSent(((Message) o).getTimeSent());
                mb.setIsRead(((Message) o).getIsRead());
                if (mb.isIsRead() == false) {
                    totalUnread++;
                }
                mb.setSender(((Message) o).getSender());
                mb.setRecipient(recipientDB.getUser(recipientUsername));
                mb.setHiddenFromInbox(((Message) o).getHiddenFromInbox());
                mb.setHiddenFromOutbox(((Message) o).getHiddenFromOutbox());

                if (showHidden == false) {
                    if (mb.isHiddenFromInbox() == false) {
                        mList.add(mb);
                    }
                } else {
                    mList.add(mb);
                }
            }

            ret = new ListDataModel<MessageBean>(mList);
            messageList = ret;

        }

        return ret;
    }

}
