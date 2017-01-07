package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "categories")
@RequestScoped
public class CategoriesBean {

    private List<String> categories;
    
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public CategoriesBean() {
        categories = new ArrayList<String>();
        categories.add("Antiques");
        categories.add("Art");
        categories.add("Baby");
        categories.add("Books");
        categories.add("Business & Industrial");
        categories.add("Cameras & Photo");
        categories.add("Cell phones & Accessories");
        categories.add("Clothing, Shoes & Accessories");
        categories.add("Coins & Paper Money");
        categories.add("Collectibles");
        categories.add("Computers/Tablets & Networing");
        categories.add("Consumer Electronics");
        categories.add("Crafts");
        categories.add("Dolls & Bears");
        categories.add("DVDs & Movies");
        categories.add("Entertainment Memorabilia");
        categories.add("Gift Cards & Coupons");
        categories.add("Health & Beauty");
        categories.add("Home & Garden");
        categories.add("Jewelry & Watches");
        categories.add("Motors");
        categories.add("Music");
        categories.add("Musical Instruments & Gear");
        categories.add("Pet Supplies");
        categories.add("Pottery & Glass");
        categories.add("Real Estate");
        categories.add("Specialty Services");
        categories.add("Sporting Goods");
        categories.add("Sports Mem, Cards & Fan Shop");
        categories.add("Stamps");
        categories.add("Tickets & Experiences");
        categories.add("Toys & Hobbies");
        categories.add("Travel");
        categories.add("Video Games & Consoles");
    }

}
