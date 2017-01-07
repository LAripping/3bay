package beans;

import db.UserDB;
import entities.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "profile")
@SessionScoped
public class ProfileBean {

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

    public String makeProfile(String p_username) {
        UserDB userDB = new UserDB();
        User p = userDB.getUser(p_username);
        username = p.getUsername();
        socialSecurityNumber = p.getSocialSecurityNumber();
        passwords = p.getPasswords();
        firstName = p.getFirstName();
        lastName = p.getLastName();
        email = p.getEmail();
        mobileNumber = p.getMobileNumber();
        country = p.getCountry();
        city = p.getCity();
        street = p.getStreet();
        streetNumber = p.getStreetNumber();
        postalCode = p.getPostalCode();
        isConfirmed = p.getIsConfirmed();
        buyerRating = p.getBuyerRating();
        sellerRating = p.getSellerRating();
        
        return "/restricted/user_profile?faces-redirect=true";
    }

    public String checkConfirm() {
        if (isConfirmed) {
            return "Approved";
        } else {
            return "Approve";
        }
    }

    public String iconConfirm() {
        if (isConfirmed) {
            return "ui-icon-check";
        } else {
            return "";
        }
    }

    public String approve() {
        UserDB userDB = new UserDB();
        String retMessage = userDB.updateUser(socialSecurityNumber);
        isConfirmed = true;
        return "/restricted/user_profile?faces-redirect=true";
    }

    public String goBack(String username) {
        if (username.equals("Admin")) {
            return "/restricted/users?faces-redirect=true";
        } else {
            return "/restricted/user_home?faces-redirect=true";
        }
    }

    public String getAddress() {
        return street + " " + streetNumber + ", " + city;
    }
}
