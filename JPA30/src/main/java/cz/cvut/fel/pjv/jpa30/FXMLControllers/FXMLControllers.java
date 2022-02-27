package cz.cvut.fel.pjv.jpa30.FXMLControllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public enum FXMLControllers {
    MenuPage ("/fxml/Menu.fxml"),
    OrdersPage("/fxml/Orders.fxml"),
    TeaPage("/fxml/Tea.fxml"),
    AllPage("/fxml/All.fxml");
    private String parentName;
    
    private FXMLControllers(String parentName) {
        this.parentName = parentName;
    }

    public Parent getPage() throws IOException {
        Parent parent = loadParent(parentName);
        return parent;
    }

    private Parent loadParent(String parent) throws IOException {
        return FXMLLoader.load(getClass().getResource(parent));
    }
    
}
