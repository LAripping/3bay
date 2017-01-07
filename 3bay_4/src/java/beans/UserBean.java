package beans;

import db.MessageDB;
import db.UserDB;
import entities.Message;
import entities.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean {

    private String socialSecurityNumber;
    private String username;
    private String passwords;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String country;
    private String city;
    private String street;
    private Integer streetNumber;
    private String postalCode;
    private boolean isConfirmed;
    private Integer sellerRating;
    private Integer buyerRating;
    private User current;

    public Integer getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Integer sellerRating) {
        this.sellerRating = sellerRating;
    }

    public Integer getBuyerRating() {
        return buyerRating;
    }

    public void setBuyerRating(Integer buyerRating) {
        this.buyerRating = buyerRating;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String n_username) {
        this.username = n_username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String n_passwords) {
        this.passwords = n_passwords;
    }

    public String login() {
        UserDB userDB = new UserDB();
        current = userDB.find(username, passwords);

        if (current == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unknown login, try again"));
            return (username = passwords = null);
        } else {
            this.copyUser(current);
            if (username.equals("Admin")) {
                return "/restricted/admin_home?faces-redirect=true";
            } else {
                if (isConfirmed) {
                    return "/restricted/user_home?faces-redirect=true";
                } else {
                    return "/restricted/not_approved?faces-redirect=true";
                }
            }
        }
    }

    public String insertUser() {
        UserDB userDB = new UserDB();
        User nuser = new User();
        nuser.setSocialSecurityNumber(socialSecurityNumber);
        nuser.setUsername(username);
        nuser.setPasswords(passwords);
        nuser.setFirstName(firstName);
        nuser.setLastName(lastName);
        nuser.setCountry(country);
        nuser.setEmail(email);
        nuser.setMobileNumber(mobileNumber);
        nuser.setCity(city);
        nuser.setStreet(street);
        nuser.setStreetNumber(streetNumber);
        nuser.setPostalCode(postalCode);
        nuser.setIsConfirmed(false);
        nuser.setBuyerRating(0);
        nuser.setSellerRating(0);

        String retMessage = userDB.insertUser(nuser);

        if (retMessage.equals("ok")) {
            return "/restricted/successful?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Oops, something went wrong! Please try again later!"));
            return (username = passwords = socialSecurityNumber = firstName = lastName = country = email = mobileNumber = city = street = postalCode = null);
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return current != null;
    }

    public String register() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "register_user?faces-redirect=true";
    }

    public String approveAll() {
        UserDB userDB = new UserDB();
        String retMessage = userDB.updateUsers();
        return "/restricted/admin_home?faces-redirect=true";
    }

    public void copyUser(User user) {
        socialSecurityNumber = user.getSocialSecurityNumber();
        username = user.getUsername();
        passwords = user.getPasswords();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        country = user.getCountry();
        email = user.getEmail();
        mobileNumber = user.getMobileNumber();
        city = user.getCity();
        street = user.getStreet();
        streetNumber = user.getStreetNumber();
        postalCode = user.getPostalCode();
        isConfirmed = user.getIsConfirmed();
        sellerRating = user.getSellerRating();
        buyerRating = user.getBuyerRating();

    }

    public void validate() {
        UserDB userDB = new UserDB();
        current = userDB.getUser(username);
        if (current == null) {
            FacesMessage msg = new FacesMessage("E-mail validation failed.", "Invalid E-mail format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
        
    public boolean isAdmin() {
        return !username.equals("Admin");
    }
    
    public String menu() {
        if(username.equals("Guest"))
            return "/template/gmenu.xhtml";
        else if(username.equals("Admin"))
            return "/template/amenu.xhtml";
        else
            // Logged in user
            return "/template/umenu.xhtml";
    }
    
    public String guest(){
        username= "Guest";
        UserDB userDB= new UserDB();
        current = userDB.getUser(username);
        return "guest_home?faces-redirect=true";
    }
    
    public Integer countUnreadMessages(){
        MessageDB msgDB = new MessageDB();
        Integer count = 0;
        
        List list = msgDB.getMessages(username,2);
        for(Object o : list)
            if(  (((Message) o).getIsRead() == false)
               &&(((Message) o).getHiddenFromInbox() == false  ) ){ 
                        count++; 
            }
                        
        return count;
    }
    
    public String home() {
        if(username.equals("Admin"))
            return "/restricted/admin_home?faces-redirect=true";
        else if(username.equals("Guest"))
            return "/guest_home?faces-redirect=true";
        else
            return "/restricted/user_home?faces-redirect=true";
    }
}
