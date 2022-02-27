package cz.cvut.fel.pjv.jpa30;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class OrderedTea implements Serializable{
    @Id
    @Column(name = "teaID", nullable = false)
    public Integer teaID;
    @Id
    @Column(name = "orderID", nullable = false)
    public Integer orderID;
    @Column(name = "numberOfTea", nullable = false)
    public Integer numberOfTea;

        public OrderedTea() {
        this.teaID = null;
        this.orderID = null;
        this.numberOfTea = null;
    }
    
    public OrderedTea(Integer teaID, Integer orderID, Integer numberOfTea) {
        this.teaID = teaID;
        this.orderID = orderID;
        this.numberOfTea = numberOfTea;
    }

    public SimpleIntegerProperty getNumberOfTea() {
        return new SimpleIntegerProperty(numberOfTea);
    }
    
       public SimpleIntegerProperty getTeaID() {
        return new SimpleIntegerProperty(teaID);
    }

    public SimpleIntegerProperty getOrderID() {
        return new SimpleIntegerProperty(orderID);
    }
    
}

//    @EmbeddedId
//    OrderedTea id;
    
//    @ManyToOne
//    @MapsId("teaID")
//    @JoinColumn(name = "teaID")
//    Tea tea;
//    
//    @ManyToOne
//    @MapsId("OrderID")
//    @JoinColumn(name = "OrderID")
//    Orders orders;
    
//    public Integer numberOfTea;
