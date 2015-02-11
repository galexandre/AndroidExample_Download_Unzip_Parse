package project.geoffrey.fr.appdownloaddata.model;

import java.util.List;
import java.util.Map;

/**
 * Created by galexandre on 11/02/15.
 */
public class Station {

    private String id;
    private float longitude;
    private float latitude;
    private String adress;
    private String city;
    private String open;
    private List<String> services;
    private List<String> price;
    private Map<String, Float> prices;

    public Station(String id, float longitude, float latitude, String adress, String city, String open, List<String> services, List<String> price) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adress = adress;
        this.city = city;
        this.open = open;
        this.services = services;
        this.price = price;
    }

    public Station(String id, float longitude, float latitude, String city, String adress, Map<String, Float> prices) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.adress = adress;
        this.prices = prices;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
