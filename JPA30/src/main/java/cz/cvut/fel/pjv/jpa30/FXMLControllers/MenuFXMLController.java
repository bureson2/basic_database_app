package cz.cvut.fel.pjv.jpa30.FXMLControllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuFXMLController {

    @FXML
    private Button teaButton;
    @FXML
    private Button orderButton;
    @FXML
    private Button allButton;

    @FXML
    private void showTeaClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) teaButton.getScene().getWindow();
        Parent parent = FXMLControllers.TeaPage.getPage();
        stage.setTitle("Tea list");
        stage.getScene().setRoot(parent);
    }

    @FXML
    private void showOrdesClick(ActionEvent event) throws IOException {
        Parent parent = FXMLControllers.OrdersPage.getPage();
        Scene scene = orderButton.getScene();
        Stage stage = (Stage) orderButton.getScene().getWindow();
        stage.setTitle("Orders list");
        scene.setRoot(parent);
    }

    @FXML
    private void showAllClick(ActionEvent event) throws IOException {
        Parent parent = FXMLControllers.AllPage.getPage();
        Scene scene = allButton.getScene();
        Stage stage = (Stage) allButton.getScene().getWindow();
        stage.setTitle("Overall summary");
        scene.setRoot(parent);
    }
    
}
