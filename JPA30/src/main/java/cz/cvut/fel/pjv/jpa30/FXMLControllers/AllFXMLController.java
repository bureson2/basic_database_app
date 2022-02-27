package cz.cvut.fel.pjv.jpa30.FXMLControllers;

import cz.cvut.fel.pjv.jpa30.OrderedTea;
import cz.cvut.fel.pjv.jpa30.Orders;
import cz.cvut.fel.pjv.jpa30.Tea;
import java.beans.Statement;
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

public class AllFXMLController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TableView<Tea> teaTable;
    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TextField teaIDAdd;
    @FXML
    private TextField numberOfTeaAdd;
    @FXML
    private TextField OrderIDAdd;
    @FXML
    private Button addButton;
    @FXML
    private TableColumn<Tea, Number> teaIDCol;
    @FXML
    private TableColumn<Tea, String> TeaNameCol;
    @FXML
    private TableColumn<Orders, Number> orderID;
    @FXML
    private TableColumn<Orders, Number> orderPlaceID;
    @FXML
    private TableView<OrderedTea> teaInOrderTable;
    @FXML
    private TableColumn<OrderedTea, Number> allOrderID;
    @FXML
    private TableColumn<OrderedTea, Number> AllNumber;
    @FXML
    private TableColumn<OrderedTea, Number> AllTeaID;

    private  EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("JPAPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction et = em.getTransaction();    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Tea> teaList = em.createQuery("SELECT tea FROM Tea AS tea").getResultList();
        ObservableList<Tea> observableTeaList = FXCollections.observableArrayList();
        for(Tea tea : teaList){
            observableTeaList.add(
                    new Tea(tea.teaID, tea.name, 
                        tea.type, tea.sellingPrice, 
                        tea.purchasePrice, tea.country, tea.location));           
            
        }
        teaTable.setItems(observableTeaList);
        teaTable.setColumnResizePolicy(teaTable.CONSTRAINED_RESIZE_POLICY);      
        
        teaIDCol.setCellValueFactory(clbck -> clbck.getValue().getTeaIDProperty());
        TeaNameCol.setCellValueFactory(clbck -> clbck.getValue().getNameProperty());
        
        
        List<Orders> orderList = em.createQuery("SELECT orders FROM Orders AS orders").getResultList();
        ObservableList<Orders> observableOrderList = FXCollections.observableArrayList();
        for(Orders order : orderList){
            observableOrderList.add(new Orders(order.orderID, order.placeID, 
                order.year, order.month, order.day, order.price));          
        }
        orderTable.setItems(observableOrderList);
        orderTable.setColumnResizePolicy(teaTable.CONSTRAINED_RESIZE_POLICY); 
        
        orderID.setCellValueFactory(clbck -> clbck.getValue().getOrderID());
        orderPlaceID.setCellValueFactory(clbck -> clbck.getValue().getPlaceID());
        

        List<OrderedTea> orderedTeaList = em.createQuery("SELECT orderedTea FROM OrderedTea AS orderedTea").getResultList();
        ObservableList<OrderedTea> observableOrderedTeaList = FXCollections.observableArrayList();
        for(OrderedTea orderedTea : orderedTeaList) {
            observableOrderedTeaList.add(new OrderedTea(orderedTea.teaID, orderedTea.orderID, orderedTea.numberOfTea));
        }
        teaInOrderTable.setItems(observableOrderedTeaList);
        teaInOrderTable.setColumnResizePolicy(teaTable.CONSTRAINED_RESIZE_POLICY);
        
        allOrderID.setCellValueFactory(clbck -> clbck.getValue().getOrderID());
        AllTeaID.setCellValueFactory(clbck -> clbck.getValue().getTeaID());
        AllNumber.setCellValueFactory(clbck -> clbck.getValue().getNumberOfTea());      
    }    

    @FXML
    private void backButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Best tea");
        Parent parent = FXMLControllers.MenuPage.getPage();
        stage.getScene().setRoot(parent);
    }

    @FXML
    private void addButtonClick(ActionEvent event) throws IOException {
        Integer teaID = Integer.parseInt(teaIDAdd.getText());
        Integer numberOfTea = Integer.parseInt(numberOfTeaAdd.getText());
        Integer orderID = Integer.parseInt(OrderIDAdd.getText());
              
        //VALIDACE TODO
        
        et.begin();
        em.persist(new OrderedTea(teaID, orderID, numberOfTea));  
        et.commit();
        Stage stage = (Stage) addButton.getScene().getWindow();
        Parent parent = FXMLControllers.AllPage.getPage();
        stage.getScene().setRoot(parent); 
    }
    
}
