package exam.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "Equipement")
public class EquipementCulturel implements Serializable{

    /***************
     * INTERN STATE
     ***************/

    @Id
    private String id;

    private String name;
    private int postalCode;
    private String websiteURL;
    private String status;
    private String phoneNumber;
    private String theme;
    private String city;
    private String libCategory;
    private String address;
    private String libTheme;
    private Double[] localisation;
    private String type;
    private int category;

    /***************
     * CONSTRUCTORS
     ***************/

    public EquipementCulturel() {
    }

    /*********************
     * GETTERS AND SETTERS
     ********************/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLibCategory() {
        return libCategory;
    }

    public void setLibCategory(String libCategory) {
        this.libCategory = libCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLibTheme() {
        return libTheme;
    }

    public void setLibTheme(String libTheme) {
        this.libTheme = libTheme;
    }

    public Double[] getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Double[] localisation) {
        this.localisation = localisation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    /*********************
     * OVERRIDED METHODS
     ********************/

    @Override
    public String toString() {
        return "EquipementCulturel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", postalCode=" + postalCode +
                ", websiteURL='" + websiteURL + '\'' +
                ", status='" + status + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", theme='" + theme + '\'' +
                ", city='" + city + '\'' +
                ", libCategory='" + libCategory + '\'' +
                ", address='" + address + '\'' +
                ", libTheme='" + libTheme + '\'' +
                ", localisation=" + Arrays.toString(localisation) +
                ", type='" + type + '\'' +
                ", category=" + category +
                '}';
    }
}
