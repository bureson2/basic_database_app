package cz.cvut.fel.pjv.jpa30.FXMLControllers;

import cz.cvut.fel.pjv.jpa30.Orders;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OrdersFXMLController implements Initializable {

    @FXML
    private TableColumn<Orders, Number> orderIDCol;
    @FXML
    private TableColumn<Orders, Number> placeIDCol;
    @FXML
    private TableColumn<Orders, Number> yearCol;
    @FXML
    private TableColumn<Orders, Number> monthCol;
    @FXML
    private TableColumn<Orders, Number> dayCol;
    @FXML
    private TableColumn<Orders, Number> priceCol;
    @FXML
    private TableView<Orders> orderTable;
    
    private  EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("JPAPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction et = em.getTransaction();
    @FXML
    private Button BackButton;
    @FXML
    private TextField deleteInput;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField orderIDInput;
    @FXML
    private TextField placeIDInput;
    @FXML
    private TextField yearInput;
    @FXML
    private TextField monthInput;
    @FXML
    private TextField dayInput;
    @FXML
    private TextField priceInput;
    @FXML
    private Button createButton;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Orders> orderList = em.createQuery("SELECT orders FROM Orders AS orders").getResultList();
        ObservableList<Orders> observableOrderList = FXCollections.observableArrayList();
        for(Orders order : orderList){
            observableOrderList.add(new Orders(order.orderID, order.placeID, 
                        order.year, order.month, 
                        order.day, order.price));                      
        }
        orderTable.setItems(observableOrderList);
        orderTable.setColumnResizePolicy(orderTable.CONSTRAINED_RESIZE_POLICY);    
        
        orderIDCol.setCellValueFactory(clbck -> clbck.getValue().getOrderID());
        placeIDCol.setCellValueFactory(clbck -> clbck.getValue().getPlaceID());
        yearCol.setCellValueFactory(clbck -> clbck.getValue().getYear());
        monthCol.setCellValueFactory(clbck -> clbck.getValue().getMonth());
        dayCol.setCellValueFactory(clbck -> clbck.getValue().getDay());
        priceCol.setCellValueFactory(clbck -> clbck.getValue().getPrice()); 
    }    

    @FXML
    private void backButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setTitle("Best tea");
        Parent parent = FXMLControllers.MenuPage.getPage();
        stage.getScene().setRoot(parent);
    }

    @FXML
    private void deleteButtonClick(ActionEvent event) throws IOException {
      Integer orderID = Integer.parseInt(deleteInput.getText());
        et.begin();
        em.remove(em.find(Orders.class, orderID));
        et.commit();
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        Parent parent = FXMLControllers.OrdersPage.getPage();
        stage.getScene().setRoot(parent);
    }

    @FXML
    private void createButtonClick(ActionEvent event) throws IOException {
        Integer orderID = Integer.parseInt(orderIDInput.getText());
        Integer placeID = Integer.parseInt(placeIDInput.getText());
        Integer year = Integer.parseInt(yearInput.getText());
        Integer month = Integer.parseInt(monthInput.getText());
        Integer day = Integer.parseInt(dayInput.getText());
        Integer price = Integer.parseInt(priceInput.getText());
               
        //VALIDACE TODO
        
        et.begin();
        em.persist(new Orders(orderID, placeID, year ,month, day, price));  
        et.commit();
        
        
        Stage stage = (Stage) createButton.getScene().getWindow();
        Parent parent = FXMLControllers.OrdersPage.getPage();
        stage.getScene().setRoot(parent);    
    }
    
}
