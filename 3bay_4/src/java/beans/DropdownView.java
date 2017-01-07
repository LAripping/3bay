package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.annotation.PostConstruct;

@ManagedBean(name = "dropdownView")
@ViewScoped
public class DropdownView implements Serializable {
     
    private SortedMap<String,String> countries;

    @PostConstruct
    public void init() {
          countries = new TreeMap();
    }
    
    public SortedMap<String,String> makeCountries() {
	String[] locales = Locale.getISOCountries();
 
	for (String countryCode : locales) {
		Locale obj = new Locale("", countryCode);
                 countries.put(obj.getDisplayCountry(),obj.getDisplayCountry());
	}

        return countries;
    }

    
     
}