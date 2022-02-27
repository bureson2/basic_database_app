package cz.cvut.fel.pjv.jpa30.FXMLControllers;

import cz.cvut.fel.pjv.jpa30.Tea;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TeaFXMLController implements Initializable {

    @FXML
    private TableColumn<Tea, Number> teaIDCol;
    @FXML
    private TableColumn<Tea, String> nameCol;
    @FXML
    private TableColumn<Tea, String> typeCol;
    @FXML
    private TableColumn<Tea, Number> sellColl;
    @FXML
    private TableColumn<Tea, Number> purchaseCol;
    @FXML
    private TableColumn<Tea, String> CountryColl;
    @FXML
    private TableColumn<Tea, String> LocationCol;
    @FXML
    private TableView<Tea> teaTable;
    @FXML
    private TextField teaIDInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField typeInput;
    @FXML
    private TextField sellingPriceInput;
    @FXML
    private TextField purchasePriceInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField locationInput;
    @FXML
    private Button createButton;
    @FXML
    private TextField teaIDInputToDelete;
    @FXML
    private Button deleteButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private HBox updateMenu;
    @FXML
    private TextField teaIDChange;
    @FXML
    private TextField nameChange;
    @FXML
    private TextField typeChange;
    @FXML
    private TextField sellingPriceChange;
    @FXML
    private TextField purchasePrice;
    @FXML
    private TextField countryChange;
    @FXML
    private TextField locationChange;
    @FXML
    private Button acceptChange;
    @FXML
    private Button backButton;
    
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
        nameCol.setCellValueFactory(clbck -> clbck.getValue().getNameProperty());
        typeCol.setCellValueFactory(clbck -> clbck.getValue().getTypeProperty());
        sellColl.setCellValueFactory(clbck -> clbck.getValue().getSellingPriceProperty());
        purchaseCol.setCellValueFactory(clbck -> clbck.getValue().getPurchasePriceProperty());
        CountryColl.setCellValueFactory(clbck -> clbck.getValue().getCountryProperty());
        LocationCol.setCellValueFactory(clbck -> clbck.getValue().getLocationProperty());   
    }

    @FXML
    private void CreateButtonClick(ActionEvent event) throws IOException {
        Integer teaID = Integer.parseInt(teaIDInput.getText());
        String name = nameInput.getText();
        String type = typeInput.getText();
        Integer sellingPrice = Integer.parseInt(sellingPriceInput.getText());
        Integer purchasePrice = Integer.parseInt(purchasePriceInput.getText());
        String country = countryInput.getText();
        String location = locationInput.getText();
               
        //VALIDACE TODO
        
        et.begin(); 
        em.persist(new Tea(teaID, name, type ,sellingPrice, purchasePrice, country, location));  
        et.commit();
        
        Stage stage = (Stage) createButton.getScene().getWindow();
        Parent parent = FXMLControllers.TeaPage.getPage();
        stage.getScene().setRoot(parent);     
    }

    @FXML
    private void deleteButtonClcik(ActionEvent event) throws IOException {     
        Integer teaID = Integer.parseInt(teaIDInputToDelete.getText());
        et.begin();
        em.remove(em.find(Tea.class, teaID));
        et.commit();
        
        
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        Parent parent = FXMLControllers.TeaPage.getPage();
        stage.getScene().setRoot(parent);       
    }

    @FXML
    private void acceptButtonClick(ActionEvent event) throws IOException {  
       
        Integer changeTeaId = Integer.parseInt(teaIDChange.getText());
        String changeName = nameChange.getText();
        String changeType = typeChange.getText();
        Integer changeSellPrice = Integer.parseInt(sellingPriceChange.getText());
        Integer changePurchPrice = Integer.parseInt(purchasePrice.getText());
        String changeCountry = countryChange.getText();
        String changeLocation = locationChange.getText();
                                 
        et.begin();       
        Tea tea = em.find(Tea.class, changeTeaId);
        tea.name = (changeName);
        tea.type = (changeType);
        tea.sellingPrice = (changeSellPrice);
        tea.purchasePrice = (changePurchPrice);
        tea.country = (changeCountry);
        tea.location = (changeLocation);
        et.commit();      
        
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent parent = FXMLControllers.TeaPage.getPage();
        stage.getScene().setRoot(parent);
    }

    @FXML
    private void UpdateButtonClick(ActionEvent event) {
        List<Tea> teaList = em.createQuery("SELECT tea FROM Tea as tea WHERE tea.teaID = " 
                + Integer.parseInt(teaIDInputToDelete.getText())).getResultList();
        teaIDChange.setText(teaList.get(0).teaID.toString());
        nameChange.setText(teaList.get(0).name);
        typeChange.setText(teaList.get(0).type);
        sellingPriceChange.setText(teaList.get(0).sellingPrice.toString());
        purchasePrice.setText(teaList.get(0).purchasePrice.toString());
        countryChange.setText(teaList.get(0).country);
        locationChange.setText(teaList.get(0).location);
        updateMenu.setOpacity(100); 
    }

    @FXML
    private void backButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Best tea");
        Parent parent = FXMLControllers.MenuPage.getPage();
        stage.getScene().setRoot(parent);
    }
}
