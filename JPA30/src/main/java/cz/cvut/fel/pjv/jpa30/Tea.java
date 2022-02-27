package cz.cvut.fel.pjv.jpa30;


import java.util.Set;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;

@Entity
public class Tea {
    @Id
    @GeneratedValue
    public Integer teaID;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "type", nullable = false)
    public String type;
    @Column(name = "sellingPrice", nullable = false)
    public Integer sellingPrice;
    @Column(name = "purchasePrice", nullable = false)
    public Integer purchasePrice;
    @Column(name = "country", nullable = false)
    public String country;
    @Column(name = "location", nullable = true)
    public String location;
    
    
//    @OneToMany(mappedBy = "teaID")
//    Set<OrderedTea> orderedTea;

    public Tea() {
        this.teaID = null;
        this.name = null;
        this.type = null;
        this.sellingPrice = null;
        this.purchasePrice = null;
        this.country = null;
        this.location = null;
    }
    
    public Tea(int teaID, String name, String type, int sellingPrice, int purchasePrice, String country, String location ) {
        this.teaID = (teaID);
        this.name = (name);
        this.type = (type);
        this.sellingPrice = (sellingPrice);
        this.purchasePrice = (purchasePrice);
        this.country = (country);
        this.location = (location);
    }


    public SimpleIntegerProperty getTeaIDProperty() {
        return new SimpleIntegerProperty(teaID);
    }

    public SimpleStringProperty getNameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty getTypeProperty() {
        return new SimpleStringProperty(type);
    }

    public SimpleIntegerProperty getSellingPriceProperty() {
        return new SimpleIntegerProperty(sellingPrice);
    }

    public SimpleIntegerProperty getPurchasePriceProperty() {
        return new SimpleIntegerProperty(purchasePrice);
    }

    public SimpleStringProperty getCountryProperty() {
        return new SimpleStringProperty(country);
    }

    public SimpleStringProperty getLocationProperty() {
        return new SimpleStringProperty(location);
    }
}
