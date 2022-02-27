package cz.cvut.fel.pjv.jpa30;

import java.util.Set;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    public Integer orderID;
    @Column(name = "placeID", nullable = false)
    public Integer placeID;
    @Column(name = "year", nullable = false)
    public Integer year;
    @Column(name = "month", nullable = false)
    public Integer month;
    @Column(name = "day", nullable = false)
    public Integer day;
    @Column(name = "price", nullable = false)
    public Integer price;
    
//    @OneToMany(mappedBy = "teaID")
//    Set<OrderedTea> orderedTea;

    public Orders() {
        this.orderID = null;
        this.placeID = null;
        this.year = null;
        this.month = null;
        this.day = null;
        this.price = null;
    }
    
    public Orders(int orderID, int placeID, int year, int month, int day, int price) {
        this.orderID = orderID;
        this.placeID = placeID;
        this.year = year;
        this.month = month;
        this.day = day;
        this.price = price;
    }

    public SimpleIntegerProperty getOrderID() {
        return new SimpleIntegerProperty(orderID);
    }

    public SimpleIntegerProperty getPlaceID() {
        return new SimpleIntegerProperty(placeID);
    }

    public SimpleIntegerProperty getYear() {
        return new SimpleIntegerProperty(year);
    }

    public SimpleIntegerProperty getMonth() {
        return new SimpleIntegerProperty(month);
    }

    public SimpleIntegerProperty getDay() {
        return new SimpleIntegerProperty(day);
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(price);
    }
}
